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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class FcoeInterconnectSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    private FcoeMode fcoeMode = FcoeMode.NotApplicable;

    /**
     * @return the fcoeMode
     */
    public FcoeMode getFcoeMode() {
        return fcoeMode;
    }

    /**
     * @param fcoeMode the fcoeMode to set
     */
    public void setFcoeMode(FcoeMode fcoeMode) {
        this.fcoeMode = fcoeMode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof FcoeInterconnectSettings) {
            FcoeInterconnectSettings that = (FcoeInterconnectSettings) obj;

            return new EqualsBuilder()
                    .append(fcoeMode, that.fcoeMode)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(fcoeMode)
                .toHashCode();
    }

}
