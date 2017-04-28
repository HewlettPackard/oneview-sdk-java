/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.server;

import java.util.Map;

import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.firmware.FwBaseline;
import com.hp.ov.sdk.dto.servers.LicensingIntent;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.dto.servers.enclosure.FwBaselineOptions;
import com.hp.ov.sdk.dto.servers.enclosure.RefreshStateConfig;
import com.hp.ov.sdk.dto.servers.enclosure.RefreshStateConfig.RefreshForceOptions;
import com.hp.ov.sdk.dto.servers.enclosure.SsoUrlData;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.Credential;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;

public class EnclosureResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static EnclosureResource instance;

    private EnclosureClient client;
    private FirmwareDriverClient firmwareDriverClient;

    private String hostname;
    private String username;
    private String password;

    private static String resourceName;

    private EnclosureResource() {
        client = oneViewClient.enclosure();
        firmwareDriverClient = oneViewClient.firmwareDriver();
        hostname = Credential.getInstance().getEnclosureHostname();
        username = Credential.getInstance().getEnclosureUsername();
        password = Credential.getInstance().getEnclosurePassword();
    }

    public static EnclosureResource getInstance() {
        if (instance == null) {
            instance = new EnclosureResource();
        }
        return instance;
    }

    public String getEnclosureName() {
        if (resourceName == null) {
            resourceName = getEnclosure().getName();
        }
        return resourceName;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Enclosure enclosure = (Enclosure) getResource(client.getByName(name));
        return enclosure == null ? "" : enclosure.getResourceId();
    }

    public Enclosure getByName(String name) {
        return (Enclosure) getResource(client.getByName(name));
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        TaskResource task = client.add(builder());
        resourceName = task.getAssociatedResource().getResourceName();
    }

    @Override
    public String update(String id) {
        return taskToString(client.patch(id, builderPatch()));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.remove(id));
    }

    private Enclosure getEnclosure() {
        ResourceCollection<Enclosure> enclosureCollection = client.getAll();
        for (Enclosure e : enclosureCollection.getMembers()) {
            if (e.getActiveOaPreferredIP().equals(hostname)) {
                return e;
            }
        }
        return null;
    }

    private String getFirmwareUri() {
        ResourceCollection<FwBaseline> firmwareDrivers = firmwareDriverClient
                .getByName(resourceProperties.get("firmware"));
        if (!firmwareDrivers.getMembers().isEmpty()) {
            return firmwareDrivers.getMembers().get(0).getUri();
        }
        return "";
    }

    public String getEnvironmentalConfiguration(String resourceID) {
        EnvironmentalConfiguration ec = client.getEnvironmentalConfiguration(resourceID);
        return ec != null ? ec.toString() : "";
    }

    public String getScriptConfiguration(String resourceID) {
        String response = client.getConfigurationScript(resourceID);
        return response != null ? response : "";
    }

    public String getActiveOaSsoUrl(String resourceID) {
        SsoUrlData ssoUrlData = client.getActiveOaSsoUrl(resourceID);
        return ssoUrlData != null ? ssoUrlData.toString() : "";
    }

    public String getEnclosurelUtilization(String resourceID) {
        UtilizationData utilizationData = client.getUtilization(resourceID);
        return utilizationData != null ? utilizationData.toString() : "";
    }

    public String getUri() {
        Enclosure enclosure = getEnclosure();
        return enclosure == null ? "" : enclosure.getUri();
    }

    @Override
    public AddEnclosure builder() {
        AddEnclosure enclosure = new AddEnclosure();
        String enclosureGroupUri = ((EnclosureGroupResource) EnclosureGroupResource.getInstance())
                .getUri(resourceProperties.get("enclosureGroup"));
        enclosure.setEnclosureGroupUri(enclosureGroupUri);

        enclosure.setHostname(hostname);
        enclosure.setUsername(username);
        enclosure.setPassword(password);

        enclosure.setLicensingIntent(LicensingIntent.valueOf(resourceProperties.get("licensing")));
        enclosure.setForce(Boolean.parseBoolean(resourceProperties.get("force")));
        enclosure.setFirmwareBaselineUri(getFirmwareUri());
        enclosure.setUpdateFirmwareOn(FwBaselineOptions.valueOf(resourceProperties.get("updateFirmware")));
        enclosure.setForceInstallFirmware(Boolean.parseBoolean(resourceProperties.get("forceInstallFirmware")));
        return enclosure;
    }

    private Patch builderPatch() {
        resourceName = resourceProperties.get("name");
        Patch patch = new Patch();
        patch.setOp(PatchOperation.replace);
        patch.setPath("/name");
        patch.setValue(resourceName);
        return patch;
    }

    public String patch(String id) {
        Patch patch = new Patch();
        patch.setOp(Patch.PatchOperation.valueOf(resourceProperties.get("op")));
        patch.setPath(resourceProperties.get("path"));
        patch.setValue(resourceProperties.get("value"));
        return objToString(client.patch(id, patch));
    }

    public String refresh(String id) {
        RefreshStateConfig refreshStateConfig = new RefreshStateConfig();
        RefreshForceOptions refreshForceOptions = refreshStateConfig.getNewRefreshForceOptions();

        refreshForceOptions.setAddress(hostname);
        refreshForceOptions.setUsername(username);
        refreshForceOptions.setPassword(password);
        refreshStateConfig.setRefreshForceOptions(refreshForceOptions);
        refreshStateConfig.setRefreshState(RefreshState.valueOf(resourceProperties.get("refreshState")));
        return taskToString(client.updateRefreshState(id, refreshStateConfig));
    }
}
