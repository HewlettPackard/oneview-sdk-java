/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectTypeClient {

    /**
     * The module aids in fetching the interconnect type details for the
     * specified interconnect type resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect type as seen in HPE OneView.
     * @return {@link InterconnectType} containing the interconnect type details.
     */
    InterconnectType getInterconnectType(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the interconnect type details for all
     * interconnect type registered under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing
     * the details for all found interconnect types.
     */
    ResourceCollection<InterconnectType> getAllInterconnectType(final RestParams params);

    /**
     * The module aids in fetching the interconnect type details for the
     * specified interconnect type name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the interconnect type name as seen in HPE OneView.
     * @return {@link InterconnectType} containing the interconnect type details.
     */
    InterconnectType getInterconnectTypeByName(final RestParams params, final String name);

    /**
     * The module aids in fetching the interconnect type resource identifier for the
     * interconnect type name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the interconnect type name as seen in HPE OneView.
     * @return String which is the resource identifier for the interconnect type name as
     *         seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);

}
