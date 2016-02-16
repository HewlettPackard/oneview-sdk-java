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

import com.hp.ov.sdk.dto.InterconnectsCollection;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectsClient {
    /**
     * The module aids in fetching the Interconnect details for the specified
     * Interconnect resourceId
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectType details.
     */
    public Interconnects getInterconnects(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Interconnect details for the
     * FirmwareDriver name as specified in HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param interconnectName
     *            The interconnectName is the Interconnect name as seen in HP
     *            OneView.
     * @return InterconnectsDto, which is a object containing the FirmwareDriver
     *         details.
     */
    public Interconnects getInterconnectByName(final RestParams params, final String interconnectName);

    /**
     * The module aids in fetching the Interconnects details for all the
     * Interconnects found under the current HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return InterconnectsCollectionDto, which is a object containing a
     *         collection of Interconnects details.
     */
    public InterconnectsCollection getAllInterconnects(final RestParams params);

    /**
     * The module aids in fetching the Interconnect driver details for the
     * Interconnect driver name as specified in HP OneView.
     *
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Interconnect driver name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the Interconnect driver name
     *         as seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
