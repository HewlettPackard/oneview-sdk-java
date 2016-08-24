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

package com.hp.ov.sdk.dto.networking.saslogicalinterconnect;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.networking.Compliance;
import com.hp.ov.sdk.dto.networking.EnclosureType;

public class SasLogicalInterconnect extends BaseModelResource {

    private static final long serialVersionUID = 6234729451675656198L;

    private Compliance consistencyStatus;
    private List<String> driveEnclosureUris = new ArrayList<>();
    private EnclosureType enclosureType;
    private List<String> enclosureUris = new ArrayList<>();
    private Integer interconnectBaySet;
    private SasInterconnectMap interconnectMap;
    private RefreshState refreshState;
    private List<String> sasInterconnectUris = new ArrayList<>();
    private String sasLogicalInterconnectGroupUri;
    private String stateReason;

    /**
     * @return the consistencyStatus
     */
    public Compliance getConsistencyStatus() {
        return consistencyStatus;
    }

    /**
     * @param consistencyStatus the consistencyStatus to set
     */
    public void setConsistencyStatus(Compliance consistencyStatus) {
        this.consistencyStatus = consistencyStatus;
    }

    /**
     * @return the driveEnclosureUris
     */
    public List<String> getDriveEnclosureUris() {
        return driveEnclosureUris;
    }

    /**
     * @param driveEnclosureUris the driveEnclosureUris to set
     */
    public void setDriveEnclosureUris(List<String> driveEnclosureUris) {
        this.driveEnclosureUris = driveEnclosureUris;
    }

    /**
     * @return the enclosureType
     */
    public EnclosureType getEnclosureType() {
        return enclosureType;
    }

    /**
     * @param enclosureType the enclosureType to set
     */
    public void setEnclosureType(EnclosureType enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * @return the enclosureUris
     */
    public List<String> getEnclosureUris() {
        return enclosureUris;
    }

    /**
     * @param enclosureUris the enclosureUris to set
     */
    public void setEnclosureUris(List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }

    /**
     * @return the interconnectBaySet
     */
    public Integer getInterconnectBaySet() {
        return interconnectBaySet;
    }

    /**
     * @param interconnectBaySet the interconnectBaySet to set
     */
    public void setInterconnectBaySet(Integer interconnectBaySet) {
        this.interconnectBaySet = interconnectBaySet;
    }

    /**
     * @return the interconnectMap
     */
    public SasInterconnectMap getInterconnectMap() {
        return interconnectMap;
    }

    /**
     * @param interconnectMap the interconnectMap to set
     */
    public void setInterconnectMap(SasInterconnectMap interconnectMap) {
        this.interconnectMap = interconnectMap;
    }

    /**
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * @return the sasInterconnectUris
     */
    public List<String> getSasInterconnectUris() {
        return sasInterconnectUris;
    }

    /**
     * @param sasInterconnectUris the sasInterconnectUris to set
     */
    public void setSasInterconnectUris(List<String> sasInterconnectUris) {
        this.sasInterconnectUris = sasInterconnectUris;
    }

    /**
     * @return the sasLogicalInterconnectGroupUri
     */
    public String getSasLogicalInterconnectGroupUri() {
        return sasLogicalInterconnectGroupUri;
    }

    /**
     * @param sasLogicalInterconnectGroupUri the sasLogicalInterconnectGroupUri to set
     */
    public void setSasLogicalInterconnectGroupUri(String sasLogicalInterconnectGroupUri) {
        this.sasLogicalInterconnectGroupUri = sasLogicalInterconnectGroupUri;
    }

    /**
     * @return the stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * @param stateReason the stateReason to set
     */
    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
