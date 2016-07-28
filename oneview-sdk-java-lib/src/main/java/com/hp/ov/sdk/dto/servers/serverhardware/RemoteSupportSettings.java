/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RemoteSupportSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    private String destination;
    private String remoteSupportCurrentState;

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the remoteSupportCurrentState
     */
    public String getRemoteSupportCurrentState() {
        return remoteSupportCurrentState;
    }

    /**
     * @param remoteSupportCurrentState the remoteSupportCurrentState to set
     */
    public void setRemoteSupportCurrentState(String remoteSupportCurrentState) {
        this.remoteSupportCurrentState = remoteSupportCurrentState;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof RemoteSupportSettings) {
            RemoteSupportSettings that = (RemoteSupportSettings) obj;

            return new EqualsBuilder()
                    .append(destination, that.destination)
                    .append(remoteSupportCurrentState, that.remoteSupportCurrentState)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(destination)
                .append(remoteSupportCurrentState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
