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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.generated.LogicalInterconnects;

public class LogicalInterconnectCollectionV2 extends BaseCollectionResource<LogicalInterconnects> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private final List<LogicalInterconnects> members = new ArrayList<LogicalInterconnects>();

    @Override
    public List<LogicalInterconnects> getMembers() {
        final List<LogicalInterconnects> _members = new ArrayList<LogicalInterconnects>();
        if (this.members != null && !this.members.isEmpty()) {
            _members.addAll(this.members);
        }
        return _members;
    }

    @Override
    public void setMembers(final List<LogicalInterconnects> _members) {
        this.members.clear();
        if (_members != null && !_members.isEmpty()) {
            this.members.addAll(_members);
            this.setCount(this.members.size());
        }
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
