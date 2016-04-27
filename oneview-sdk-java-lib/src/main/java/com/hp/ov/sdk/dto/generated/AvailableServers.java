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

public class AvailableServers implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private String serverHardwareUri;
    private String enclosureGroupUri;
    private String serverHardwareTypeUri;
    private String powerState;
    private String name;

    /**
     *
     * @return The serverHardwareUri
     */
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }

    /**
     *
     * @param serverHardwareUri
     *            The serverHardwareUri
     */
    public void setServerHardwareUri(final String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     *
     * @return The enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     *
     * @param enclosureGroupUri
     *            The enclosureGroupUri
     */
    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     *
     * @return The serverHardwareTypeUri
     */
    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }

    /**
     *
     * @param serverHardwareTypeUri
     *            The serverHardwareTypeUri
     */
    public void setServerHardwareTypeUri(final String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     *
     * @return The powerState
     */
    public String getPowerState() {
        return powerState;
    }

    /**
     *
     * @param powerState
     *            The powerState
     */
    public void setPowerState(final String powerState) {
        this.powerState = powerState;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(serverHardwareUri).append(enclosureGroupUri).append(serverHardwareTypeUri)
                .append(powerState).append(name).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AvailableServers) == false) {
            return false;
        }
        final AvailableServers rhs = ((AvailableServers) other);
        return new EqualsBuilder().append(serverHardwareUri, rhs.serverHardwareUri)
                .append(enclosureGroupUri, rhs.enclosureGroupUri).append(serverHardwareTypeUri, rhs.serverHardwareTypeUri)
                .append(powerState, rhs.powerState).append(name, rhs.name).isEquals();
    }

}
