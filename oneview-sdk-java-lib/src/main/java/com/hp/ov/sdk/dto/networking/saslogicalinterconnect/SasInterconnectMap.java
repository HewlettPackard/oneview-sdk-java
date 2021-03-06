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

package com.hp.ov.sdk.dto.networking.saslogicalinterconnect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SasInterconnectMap implements Serializable {

    private static final long serialVersionUID = 242042550933554368L;

    private List<SasInterconnectMapEntry> interconnectMapEntries = new ArrayList<>();

    /**
     * @return the interconnectMapEntries
     */
    public List<SasInterconnectMapEntry> getInterconnectMapEntries() {
        return interconnectMapEntries;
    }

    /**
     * @param interconnectMapEntries the interconnectMapEntries to set
     */
    public void setInterconnectMapEntries(List<SasInterconnectMapEntry> interconnectMapEntries) {
        this.interconnectMapEntries = interconnectMapEntries;
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
