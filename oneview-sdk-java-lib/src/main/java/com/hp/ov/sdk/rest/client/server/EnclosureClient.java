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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class EnclosureClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnclosureClient.class);
    private static final int TIMEOUT = 1200000; // in milliseconds

    private static final String ROLE_STANDBY = "?role=Standby";
    private static final String ROLE_ACTIVE = "?role=Active";

    private final BaseClient baseClient;

    public EnclosureClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link Enclosure} details for the specified enclosure.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link Enclosure} object containing the details.
     */
    public Enclosure getById(String resourceId) {
        LOGGER.info("EnclosureClient : getById : Start");

        Enclosure logicalEnclosure = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId), Enclosure.class);

        LOGGER.info("EnclosureClient : getById : End");

        return logicalEnclosure;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Enclosure}&gt; containing the details
     * for all the available enclosures found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Enclosure}&gt; containing
     * the details for all found enclosures.
     */
    public ResourceCollection<Enclosure> getAll() {
        LOGGER.info("EnclosureClient : getAll : Start");

        ResourceCollection<Enclosure> logicalEnclosures = baseClient.getResourceCollection(
                ResourceUris.ENCLOSURE_URI, Enclosure.class);

        LOGGER.info("EnclosureClient : getAll : End");

        return logicalEnclosures;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Enclosure}&gt; containing details
     * for the available enclosures found under the current HPE OneView that match the name.
     *
     * @param name enclosure name as seen in HPE OneView.
     *
     * @return {@link Enclosure} object containing the details.
     */
    public ResourceCollection<Enclosure> getByName(String name) {
        LOGGER.info("EnclosureClient : getByName : Start");

        ResourceCollection<Enclosure> logicalEnclosures = baseClient.getResourceCollection(
                ResourceUris.ENCLOSURE_URI, Enclosure.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("EnclosureClient : getByName : End");

        return logicalEnclosures;
    }

    /**
     * Creates a enclosure according to the provided {@link Enclosure} object.
     *
     * @param addEnclosure object containing the enclosure details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 add(AddEnclosure addEnclosure, boolean aSync) {
        LOGGER.info("EnclosureClient : add : Start");

        Request request = new Request(HttpMethodType.POST, ResourceUris.ENCLOSURE_URI, addEnclosure);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : add : End");

        return taskResource;
    }

    /**
     * Updates a {@link Enclosure} identified by the given resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param enclosure object containing the enclosure details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, Enclosure enclosure, boolean aSync) {
        LOGGER.info("EnclosureClient : update : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId), enclosure);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : update : End");

        return taskResource;
    }

    /**
     * Performs a PATCH request and updates the existing enclosure based
     * on the resource identifier and the content of the {@link Patch} object.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param patch object containing the update to be made to existing enclosure.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 patch(String resourceId, Patch patch, boolean aSync) {
        LOGGER.info("EnclosureClient : patch : Start");

        Request request = new Request(HttpMethodType.PATCH,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId), patch);

        request.setTimeout(TIMEOUT);

        if (this.baseClient.getApiVersion().getValue() >= ApiVersion.V_300.getValue()) {
            request.setContentType(ContentType.APPLICATION_JSON_PATCH);
        }

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : patch : End");

        return taskResource;
    }

    /**
     * Removes the {@link Enclosure} identified by the given resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("EnclosureClient : remove : Start");

        Request request = new Request(HttpMethodType.DELETE,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId));

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : remove : End");

        return taskResource;
    }

    /**
     * Reapplies the enclosure configuration in the enclosure
     * identified by the given resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateConfiguration(String resourceId, boolean aSync) {
        LOGGER.info("EnclosureClient : updateConfiguration : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENCLOSURE_CONFIGURATION_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : updateConfiguration : End");

        return taskResource;
    }

    /**
     * Retrieves the configuration script for the specified enclosure resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return the configuration script for the specified enclosure.
     */
    public String getConfigurationScript(String resourceId) {
        LOGGER.info("EnclosureClient : getConfigurationScript : Start");

        Request request = new Request(HttpMethodType.GET,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                        ResourceUris.ENCLOSURE_SCRIPT_URI));

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("EnclosureClient : getConfigurationScript : End");

        return response;
    }

    /**
     * Updates the configuration script for the specified enclosure resource identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param scriptData script data to be updated for enclosure.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateConfigurationScript(String resourceId, String scriptData, boolean aSync) {
        LOGGER.info("EnclosureClient : updateConfigurationScript : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENCLOSURE_SCRIPT_URI);

        TaskResourceV2 taskResource = this.baseClient.updateResource(updateUri, scriptData, aSync);

        LOGGER.info("EnclosureClient : updateConfigurationScript : End");

        return taskResource;
    }

    /**
     * Retrieves data that can be used to construct a single sign-on URL
     * for an onboard administrator.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link SsoUrlData} the data used for single sign URL.
     */
    public SsoUrlData getActiveOaSsoUrl(String resourceId) {
        LOGGER.info("EnclosureClient : getActiveOaSsoUrl : Start");

        String oaSsoUrl;

        if (this.baseClient.getApiVersion().getValue() >= ApiVersion.V_200.getValue()) {
            oaSsoUrl = new StringBuilder()
                    .append(ResourceUris.ENCLOSURE_OA_SSO_URI)
                    .append(ROLE_ACTIVE).toString();
        } else {
            oaSsoUrl = ResourceUris.ENCLOSURE_ACTIVE_OA_SSO_URI;
        }

        SsoUrlData ssoUrlData = this.getOaSsoUrl(resourceId, oaSsoUrl);

        LOGGER.info("EnclosureClient : getActiveOaSsoUrl : End");

        return ssoUrlData;
    }

    /**
     * Retrieves data that can be used to construct a single sign-on URL
     * for an onboard administrator.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link SsoUrlData} the data used for single sign URL.
     */
    public SsoUrlData getStandbyOaSsoUrl(String resourceId) {
        LOGGER.info("EnclosureClient : getStandbyOaSsoUrl : Start");

        String oaSsoUrl;

        if (this.baseClient.getApiVersion().getValue() >= ApiVersion.V_200.getValue()) {
            oaSsoUrl = new StringBuilder()
                    .append(ResourceUris.ENCLOSURE_OA_SSO_URI)
                    .append(ROLE_STANDBY).toString();
        } else {
            oaSsoUrl = ResourceUris.ENCLOSURE_STANDBY_OA_SSO_URI;
        }

        SsoUrlData ssoUrlData = this.getOaSsoUrl(resourceId, oaSsoUrl);

        LOGGER.info("EnclosureClient : getStandbyOaSsoUrl : End");

        return ssoUrlData;
    }

    private SsoUrlData getOaSsoUrl(String resourceId, String oaSsoUrl) {
        LOGGER.info("EnclosureClient : getOaSsoUrl : Start");

        SsoUrlData ssoUrlData = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId, oaSsoUrl),
                SsoUrlData.class);

        LOGGER.info("EnclosureClient : getOaSsoUrl : End");

        return ssoUrlData;
    }

    /**
     * Updates the enclosure configuration with that of the enclosure group script.
     * <b>This is not applicable if the enclosure is monitored.</b>
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateCompliance(String resourceId, boolean aSync) {
        LOGGER.info("EnclosureClient : updateCompliance : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENCLOSURE_COMPLIANCE_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : updateCompliance : End");

        return taskResource;
    }

    /**
     * Applies the firmware baseline to the enclosure. This method can be used to
     * update the OA firmware, or the OA, logical interconnect, and server profiles
     * in the enclosure.
     * <b>This is not applicable if the enclosure is monitored.</b>
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param fwBaselineConfig the firmware baseline to be applied to the enclosure.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateFwBaseline(String resourceId, FwBaselineConfig fwBaselineConfig, boolean aSync) {
        LOGGER.info("EnclosureClient : updateFwBaseline : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENCLOSURE_FW_BASELINE_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri, fwBaselineConfig);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : updateFwBaseline : End");

        return taskResource;
    }

    /**
     * Retrieves historical utilization data for the specified enclosure.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} containing resource data utilization
     * such as power and cpu.
     */
    public UtilizationData getUtilization(String resourceId) {
        LOGGER.info("EnclosureClient : getUtilization : Start");

        UtilizationData utilizationData = this.baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                        ResourceUris.ENCLOSURE_UTILIZATION_URI),
                UtilizationData.class);

        LOGGER.info("EnclosureClient : getUtilization : End");

        return utilizationData;
    }

    /**
     * Retrieves the environmental configuration of the enclosure identified
     * by the given enclosure identifier.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} for the specified enclosure.
     */
    public EnvironmentalConfiguration getEnvironmentalConfiguration(String resourceId) {
        LOGGER.info("EnclosureClient : getEnvironmentalConfiguration : Start");

        EnvironmentalConfiguration configuration = this.baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                        ResourceUris.ENVIRONMENT_CONFIGURATION_URI),
                EnvironmentalConfiguration.class);

        LOGGER.info("EnclosureClient : getEnvironmentalConfiguration : End");

        return configuration;
    }

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
    public EnvironmentalConfiguration updateEnvironmentalConfiguration(String resourceId,
            EnvironmentalConfigurationUpdate updateEnvironmentalConfiguration) {

        LOGGER.info("EnclosureClient : getEnvironmentalConfiguration : Start");

        Request request = new Request(
                HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                        ResourceUris.ENVIRONMENT_CONFIGURATION_URI),
                updateEnvironmentalConfiguration);

        EnvironmentalConfiguration configuration = baseClient.executeRequest(request,
                EnvironmentalConfiguration.class);

        LOGGER.info("EnclosureClient : updateEnvironmentalConfiguration : End");

        return configuration;
    }

    /**
     * Refresh the enclosure to fix any configuration issue.
     *
     * @param resourceId enclosure resource identifier as seen in HPE OneView.
     * @param refreshStateConfig refresh state details to fix configuration issues.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateRefreshState(String resourceId, RefreshStateConfig refreshStateConfig, boolean aSync) {
        LOGGER.info("EnclosureClient : updateRefreshState : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENCLOSURE_REFRESH_STATE_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri, refreshStateConfig);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("EnclosureClient : updateRefreshState : End");

        return taskResource;
    }

}
