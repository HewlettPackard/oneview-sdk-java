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
package com.hp.ov.sdk.dto.networking.ethernet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.BaseModelResource;

/**
 * The Network data transfer object (DTO) contains the information used to
 * represent a vlan in the system. It is passed in to the add/update network
 * REST api, as well as the add/update network through java client api.
 */
public class Network extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    public enum EthernetNetworkType {
        ImageStreamer, Tagged, Untagged, Tunnel, Unknown, NotApplicable
    }

    public enum Purpose {
        General, ISCSI, Management, VMMigration, FaultTolerance
    }

    private String connectionTemplateUri;
    private EthernetNetworkType ethernetNetworkType = EthernetNetworkType.Tagged;

    @Until(199)
    private Integer internalVlanId;

    @Since(200)
    private String fabricUri;

    private Boolean privateNetwork;
    private Purpose purpose;
    private Boolean smartLink;
    private Integer vlanId;
    private ConnectionTemplate connectionTemplate;

    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    @Since(300)
    private String subnetUri;

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
     * @return the ethernetNetworkType
     */
    public EthernetNetworkType getEthernetNetworkType() {
        return ethernetNetworkType;
    }

    /**
     * @param ethernetNetworkType the ethernetNetworkType to set
     */
    public void setEthernetNetworkType(EthernetNetworkType ethernetNetworkType) {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    /**
     * @return the internalVlanId
     */
    public Integer getInternalVlanId() {
        return internalVlanId;
    }

    /**
     * @param internalVlanId the internalVlanId to set
     */
    public void setInternalVlanId(Integer internalVlanId) {
        this.internalVlanId = internalVlanId;
    }

    /**
     * @return the fabricUri
     */
    public String getFabricUri() {
        return fabricUri;
    }

    /**
     * @param fabricUri the fabricUri to set
     */
    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    /**
     * @return the privateNetwork
     */
    public Boolean getPrivateNetwork() {
        return privateNetwork;
    }

    /**
     * @param privateNetwork the privateNetwork to set
     */
    public void setPrivateNetwork(Boolean privateNetwork) {
        this.privateNetwork = privateNetwork;
    }

    /**
     * @return the purpose
     */
    public Purpose getPurpose() {
        return purpose;
    }

    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    /**
     * @return the smartLink
     */
    public Boolean getSmartLink() {
        return smartLink;
    }

    /**
     * @param smartLink the smartLink to set
     */
    public void setSmartLink(Boolean smartLink) {
        this.smartLink = smartLink;
    }

    /**
     * @return the vlanId
     */
    public Integer getVlanId() {
        return vlanId;
    }

    /**
     * @param vlanId the vlanId to set
     */
    public void setVlanId(Integer vlanId) {
        this.vlanId = vlanId;
    }

    /**
     * @return the connectionTemplate
     */
    public ConnectionTemplate getConnectionTemplate() {
        return connectionTemplate;
    }

    /**
     * @param connectionTemplate the connectionTemplate to set
     */
    public void setConnectionTemplate(ConnectionTemplate connectionTemplate) {
        this.connectionTemplate = connectionTemplate;
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

    /**
     * @return the subnetUri
     */
    public String getSubnetUri() {
        return subnetUri;
    }

    /**
     * @param subnetUri the subnetUri to set
     */
    public void setSubnetUri(String subnetUri) {
        this.subnetUri = subnetUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(connectionTemplateUri)
                .append(ethernetNetworkType)
                .append(internalVlanId)
                .append(fabricUri)
                .append(privateNetwork)
                .append(purpose)
                .append(smartLink)
                .append(vlanId)
                .append(connectionTemplate)
                .append(scopeUris)
                .append(subnetUri)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Network);
    }

    @Override
    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Network) {
            Network rhs = ((Network) other);

            return rhs.canEqual(this) && new EqualsBuilder()
                    .append(connectionTemplateUri, rhs.connectionTemplateUri)
                    .append(ethernetNetworkType, rhs.ethernetNetworkType)
                    .append(internalVlanId, rhs.internalVlanId)
                    .append(fabricUri, rhs.fabricUri)
                    .append(privateNetwork, rhs.privateNetwork)
                    .append(purpose, rhs.purpose)
                    .append(smartLink, rhs.smartLink)
                    .append(vlanId, rhs.vlanId)
                    .append(connectionTemplate, rhs.connectionTemplate)
                    .append(scopeUris, rhs.scopeUris)
                    .append(subnetUri, rhs.subnetUri)
                    .appendSuper(super.equals(other))
                    .isEquals();

        }
        return false;
    }

}
