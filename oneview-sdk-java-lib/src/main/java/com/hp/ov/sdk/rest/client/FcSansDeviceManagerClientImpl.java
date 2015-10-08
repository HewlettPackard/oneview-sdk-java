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
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.DeviceManagerAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.DeviceManagerResponseCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class FcSansDeviceManagerClientImpl implements FcSansDeviceManagerClient {

    private static final Logger logger = LoggerFactory.getLogger(FcSansDeviceManagerClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private DeviceManagerAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public DeviceManagerResponse createDeviceManager(final RestParams params, final String providerUrl,
            final DeviceManagerResponse addDeviceManagerResponseDto, final boolean aSync, final boolean useJsonRequest) {
        logger.info("DeviceManagerClientImpl : createDeviceManager : Start");
        String returnObj = null;

        // validate params
        if (addDeviceManagerResponseDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.DEVICE_MANAGER, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), providerUrl));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(addDeviceManagerResponseDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to deviceManagerResponseDto
        DeviceManagerResponse deviceManagerResponseDto = adaptor.buildDto(returnObj);

        logger.debug("DeviceManagerClientImpl : createDeviceManager : returnObj =" + returnObj);
        logger.debug("DeviceManagerClientImpl : createDeviceManager : DeviceManagerResponse =" + deviceManagerResponseDto);

        logger.info("DeviceManagerClientImpl : createDeviceManager : End");

        return deviceManagerResponseDto;
    }

    @Override
    public DeviceManagerResponseCollection getAllDeviceManager(final RestParams params) {
        DeviceManagerResponseCollection deviceManagerResponseCollectionDto = null;
        logger.info("DeviceManagerClientImpl : getAllDeviceManager : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("DeviceManagerClientImpl : getAllDeviceManager : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }
        // Call adaptor to convert to DTO

        deviceManagerResponseCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("DeviceManagerClientImpl : getAllDeviceManager : count :" + deviceManagerResponseCollectionDto.getCount());
        logger.info("DeviceManagerClientImpl : getAllDeviceManager : End");

        return deviceManagerResponseCollectionDto;
    }

    @Override
    public DeviceManagerResponse getDeviceManager(final RestParams params, final String resourceId) {
        DeviceManagerResponse deviceManagerResponseDto = null;
        logger.info("DeviceManagerClientImpl : getDeviceManager : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("DeviceManagerClientImpl : getDeviceManager : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }
        // Call adaptor to convert to DTO

        deviceManagerResponseDto = adaptor.buildDto(returnObj);

        logger.debug("DeviceManagerClientImpl : getDeviceManager : name :" + deviceManagerResponseDto.getName());
        logger.info("DeviceManagerClientImpl : getDeviceManager : End");

        return deviceManagerResponseDto;
    }

    @Override
    public String deleteDeviceManager(final RestParams params, final String resourceId) {
        logger.info("DeviceManagerClientImpl : deleteDeviceManager : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("DeviceManagerClientImpl : deleteDeviceManager : response from OV :" + returnObj);
        /************ Returns Response code 204 *********************************/

        logger.debug("DeviceManagerClientImpl : deleteDeviceManager : returnObj =" + returnObj);

        logger.info("DeviceManagerClientImpl : deleteDeviceManager : End");

        return "Deleted";
    }

    @Override
    public DeviceManagerResponse updateDeviceManager(final RestParams params, final String resourceId,
            final DeviceManagerResponse updateDeviceManagerResponseDto, final boolean useJsonRequest) {
        logger.info("DeviceManagerClientImpl : updateDeviceManager : Start");
        String returnObj = null;

        // validate params
        if (updateDeviceManagerResponseDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.DEVICE_MANAGER, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(updateDeviceManagerResponseDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to deviceManagerResponseDto
        final DeviceManagerResponse deviceManagerResponseDto = adaptor.buildDto(returnObj);

        logger.debug("DeviceManagerClientImpl : updateDeviceManager : returnObj =" + returnObj);
        logger.debug("DeviceManagerClientImpl : updateDeviceManager : DeviceManagerResponse =" + deviceManagerResponseDto);

        logger.info("DeviceManagerClientImpl : updateDeviceManager : End");

        return deviceManagerResponseDto;
    }

    @Override
    public DeviceManagerResponse getDeviceManagerByName(final RestParams params, final String name) {
        DeviceManagerResponse deviceManagerResponseDto = null;
        logger.info("DeviceManagerClientImpl : getDeviceManagerByName : Start");
        final String query = urlUtils.createQueryString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("DeviceManagerClientImpl : getDeviceManagerByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }
        // Call adaptor to convert to DTO

        final DeviceManagerResponseCollection deviceManagerResponseCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (deviceManagerResponseCollectionDto.getCount() != 0) {
            deviceManagerResponseDto = deviceManagerResponseCollectionDto.getMembers().get(0);
        } else {
            deviceManagerResponseDto = null;
        }

        if (deviceManagerResponseDto == null) {
            logger.error("DeviceManagerClientImpl : getDeviceManagerByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }
        logger.info("DeviceManagerClientImpl : getDeviceManagerByName : End");

        return deviceManagerResponseDto;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        DeviceManagerResponse deviceManagerResponseDto = getDeviceManagerByName(creds, name);

        if (null != deviceManagerResponseDto.getUri()) {
            resourceId = urlUtils.getResourceIdFromUri(deviceManagerResponseDto.getUri());
        }
        return resourceId;
    }

}
