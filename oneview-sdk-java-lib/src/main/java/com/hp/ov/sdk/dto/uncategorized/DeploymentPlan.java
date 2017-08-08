/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.uncategorized;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.uncategorized.CustomAttribute;

public class DeploymentPlan extends BaseModelResource {

    private static final long serialVersionUID = 5120623722702146755L;

    private List<CustomAttribute> additionalParameters = new ArrayList<>();
    private String architecture;
    private String deploymentAppliance;
    private String deploymentApplianceIpv4;
    private String deploymentType;
    private String id;
    private String nativePlanUri;
    private String osType;
    private String osdpSize;

    /**
     * @return the additionalParameters
     */
    public List<CustomAttribute> getAdditionalParameters() {
        return additionalParameters;
    }

    /**
     * @param additionalParameters
     *            the additionalParameters to set
     */
    public void setAdditionalParameters(List<CustomAttribute> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    /**
     * @return the architecture
     */
    public String getArchitecture() {
        return architecture;
    }

    /**
     * @param architecture
     *            the architecture to set
     */
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    /**
     * @return the deploymentAppliance
     */
    public String getDeploymentAppliance() {
        return deploymentAppliance;
    }

    /**
     * @param deploymentAppliance
     *            the deploymentAppliance to set
     */
    public void setDeploymentAppliance(String deploymentAppliance) {
        this.deploymentAppliance = deploymentAppliance;
    }

    /**
     * @return the deploymentApplianceIpv4
     */
    public String getDeploymentApplianceIpv4() {
        return deploymentApplianceIpv4;
    }

    /**
     * @param deploymentApplianceIpv4
     *            the deploymentApplianceIpv4 to set
     */
    public void setDeploymentApplianceIpv4(String deploymentApplianceIpv4) {
        this.deploymentApplianceIpv4 = deploymentApplianceIpv4;
    }

    /**
     * @return the deploymentType
     */
    public String getDeploymentType() {
        return deploymentType;
    }

    /**
     * @param deploymentType
     *            the deploymentType to set
     */
    public void setDeploymentType(String deploymentType) {
        this.deploymentType = deploymentType;
    }

    /**
     * @return the nativePlanUri
     */
    public String getNativePlanUri() {
        return nativePlanUri;
    }

    /**
     * @param nativePlanUri
     *            the nativePlanUri to set
     */
    public void setNativePlanUri(String nativePlanUri) {
        this.nativePlanUri = nativePlanUri;
    }

    /**
     * @return the osType
     */
    public String getOsType() {
        return osType;
    }

    /**
     * @param osType
     *            the osType to set
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }

    /**
     * @return the osdpSize
     */
    public String getOsdpSize() {
        return osdpSize;
    }

    /**
     * @param osdpSize
     *            the osdpSize to set
     */
    public void setOsdpSize(String osdpSize) {
        this.osdpSize = osdpSize;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
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
