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
package com.hp.ov.sdk.dto.servers.enclosure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Ipv6Address implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String address;
    private Ipv6Address.Type type;

    /**
     * 
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *            The address
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 
     * @return The type
     */
    public Ipv6Address.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    public void setType(final Ipv6Address.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(address).append(type).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ipv6Address) == false) {
            return false;
        }
        final Ipv6Address rhs = ((Ipv6Address) other);
        return new EqualsBuilder().append(address, rhs.address).append(type, rhs.type).isEquals();
    }

    public static enum Type {

        LinkLocal("LinkLocal"), RouterAdv("RouterAdv"), NotSet("NotSet"), Unknown("Unknown"), Static("Static");
        private final String value;
        private static Map<String, Ipv6Address.Type> constants = new HashMap<String, Ipv6Address.Type>();

        static {
            for (final Ipv6Address.Type c : values()) {
                constants.put(c.value, c);
            }
        }

        private Type(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Ipv6Address.Type fromValue(final String value) {
            final Ipv6Address.Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
