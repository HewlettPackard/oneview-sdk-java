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
package com.hp.ov.sdk.rest.client.networking;

import java.util.List;

import com.google.common.reflect.Parameter;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectType;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectTypeName;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.RequestInterceptor;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;

@Api(InterconnectTypeClient.INTERCONNECT_TYPE_URI)
public interface InterconnectTypeClient extends SearchableResource<InterconnectType> {

    String INTERCONNECT_TYPE_URI = "/rest/interconnect-types";

    /**
     * Retrieves a paginated collection ({@link ResourceCollection}) containing the details for the
     * available resources found under the current HPE OneView that match the <code>name</code> provided.
     *
     * <p>The filter applied in this case is as follows:
     * <pre>
     *     filter="name matches '<i>name</i>'"
     * </pre>
     *
     * @param name interconnect type name that should be used to filter the resources found in HPE OneView.
     *
     * @return {@link ResourceCollection} paginated collection containing the details for the
     * available resources that match the filter.
     */
    @Endpoint(requestInterceptor = GetByNameRequestInterceptor.class)
    ResourceCollection<InterconnectType> getByName(InterconnectTypeName name);

    class GetByNameRequestInterceptor implements RequestInterceptor {
        @Override
        public Request intercept(Request request, List<Parameter> params, Object[] args) {
            InterconnectTypeName name = (InterconnectTypeName) args[0];

            request.addQuery(UrlParameter.getFilterByNameParameter(name.getValue()));

            return request;
        }
    }
}
