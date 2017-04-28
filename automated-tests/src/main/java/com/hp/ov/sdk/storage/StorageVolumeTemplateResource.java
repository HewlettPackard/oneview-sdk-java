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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.storage.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.storage.TemplateProvisioningData;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeTemplateClient;

public class StorageVolumeTemplateResource extends BasicResource
        implements CreateResource, RemoveResource, UpdateResource {

    private static StorageVolumeTemplateResource instance;

    private StorageVolumeTemplateClient client;

    private String systemUri;
    private String poolUri;

    public StorageVolumeTemplateResource() {
        category.put("V_300", "StorageVolumeTemplateV3");
        category.put("V_200", "StorageVolumeTemplateV3");
        client = oneViewClient.storageVolumeTemplate();
    }

    public static StorageVolumeTemplateResource getInstance() {
        if (instance == null) {
            instance = new StorageVolumeTemplateResource();
        }
        return instance;
    }

    public void setStorageSystemUri(String systemUri) {
        this.systemUri = systemUri;
    }

    public void setStoragePoolUri(String poolUri) {
        this.poolUri = poolUri;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        StorageVolumeTemplate volumeTemplate = (StorageVolumeTemplate) getResource(client.getByName(name));
        return volumeTemplate == null ? "" : volumeTemplate.getResourceId();
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
        client.create(builder());
    }

    @Override
    public String remove(String id) {
        return objToString(client.delete(id));
    }

    @Override
    public String update(String id) {
        return objToString(client.update(id, builderUpdate(client.getById(id))));
    }

    public int getConnectableVolumeTemplate() {
        return getCount(client.getConnectableVolumeTemplates());
    }

    @Override
    public StorageVolumeTemplate builder() {
        StorageVolumeTemplate volumeTemplate = new StorageVolumeTemplate();
        volumeTemplate.setName(resourceProperties.get("name"));
        volumeTemplate.setState(resourceProperties.get("state"));
        volumeTemplate.setDescription(resourceProperties.get("description"));
        volumeTemplate.setStateReason(resourceProperties.get("stateReason"));
        volumeTemplate.setStorageSystemUri(systemUri);
        volumeTemplate.setSnapshotPoolUri(poolUri);

        TemplateProvisioningData provisioning = new TemplateProvisioningData();
        provisioning.setProvisionType(resourceProperties.get("provisionType"));
        provisioning.setShareable(Boolean.valueOf(resourceProperties.get("shareable")));
        provisioning.setCapacity(resourceProperties.get("capacity"));
        provisioning.setStoragePoolUri(poolUri);

        volumeTemplate.setProvisioning(provisioning);
        volumeTemplate.setType(getCategory());

        return volumeTemplate;
    }

    private StorageVolumeTemplate builderUpdate(StorageVolumeTemplate volumeTemplate) {
        volumeTemplate.setName(resourceProperties.get("name"));
        return volumeTemplate;
    }

}
