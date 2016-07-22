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
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class ProfilePorts implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<Object> ports = new ArrayList<Object>();

    /**
     * 
     * @return The ports
     */
    public List<Object> getPorts() {
        return ports;
    }

    /**
     * 
     * @param ports
     *            The ports
     */
    public void setPorts(final List<Object> ports) {
        this.ports = ports;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ports).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProfilePorts) == false) {
            return false;
        }
        final ProfilePorts rhs = ((ProfilePorts) other);
        return new EqualsBuilder().append(ports, rhs.ports).isEquals();
    }

}
