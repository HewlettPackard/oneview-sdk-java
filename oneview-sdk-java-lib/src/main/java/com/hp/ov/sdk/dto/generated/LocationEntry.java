/*
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
 */

package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LocationEntry implements Serializable {

    public enum Type {
        Port, Bay, Enclosure, Ip, Password, UserId, StackingDomainId, StackingMemberId
    }

    private Integer relativeValue;
    private String value;
    private LocationEntry.Type type;

    public LocationEntry() { }

    public LocationEntry(Integer relativeValue, String value, Type type) {
        this.relativeValue = relativeValue;
        this.value = value;
        this.type = type;
    }

    public Integer getRelativeValue() {
        return relativeValue;
    }

    public void setRelativeValue(final Integer relativeValue) {
        this.relativeValue = relativeValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public LocationEntry.Type getType() {
        return type;
    }

    public void setType(final LocationEntry.Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        LocationEntry that = (LocationEntry) obj;

        return new EqualsBuilder()
                .append(relativeValue, that.relativeValue)
                .append(value, that.value)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(relativeValue)
                .append(value)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("relativeValue", relativeValue)
                .append("value", value)
                .append("type", type)
                .toString();
    }

}