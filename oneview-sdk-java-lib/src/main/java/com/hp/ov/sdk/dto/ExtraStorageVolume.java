/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto;


import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ExtraStorageVolume implements Serializable {

    private static final long serialVersionUID = -2422614527215315517L;

    private String extra;
    private String extraType;
    private String owner;
    private String ownerUri;
    private String resourceUri;

    public ExtraStorageVolume(String extra, String extraType,
            String owner, String ownerUri, String resourceUri) {

        this.extra = extra;
        this.extraType = extraType;
        this.owner = owner;
        this.ownerUri = ownerUri;
        this.resourceUri = resourceUri;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtraType() {
        return extraType;
    }

    public void setExtraType(String extraType) {
        this.extraType = extraType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerUri() {
        return ownerUri;
    }

    public void setOwnerUri(String ownerUri) {
        this.ownerUri = ownerUri;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ExtraStorageVolume that = (ExtraStorageVolume) o;

        return new EqualsBuilder()
                .append(extra, that.extra)
                .append(extraType, that.extraType)
                .append(owner, that.owner)
                .append(ownerUri, that.ownerUri)
                .append(resourceUri, that.resourceUri)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(extra)
                .append(extraType)
                .append(owner)
                .append(ownerUri)
                .append(resourceUri)
                .toHashCode();
    }
}
