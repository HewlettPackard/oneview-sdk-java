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


public class ConnectionProperty implements Serializable {

    private static final long serialVersionUID = -3262431131387010966L;

    private String propertyName;
    private String value;
    private ValueFormat valueFormat;
    private ValueType valueType;

    public ConnectionProperty() { }

    public ConnectionProperty(String propertyName, String value, ValueFormat valueFormat, ValueType valueType) {
        this.propertyName = propertyName;
        this.value = value;
        this.valueFormat = valueFormat;
        this.valueType = valueType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        ConnectionProperty that = (ConnectionProperty) obj;

        return new EqualsBuilder()
                .append(propertyName, that.propertyName)
                .append(value, that.value)
                .append(valueFormat, that.valueFormat)
                .append(valueType, that.valueType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(propertyName)
                .append(value)
                .append(valueFormat)
                .append(valueType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("propertyName", propertyName)
                .append("value", value)
                .append("valueFormat", valueFormat)
                .append("valueType", valueType)
                .toString();
    }
}
