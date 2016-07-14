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

package com.hp.ov.sdk.dto.rack;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RackMount {

    private RackMountLocation location;
    private String mountUri;
    private Integer relativeOrder;
    private Integer topUSlot;
    private Integer uHeight;

    public RackMountLocation getLocation() {
        return location;
    }

    public void setLocation(RackMountLocation location) {
        this.location = location;
    }

    public String getMountUri() {
        return mountUri;
    }

    public void setMountUri(String mountUri) {
        this.mountUri = mountUri;
    }

    public Integer getRelativeOrder() {
        return relativeOrder;
    }

    public void setRelativeOrder(Integer relativeOrder) {
        this.relativeOrder = relativeOrder;
    }

    public Integer getTopUSlot() {
        return topUSlot;
    }

    public void setTopUSlot(Integer topUSlot) {
        this.topUSlot = topUSlot;
    }

    public Integer getuHeight() {
        return uHeight;
    }

    public void setuHeight(Integer uHeight) {
        this.uHeight = uHeight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof RackMount) {
            RackMount that = (RackMount) obj;

            return new EqualsBuilder()
                    .append(location, that.location)
                    .append(mountUri, that.mountUri)
                    .append(relativeOrder, that.relativeOrder)
                    .append(topUSlot, that.topUSlot)
                    .append(uHeight, that.uHeight)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(location)
                .append(mountUri)
                .append(relativeOrder)
                .append(topUSlot)
                .append(uHeight)
                .toHashCode();
    }
}
