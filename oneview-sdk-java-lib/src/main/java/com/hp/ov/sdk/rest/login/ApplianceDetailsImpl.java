/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.ApplianceDetailsAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class ApplianceDetailsImpl implements ApplianceDetails {

    private static final Logger logger = LoggerFactory.getLogger(ApplianceDetailsImpl.class);

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ApplianceDetailsAdaptor adaptor;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public ApplianceDetailsDto getVersion(final RestParams params) {
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.APPLIANCE_VERSION));
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        final ApplianceDetailsDto applianceDetailsDto = adaptor.buildDto(returnObj);
        logger.debug("ApplianceDetailsImpl : getVersion =" + applianceDetailsDto.getCurrentVersion());
        return applianceDetailsDto;
    }

}
