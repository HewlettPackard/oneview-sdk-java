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
package com.hp.ov.sdk.rest.login;

import com.hp.ov.sdk.adaptors.ApplianceDetailsAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplianceDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplianceDetails.class);

    private ApplianceDetailsAdaptor adaptor;

    public ApplianceDetails(ApplianceDetailsAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public ApplianceDetailsDto getVersion(final RestParams params) {
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.APPLIANCE_VERSION));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        final ApplianceDetailsDto applianceDetailsDto = adaptor.buildDto(returnObj);

        LOGGER.debug("ApplianceDetails : getVersion =" + applianceDetailsDto.getCurrentVersion());

        return applianceDetailsDto;
    }

}
