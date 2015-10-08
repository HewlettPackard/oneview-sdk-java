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

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.ov.sdk.dto.SwitchCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class SwitchClientImpl implements SwitchClient {

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private UrlUtils urlUtils;

    // TODO
    @Override
    public SwitchCollection getAllSwitches(final RestParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
    @Override
    public Switch getSwitchById(final RestParams params, final String resourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
    @Override
    public TaskResourceV2 createSwitch(final RestParams params, final Switch switchDto) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
    @Override
    public TaskResourceV2 refreshSwitch(final RestParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
    @Override
    public TaskResourceV2 updateSwitch(final RestParams params, final String resourceId, final Switch switchDto) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
    @Override
    public TaskResourceV2 deleteSwitch(final RestParams params, final String resourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
    @Override
    public EnvironmentalConfiguration getSwitchEnvironmentConfiguration(final RestParams params, final String resourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Switch switchDto = getSwitchByName(creds, name);

        if (null != switchDto.getUri()) {
            resourceId = urlUtils.getResourceIdFromUri(switchDto.getUri());
        }
        return resourceId;
    }

    @Override
    public Switch getSwitchByName(RestParams params, String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
