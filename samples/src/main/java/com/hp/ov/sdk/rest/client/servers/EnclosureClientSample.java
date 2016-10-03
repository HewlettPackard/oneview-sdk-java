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
package com.hp.ov.sdk.rest.client.servers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.RefreshStateConfig.RefreshForceOptions;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.LicensingIntent;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.dto.servers.enclosure.FwBaselineOptions;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * EnclosureClientSample is a sample program to consume the REST API of c7000 enclosure managed
 * by HPE OneView. It invokes APIs of EnclosureClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on enclosure resource
 */
public class EnclosureClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnclosureClientSample.class);

    private final EnclosureClient enclosureClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final EnclosureGroupClient enclosureGroupClient;

    // test values - user input
    // ================================
    public static final String RESOURCE_NAME = "Encl1";
    public static final String RESOURCE_NAME_UPDATED = "Encl1_Updated";

    private static final String RESOURCE_ID = "09SGH100X6J1";
    private static final String ENCLOSURE_CONFIGURATION_SCRIPT = "name=Enclosure_test_script";
    private static final String HOSTNAME = "172.18.1.11";
    private static final String USERNAME = "dcs";
    private static final String PASSWORD = "dcs";
    private static final String FIRMWARE = "Service Pack for ProLiant";
    // ================================

    private EnclosureClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.enclosureClient = oneViewClient.enclosure();
        this.firmwareDriverClient = oneViewClient.firmwareDriver();
        this.enclosureGroupClient = oneViewClient.enclosureGroup();
    }

    private void getEnclosureById() {
        Enclosure enclosure = enclosureClient.getById(RESOURCE_ID);

        LOGGER.info("Enclosure object returned to client: " + enclosure.toJsonString());
    }

    private void getAllEnclosures() {
        ResourceCollection<Enclosure> enclosures = enclosureClient.getAll();

        LOGGER.info("Enclosures returned to client: " + enclosures.toJsonString());
    }

    private void getEnclosureByName() {
        Enclosure enclosure = enclosureClient.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("Enclosure object returned to client: " + enclosure.toJsonString());
    }

    private void addEnclosure() {
        AddEnclosure addEnclosure = buildAddEnclosure();

        TaskResource taskResource = this.enclosureClient.add(addEnclosure);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void updateEnclosure() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        enclosure.setName(RESOURCE_NAME_UPDATED);

        TaskResource taskResource = this.enclosureClient.update(enclosure.getResourceId(), enclosure);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void patchEnclosure() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);
        Patch patch = new Patch();

        // Enclosure patch supports the update of Name and Rack Name
        patch.setOp(PatchOperation.replace);
        patch.setPath("/name");
        patch.setValue(RESOURCE_NAME_UPDATED);

        TaskResource taskResource = this.enclosureClient.patch(enclosure.getResourceId(), patch);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void removeEnclosure() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.enclosureClient.remove(enclosure.getResourceId());

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void updateEnclosureConfiguration() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.enclosureClient.updateConfiguration(enclosure.getResourceId());

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void getEnclosureConfigurationScript() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        String script = this.enclosureClient.getConfigurationScript(enclosure.getResourceId());

        LOGGER.info("Script returned to client: " + script);
    }

    private void updateEnclosureConfigurationScript() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.enclosureClient.updateConfigurationScript(enclosure.getResourceId(),
                ENCLOSURE_CONFIGURATION_SCRIPT);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void getEnclosureActiveOaSsoUrl() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        SsoUrlData ssoUrlData = this.enclosureClient.getActiveOaSsoUrl(enclosure.getResourceId());

        LOGGER.info("SsoUrlData object returned to client: " + JsonPrettyPrinter.print(ssoUrlData));
    }

    private void getEnclosureStandbyOaSsoUrl() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        SsoUrlData ssoUrlData = this.enclosureClient.getStandbyOaSsoUrl(enclosure.getResourceId());

        LOGGER.info("SsoUrlData object returned to client: " + JsonPrettyPrinter.print(ssoUrlData));
    }

    private void updateEnclosureCompliance() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.enclosureClient.updateCompliance(enclosure.getResourceId());

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void updateEnclosureFwBaseline() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        FwBaselineConfig fwBaselineConfig = buildFwBaselineConfig();

        TaskResource taskResource = this.enclosureClient.updateFwBaseline(
                enclosure.getResourceId(), fwBaselineConfig);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void getEnclosureUtilization() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        UtilizationData utilizationData = this.enclosureClient.getUtilization(enclosure.getResourceId());

        LOGGER.info("UtilizationData object returned to client: " + JsonPrettyPrinter.print(utilizationData));
    }

    private void getEnclosureEnvironmentalConfiguration() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        EnvironmentalConfiguration configuration = this.enclosureClient.getEnvironmentalConfiguration(
                enclosure.getResourceId());

        LOGGER.info("EnvironmentalConfiguration object returned to client: " + JsonPrettyPrinter.print(configuration));
    }

    private void updateEnclosureEnvironmentalConfiguration() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        EnvironmentalConfigurationUpdate configuration = buildEnvironmentalConfigurationUpdateConfig();

        EnvironmentalConfiguration environmental = this.enclosureClient.updateEnvironmentalConfiguration(
                enclosure.getResourceId(), configuration);

        LOGGER.info("EnvironmentalConfiguration object returned to client: " + JsonPrettyPrinter.print(environmental));
    }

    private void updateEnclosureRefreshState() {
        Enclosure enclosure = this.enclosureClient.getByName(RESOURCE_NAME).get(0);

        RefreshStateConfig refreshStateConfig = buildRefreshStateConfig();

        TaskResource taskResource = this.enclosureClient.updateRefreshState(
                enclosure.getResourceId(), refreshStateConfig);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private AddEnclosure buildAddEnclosure() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();
        AddEnclosure addEnclosure = new AddEnclosure();

        addEnclosure.setHostname(HOSTNAME);
        addEnclosure.setUsername(USERNAME);
        addEnclosure.setPassword(PASSWORD);
        addEnclosure.setLicensingIntent(LicensingIntent.OneView);
        addEnclosure.setForce(false);
        addEnclosure.setEnclosureGroupUri(enclosureGroupUri);
        addEnclosure.setFirmwareBaselineUri(firmwareDriverClient.getByName(FIRMWARE).get(0).getUri());
        addEnclosure.setUpdateFirmwareOn(FwBaselineOptions.EnclosureOnly);
        addEnclosure.setForceInstallFirmware(false);

        return addEnclosure;
    }

    private FwBaselineConfig buildFwBaselineConfig() {
        FwBaselineConfig fwBaselineConfigDto = new FwBaselineConfig();

        fwBaselineConfigDto.setFwBaselineUri(firmwareDriverClient.getByName(FIRMWARE).get(0).getUri());
        fwBaselineConfigDto.setIsFwManaged(true);
        fwBaselineConfigDto.setFirmwareUpdateOn("EnclosureOnly");
        fwBaselineConfigDto.setForceInstallFirmware(false);

        return fwBaselineConfigDto;
    }

    private EnvironmentalConfigurationUpdate buildEnvironmentalConfigurationUpdateConfig() {
        EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto = new EnvironmentalConfigurationUpdate();
        environmentalConfigurationUpdateDto.setCalibratedMaxPower(2500);

        return environmentalConfigurationUpdateDto;
    }

    private RefreshStateConfig buildRefreshStateConfig() {
        RefreshStateConfig refreshStateConfig = new RefreshStateConfig();
        RefreshForceOptions refreshForceOptions = refreshStateConfig.getNewRefreshForceOptions();

        refreshForceOptions.setAddress(HOSTNAME);
        refreshForceOptions.setUsername(USERNAME);
        refreshForceOptions.setPassword(PASSWORD);
        refreshStateConfig.setRefreshForceOptions(refreshForceOptions);
        refreshStateConfig.setRefreshState(RefreshState.RefreshPending);

        return refreshStateConfig;
    }

    public static void main(final String[] args) throws Exception {
        EnclosureClientSample client = new EnclosureClientSample();

        client.getAllEnclosures();
        client.addEnclosure();
        client.getEnclosureById();
        client.getEnclosureActiveOaSsoUrl();
        client.getEnclosureByName();
        client.updateEnclosureConfiguration();

        client.updateEnclosureCompliance(); // For 2.0 use LogicalEnclosure.updateFromGroup() method
        client.updateEnclosureFwBaseline(); // For 2.0 use LogicalEnclosure.patch() method

        client.getEnclosureEnvironmentalConfiguration();
        // Check if the privilege exists
        client.updateEnclosureEnvironmentalConfiguration();

        client.updateEnclosureRefreshState();

        client.updateEnclosureConfigurationScript(); // For 2.0 use LogicalEnclosure.updateScript() method

        client.getEnclosureConfigurationScript();
        client.getEnclosureStandbyOaSsoUrl();
        client.getEnclosureUtilization();

        client.updateEnclosure(); // For 2.0 use patchEnclosure() method

        client.patchEnclosure();
        client.removeEnclosure();
    }
}
