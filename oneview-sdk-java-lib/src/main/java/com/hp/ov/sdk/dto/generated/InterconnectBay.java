/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
    "bayNumber",
    "interconnectUri",
    "ipv4Setting",
    "logicalInterconnectUri",
    "serialNumber"})
public class InterconnectBay implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    @JsonProperty("bayNumber")
    private Integer bayNumber;
    @JsonProperty("interconnectUri")
    private String interconnectUri;
    @JsonProperty("ipv4Setting")
    @Since(200)
    private Ipv4Setting ipv4Setting;
    @JsonProperty("logicalInterconnectUri")
    private String logicalInterconnectUri;
    @Since(200)
    @JsonProperty("serialNumber")
    private String serialNumber;

    /**
     *
     * @return The bayNumber
     */
    @JsonProperty("bayNumber")
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     *
     * @param bayNumber
     *            The bayNumber
     */
    @JsonProperty("bayNumber")
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     *
     * @return The interconnectUri
     */
    @JsonProperty("interconnectUri")
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     *
     * @param interconnectUri
     *            The interconnectUri
     */
    @JsonProperty("interconnectUri")
    public void setInterconnectUri(final String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     *
     * @return The ipv4Setting
     */
    @JsonProperty("ipv4Setting")
    public Ipv4Setting getIpv4Setting() {
        return ipv4Setting;
    }

    /**
     *
     * @param ipv4Setting
     *            The ipv4Setting
     */
    @JsonProperty("ipv4Setting")
    public void setIpv4Setting(final Ipv4Setting ipv4Setting) {
        this.ipv4Setting = ipv4Setting;
    }

    /**
     *
     * @return The logicalInterconnectUri
     */
    @JsonProperty("logicalInterconnectUri")
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }

    /**
     *
     * @param logicalInterconnectUri
     *            The logicalInterconnectUri
     */
    @JsonProperty("logicalInterconnectUri")
    public void setLogicalInterconnectUri(final String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    /**
     *
     * @return The serialNumber
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     *            The serialNumber
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bayNumber)
                .append(interconnectUri)
                .append(ipv4Setting)
                .append(logicalInterconnectUri)
                .append(serialNumber)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InterconnectBay) == false) {
            return false;
        }
        final InterconnectBay rhs = ((InterconnectBay) other);
        return new EqualsBuilder()
                .append(bayNumber, rhs.bayNumber)
                .append(interconnectUri, rhs.interconnectUri)
                .append(ipv4Setting, rhs.ipv4Setting)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .append(serialNumber, rhs.serialNumber)
                .isEquals();
    }

}