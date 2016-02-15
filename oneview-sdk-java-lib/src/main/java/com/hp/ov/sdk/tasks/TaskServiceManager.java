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

import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskServiceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceManager.class);

    public TaskResourceV2 getTaskResource(final RestParams params, final String taskUri) {

        LOGGER.info("TaskServiceManager : getTaskResource : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), taskUri));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("TaskServiceManager : getTaskResource : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(returnObj);

        LOGGER.debug("TaskServiceManager : getTaskResource : taskstate :" + taskResourceV2.getTaskState());
        LOGGER.info("TaskServiceManager : getTaskResource : End");

        return taskResourceV2;
    }
}
