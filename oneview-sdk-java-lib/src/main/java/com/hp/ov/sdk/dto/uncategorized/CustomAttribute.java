/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.uncategorized;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomAttribute implements Serializable {

    private static final long serialVersionUID = 2392380825639965752L;

    private String caConstraints;
    private String description;
    private Boolean caEditable;
    private String caId;
    private String name;
    private String caType;
    private String value;

    /**
     * @return the caConstraints
     */
    public String getCaConstraints() {
        return caConstraints;
    }

    /**
     * @param caConstraints
     *            the caConstraints to set
     */
    public void setCaConstraints(String caConstraints) {
        this.caConstraints = caConstraints;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the caEditable
     */
    public Boolean getCaEditable() {
        return caEditable;
    }

    /**
     * @param caEditable
     *            the caEditable to set
     */
    public void setCaEditable(Boolean caEditable) {
        this.caEditable = caEditable;
    }

    /**
     * @return the caId
     */
    public String getCaId() {
        return caId;
    }

    /**
     * @param caId
     *            the caId to set
     */
    public void setCaId(String caId) {
        this.caId = caId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the caType
     */
    public String getCaType() {
        return caType;
    }

    /**
     * @param caType
     *            the caType to set
     */
    public void setCaType(String caType) {
        this.caType = caType;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
