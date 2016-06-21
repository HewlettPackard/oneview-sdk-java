/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.networking.fcoenetworks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

public final class FcoeNetwork extends BaseModelResource {

    private static final long serialVersionUID = -7417831345235369294L;

    private String connectionTemplateUri;
    private String fabricUri;
    private String managedSanUri;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private Integer vlanId;

    public String getConnectionTemplateUri() {
        return connectionTemplateUri;
    }

    public void setConnectionTemplateUri(String connectionTemplateUri) {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    public String getFabricUri() {
        return fabricUri;
    }

    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    public String getManagedSanUri() {
        return managedSanUri;
    }

    public void setManagedSanUri(String managedSanUri) {
        this.managedSanUri = managedSanUri;
    }

    public List<String> getScopeUris() {
        return scopeUris;
    }

    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    public Integer getVlanId() {
        return vlanId;
    }

    public void setVlanId(Integer vlanId) {
        this.vlanId = vlanId;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof FcoeNetwork);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof FcoeNetwork) {
            FcoeNetwork that = (FcoeNetwork) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(connectionTemplateUri, that.connectionTemplateUri)
                    .append(fabricUri, that.fabricUri)
                    .append(managedSanUri, that.managedSanUri)
                    .append(scopeUris, that.scopeUris)
                    .append(vlanId, that.vlanId)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(connectionTemplateUri)
                .append(fabricUri)
                .append(managedSanUri)
                .append(scopeUris)
                .append(vlanId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("connectionTemplateUri", connectionTemplateUri)
                .append("fabricUri", fabricUri)
                .append("managedSanUri", managedSanUri)
                .append("scopeUris", scopeUris)
                .append("vlanId", vlanId)
                .toString();
    }
}
