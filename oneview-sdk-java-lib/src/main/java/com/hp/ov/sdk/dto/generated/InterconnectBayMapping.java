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


public class InterconnectBayMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
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
     */
    private String logicalInterconnectGroupUri;

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

    /**
     * 
     * (Required)
     * 
     * @return The logicalInterconnectGroupUri
     */
    public String getLogicalInterconnectGroupUri() {
        return logicalInterconnectGroupUri;
    }

    /**
     * 
     * (Required)
     * 
     * @param logicalInterconnectGroupUri
     *            The logicalInterconnectGroupUri
     */
    public void setLogicalInterconnectGroupUri(final String logicalInterconnectGroupUri) {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(interconnectBay).append(logicalInterconnectGroupUri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InterconnectBayMapping) == false) {
            return false;
        }
        final InterconnectBayMapping rhs = ((InterconnectBayMapping) other);
        return new EqualsBuilder().append(interconnectBay, rhs.interconnectBay)
                .append(logicalInterconnectGroupUri, rhs.logicalInterconnectGroupUri).isEquals();
    }

}
