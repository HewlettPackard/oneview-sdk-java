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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class TaskServiceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceManager.class);
    private HttpRestClient httpClient = HttpRestClient.getClient();

    private final ResourceAdaptor adaptor;

    public TaskServiceManager() {
        this.adaptor = new ResourceAdaptor();
    }

    public TaskResourceV2 getTaskResource(final RestParams params, final String taskUri) {
        LOGGER.trace("TaskServiceManager : getTaskResource : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Request request = new Request(HttpMethod.GET, taskUri);
        String returnObj = httpClient.sendRequest(params, request);

        if (StringUtils.isEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.TASK_MONITOR, null);
        }

        final TaskResourceV2 taskResourceV2 = adaptor.buildResource(returnObj, TaskResourceV2.class);

        LOGGER.debug("TaskServiceManager : getTaskResource : task state:" + taskResourceV2.getTaskState());
        LOGGER.trace("TaskServiceManager : getTaskResource : End");

        return taskResourceV2;
    }
}
