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

import com.hp.ov.sdk.dto.InterconnectTypeCollection;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectTypeClient {

    /**
     * The module aids in fetching the InterconnectType details for the
     * specified InterconnectType resourceId
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnectTypes as seen in HP OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectType details.
     */
    public InterconnectType getInterconnectType(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the InterconnectType details for all
     * InterconnectType registered under current HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return interconnectTypesCollectionDto, which is a object containing the
     *         collection of InterconnectType details.
     */
    public InterconnectTypeCollection getAllInterconnectType(final RestParams params);

    /**
     * The module aids in fetching the InterconnectType details for the
     * specified InterconnectType name as specified in HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the InterconnectType name as seen in HP
     *            OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectType details.
     */
    public InterconnectType getInterconnectTypeByName(final RestParams params, final String name);

    /**
     * The module aids in fetching the InterconnectType details for the
     * InterconnectType name as specified in HP OneView.
     *
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the InterconnectType name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the InterconnectType name as
     *         seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);

}
