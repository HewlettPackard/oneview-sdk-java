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

package com.hp.ov.sdk.dto.serverhardwaretype;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.BaseModelResource;

public class ServerHardwareType extends BaseModelResource {

    private List<Adapter> adapters;
    private List<BiosSettings> biosSettings;
    private List<String> bootCapabilities;
    private List<BootMode> bootModes;
    private List<String> capabilities;
    private String formFactor;
    @Until(199)
    private String id;
    private String model;
    private List<PxeBootPolicy> pxeBootPolicies;

    /**
     * This field has a special treatment when deserialization occurs. Since the
     * {@link ServerHardwareType} is never serialized, there is no need to deal
     * with the serialization.
     *
     * @see com.hp.ov.sdk.adaptors.StorageCapabilitiesDeserializer
     */
    private StorageCapabilities storageCapabilities;

    public List<Adapter> getAdapters() {
        return adapters;
    }

    public void setAdapters(List<Adapter> adapters) {
        this.adapters = adapters;
    }

    public List<BiosSettings> getBiosSettings() {
        return biosSettings;
    }

    public void setBiosSettings(List<BiosSettings> biosSettings) {
        this.biosSettings = biosSettings;
    }

    public List<String> getBootCapabilities() {
        return bootCapabilities;
    }

    public void setBootCapabilities(List<String> bootCapabilities) {
        this.bootCapabilities = bootCapabilities;
    }

    public List<BootMode> getBootModes() {
        return bootModes;
    }

    public void setBootModes(List<BootMode> bootModes) {
        this.bootModes = bootModes;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<PxeBootPolicy> getPxeBootPolicies() {
        return pxeBootPolicies;
    }

    public void setPxeBootPolicies(List<PxeBootPolicy> pxeBootPolicies) {
        this.pxeBootPolicies = pxeBootPolicies;
    }

    public StorageCapabilities getStorageCapabilities() {
        return storageCapabilities;
    }

    public void setStorageCapabilities(StorageCapabilities storageCapabilities) {
        this.storageCapabilities = storageCapabilities;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof ServerHardwareType);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof ServerHardwareType) {
            ServerHardwareType that = (ServerHardwareType) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(adapters, that.adapters)
                    .append(biosSettings, that.biosSettings)
                    .append(bootCapabilities, that.bootCapabilities)
                    .append(bootModes, that.bootModes)
                    .append(capabilities, that.capabilities)
                    .append(formFactor, that.formFactor)
                    .append(id, that.id)
                    .append(model, that.model)
                    .append(pxeBootPolicies, that.pxeBootPolicies)
                    .append(storageCapabilities, that.storageCapabilities)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(adapters)
                .append(biosSettings)
                .append(bootCapabilities)
                .append(bootModes)
                .append(capabilities)
                .append(formFactor)
                .append(id)
                .append(model)
                .append(pxeBootPolicies)
                .append(storageCapabilities)
                .toHashCode();
    }
}
