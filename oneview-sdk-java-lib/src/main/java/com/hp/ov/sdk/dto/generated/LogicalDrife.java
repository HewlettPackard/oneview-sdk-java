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


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class LogicalDrife implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Boolean bootable;
    private String raidLevel;

    /**
     * 
     * @return The bootable
     */
    public Boolean getBootable() {
        return bootable;
    }

    /**
     * 
     * @param bootable
     *            The bootable
     */
    public void setBootable(final Boolean bootable) {
        this.bootable = bootable;
    }

    /**
     * 
     * @return The raidLevel
     */
    public String getRaidLevel() {
        return raidLevel;
    }

    /**
     * 
     * @param raidLevel
     *            The raidLevel
     */
    public void setRaidLevel(final String raidLevel) {
        this.raidLevel = raidLevel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bootable).append(raidLevel).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalDrife) == false) {
            return false;
        }
        final LogicalDrife rhs = ((LogicalDrife) other);
        return new EqualsBuilder().append(bootable, rhs.bootable).append(raidLevel, rhs.raidLevel).isEquals();
    }

}
