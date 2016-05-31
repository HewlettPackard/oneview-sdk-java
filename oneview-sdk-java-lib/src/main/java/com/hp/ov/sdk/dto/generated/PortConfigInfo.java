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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.OpSpeed;


public class PortConfigInfo implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private OpSpeed desiredSpeed;
    private String portUri;
    private Location location;

    /**
     *
     * @return The desiredSpeed
     */
    public OpSpeed getDesiredSpeed() {
        return desiredSpeed;
    }

    /**
     *
     * @param desiredSpeed
     *            The desiredSpeed
     */
    public void setDesiredSpeed(final OpSpeed desiredSpeed) {
        this.desiredSpeed = desiredSpeed;
    }

    /**
     *
     * @return The portUri
     */
    public String getPortUri() {
        return portUri;
    }

    /**
     *
     * @param portUri
     *            The portUri
     */
    public void setPortUri(final String portUri) {
        this.portUri = portUri;
    }

    /**
     *
     * @return The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     *            The location
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(desiredSpeed).append(portUri).append(location).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortConfigInfo) == false) {
            return false;
        }
        final PortConfigInfo rhs = ((PortConfigInfo) other);
        return new EqualsBuilder().append(desiredSpeed, rhs.desiredSpeed).append(portUri, rhs.portUri)
                .append(location, rhs.location).isEquals();
    }

}
