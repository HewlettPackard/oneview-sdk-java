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

package com.hp.ov.sdk.dto.servers.serverhardwaretype;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.BaseModelResource;

public class ServerHardwareType extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private List<Adapter> adapters;
    private List<BiosSettings> biosSettings;
    private List<String> bootCapabilities;
    private List<BootMode> bootModes;
    private List<String> capabilities;
    @Since(300)
    private String family;
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

    /**
     * @return the adapters
     */
    public List<Adapter> getAdapters() {
        return adapters;
    }

    /**
     * @param adapters the adapters to set
     */
    public void setAdapters(List<Adapter> adapters) {
        this.adapters = adapters;
    }

    /**
     * @return the biosSettings
     */
    public List<BiosSettings> getBiosSettings() {
        return biosSettings;
    }

    /**
     * @param biosSettings the biosSettings to set
     */
    public void setBiosSettings(List<BiosSettings> biosSettings) {
        this.biosSettings = biosSettings;
    }

    /**
     * @return the bootCapabilities
     */
    public List<String> getBootCapabilities() {
        return bootCapabilities;
    }

    /**
     * @param bootCapabilities the bootCapabilities to set
     */
    public void setBootCapabilities(List<String> bootCapabilities) {
        this.bootCapabilities = bootCapabilities;
    }

    /**
     * @return the bootModes
     */
    public List<BootMode> getBootModes() {
        return bootModes;
    }

    /**
     * @param bootModes the bootModes to set
     */
    public void setBootModes(List<BootMode> bootModes) {
        this.bootModes = bootModes;
    }

    /**
     * @return the capabilities
     */
    public List<String> getCapabilities() {
        return capabilities;
    }

    /**
     * @param capabilities the capabilities to set
     */
    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the formFactor
     */
    public String getFormFactor() {
        return formFactor;
    }

    /**
     * @param formFactor the formFactor to set
     */
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the pxeBootPolicies
     */
    public List<PxeBootPolicy> getPxeBootPolicies() {
        return pxeBootPolicies;
    }

    /**
     * @param pxeBootPolicies the pxeBootPolicies to set
     */
    public void setPxeBootPolicies(List<PxeBootPolicy> pxeBootPolicies) {
        this.pxeBootPolicies = pxeBootPolicies;
    }

    /**
     * @return the storageCapabilities
     */
    public StorageCapabilities getStorageCapabilities() {
        return storageCapabilities;
    }

    /**
     * @param storageCapabilities the storageCapabilities to set
     */
    public void setStorageCapabilities(StorageCapabilities storageCapabilities) {
        this.storageCapabilities = storageCapabilities;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof ServerHardwareType);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
                    .append(family, that.family)
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
                .append(family)
                .append(formFactor)
                .append(id)
                .append(model)
                .append(pxeBootPolicies)
                .append(storageCapabilities)
                .toHashCode();
    }
}
