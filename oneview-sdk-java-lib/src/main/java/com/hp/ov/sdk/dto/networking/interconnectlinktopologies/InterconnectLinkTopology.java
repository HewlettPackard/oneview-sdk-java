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
package com.hp.ov.sdk.dto.networking.interconnectlinktopologies;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class InterconnectLinkTopology extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private List<EnclosureMember> enclosureMembers = new ArrayList<>();
    private List<ILTConnection> iltConnections = new ArrayList<>();
    private List<ILTError> topologyErrors = new ArrayList<>();

    /**
     * @return the enclosureMembers
     */
    public List<EnclosureMember> getEnclosureMembers() {
        return enclosureMembers;
    }

    /**
     * @param enclosureMembers the enclosureMembers to set
     */
    public void setEnclosureMembers(List<EnclosureMember> enclosureMembers) {
        this.enclosureMembers = enclosureMembers;
    }

    /**
     * @return the iltConnections
     */
    public List<ILTConnection> getIltConnections() {
        return iltConnections;
    }

    /**
     * @param iltConnections the iltConnections to set
     */
    public void setIltConnections(List<ILTConnection> iltConnections) {
        this.iltConnections = iltConnections;
    }

    /**
     * @return the topologyErrors
     */
    public List<ILTError> getTopologyErrors() {
        return topologyErrors;
    }

    /**
     * @param topologyErrors the topologyErrors to set
     */
    public void setTopologyErrors(List<ILTError> topologyErrors) {
        this.topologyErrors = topologyErrors;
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
}
