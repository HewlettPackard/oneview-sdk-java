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

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Since;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "permittedInterconnectTypeUri",
    "logicalLocation",
    "enclosureIndex",
    "logicalDownlinkUri"
    })
public class InterconnectMapEntryTemplate implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("permittedInterconnectTypeUri")
    private String permittedInterconnectTypeUri;
    @JsonProperty("logicalLocation")
    private LogicalLocation logicalLocation;
    @Since(200)
    @JsonProperty("enclosureIndex")
    private int enclosureIndex;
    @JsonProperty("logicalDownlinkUri")
    private String logicalDownlinkUri;

    /**
     *
     * @return The permittedInterconnectTypeUri
     */
    @JsonProperty("permittedInterconnectTypeUri")
    public String getPermittedInterconnectTypeUri() {
        return permittedInterconnectTypeUri;
    }

    /**
     *
     * @param permittedInterconnectTypeUri
     *            The permittedInterconnectTypeUri
     */
    @JsonProperty("permittedInterconnectTypeUri")
    public void setPermittedInterconnectTypeUri(final String permittedInterconnectTypeUri) {
        this.permittedInterconnectTypeUri = permittedInterconnectTypeUri;
    }

    /**
     *
     * @return The logicalLocation
     */
    @JsonProperty("logicalLocation")
    public LogicalLocation getLogicalLocation() {
        return logicalLocation;
    }

    /**
     *
     * @param logicalLocation
     *            The logicalLocation
     */
    @JsonProperty("logicalLocation")
    public void setLogicalLocation(final LogicalLocation logicalLocation) {
        this.logicalLocation = logicalLocation;
    }

    /**
     *
     * @return The enclosureIndex
     */
    @JsonProperty("enclosureIndex")
    public int getEnclosureIndex() {
        return enclosureIndex;
    }

    /**
     *
     * @param enclosureIndex
     *            The enclosureIndex
     */
    @JsonProperty("enclosureIndex")
    public void setEnclosureIndex(final int enclosureIndex) {
        this.enclosureIndex = enclosureIndex;
    }

    /**
     *
     * @return The logicalDownlinkUri
     */
    @JsonProperty("logicalDownlinkUri")
    public String getLogicalDownlinkUri() {
        return logicalDownlinkUri;
    }

    /**
     *
     * @param logicalDownlinkUri
     *            The logicalDownlinkUri
     */
    @JsonProperty("logicalDownlinkUri")
    public void setLogicalDownlinkUri(final String logicalDownlinkUri) {
        this.logicalDownlinkUri = logicalDownlinkUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(permittedInterconnectTypeUri)
                .append(logicalLocation)
                .append(enclosureIndex)
                .append(logicalDownlinkUri)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InterconnectMapEntryTemplate) == false) {
            return false;
        }
        final InterconnectMapEntryTemplate rhs = ((InterconnectMapEntryTemplate) other);
        return new EqualsBuilder()
                .append(permittedInterconnectTypeUri, rhs.permittedInterconnectTypeUri)
                .append(logicalLocation, rhs.logicalLocation)
                .append(enclosureIndex, rhs.enclosureIndex)
                .append(logicalDownlinkUri, rhs.logicalDownlinkUri)
                .isEquals();
    }

}
