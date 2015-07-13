/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Data representing the current configuration or 'signature' of the server.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "personalityChecksum", "serverHwChecksum"
})
public class Signature implements Serializable
{

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
    public Integer getPersonalityChecksum()
    {
        return personalityChecksum;
    }

    /**
     * A calculated checksum of the server 'personality,' based on the defined
     * connections and server identifiers.
     * 
     * @param personalityChecksum
     *        The personalityChecksum
     */
    @JsonProperty("personalityChecksum")
    public void setPersonalityChecksum(Integer personalityChecksum)
    {
        this.personalityChecksum = personalityChecksum;
    }

    /**
     * A calculated checksum of the server hardware, based on the hardware
     * components installed in the server.
     * 
     * @return The serverHwChecksum
     */
    @JsonProperty("serverHwChecksum")
    public Integer getServerHwChecksum()
    {
        return serverHwChecksum;
    }

    /**
     * A calculated checksum of the server hardware, based on the hardware
     * components installed in the server.
     * 
     * @param serverHwChecksum
     *        The serverHwChecksum
     */
    @JsonProperty("serverHwChecksum")
    public void setServerHwChecksum(Integer serverHwChecksum)
    {
        this.serverHwChecksum = serverHwChecksum;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(personalityChecksum)
                .append(serverHwChecksum).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Signature) == false)
        {
            return false;
        }
        Signature rhs = ((Signature) other);
        return new EqualsBuilder()
                .append(personalityChecksum, rhs.personalityChecksum)
                .append(serverHwChecksum, rhs.serverHwChecksum).isEquals();
    }

}
