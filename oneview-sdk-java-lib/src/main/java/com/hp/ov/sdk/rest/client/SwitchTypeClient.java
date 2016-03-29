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

package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * The switch types resource provides methods for managing top-of-rack switch types.
 */
public interface SwitchTypeClient {

    /**
     * This method aids in fetching the switch type for the given resourceId.
     *
     * @param params structure containing the connection details.
     * @param resourceId resourceId as seen in OneView.
     *
     * @return InterconnectType, which is a object containing the switch type details.
     */
    InterconnectType getSwitchType(RestParams params, String resourceId);

    /**
     * This method aids in fetching the switch type for the given name.
     *
     * @param params structure containing the connection details.
     * @param name name of the switch type.
     *
     * @return InterconnectType, which is a object containing the switch type details.
     */
    InterconnectType getSwitchTypeByName(RestParams params, String name);

    /**
     * Gets a collection of all the switch types available in OneView.
     * Filters can be used in the URL to control the number of switch types that are returned.
     * With no filters specified, the API returns all supported switch types.
     *
     * @param params structure containing the connection details.
     *
     * @return InterconnectTypeCollection, which is a object containing the collection
     *         of switch types.
     */
    InterconnectTypeCollection getAllSwitchTypes(RestParams params);

}
