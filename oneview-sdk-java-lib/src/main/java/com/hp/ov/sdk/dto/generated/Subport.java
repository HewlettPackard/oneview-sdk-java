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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "portStatus", "portStatusReason", "portNumber" })
public class Subport implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("portStatus")
    private Subport.PortStatus portStatus;
    @JsonProperty("portStatusReason")
    private Subport.PortStatusReason portStatusReason;
    @JsonProperty("portNumber")
    private Integer portNumber;

    /**
     * 
     * @return The portStatus
     */
    @JsonProperty("portStatus")
    public Subport.PortStatus getPortStatus() {
        return portStatus;
    }

    /**
     * 
     * @param portStatus
     *            The portStatus
     */
    @JsonProperty("portStatus")
    public void setPortStatus(final Subport.PortStatus portStatus) {
        this.portStatus = portStatus;
    }

    /**
     * 
     * @return The portStatusReason
     */
    @JsonProperty("portStatusReason")
    public Subport.PortStatusReason getPortStatusReason() {
        return portStatusReason;
    }

    /**
     * 
     * @param portStatusReason
     *            The portStatusReason
     */
    @JsonProperty("portStatusReason")
    public void setPortStatusReason(final Subport.PortStatusReason portStatusReason) {
        this.portStatusReason = portStatusReason;
    }

    /**
     * 
     * @return The portNumber
     */
    @JsonProperty("portNumber")
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * 
     * @param portNumber
     *            The portNumber
     */
    @JsonProperty("portNumber")
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(portStatus).append(portStatusReason).append(portNumber).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Subport) == false) {
            return false;
        }
        final Subport rhs = ((Subport) other);
        return new EqualsBuilder().append(portStatus, rhs.portStatus).append(portStatusReason, rhs.portStatusReason)
                .append(portNumber, rhs.portNumber).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum PortStatus {

        Linked("Linked"), Unlinked("Unlinked"), Unknown("Unknown");
        private final String value;
        private static Map<String, Subport.PortStatus> constants = new HashMap<String, Subport.PortStatus>();

        static {
            for (final Subport.PortStatus c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortStatus(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Subport.PortStatus fromValue(final String value) {
            final Subport.PortStatus constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PortStatusReason {

        None("None"), Active("Active"), StandBy("StandBy"), LoggedIn("LoggedIn"), NotLoggedIn("NotLoggedIn"), Unknown("Unknown"), AdminHidden(
                "AdminHidden"), LoopDetected("LoopDetected"), PauseFloodDetected("PauseFloodDetected"), AdminDisabled(
                "AdminDisabled"), EkeyMismatch("EkeyMismatch"), Unpopulated("Unpopulated"), ModuleUnrecognized("ModuleUnrecognized"), FailedValidation(
                "FailedValidation"), ModuleUnsupported("ModuleUnsupported"), ModuleIncompatible("ModuleIncompatible"), SmartLink(
                "SmartLink"), SmartLinkButNoDCC("SmartLinkButNoDCC"), OkUncertified("OkUncertified"), OkNonHP("OkNonHP"), UnsupportedStorage(
                "UnsupportedStorage"), FabricTypeMismatch("FabricTypeMismatch"), Ok("Ok"), Unavailable("Unavailable"), PortPairMisMatchSfpType(
                "PortPairMisMatchSfpType"), PortPairMismatchEnetSpeed("PortPairMismatchEnetSpeed");
        private final String value;
        private static Map<String, Subport.PortStatusReason> constants = new HashMap<String, Subport.PortStatusReason>();

        static {
            for (final Subport.PortStatusReason c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortStatusReason(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Subport.PortStatusReason fromValue(final String value) {
            final Subport.PortStatusReason constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
