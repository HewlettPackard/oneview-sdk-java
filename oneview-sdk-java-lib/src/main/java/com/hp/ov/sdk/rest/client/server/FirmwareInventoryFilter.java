/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.rest.client.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.UrlQuery;

public class FirmwareInventoryFilter implements UrlQuery {

    private static final String COMPONENT_NAME = "components.componentName";
    private static final String COMPONENT_LOCATION = "components.componentLocation";
    private static final String COMPONENT_VERSION = "components.componentVersion";
    private static final String SERVER_NAME = "serverName";
    private static final String SERVER_MODEL = "serverModel";

    private Map<String, UrlParameter> filters = new HashMap<>();

    public FirmwareInventoryFilter setComponentName(String componentName) {
        this.filters.put(COMPONENT_NAME,
                new UrlParameter("filter", "components.componentName='" + componentName + "'"));
        return this;
    }

    public FirmwareInventoryFilter setComponentLocation(String componentLocation) {
        this.filters.put(COMPONENT_LOCATION,
                new UrlParameter("filter", "components.componentLocation='" + componentLocation + "'"));
        return this;
    }

    public FirmwareInventoryFilter setComponentVersion(String componentVersion) {
        this.filters.put(COMPONENT_VERSION,
                new UrlParameter("filter", "components.componentVersion='" + componentVersion + "'"));
        return this;
    }

    public FirmwareInventoryFilter setServerName(String serverName) {
        this.filters.put(SERVER_NAME, new UrlParameter("filter", "serverName='" + serverName + "'"));
        return this;
    }

    public FirmwareInventoryFilter setServerModel(String serverModel) {
        this.filters.put(SERVER_MODEL, new UrlParameter("filter", "serverModel='" + serverModel + "'"));
        return this;
    }

    @Override
    public List<UrlParameter> parameters() {
        return ImmutableList.copyOf(this.filters.values());
    }
}
