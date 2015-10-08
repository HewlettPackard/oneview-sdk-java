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

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Data representing the current configuration or 'signature' of the server.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "personalityChecksum", "serverHwChecksum" })
public class Signature implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * A calculated checksum of the server 'personality,' based on the defined
     * connections and server identifiers.
     * 
     */
    @JsonProperty("personalityChecksum")
    private Integer personalityChecksum;
    /**
     * A calculated checksum of the server hardware, based on the hardware
     * components installed in the server.
     * 
     */
    @JsonProperty("serverHwChecksum")
    private Integer serverHwChecksum;

    /**
     * A calculated checksum of the server 'personality,' based on the defined
     * connections and server identifiers.
     * 
     * @return The personalityChecksum
     */
    @JsonProperty("personalityChecksum")
    public Integer getPersonalityChecksum() {
        return personalityChecksum;
    }

    /**
     * A calculated checksum of the server 'personality,' based on the defined
     * connections and server identifiers.
     * 
     * @param personalityChecksum
     *            The personalityChecksum
     */
    @JsonProperty("personalityChecksum")
    public void setPersonalityChecksum(final Integer personalityChecksum) {
        this.personalityChecksum = personalityChecksum;
    }

    /**
     * A calculated checksum of the server hardware, based on the hardware
     * components installed in the server.
     * 
     * @return The serverHwChecksum
     */
    @JsonProperty("serverHwChecksum")
    public Integer getServerHwChecksum() {
        return serverHwChecksum;
    }

    /**
     * A calculated checksum of the server hardware, based on the hardware
     * components installed in the server.
     * 
     * @param serverHwChecksum
     *            The serverHwChecksum
     */
    @JsonProperty("serverHwChecksum")
    public void setServerHwChecksum(final Integer serverHwChecksum) {
        this.serverHwChecksum = serverHwChecksum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(personalityChecksum).append(serverHwChecksum).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Signature) == false) {
            return false;
        }
        final Signature rhs = ((Signature) other);
        return new EqualsBuilder().append(personalityChecksum, rhs.personalityChecksum)
                .append(serverHwChecksum, rhs.serverHwChecksum).isEquals();
    }

}
