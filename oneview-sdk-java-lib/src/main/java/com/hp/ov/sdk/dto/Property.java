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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Property implements Serializable {

    private static final long serialVersionUID = 3023061337087172134L;

    public enum ValueType {
        Integer, String, Float, Double, Boolean, Unknown
    }

    public enum ValueFormat{
        IPAddressOrHostname, None, SecuritySensitive
    }

    private String displayName;
    private Boolean required;
    private String value;
    private ValueFormat valueFormat;
    private ValueType valueType;
    private String name;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(final Boolean required) {
        this.required = required;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public ValueFormat getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(ValueFormat valueFormat) {
        this.valueFormat = valueFormat;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Property) {
            Property that = (Property) obj;

            return new EqualsBuilder()
                    .append(displayName, that.displayName)
                    .append(name, that.name)
                    .append(required, that.required)
                    .append(value, that.value)
                    .append(valueFormat, that.valueFormat)
                    .append(valueType, that.valueType)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(displayName)
                .append(name)
                .append(required)
                .append(value)
                .append(valueFormat)
                .append(valueType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("displayName", displayName)
                .append("name", name)
                .append("required", required)
                .append("value", value)
                .append("valueFormat", valueFormat)
                .append("valueType", valueType)
                .toString();
    }

}
