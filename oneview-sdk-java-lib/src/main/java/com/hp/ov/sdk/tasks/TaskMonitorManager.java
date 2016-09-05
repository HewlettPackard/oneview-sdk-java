/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ErrorMessage;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class TaskMonitorManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskMonitorManager.class);

    private static final int MILLI_SEC = 5000;

    private final TaskServiceManager taskServiceManager;

    private static final class TaskMonitorManagerHolder {
        private static final TaskMonitorManager INSTANCE = new TaskMonitorManager(new TaskServiceManager());
    }

    private TaskMonitorManager(TaskServiceManager taskServiceManager) {
        this.taskServiceManager = taskServiceManager;
    }

    public static TaskMonitorManager getInstance() {
        return TaskMonitorManagerHolder.INSTANCE;
    }

    public TaskResourceV2 checkStatus(final RestParams params, final String taskUri, final int timeoutInMilliseconds) throws SDKTasksException {
        LOGGER.info("TaskMonitorManager : checkStatus : start");

        Calendar dateToLive = Calendar.getInstance();
        dateToLive.add(Calendar.MILLISECOND, timeoutInMilliseconds);

        // validate args
        if (null == taskUri) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.TASK_MONITOR, null);
        }

        // poll for task state
        while (true) {
            final TaskResourceV2 taskResourceV2 = this.getTask(params, taskUri);

            LOGGER.debug("TaskMonitorManager : checkStatus : percentageComplete: " + taskResourceV2.getComputedPercentComplete());
            LOGGER.debug("TaskMonitorManager : checkStatus : taskState: " + taskResourceV2.getTaskState());
            if (dateToLive.before(Calendar.getInstance())) {
                LOGGER.error("Task time exceeded: " + dateToLive.getTime() + " < " + Calendar.getInstance().getTime());
                throw new SDKTasksException(SDKErrorEnum.tasksError,
                        new String[] {"Task check exceeded the timeout limit of " + timeoutInMilliseconds + " milliseconds."},
                        null,
                        new String[] {"Increase the timeout limit."},
                        SdkConstants.TASK_MONITOR,
                        null);
            }

            if (taskResourceV2.getPercentComplete() == SdkConstants.PERCENTAGE_100) {
                if (taskResourceV2.getTaskState().equals(TaskState.Error)) { // check for errors
                    List<String> errorMessages = new ArrayList<String>();
                    List<String> errorRecomendations = new ArrayList<String>();

                    for (ErrorMessage errorMessage : taskResourceV2.getTaskErrors()) {
                        errorMessages.add(errorMessage.getMessage());
                        errorRecomendations.addAll(errorMessage.getRecommendedActions());
                    }

                    throw new SDKTasksException(SDKErrorEnum.tasksError,
                            errorMessages.toArray(),
                            null,
                            errorRecomendations.toArray(),
                            SdkConstants.TASK_MONITOR,
                            null);
                }

                return taskResourceV2;
            }

            try {
                Thread.sleep(MILLI_SEC);
            } catch (final InterruptedException e) {
                LOGGER.error("interrupt exception in taskMonitor");
            }
        }
    }

    private TaskResourceV2 getTask(final RestParams params, final String taskUri) {
        return taskServiceManager.getTaskResource(params, taskUri);
    }

}
