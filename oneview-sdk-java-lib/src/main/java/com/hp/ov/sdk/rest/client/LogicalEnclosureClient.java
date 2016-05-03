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

import com.hp.ov.sdk.dto.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalEnclosure;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalEnclosureClient {

    /**
     * The module aids in fetching the logical enclosure details for the specified
     * logical enclosure resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @return {@link LogicalEnclosure} containing the logical enclosure details.
     */
    LogicalEnclosure getLogicalEnclosure(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the logical enclosure details for all logical enclosures
     * registered under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link LogicalEnclosure}&gt; containing
     * the details for all found logical enclosures.
     */
    ResourceCollection<LogicalEnclosure> getAllLogicalEnclosures(final RestParams params);

    /**
     * The module aids in fetching the logical enclosure details for the specified
     * logical enclosure name.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the logical enclosure name as seen in HPE OneView.
     * @return {@link LogicalEnclosure} containing the logical enclosure details.
     */
    LogicalEnclosure getLogicalEnclosureByName(final RestParams params, final String name);

    /**
     * The module aids in creation of logical enclosure when provided with the logical enclosure
     * details as AddEnclosure object or JsonRequest. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param addLogicalEnclosureDto
     *            The connection details to fetch logical enclosure.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddLogicalEnclosure object which
     *            takes in a String containing the new logical enclosure details, which
     *            is converted to AddLogicalEnclosure object using adaptor and
     *            processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createLogicalEnclosure(final RestParams params, final AddLogicalEnclosure addLogicalEnclosureDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an LogicalEnclosure object or JsonRequest and updates the
     * existing logical enclosure based on resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param logicalEnclosureDto
     *            This is a object containing the update to be made to existing
     *            logical enclosure pointed to by the above mentioned resource identifier
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalEnclosure object which takes
     *            in a String containing the update to be made, which is
     *            converted to LogicalEnclosure object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalEnclosure(final RestParams params, final String resourceId, final LogicalEnclosure logicalEnclosureDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in a Patch object or JsonRequest and updates the
     * existing logical enclosure based on the resource identifier and the content of the Patch object.
     * It can process the request asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param patchDto
     *            This is a object containing the update to be made to existing
     *            logical enclosure pointed to by the above mentioned resource identifier
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Patch object which takes
     *            in a String containing the update to be made, which is
     *            converted to Patch object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 patchLogicalEnclosure(final RestParams params, final String resourceId, final Patch patchDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a logical enclosure for the specified logical enclosure
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteLogicalEnclosure(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in updating the logical enclosure to be consistent with the
     * enclosure group when the logical enclosure is in an inconsistent state.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateFromGroup(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in reapplying the logical enclosure configuration.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateConfiguration(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the configuration script for the specified
     * logical enclosure resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @return the configuration script for the specified enclosure.
     */
    String getScript(final RestParams params, final String resourceId);

    /**
     * The module aids in updating the configuration script for the specified
     * logical enclosure resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param scriptData
     *            The script data to be updated for logical enclosure.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateScript(final RestParams params, final String resourceId, final String scriptData,
            final boolean aSync);

    /**
     * The module aids in creation of a support dump for the logical enclosure with the
     * specified resource identifier. A logical enclosure support dump includes content for
     * logical interconnects associated with that logical enclosure. By default, it also
     * contains appliance support dump content. It can process the request asynchronously
     * or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param supportDumpDto
     *            The details to create the support dump.
     * @param resourceId
     *            The resource identifier for logical enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of SupportDump object which
     *            takes in a String containing the support dump details, which
     *            is converted to SupportDump object using adaptor and
     *            processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createSupportDump(final RestParams params, final SupportDump supportDumpDto, String resourceId, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module aids in fetching the logical enclosure resource identifier for
     * the logical enclosure name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the logical enclosure name as seen in HPE OneView.
     * @return String which is the resource identifier for the logical enclosure name as seen in
     *         HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
