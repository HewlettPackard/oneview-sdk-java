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

package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.LocationType;

public class LogicalLocationEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer relativeValue;
    private String value;
    private LocationType type;

    public LogicalLocationEntry() { }

    public LogicalLocationEntry(Integer relativeValue, String value, LocationType type) {
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

    public LocationType getType() {
        return type;
    }

    public void setType(final LocationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        LogicalLocationEntry that = (LogicalLocationEntry) obj;

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
