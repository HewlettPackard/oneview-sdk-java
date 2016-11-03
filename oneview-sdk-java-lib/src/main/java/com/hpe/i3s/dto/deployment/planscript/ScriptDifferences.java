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
package com.hpe.i3s.dto.deployment.planscript;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ScriptDifferences implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> caAdded = new ArrayList<>();
    private List<String> caRemoved = new ArrayList<>();
    private Boolean contentChanged;
    private List<String> defaultValueChanged = new ArrayList<>();

    /**
     * @return the caAdded
     */
    public List<String> getCaAdded() {
        return caAdded;
    }

    /**
     * @param caAdded the caAdded to set
     */
    public void setCaAdded(List<String> caAdded) {
        this.caAdded = caAdded;
    }

    /**
     * @return the caRemoved
     */
    public List<String> getCaRemoved() {
        return caRemoved;
    }

    /**
     * @param caRemoved the caRemoved to set
     */
    public void setCaRemoved(List<String> caRemoved) {
        this.caRemoved = caRemoved;
    }

    /**
     * @return the contentChanged
     */
    public Boolean isContentChanged() {
        return contentChanged;
    }

    /**
     * @param contentChanged the contentChanged to set
     */
    public void setContentChanged(Boolean contentChanged) {
        this.contentChanged = contentChanged;
    }

    /**
     * @return the defaultValueChanged
     */
    public List<String> getDefaultValueChanged() {
        return defaultValueChanged;
    }

    /**
     * @param defaultValueChanged the defaultValueChanged to set
     */
    public void setDefaultValueChanged(List<String> defaultValueChanged) {
        this.defaultValueChanged = defaultValueChanged;
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
