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

package com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.EnclosureType;

public class SasLogicalInterconnectGroup extends BaseModelResource {

    private static final long serialVersionUID = -2305984601910316020L;

    private List<Integer> enclosureIndexes = new ArrayList<>();
    private EnclosureType enclosureType;
    private Integer interconnectBaySet;
    private SasInterconnectMapTemplate interconnectMapTemplate;

    /**
     * @return the enclosureIndexes
     */
    public List<Integer> getEnclosureIndexes() {
        return enclosureIndexes;
    }

    /**
     * @param enclosureIndexes the enclosureIndexes to set
     */
    public void setEnclosureIndexes(List<Integer> enclosureIndexes) {
        this.enclosureIndexes = enclosureIndexes;
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
     * @return the interconnectMapTemplate
     */
    public SasInterconnectMapTemplate getInterconnectMapTemplate() {
        return interconnectMapTemplate;
    }

    /**
     * @param interconnectMapTemplate the interconnectMapTemplate to set
     */
    public void setInterconnectMapTemplate(SasInterconnectMapTemplate interconnectMapTemplate) {
        this.interconnectMapTemplate = interconnectMapTemplate;
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
