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
package com.hp.ov.sdk.dto.generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class FcNetwork extends BaseModelResource {

    private FabricType fabricType = FabricType.FabricAttach;
    private String connectionTemplateUri;
    private Integer linkStabilityTime = 30;
    private String managedSanUri;
    private Boolean autoLoginRedistribution = false;

    public FabricType getFabricType() {
        return fabricType;
    }

    public void setFabricType(FabricType fabricType) {
        this.fabricType = fabricType;
    }

    public String getConnectionTemplateUri() {
        return connectionTemplateUri;
    }

    public void setConnectionTemplateUri(String connectionTemplateUri) {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    public Integer getLinkStabilityTime() {
        return linkStabilityTime;
    }

    public void setLinkStabilityTime(Integer linkStabilityTime) {
        this.linkStabilityTime = linkStabilityTime;
    }

    public String getManagedSanUri() {
        return managedSanUri;
    }

    public void setManagedSanUri(String managedSanUri) {
        this.managedSanUri = managedSanUri;
    }

    public Boolean getAutoLoginRedistribution() {
        return autoLoginRedistribution;
    }

    public void setAutoLoginRedistribution(Boolean autoLoginRedistribution) {
        this.autoLoginRedistribution = autoLoginRedistribution;
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
                    .append(fabricType, that.fabricType)
                    .append(connectionTemplateUri, that.connectionTemplateUri)
                    .append(linkStabilityTime, that.linkStabilityTime)
                    .append(managedSanUri, that.managedSanUri)
                    .append(autoLoginRedistribution, that.autoLoginRedistribution)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(fabricType)
                .append(connectionTemplateUri)
                .append(linkStabilityTime)
                .append(managedSanUri)
                .append(autoLoginRedistribution)
                .toHashCode();
    }
}
