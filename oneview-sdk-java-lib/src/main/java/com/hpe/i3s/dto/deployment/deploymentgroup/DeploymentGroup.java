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
package com.hpe.i3s.dto.deployment.deploymentgroup;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class DeploymentGroup extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String id;
    private String managementNetworkname;
    private String managementNetworkuri;

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
     * @return the managementNetworkname
     */
    public String getManagementNetworkName() {
        return managementNetworkname;
    }

    /**
     * @param managementNetworkname the managementNetworkname to set
     */
    public void setManagementNetworkName(String managementNetworkname) {
        this.managementNetworkname = managementNetworkname;
    }

    /**
     * @return the managementNetworkuri
     */
    public String getManagementNetworkUri() {
        return managementNetworkuri;
    }

    /**
     * @param managementNetworkuri the managementNetworkuri to set
     */
    public void setManagementNetworkUri(String managementNetworkuri) {
        this.managementNetworkuri = managementNetworkuri;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
