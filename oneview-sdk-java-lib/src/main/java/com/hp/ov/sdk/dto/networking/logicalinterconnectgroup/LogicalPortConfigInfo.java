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
package com.hp.ov.sdk.dto.networking.logicalinterconnectgroup;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.OpSpeed;


public class LogicalPortConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private LogicalLocation logicalLocation;
    private OpSpeed desiredSpeed;

    /**
     * @return the logicalLocation
     */
    public LogicalLocation getLogicalLocation() {
        return logicalLocation;
    }

    /**
     * @param logicalLocation the logicalLocation to set
     */
    public void setLogicalLocation(LogicalLocation logicalLocation) {
        this.logicalLocation = logicalLocation;
    }

    /**
     * @return the desiredSpeed
     */
    public OpSpeed getDesiredSpeed() {
        return desiredSpeed;
    }

    /**
     * @param desiredSpeed the desiredSpeed to set
     */
    public void setDesiredSpeed(OpSpeed desiredSpeed) {
        this.desiredSpeed = desiredSpeed;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(logicalLocation)
                .append(desiredSpeed).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;

        if (obj instanceof LogicalPortConfigInfo) {
            LogicalPortConfigInfo that = ((LogicalPortConfigInfo) obj);

            return new EqualsBuilder()
                    .append(logicalLocation, that.logicalLocation)
                    .append(desiredSpeed, that.desiredSpeed)
                    .isEquals();
        }
        return false;
    }

}
