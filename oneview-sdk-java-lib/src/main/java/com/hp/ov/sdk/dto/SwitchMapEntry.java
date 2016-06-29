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

package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.networking.Location;

public class SwitchMapEntry implements Serializable {

    private static final long serialVersionUID = -5731539765828491361L;

    private Location location;
    private String permittedSwitchTypeUri;
    private String switchUri;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPermittedSwitchTypeUri() {
        return permittedSwitchTypeUri;
    }

    public void setPermittedSwitchTypeUri(String permittedSwitchTypeUri) {
        this.permittedSwitchTypeUri = permittedSwitchTypeUri;
    }

    public String getSwitchUri() {
        return switchUri;
    }

    public void setSwitchUri(String switchUri) {
        this.switchUri = switchUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SwitchMapEntry that = (SwitchMapEntry) obj;

        return new EqualsBuilder()
                .append(location, that.location)
                .append(permittedSwitchTypeUri, that.permittedSwitchTypeUri)
                .append(switchUri, that.switchUri)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(location)
                .append(permittedSwitchTypeUri)
                .append(switchUri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("location", location)
                .append("permittedSwitchTypeUri", permittedSwitchTypeUri)
                .append("switchUri", switchUri)
                .toString();
    }
}
