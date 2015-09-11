/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.ProviderAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.SanProviderResponseCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class FcSansProviderClientImpl implements FcSansProviderClient {
    private static final Logger logger = LoggerFactory.getLogger(FcSansProviderClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ProviderAdaptor adaptor;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public SanProviderResponseCollection getAllProviders(final RestParams params) {
        SanProviderResponseCollection sanProviderResponseCollectionDto = null;
        logger.info("ProviderClientImpl : getAllProviders : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_PROVIDER_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ProviderClientImpl : getAllProviders : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.PROVIDERS, null);
        }
        // Call adaptor to convert to DTO

        sanProviderResponseCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("ProviderClientImpl : getAllProviders : count :" + sanProviderResponseCollectionDto.getCount());
        logger.info("ProviderClientImpl : getAllProviders : End");

        return sanProviderResponseCollectionDto;
    }

    @Override
    public SanProviderResponse getProviderByName(final RestParams params, final String displayName) {
        logger.info("ProviderClientImpl : getProviderByName : start");
        final SanProviderResponseCollection sanProviderResponseCollectionDto = getAllProviders(params);

        for (final SanProviderResponse sanProviderResponseDto : new ArrayList<>(sanProviderResponseCollectionDto.getMembers())) {
            if (sanProviderResponseDto.getDisplayName().equals(displayName)) {
                System.out.println(sanProviderResponseDto.getName());
                logger.info("ProviderClientImpl : getProviderByName : End");
                return sanProviderResponseDto;
            }
        }
        logger.error("ProviderClientImpl : getProviderByName : resource not Found for name :" + displayName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.PROVIDERS, null);
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        SanProviderResponse sanProviderResponseDto = getProviderByName(creds, name);

        if (null != sanProviderResponseDto.getUri()) {
            resourceId = urlUtils.getResourceIdFromUri(sanProviderResponseDto.getUri());
        }
        return resourceId;
    }
}
