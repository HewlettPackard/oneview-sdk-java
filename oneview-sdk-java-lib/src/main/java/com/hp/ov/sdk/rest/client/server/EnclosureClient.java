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

import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.rest.client.common.AddableResource;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.RemovableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(EnclosureClient.ENCLOSURE_URI)
public interface EnclosureClient extends
        AddableResource<AddEnclosure>,
        SearchableResource<Enclosure>,
        UpdatableResource<Enclosure>,
        RemovableResource,
        PatchableResource {

    String ENCLOSURE_ACTIVE_OA_SSO_URI = "/activeOaSsoUrl";
    String ENCLOSURE_CONFIGURATION_URI = "/configuration";
    String ENCLOSURE_COMPLIANCE_URI = "/compliance";
    String ENCLOSURE_FW_BASELINE_URI = "/enclosureFwBaseline";
    String ENCLOSURE_SCRIPT_URI = "/script";
    String ENCLOSURE_STANDBY_OA_SSO_URI = "/standbyOaSsoUrl";
    String ENCLOSURE_OA_SSO_URI = "/sso";
    String ENCLOSURE_REFRESH_STATE_URI = "/refreshState";
    String ENCLOSURE_URI = "/rest/enclosures";
    String ENCLOSURE_UTILIZATION_URI = "/utilization";
    String ENVIRONMENT_CONFIGURATION_URI = "/environmentalConfiguration";

    String ROLE_STANDBY = "?role=Standby";
    String ROLE_ACTIVE = "?role=Active";

    /**
     * Reapplies the enclosure configuration in the enclosure
     * identified by the given resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_CONFIGURATION_URI, method = HttpMethod.PUT)
    public TaskResource updateConfiguration(@PathParam("resourceId") String resourceId, RequestOption ... options);

    /**
     * Retrieves the configuration script for the specified enclosure resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return the configuration script for the specified enclosure.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_SCRIPT_URI)
    public String getConfigurationScript(@PathParam("resourceId") String resourceId);

    /**
     * Updates the configuration script for the specified enclosure resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param scriptData script data to be updated for enclosure.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_SCRIPT_URI, method = HttpMethod.PUT)
    public TaskResource updateConfigurationScript(@PathParam("resourceId") String resourceId
            , @BodyParam String scriptData, RequestOption ... options);

    /**
     * Retrieves data that can be used to construct a single sign-on URL
     * for an onboard administrator.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link SsoUrlData} the data used for single sign URL.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_OA_SSO_URI + ROLE_ACTIVE)
    public SsoUrlData getActiveOaSsoUrl(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves data that can be used to construct a single sign-on URL
     * for an onboard administrator. Available when using API version 120.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link SsoUrlData} the data used for single sign URL.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_ACTIVE_OA_SSO_URI)
    public SsoUrlData getActiveOaSsoUrl_V120(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves data that can be used to construct a single sign-on URL
     * for an onboard administrator.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link SsoUrlData} the data used for single sign URL.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_OA_SSO_URI + ROLE_STANDBY)
    public SsoUrlData getStandbyOaSsoUrl(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves data that can be used to construct a single sign-on URL
     * for an onboard administrator. Available when using API version 120.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link SsoUrlData} the data used for single sign URL.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_STANDBY_OA_SSO_URI)
    public SsoUrlData getStandbyOaSsoUrl_V120(@PathParam("resourceId") String resourceId);

    /**
     * Updates the enclosure configuration with that of the enclosure group script.
     * <b>This is not applicable if the enclosure is monitored.</b>
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_COMPLIANCE_URI, method = HttpMethod.PUT)
    public TaskResource updateCompliance(@PathParam("resourceId") String resourceId, RequestOption ... options);

    /**
     * Applies the firmware baseline to the enclosure. This method can be used to
     * update the OA firmware, or the OA, logical interconnect, and server profiles
     * in the enclosure.
     * <b>This is not applicable if the enclosure is monitored.</b>
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param fwBaselineConfig the firmware baseline to be applied to the enclosure.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_FW_BASELINE_URI, method = HttpMethod.PUT)
    public TaskResource updateFwBaseline(@PathParam("resourceId") String resourceId,
            @BodyParam FwBaselineConfig fwBaselineConfig, RequestOption ... options);

    /**
     * Retrieves historical utilization data for the specified enclosure.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} containing resource data utilization
     * such as power and cpu.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_UTILIZATION_URI)
    public UtilizationData getUtilization(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the environmental configuration of the enclosure identified
     * by the given enclosure identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} for the specified enclosure.
     */
    @Endpoint(uri = "/{resourceId}" + ENVIRONMENT_CONFIGURATION_URI)
    public EnvironmentalConfiguration getEnvironmentalConfiguration(@PathParam("resourceId") String resourceId);

    /**
     * Updates the environmental configuration of the enclosure identified
     * by the given enclosure identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param updateEnvironmentalConfiguration environmental configuration of
     * enclosure resource specifying the cpu and power value.
     *
     * @return {@link EnvironmentalConfiguration} for the specified enclosure.
     */
    @Endpoint(uri = "/{resourceId}" + ENVIRONMENT_CONFIGURATION_URI, method = HttpMethod.PUT)
    public EnvironmentalConfiguration updateEnvironmentalConfiguration(@PathParam("resourceId") String resourceId,
            @BodyParam EnvironmentalConfigurationUpdate updateEnvironmentalConfiguration);

    /**
     * Refresh the enclosure to fix any configuration issue.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param refreshStateConfig refresh state details to fix configuration issues.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_REFRESH_STATE_URI, method = HttpMethod.PUT)
    public TaskResource updateRefreshState(@PathParam("resourceId") String resourceId,
            @BodyParam RefreshStateConfig refreshStateConfig, RequestOption ... options);

}
