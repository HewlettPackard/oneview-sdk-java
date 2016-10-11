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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AvailableNetworks implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Object> ethernetNetworks = new ArrayList<Object>();
    private List<Object> networkSets = new ArrayList<Object>();
    private List<Object> fcNetworks = new ArrayList<Object>();
    private String type;

    /**
     *
     * @return The ethernetNetworks
     */
    public List<Object> getEthernetNetworks() {
        return ethernetNetworks;
    }

    /**
     *
     * @param ethernetNetworks
     *            The ethernetNetworks
     */
    public void setEthernetNetworks(final List<Object> ethernetNetworks) {
        this.ethernetNetworks = ethernetNetworks;
    }

    /**
     *
     * @return The networkSets
     */
    public List<Object> getNetworkSets() {
        return networkSets;
    }

    /**
     *
     * @param networkSets
     *            The networkSets
     */
    public void setNetworkSets(final List<Object> networkSets) {
        this.networkSets = networkSets;
    }

    /**
     *
     * @return The fcNetworks
     */
    public List<Object> getFcNetworks() {
        return fcNetworks;
    }

    /**
     *
     * @param fcNetworks
     *            The fcNetworks
     */
    public void setFcNetworks(final List<Object> fcNetworks) {
        this.fcNetworks = fcNetworks;
    }

    /**
     *
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *            The type
     */
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(ethernetNetworks).append(networkSets).append(fcNetworks).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AvailableNetworks) == false) {
            return false;
        }
        final AvailableNetworks rhs = ((AvailableNetworks) other);
        return new EqualsBuilder().append(type, rhs.type).append(ethernetNetworks, rhs.ethernetNetworks)
                .append(networkSets, rhs.networkSets).append(fcNetworks, rhs.fcNetworks).isEquals();
    }

}
