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

import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface EnclosureClient {

    /**
     * The module aids in fetching the enclosure details for the specified
     * enclosure resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @return {@link Enclosure} containing the enclosure details.
     */
    Enclosure getEnclosure(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the enclosure details for all enclosures
     * registered under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link Enclosure}&gt; containing
     * the details for all found enclosures.
     */
    ResourceCollection<Enclosure> getAllEnclosures(final RestParams params);

    /**
     * The module aids in fetching the enclosure details for the specified
     * enclosure name.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the enclosure name as seen in HPE.
     * @return {@link Enclosure} containing the enclosure details.
     */
    Enclosure getEnclosureByName(final RestParams params, final String name);

    /**
     * The module aids in the creation of an enclosure when provided with the enclosure
     * details as an AddEnclosure object or JsonRequest. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param addEnclosureDto
     *            The enclosure information that should be created.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddEnclosure object which
     *            takes in a String containing the new enclosure details, which
     *            is converted to AddEnclosure object using adaptor and
     *            processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createEnclosure(final RestParams params, final AddEnclosure addEnclosureDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an enclosure object or JsonRequest and updates the
     * existing enclosure based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param enclosureDto
     *            This is a object containing the update to be made to existing
     *            enclosure pointed to by the above mentioned resource identifier.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Enclosures object which takes
     *            in a String containing the update to be made, which is
     *            converted to Enclosure object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateEnclosure(final RestParams params, final String resourceId, final Enclosure enclosureDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in a Patch object or JsonRequest and updates the
     * existing enclosure based on the resource identifier and the content of the Patch object.
     * It can process the request asynchronously or synchronously based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param patchDto
     *            This is a object containing the update to be made to existing
     *            enclosure pointed to by the above mentioned resource identifier.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 patchEnclosure(final RestParams params, final String resourceId, final Patch patchDto,
            final boolean aSync);

    /**
     * The module aids in deleting an enclosure for the specified enclosure
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteEnclosure(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in fetching data that can be used to construct a single
     * sign-on URL for an Onboard Administrator.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @return SsoUrlData, gets the data used for single sign URL for onboard administrator.
     */
    SsoUrlData getActiveOaSsoUrl(final RestParams params, final String resourceId);

    /**
     * This method aids in updating the enclosure configuration with that of
     * the enclosure group script. This is not applicable if the enclosure is monitored.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateCompliance(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in reapplying the enclosure configuration.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateConfiguration(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in applying the firmware baseline to the enclosure. This
     * method can be used to update the OA firmware, or the OA, logical interconnect,
     * and server profiles in the enclosure. This is not applicable if enclosure is
     * monitored.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param fwBaselineConfigDto
     *            fwBaselineConfig is the firmware baseline to be applied to the
     *            enclosure
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateEnclosureFwBaseline(final RestParams params, final String resourceId,
            final FwBaselineConfig fwBaselineConfigDto, final boolean aSync);

    /**
     * This method aids in fetching the environmental configuration of the
     * enclosure identified by the given enclosure identifier.
     *
     * @param param
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @return {@link EnvironmentalConfiguration} of the enclosure resource.
     */
    EnvironmentalConfiguration getEnvironmentalConfiguration(final RestParams param, final String resourceId);

    /**
     * This method aids in updating the environmental configuration of the
     * enclosure resource identifier.
     *
     * @param param
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param environmentalConfigurationUpdateDto
     *            environmentalConfiguration, environmental configuration of
     *            enclosure resource specifying the cpu, power value.
     * @return {@link EnvironmentalConfiguration} of the enclosure resource.
     */
    EnvironmentalConfiguration updateEnvironmentalConfiguration(final RestParams param, final String resourceId,
            final EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto);

    /**
     * This method aids in refreshing the enclosure to fix configuration issues.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param refreshStateConfigDto
     *            refreshStateConfigDto, RefreshStateConfig containing the
     *            refresh state details to fix configuration issues.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateRefreshState(final RestParams params, final String resourceId,
            final RefreshStateConfig refreshStateConfigDto, final boolean aSync);

    /**
     * The module aids in fetching the configuration script for the specified
     * enclosure resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @return String, the configuration script for the specified enclosure.
     */
    String getScript(final RestParams params, final String resourceId);

    /**
     * The module aids in updating the configuration script for the specified
     * enclosure resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @param scriptData
     *            The script data to be updated for enclosure.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Enclosure object which takes
     *            in a String containing the update to be made, which is
     *            converted to FwBaselineConfig object using adaptor and
     *            processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateScript(final RestParams params, final String resourceId, final String scriptData,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * This method aids in fetching data that can be used to construct a single
     * sign-on URL for an onboard administrator.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @return {@link SsoUrlData} containing the data used for single sign URL for onboard
     *         administrator.
     */
    SsoUrlData getStandbyOaSsoUrl(final RestParams params, final String resourceId);

    /**
     * This method aids in retrieving historical utilization data for the
     * specified enclosure.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure as seen in HPE OneView.
     * @return {@link UtilizationData} containing resource data utilization such as power, cpu.
     */
    UtilizationData getUtilization(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the enclosure resource identifier for the enclosure name
     * as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the enclosure name as seen in HPE OneView.
     * @return String which is the resource identifier for the enclosure name
     *         as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
