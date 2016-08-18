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

package com.hp.ov.sdk.dto.networking.sasinterconnect;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.PortInfo;
import com.hp.ov.sdk.dto.networking.EnclosureType;

public class SasInterconnectType extends BaseModelResource {

    private static final long serialVersionUID = -8003701961701096489L;

    private EnclosureType enclosureType;
    private Integer externalPortCount;
    private Integer internalPortCount;
    private String model;
    private String partNumber;
    private List<PortInfo> portInfos = new ArrayList<>();

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
     * @return the externalPortCount
     */
    public Integer getExternalPortCount() {
        return externalPortCount;
    }

    /**
     * @param externalPortCount the externalPortCount to set
     */
    public void setExternalPortCount(Integer externalPortCount) {
        this.externalPortCount = externalPortCount;
    }

    /**
     * @return the internalPortCount
     */
    public Integer getInternalPortCount() {
        return internalPortCount;
    }

    /**
     * @param internalPortCount the internalPortCount to set
     */
    public void setInternalPortCount(Integer internalPortCount) {
        this.internalPortCount = internalPortCount;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the portInfos
     */
    public List<PortInfo> getPortInfos() {
        return portInfos;
    }

    /**
     * @param portInfos the portInfos to set
     */
    public void setPortInfos(List<PortInfo> portInfos) {
        this.portInfos = portInfos;
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
