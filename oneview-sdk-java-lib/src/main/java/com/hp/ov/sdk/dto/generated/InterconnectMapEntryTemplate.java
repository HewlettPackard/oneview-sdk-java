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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.networking.LogicalLocation;

public class InterconnectMapEntryTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String permittedInterconnectTypeUri;
    private LogicalLocation logicalLocation;
    @Since(200)
    private int enclosureIndex;
    private String logicalDownlinkUri;

    /**
     *
     * @return The permittedInterconnectTypeUri
     */
    public String getPermittedInterconnectTypeUri() {
        return permittedInterconnectTypeUri;
    }

    /**
     *
     * @param permittedInterconnectTypeUri
     *            The permittedInterconnectTypeUri
     */
    public void setPermittedInterconnectTypeUri(final String permittedInterconnectTypeUri) {
        this.permittedInterconnectTypeUri = permittedInterconnectTypeUri;
    }

    /**
     *
     * @return The logicalLocation
     */
    public LogicalLocation getLogicalLocation() {
        return logicalLocation;
    }

    /**
     *
     * @param logicalLocation
     *            The logicalLocation
     */
    public void setLogicalLocation(final LogicalLocation logicalLocation) {
        this.logicalLocation = logicalLocation;
    }

    /**
     *
     * @return The enclosureIndex
     */
    public int getEnclosureIndex() {
        return enclosureIndex;
    }

    /**
     *
     * @param enclosureIndex
     *            The enclosureIndex
     */
    public void setEnclosureIndex(final int enclosureIndex) {
        this.enclosureIndex = enclosureIndex;
    }

    /**
     *
     * @return The logicalDownlinkUri
     */
    public String getLogicalDownlinkUri() {
        return logicalDownlinkUri;
    }

    /**
     *
     * @param logicalDownlinkUri
     *            The logicalDownlinkUri
     */
    public void setLogicalDownlinkUri(final String logicalDownlinkUri) {
        this.logicalDownlinkUri = logicalDownlinkUri;
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
