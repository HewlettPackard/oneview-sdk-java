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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Ipv4Setting implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private String ipAddress;
    private Ipv4Setting.IpAssignmentState ipAssignmentState;
    private String ipRangeUri;
    private Ipv4Setting.Mode mode;

    /**
     *
     * @return The ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress
     *            The ipAddress
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     * @return The ipAssignmentState
     */
    public IpAssignmentState getIpAssignmentState() {
        return ipAssignmentState;
    }

    /**
     *
     * @param ipAssignmentState
     *            The ipAssignmentState
     */
    public void setIpAssignmentState(final IpAssignmentState ipAssignmentState) {
        this.ipAssignmentState = ipAssignmentState;
    }

    /**
     *
     * @return The ipRangeUri
     */
    public String getIpRangeUri() {
        return ipRangeUri;
    }

    /**
     *
     * @param ipRangeUri
     *            The ipRangeUri
     */
    public void setIpRangeUri(final String ipRangeUri) {
        this.ipRangeUri = ipRangeUri;
    }

    /**
     *
     * @return The mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     *            The mode
     */
    public void setMode(final Mode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(ipAddress)
                .append(ipAssignmentState)
                .append(ipRangeUri)
                .append(mode)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ipv4Setting) == false) {
            return false;
        }
        final Ipv4Setting rhs = ((Ipv4Setting) other);
        return new EqualsBuilder()
                .append(ipAddress, rhs.ipAddress)
                .append(ipAssignmentState, rhs.ipAssignmentState)
                .append(ipRangeUri, rhs.ipRangeUri)
                .append(mode, rhs.mode)
                .isEquals();
    }

    public static enum IpAssignmentState {

        Assigned("Assigned"),
        None("None"),
        Reserved("Reserved");

        private final String value;
        private static Map<String, Ipv4Setting.IpAssignmentState> constants = new HashMap<String, Ipv4Setting.IpAssignmentState>();

        static {
            for (final Ipv4Setting.IpAssignmentState c : values()) {
                constants.put(c.value, c);
            }
        }

        private IpAssignmentState(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Ipv4Setting.IpAssignmentState fromValue(final String value) {
            final Ipv4Setting.IpAssignmentState constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

        public static enum Mode {

            DHCP("DHCP"),
            External("External"),
            IpPool("IpPool");

            private final String value;
            private static Map<String, Ipv4Setting.Mode> constants = new HashMap<String, Ipv4Setting.Mode>();

            static {
                for (final Ipv4Setting.Mode c : values()) {
                    constants.put(c.value, c);
                }
            }

            private Mode(final String value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return this.value;
            }

            public static Ipv4Setting.Mode fromValue(final String value) {
                final Ipv4Setting.Mode constant = constants.get(value);
                if (constant == null) {
                    throw new IllegalArgumentException(value);
                } else {
                    return constant;
                }
            }
        }


}
