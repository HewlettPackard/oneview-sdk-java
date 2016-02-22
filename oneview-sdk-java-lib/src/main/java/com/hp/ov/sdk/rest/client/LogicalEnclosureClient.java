/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.LogicalEnclosureList;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalEnclosure;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalEnclosureClient {

    /**
     * The module aids in fetching the logical enclosure details for the specified
     * logical enclosure resourceId
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @return logicalEnclosureDto, which is a object containing the logical enclosure
     *         details.
     */
    public LogicalEnclosure getLogicalEnclosure(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Logical Enclosure details for all Logical Enclosures
     * registered under current HP OneView
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return List<LogicalEnclosure> which is a object containing the
     *         collection of logical enclosures.
     */
    public LogicalEnclosureList getAllLogicalEnclosures(final RestParams params);

    /**
     * The module aids in fetching the logical enclosure details for the specified
     * logical enclosure name
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the logical enclosure name as seen in HP OneView
     * @return LogicalEnclosure, which is a object containing the logical enclosure
     *         details.
     */
    public LogicalEnclosure getLogicalEnclosureByName(final RestParams params, final String name);

    /**
     * The module aids in creation of logical enclosure when provided with the logical enclosure
     * details as AddEnclosure object or JsonRequest. It can process the request
     * asynchronously or synchronously based on flag input.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param addLogicalEnclosureDto
     *            The connection details to fetch logical enclosure.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddEnclosure Object which
     *            takes in a String containing the new Logical Enclosure details, which
     *            is converted to AddEnclosure Object using adaptor and
     *            processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createLogicalEnclosure(final RestParams params, final AddLogicalEnclosure addLogicalEnclosureDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an Logical Enclosure object or JsonRequest and updates the
     * existing logical enclosure based on resource Id. It can process the request
     * asynchronously or synchronously based on flag input.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param logicalEnclosureDto
     *            This is a object containing the update to be made to existing
     *            Logical Enclosure pointed to by the above mentioned resourceId
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Enclosure Object which takes
     *            in a String containing the update to be made, which is
     *            converted to Enclosure Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalEnclosure(final RestParams params, final String resourceId, final LogicalEnclosure logicalEnclosureDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in an Patch object or JsonRequest and updates the
     * existing logical enclosure based on resource Id and the content of the Patch object.
     * It can process the request asynchronously or synchronously based on flag
     * input.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param patchDto
     *            This is a object containing the update to be made to existing
     *            Logical Enclosure pointed to by the above mentioned resourceId
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Logical Enclosure Object which takes
     *            in a String containing the update to be made, which is
     *            converted to Patch Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 patchLogicalEnclosure(final RestParams params, final String resourceId, final Patch patchDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a logical enclosure for the specified logical enclosure
     * resourceId. It can process the request asynchronously or synchronously
     * based on flag input.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteLogicalEnclosure(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in updating the logical enclosure to be consistent with the
     * enclosure group when the logical enclosure is in the Inconsistent state.
     *
     * @param param
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateFromGroup(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in reapplying the logical enclosure configuration.
     *
     * @param param
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateConfiguration(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the configuration script for the specified
     * logical enclosure resourceId.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @return String, the configuration script for the specified enclosure
     */
    public String getScript(final RestParams params, final String resourceId);

    /**
     * The module aids in updating the configuration script for the specified
     * logical enclosure resourceId.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param scriptData
     *            The script data to be updated for logical enclosure.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateScript(final RestParams params, final String resourceId, final String scriptData,
            final boolean aSync);

    /**
     * The module aids in creation of a support dump for the logical enclosure with the specified ID.
     * A logical enclosure support dump includes content for logical interconnects associated with that
     * logical enclosure. By default, it also contains appliance support dump content.. It can process
     * the request asynchronously or synchronously based on flag input.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param logicalEnclosureId
     * @param SupportDumpDto
     *            The details to create the support dump.
     * @param resourceId
     *            The resourceId for logical enclosure as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of SupportDumpDto Object which
     *            takes in a String containing the support dump details, which
     *            is converted to SupportDumpDto Object using adaptor and
     *            processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createSupportDump(final RestParams params, final SupportDump supportDumpDto, String resourceId, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module aids in fetching the Logical Enclosure details for the Logical Enclosure name
     * as specified in HP OneView.
     *
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Logical Enclosure name as seen in HP OneView.
     * @return String, which is a resource Id for the Logical Enclosure name as seen in
     *         HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
