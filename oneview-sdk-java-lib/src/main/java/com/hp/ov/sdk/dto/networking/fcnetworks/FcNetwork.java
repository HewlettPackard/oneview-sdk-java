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
package com.hp.ov.sdk.dto.networking.fcnetworks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

public class FcNetwork extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Boolean autoLoginRedistribution = false;
    private String connectionTemplateUri;
    private FabricType fabricType = FabricType.FabricAttach;
    private String managedSanUri;
    private Integer linkStabilityTime = 30;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();

    /**
     * @return the autoLoginRedistribution
     */
    public Boolean getAutoLoginRedistribution() {
        return autoLoginRedistribution;
    }

    /**
     * @param autoLoginRedistribution the autoLoginRedistribution to set
     */
    public void setAutoLoginRedistribution(Boolean autoLoginRedistribution) {
        this.autoLoginRedistribution = autoLoginRedistribution;
    }

    /**
     * @return the connectionTemplateUri
     */
    public String getConnectionTemplateUri() {
        return connectionTemplateUri;
    }

    /**
     * @param connectionTemplateUri the connectionTemplateUri to set
     */
    public void setConnectionTemplateUri(String connectionTemplateUri) {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    /**
     * @return the fabricType
     */
    public FabricType getFabricType() {
        return fabricType;
    }

    /**
     * @param fabricType the fabricType to set
     */
    public void setFabricType(FabricType fabricType) {
        this.fabricType = fabricType;
    }

    /**
     * @return the managedSanUri
     */
    public String getManagedSanUri() {
        return managedSanUri;
    }

    /**
     * @param managedSanUri the managedSanUri to set
     */
    public void setManagedSanUri(String managedSanUri) {
        this.managedSanUri = managedSanUri;
    }

    /**
     * @return the linkStabilityTime
     */
    public Integer getLinkStabilityTime() {
        return linkStabilityTime;
    }

    /**
     * @param linkStabilityTime the linkStabilityTime to set
     */
    public void setLinkStabilityTime(Integer linkStabilityTime) {
        this.linkStabilityTime = linkStabilityTime;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof FcNetwork);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof FcNetwork) {
            FcNetwork that = (FcNetwork) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(autoLoginRedistribution, that.autoLoginRedistribution)
                    .append(connectionTemplateUri, that.connectionTemplateUri)
                    .append(fabricType, that.fabricType)
                    .append(linkStabilityTime, that.linkStabilityTime)
                    .append(managedSanUri, that.managedSanUri)
                    .append(scopeUris, that.scopeUris)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(autoLoginRedistribution)
                .append(connectionTemplateUri)
                .append(fabricType)
                .append(linkStabilityTime)
                .append(managedSanUri)
                .append(scopeUris)
                .toHashCode();
    }
}
