/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.JsonRequest;

/**
 * The Network data transfer object (DTO) contains the information used to
 * represent a vlan in the system. It is passed in to the add/update network
 * REST api, as well as the add/update network through java client api.
 */
public class Network extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    public enum EthernetNetworkType {
        Tagged, Untagged, Tunnel, Unknown, NotApplicable
    }

    public enum Purpose {
        General, Management, VMMigration, FaultTolerance
    }

    private String connectionTemplateUri;
    private EthernetNetworkType ethernetNetworkType = EthernetNetworkType.Tagged;
    private Integer internalVlanId;

    @Since(200)
    private String fabricUri;

    private Boolean privateNetwork;
    private Purpose purpose;
    private Boolean smartLink;
    private Integer vlanId;
    private ConnectionTemplate connectionTemplate;

    private transient JsonRequest jsonRequest;

    public String getFabricUri() {
        return fabricUri;
    }

    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    public Integer getVlanId() {
        return vlanId;
    }

    public void setVlanId(final Integer vlanId) {
        this.vlanId = vlanId;
    }

    public Integer getInternalVlanId() {
        return internalVlanId;
    }

    public void setInternalVlanId(final Integer internalVlanId) {
        this.internalVlanId = internalVlanId;
    }

    public Boolean getSmartLink() {
        return smartLink;
    }

    public void setSmartLink(final Boolean smartLink) {
        this.smartLink = smartLink;
    }

    public Boolean getPrivateNetwork() {
        return privateNetwork;
    }

    public void setPrivateNetwork(final Boolean privateNetwork) {
        this.privateNetwork = privateNetwork;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(final Purpose purpose) {
        this.purpose = purpose;
    }

    public String getConnectionTemplateUri() {
        return connectionTemplateUri;
    }

    public void setConnectionTemplateUri(final String connectionTemplateUri) {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    public EthernetNetworkType getEthernetNetworkType() {
        return ethernetNetworkType;
    }

    public void setEthernetNetworkType(final EthernetNetworkType ethernetNetworkType) {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    public ConnectionTemplate getConnectionTemplate() {
        return connectionTemplate;
    }

    public void setConnectionTemplate(final ConnectionTemplate connectionTemplate) {
        this.connectionTemplate = connectionTemplate;
    }

    public JsonRequest getJsonRequest() {
        return jsonRequest;
    }

    public void setJsonRequest(final JsonRequest jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

}
