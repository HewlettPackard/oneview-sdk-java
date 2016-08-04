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

public class SwitchMap implements Serializable {

    private static final long serialVersionUID = -7784352408192006990L;

    private List<SwitchMapEntry> switchMapEntries = new ArrayList<>();

    public List<SwitchMapEntry> getSwitchMapEntries() {
        return switchMapEntries;
    }

    public void setSwitchMapEntries(List<SwitchMapEntry> switchMapEntries) {
        this.switchMapEntries = switchMapEntries;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SwitchMap switchMap = (SwitchMap) obj;

        return new EqualsBuilder()
                .append(switchMapEntries, switchMap.switchMapEntries)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(switchMapEntries)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("switchMapEntries", switchMapEntries)
                .toString();
    }
}
