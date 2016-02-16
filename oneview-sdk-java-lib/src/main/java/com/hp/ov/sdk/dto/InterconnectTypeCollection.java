/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class InterconnectTypeCollection extends BaseCollectionResource<InterconnectType> {

    private final List<InterconnectType> members = new ArrayList<>();

    @Override
    public List<InterconnectType> getMembers() {
        return new ArrayList<>(this.members);
    }

    @Override
    public void setMembers(final List<InterconnectType> members) {
        this.members.clear();

        if (members != null ) {
            this.members.addAll(members);

            this.setCount(this.members.size());
        }
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public InterconnectType searchName(final String value) {
        for (InterconnectType member : this.members) {
            if (member.getName().equalsIgnoreCase(value)) {
                return member;
            }
        }
        return null;
    }

}
