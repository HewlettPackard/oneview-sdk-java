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

package com.hp.ov.sdk.dto.networking.logicalswitches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class StackingGroup implements Serializable {

    private static final long serialVersionUID = 1772958185022103403L;

    private List<StackingPortPair> switchStackingElements = new ArrayList<>();

    public List<StackingPortPair> getSwitchStackingElements() {
        return switchStackingElements;
    }

    public void setSwitchStackingElements(List<StackingPortPair> switchStackingElements) {
        this.switchStackingElements = switchStackingElements;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        StackingGroup that = (StackingGroup) obj;

        return new EqualsBuilder()
                .append(switchStackingElements, that.switchStackingElements)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(switchStackingElements)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("switchStackingElements", switchStackingElements)
                .toString();
    }
}
