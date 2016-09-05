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

package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FirmwareComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String componentKey;
    private String componentLocation;
    private String componentName;
    private String componentVersion;
    
    /**
     * @return the componentKey
     */
    public String getComponentKey() {
        return componentKey;
    }

    /**
     * @param componentKey the componentKey to set
     */
    public void setComponentKey(String componentKey) {
        this.componentKey = componentKey;
    }

    /**
     * @return the componentLocation
     */
    public String getComponentLocation() {
        return componentLocation;
    }

    /**
     * @param componentLocation the componentLocation to set
     */
    public void setComponentLocation(String componentLocation) {
        this.componentLocation = componentLocation;
    }

    /**
     * @return the componentName
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * @param componentName the componentName to set
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * @return the componentVersion
     */
    public String getComponentVersion() {
        return componentVersion;
    }

    /**
     * @param componentVersion the componentVersion to set
     */
    public void setComponentVersion(String componentVersion) {
        this.componentVersion = componentVersion;
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
