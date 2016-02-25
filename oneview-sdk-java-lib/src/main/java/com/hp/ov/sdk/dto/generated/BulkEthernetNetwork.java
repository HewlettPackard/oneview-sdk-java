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

import java.io.Serializable;

import com.hp.ov.sdk.dto.JsonRequest;


public class BulkEthernetNetwork implements Serializable {

    private static final long serialVersionUID = 1128808308263935003L;

    private Bandwidth bandwidth;
    private Boolean smartLink;
    private String namePrefix;
    private String vlanIdRange;
    private Boolean privateNetwork;
    private Network.Purpose purpose;
    private String type;

    private JsonRequest jsonRequest;

    public Bandwidth getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(final Bandwidth bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Boolean getSmartLink() {
        return smartLink;
    }

    public void setSmartLink(final Boolean smartLink) {
        this.smartLink = smartLink;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(final String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getVlanIdRange() {
        return vlanIdRange;
    }

    public void setVlanIdRange(final String vlanIdRange) {
        this.vlanIdRange = vlanIdRange;
    }

    public Boolean getPrivateNetwork() {
        return privateNetwork;
    }

    public void setPrivateNetwork(final Boolean privateNetwork) {
        this.privateNetwork = privateNetwork;
    }

    public Network.Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(final Network.Purpose purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public JsonRequest getJsonRequest() {
        return jsonRequest;
    }

    public void setJsonRequest(final JsonRequest jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

}
