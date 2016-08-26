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

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.networking.LogicalLocation;

public class SasInterconnectMapEntryTemplate implements Serializable {

    private static final long serialVersionUID = -7235768479340040286L;

    private Integer enclosureIndex;
    private LogicalLocation logicalLocation;
    private String permittedInterconnectTypeUri;

    /**
     * @return the enclosureIndex
     */
    public Integer getEnclosureIndex() {
        return enclosureIndex;
    }

    /**
     * @param enclosureIndex the enclosureIndex to set
     */
    public void setEnclosureIndex(Integer enclosureIndex) {
        this.enclosureIndex = enclosureIndex;
    }

    /**
     * @return the logicalLocation
     */
    public LogicalLocation getLogicalLocation() {
        return logicalLocation;
    }

    /**
     * @param logicalLocation the logicalLocation to set
     */
    public void setLogicalLocation(LogicalLocation logicalLocation) {
        this.logicalLocation = logicalLocation;
    }

    /**
     * @return the permittedInterconnectTypeUri
     */
    public String getPermittedInterconnectTypeUri() {
        return permittedInterconnectTypeUri;
    }

    /**
     * @param permittedInterconnectTypeUri the permittedInterconnectTypeUri to set
     */
    public void setPermittedInterconnectTypeUri(String permittedInterconnectTypeUri) {
        this.permittedInterconnectTypeUri = permittedInterconnectTypeUri;
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
