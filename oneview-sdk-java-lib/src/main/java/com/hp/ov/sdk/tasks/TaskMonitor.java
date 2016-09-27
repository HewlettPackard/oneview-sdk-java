/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hp.ov.sdk.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ErrorMessage;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.BaseClient;

public class TaskMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskMonitor.class);

    //TODO externalize this value to make it configurable
    private static final int TASK_MONITORING_INTERVAL = 5000; //milliseconds

    public TaskResource execute(BaseClient client, TaskResource task, int timeoutInMilliseconds) {
        Calendar dateToLive = Calendar.getInstance();

        dateToLive.add(Calendar.MILLISECOND, timeoutInMilliseconds);

        while (dateToLive.after(Calendar.getInstance())) {
            task = client.getResource(task.getUri(), TaskResource.class);

            LOGGER.info("Task completed percentage {} and status {}", task.getPercentComplete(), task.getTaskState());

            if (task.getPercentComplete() == SdkConstants.PERCENTAGE_100) {
                if (task.getTaskState() == (TaskState.Error)) {
                    List<String> errorMessages = new ArrayList<>();
                    List<String> errorRecommendations = new ArrayList<>();

                    for (ErrorMessage errorMessage : task.getTaskErrors()) {
                        errorMessages.add(errorMessage.getMessage());
                        errorRecommendations.addAll(errorMessage.getRecommendedActions());
                    }
                    throw new SDKTasksException(SDKErrorEnum.tasksError, errorMessages.toArray(),
                            null, errorRecommendations.toArray(), SdkConstants.TASK_MONITOR, null);
                }
                return task;
            }

            try {
                Thread.sleep(TASK_MONITORING_INTERVAL);
            } catch (final InterruptedException e) {
                LOGGER.warn("An interruption occurred while monitoring the task {}", task.getResourceId(), e);
            }
        }

        LOGGER.warn("Task timeout exceeded: " + dateToLive.getTime() + " < " + Calendar.getInstance().getTime());

        throw new SDKTasksException(SDKErrorEnum.tasksError,
                new String[] {"Task monitoring exceeded the timeout limit of " + timeoutInMilliseconds + " milliseconds."},
                null, new String[] {"Increase the timeout."}, SdkConstants.TASK_MONITOR, null);
    }

}
