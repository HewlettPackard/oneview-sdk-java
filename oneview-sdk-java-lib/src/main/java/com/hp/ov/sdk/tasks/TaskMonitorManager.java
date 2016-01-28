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

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskMonitorManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskMonitorManager.class);

    private static final int MILLI_SEC = 1000;

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

    // TODO - for responseCode == 202
    // Implement review comment from Geoff
    // Async APIs should return Task URI in header, eg response = 202,
    // check location header vs returned object. SDK should check 202,
    // get URI from location header then GET task as needed.

    public TaskResourceV2 checkStatus(final RestParams params, final String taskUri, final int timeout) {
        LOGGER.info("TaskMonitorManager : checkStatus : start");
        // validate args
        if (null == taskUri) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.TASK_MONITOR, null);
        }
        // poll for task state
        while (true) {
            final TaskResourceV2 taskResourceV2 = taskServiceManager.getTaskResource(params, taskUri);
            // TODO - extend the condition
            // stopwatch logic
            /**
             * Exit conditions : 1. Task should be completed with 100% 2.
             * timeout
             */

            LOGGER.debug("TaskMonitorManager : checkStatus : percentageComplete: "
                    + taskResourceV2.getComputedPercentComplete());
            LOGGER.debug("TaskMonitorManager : checkStatus : taskState: " + taskResourceV2.getTaskState());
            if (taskResourceV2.getTaskState().equals(ResourceStates.Completed)
                    || taskResourceV2.getPercentComplete() == SdkConstants.PERCENTAGE
                    || taskResourceV2.getTaskState().equals(ResourceStates.Terminated)) {
                return taskResourceV2;
            } else if (!taskResourceV2.getTaskErrors().isEmpty()) { // check for
                                                                    // errors
                throw new SDKTasksException(SDKErrorEnum.tasksError, null, null, null, SdkConstants.TASK_MONITOR, null);
            }

            try {
                Thread.sleep(MILLI_SEC);
            } catch (final InterruptedException e) {
                LOGGER.error("interrupt execption in taskMonitor");
            }
        }
    }

    public TaskResourceV2 getTask(final RestParams params, final String taskUri) {
        // TODO Auto-generated method stub
        return null;
    }

}
