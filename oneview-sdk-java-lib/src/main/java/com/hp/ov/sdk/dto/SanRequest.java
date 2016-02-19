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
package com.hp.ov.sdk.dto;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Since;

public class SanRequest {

    private String deviceManagerUri;

    @Since(200)
    private Boolean isExpectedFc;
    @Since(200)
    private Boolean isExpectedFcoe;

    private List<Property> publicAttributes = new ArrayList<>();
    private SanResponse.RefreshState refreshState;
    private SanPolicy sanPolicy;

    @Since(200)
    private Integer vLanId;

    public String getDeviceManagerUri() {
        return deviceManagerUri;
    }

    public void setDeviceManagerUri(String deviceManagerUri) {
        this.deviceManagerUri = deviceManagerUri;
    }

    public Boolean getExpectedFc() {
        return isExpectedFc;
    }

    public void setExpectedFc(Boolean expectedFc) {
        isExpectedFc = expectedFc;
    }

    public Boolean getExpectedFcoe() {
        return isExpectedFcoe;
    }

    public void setExpectedFcoe(Boolean expectedFcoe) {
        isExpectedFcoe = expectedFcoe;
    }

    public List<Property> getPublicAttributes() {
        return publicAttributes;
    }

    public void setPublicAttributes(List<Property> publicAttributes) {
        this.publicAttributes = publicAttributes;
    }

    public SanResponse.RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(SanResponse.RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public SanPolicy getSanPolicy() {
        return sanPolicy;
    }

    public void setSanPolicy(SanPolicy sanPolicy) {
        this.sanPolicy = sanPolicy;
    }

    public Integer getvLanId() {
        return vLanId;
    }

    public void setvLanId(Integer vLanId) {
        this.vLanId = vLanId;
    }
}
