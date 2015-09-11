/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.InterconnectAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectsCollection;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class InterconnectsClientImpl implements InterconnectsClient {

    private static final Logger logger = LoggerFactory.getLogger(InterconnectsClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private InterconnectAdaptor adaptor;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public Interconnects getInterconnects(final RestParams params, final String resourceId) {
        logger.info("InterconnectsClientImpl : getInterconnects : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("InterconnectsClientImpl : getInterconnects : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final Interconnects interconnectDto = adaptor.buildDto(returnObj);

        logger.debug("InterconnectsClientImpl : getInterconnects : name :" + interconnectDto.getName());
        logger.info("InterconnectsClientImpl : getInterconnects : End");

        return interconnectDto;
    }

    @Override
    public InterconnectsCollection getAllInterconnects(final RestParams params) {
        logger.info("InterconnectsClientImpl : getAllInterconnects : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_URI));

        // call rest client
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("InterconnectsClientImpl : getAllInterconnects : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final InterconnectsCollection interconnectsCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("InterconnectsClientImpl : getAllInterconnects : members count :" + interconnectsCollectionDto.getCount());
        logger.info("InterconnectsClientImpl : getAllInterconnects : End");

        return interconnectsCollectionDto;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Interconnects interconnectsDto = getInterconnectByName(creds, name);

        if (null != interconnectsDto.getUri()) {
            resourceId = urlUtils.getResourceIdFromUri(interconnectsDto.getUri());
        }
        return resourceId;
    }

    @Override
    public Interconnects getInterconnectByName(RestParams params, String interconnectName) {
        logger.info("InterconnectsClientImpl : getInterconnectByName : start");
        final InterconnectsCollection interconnectsCollectionDto = getAllInterconnects(params);

        for (final Interconnects interconnectsDto : new ArrayList<>(interconnectsCollectionDto.getMembers())) {
            if (interconnectsDto.getName().equals(interconnectName)) {
                System.out.println(interconnectsDto.getName());
                logger.info("InterconnectsClientImpl : getInterconnectByName : End");
                return interconnectsDto;
            }
        }
        logger.error("InterconnectsClientImpl : getInterconnectByName : resource not Found for name :" + interconnectName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.INTERCONNECT, null);
    }

}
