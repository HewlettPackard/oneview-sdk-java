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

    private List<String> canAdded = new ArrayList<>();
    private List<String> canRemoved = new ArrayList<>();
    private boolean contentChanged;
    private List<String> defaultValueChanged = new ArrayList<>();

    /**
     * @return the canAdded
     */
    public List<String> getCanAdded() {
        return canAdded;
    }

    /**
     * @param canAdded the canAdded to set
     */
    public void setCanAdded(List<String> canAdded) {
        this.canAdded = canAdded;
    }

    /**
     * @return the canRemoved
     */
    public List<String> getCanRemoved() {
        return canRemoved;
    }

    /**
     * @param canRemoved the canRemoved to set
     */
    public void setCanRemoved(List<String> canRemoved) {
        this.canRemoved = canRemoved;
    }

    /**
     * @return the contentChanged
     */
    public boolean isContentChanged() {
        return contentChanged;
    }

    /**
     * @param contentChanged the contentChanged to set
     */
    public void setContentChanged(boolean contentChanged) {
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
