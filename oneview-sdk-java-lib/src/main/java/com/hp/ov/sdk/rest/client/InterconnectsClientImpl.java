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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class InterconnectsClientImpl implements InterconnectsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectsClientImpl.class);

    private final InterconnectAdaptor adaptor;

    protected InterconnectsClientImpl(InterconnectAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public static InterconnectsClient getClient() {
        return new InterconnectsClientImpl(new InterconnectAdaptor());
    }

    @Override
    public Interconnects getInterconnects(final RestParams params, final String resourceId) {
        LOGGER.info("InterconnectsClientImpl : getInterconnects : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnects : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final Interconnects interconnectDto = adaptor.buildDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : getInterconnects : name :" + interconnectDto.getName());
        LOGGER.info("InterconnectsClientImpl : getInterconnects : End");

        return interconnectDto;
    }

    @Override
    public InterconnectsCollection getAllInterconnects(final RestParams params) {
        LOGGER.info("InterconnectsClientImpl : getAllInterconnects : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_URI));

        // call rest client
        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("InterconnectsClientImpl : getAllInterconnects : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final InterconnectsCollection interconnectsCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : getAllInterconnects : members count :" + interconnectsCollectionDto.getCount());
        LOGGER.info("InterconnectsClientImpl : getAllInterconnects : End");

        return interconnectsCollectionDto;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Interconnects interconnectsDto = getInterconnectByName(creds, name);

        if (null != interconnectsDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(interconnectsDto.getUri());
        }
        return resourceId;
    }

    @Override
    public Interconnects getInterconnectByName(RestParams params, String interconnectName) {
        LOGGER.info("InterconnectsClientImpl : getInterconnectByName : start");
        final InterconnectsCollection interconnectsCollectionDto = getAllInterconnects(params);

        for (final Interconnects interconnectsDto : new ArrayList<>(interconnectsCollectionDto.getMembers())) {
            if (interconnectsDto.getName().equals(interconnectName)) {
                System.out.println(interconnectsDto.getName());
                LOGGER.info("InterconnectsClientImpl : getInterconnectByName : End");
                return interconnectsDto;
            }
        }
        LOGGER.error("InterconnectsClientImpl : getInterconnectByName : resource not Found for name :" + interconnectName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.INTERCONNECT, null);
    }

}
