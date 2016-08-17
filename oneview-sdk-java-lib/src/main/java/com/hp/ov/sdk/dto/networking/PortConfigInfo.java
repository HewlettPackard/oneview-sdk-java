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
package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.Location;


public class PortConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private OpSpeed desiredSpeed;
    @Since(300)
    private NeighborConfigInfo expectedNeighbor;
    private Location location;
    private String portUri;

    /**
     * @return the desiredSpeed
     */
    public OpSpeed getDesiredSpeed() {
        return desiredSpeed;
    }

    /**
     * @param desiredSpeed the desiredSpeed to set
     */
    public void setDesiredSpeed(OpSpeed desiredSpeed) {
        this.desiredSpeed = desiredSpeed;
    }

    /**
     * @return the expectedNeighbor
     */
    public NeighborConfigInfo getExpectedNeighbor() {
        return expectedNeighbor;
    }

    /**
     * @param expectedNeighbor the expectedNeighbor to set
     */
    public void setExpectedNeighbor(NeighborConfigInfo expectedNeighbor) {
        this.expectedNeighbor = expectedNeighbor;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the portUri
     */
    public String getPortUri() {
        return portUri;
    }

    /**
     * @param portUri the portUri to set
     */
    public void setPortUri(String portUri) {
        this.portUri = portUri;
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
