/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class OaIpv6Address implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String address;
    private OaIpv6Type type;

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(final String address)
    {
        this.address = address;
    }

    /**
     * @return the type
     */
    public OaIpv6Type getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final OaIpv6Type type)
    {
        this.type = type;
    }

}
