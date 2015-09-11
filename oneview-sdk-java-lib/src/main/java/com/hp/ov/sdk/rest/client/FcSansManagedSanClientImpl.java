/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.ManagedSanAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class FcSansManagedSanClientImpl implements FcSansManagedSanClient {

    private static final Logger logger = LoggerFactory.getLogger(FcSansManagedSanClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ManagedSanAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public SanResponse getManagedSan(final RestParams params, final String resourceId) {
        SanResponse sanResponseDto = null;
        logger.info("ManagedSanClientImpl : getManagedSan : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ManagedSanClientImpl : getManagedSan : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // Call adaptor to convert to DTO

        sanResponseDto = adaptor.buildDto(returnObj);

        logger.debug("ManagedSanClientImpl : getManagedSan : name :" + sanResponseDto.getName());
        logger.info("ManagedSanClientImpl : getManagedSan : End");

        return sanResponseDto;
    }

    @Override
    public SanResponseCollection getAllManagedSan(final RestParams params) {
        SanResponseCollection sanResponseCollectionDto = null;
        logger.info("ManagedSanClientImpl : getAllManagedSan : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ManagedSanClientImpl : getAllManagedSan : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // Call adaptor to convert to DTO

        sanResponseCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("ManagedSanClientImpl : getAllManagedSan : count :" + sanResponseCollectionDto.getCount());
        logger.info("ManagedSanClientImpl : getAllManagedSan : End");

        return sanResponseCollectionDto;
    }

    @Override
    public SanResponse getManagedSanByName(final RestParams params, final String name) {
        SanResponse sanResponseDto = null;
        logger.info("ManagedSanClientImpl : getManagedSanByName : Start");
        final String query = urlUtils.createQueryString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ManagedSanClientImpl : getManagedSanByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // Call adaptor to convert to DTO

        final SanResponseCollection sanResponseCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (sanResponseCollectionDto.getCount() != 0) {
            sanResponseDto = sanResponseCollectionDto.getMembers().get(0);
        } else {
            sanResponseDto = null;
        }

        if (sanResponseDto == null) {
            logger.error("ManagedSanClientImpl : getManagedSanByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        logger.info("ManagedSanClientImpl : getManagedSanByName : End");

        return sanResponseDto;
    }

    @Override
    public SanResponse updateManagedSan(final RestParams params, final String resourceId, final SanResponse updateSanResponseDto,
            final boolean aSync, final boolean useJsonRequest) {
        logger.info("ManagedSanClientImpl : updateManagedSan : Start");
        String returnObj = null;

        // validate params
        if (updateSanResponseDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(updateSanResponseDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to sanResponseDto
        final SanResponse sanResponseDto = adaptor.buildDto(returnObj);

        logger.debug("ManagedSanClientImpl : updateManagedSan : returnObj =" + returnObj);
        logger.debug("ManagedSanClientImpl : updateManagedSan : SanResponse =" + sanResponseDto);

        logger.info("ManagedSanClientImpl : updateManagedSan : End");

        return sanResponseDto;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        SanResponse sanResponseDto = getManagedSanByName(creds, name);

        if (null != sanResponseDto.getUri()) {
            resourceId = urlUtils.getResourceIdFromUri(sanResponseDto.getUri());
        }
        return resourceId;
    }

}
