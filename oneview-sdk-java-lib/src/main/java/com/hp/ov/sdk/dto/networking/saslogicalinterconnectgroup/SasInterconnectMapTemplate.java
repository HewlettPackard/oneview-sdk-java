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

package com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SasInterconnectMapTemplate implements Serializable {

    private static final long serialVersionUID = 2725286044409006736L;

    private List<SasInterconnectMapEntryTemplate> interconnectMapEntryTemplates = new ArrayList<>();

    /**
     * @return the interconnectMapEntryTemplates
     */
    public List<SasInterconnectMapEntryTemplate> getInterconnectMapEntryTemplates() {
        return interconnectMapEntryTemplates;
    }

    /**
     * @param interconnectMapEntryTemplates the interconnectMapEntryTemplates to set
     */
    public void setInterconnectMapEntryTemplates(List<SasInterconnectMapEntryTemplate> interconnectMapEntryTemplates) {
        this.interconnectMapEntryTemplates = interconnectMapEntryTemplates;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
