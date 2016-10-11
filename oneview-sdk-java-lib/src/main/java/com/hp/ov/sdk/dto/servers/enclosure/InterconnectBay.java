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
package com.hp.ov.sdk.dto.servers.enclosure;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class InterconnectBay implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    private Integer bayNumber;
    private String interconnectUri;
    @Since(200)
    private Ipv4Setting ipv4Setting;
    private String logicalInterconnectUri;
    @Since(200)
    private String serialNumber;

    /**
     *
     * @return The bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     *
     * @param bayNumber
     *            The bayNumber
     */
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     *
     * @return The interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     *
     * @param interconnectUri
     *            The interconnectUri
     */
    public void setInterconnectUri(final String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     *
     * @return The ipv4Setting
     */
    public Ipv4Setting getIpv4Setting() {
        return ipv4Setting;
    }

    /**
     *
     * @param ipv4Setting
     *            The ipv4Setting
     */
    public void setIpv4Setting(final Ipv4Setting ipv4Setting) {
        this.ipv4Setting = ipv4Setting;
    }

    /**
     *
     * @return The logicalInterconnectUri
     */
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }

    /**
     *
     * @param logicalInterconnectUri
     *            The logicalInterconnectUri
     */
    public void setLogicalInterconnectUri(final String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    /**
     *
     * @return The serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     *            The serialNumber
     */
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
