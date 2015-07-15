/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoginSessionDto implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String authLoginDomain;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(final String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getAuthLoginDomain()
    {
        return authLoginDomain;
    }

    public void setAuthLoginDomain(final String authLoginDomain)
    {
        this.authLoginDomain = authLoginDomain;
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
