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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "ethernetNetworks", "networkSets", "fcNetworks", "type" })
public class AvailableNetworks implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("ethernetNetworks")
    private List<Object> ethernetNetworks = new ArrayList<Object>();
    @JsonProperty("networkSets")
    private List<Object> networkSets = new ArrayList<Object>();
    @JsonProperty("fcNetworks")
    private List<Object> fcNetworks = new ArrayList<Object>();
    @JsonProperty("type")
    private String type;

    /**
     * 
     * @return The ethernetNetworks
     */
    @JsonProperty("ethernetNetworks")
    public List<Object> getEthernetNetworks() {
        return ethernetNetworks;
    }

    /**
     * 
     * @param ethernetNetworks
     *            The ethernetNetworks
     */
    @JsonProperty("ethernetNetworks")
    public void setEthernetNetworks(final List<Object> ethernetNetworks) {
        this.ethernetNetworks = ethernetNetworks;
    }

    /**
     * 
     * @return The networkSets
     */
    @JsonProperty("networkSets")
    public List<Object> getNetworkSets() {
        return networkSets;
    }

    /**
     * 
     * @param networkSets
     *            The networkSets
     */
    @JsonProperty("networkSets")
    public void setNetworkSets(final List<Object> networkSets) {
        this.networkSets = networkSets;
    }

    /**
     * 
     * @return The fcNetworks
     */
    @JsonProperty("fcNetworks")
    public List<Object> getFcNetworks() {
        return fcNetworks;
    }

    /**
     * 
     * @param fcNetworks
     *            The fcNetworks
     */
    @JsonProperty("fcNetworks")
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
