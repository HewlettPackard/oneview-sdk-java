/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class PortMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    private Integer midplanePort;
    /**
     * 
     * (Required)
     * 
     */
    private Integer interconnectBay;

    /**
     * 
     * (Required)
     * 
     * @return The midplanePort
     */
    public Integer getMidplanePort() {
        return midplanePort;
    }

    /**
     * 
     * (Required)
     * 
     * @param midplanePort
     *            The midplanePort
     */
    public void setMidplanePort(final Integer midplanePort) {
        this.midplanePort = midplanePort;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectBay
     */
    public Integer getInterconnectBay() {
        return interconnectBay;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnectBay
     *            The interconnectBay
     */
    public void setInterconnectBay(final Integer interconnectBay) {
        this.interconnectBay = interconnectBay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(midplanePort).append(interconnectBay).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortMapping) == false) {
            return false;
        }
        final PortMapping rhs = ((PortMapping) other);
        return new EqualsBuilder().append(midplanePort, rhs.midplanePort).append(interconnectBay, rhs.interconnectBay).isEquals();
    }

}
