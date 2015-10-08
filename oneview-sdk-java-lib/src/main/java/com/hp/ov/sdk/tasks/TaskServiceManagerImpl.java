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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class TaskServiceManagerImpl implements TaskServiceManager {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceManagerImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private TaskAdaptor adaptor;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public TaskResourceV2 getTaskResource(final RestParams params, final String taskUri) {

        logger.info("TaskServiceManagerImpl : getTaskResource : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), taskUri));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("TaskServiceManagerImpl : getTaskResource : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final TaskResourceV2 taskResourceV2 = adaptor.buildDto(returnObj);

        logger.debug("TaskServiceManagerImpl : getTaskResource : taskstate :" + taskResourceV2.getTaskState());
        logger.info("TaskServiceManagerImpl : getTaskResource : End");

        return taskResourceV2;
    }
}
