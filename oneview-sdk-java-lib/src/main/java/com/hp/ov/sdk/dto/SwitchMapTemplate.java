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

package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SwitchMapTemplate implements Serializable {

    private List<SwitchMapEntryTemplate> switchMapEntryTemplates;

    public List<SwitchMapEntryTemplate> getSwitchMapEntryTemplates() {
        return switchMapEntryTemplates;
    }

    public void setSwitchMapEntryTemplates(List<SwitchMapEntryTemplate> switchMapEntryTemplates) {
        this.switchMapEntryTemplates = switchMapEntryTemplates;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SwitchMapTemplate that = (SwitchMapTemplate) obj;

        return new EqualsBuilder()
                .append(switchMapEntryTemplates, that.switchMapEntryTemplates)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(switchMapEntryTemplates)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("switchMapEntryTemplates", switchMapEntryTemplates)
                .toString();
    }
}
