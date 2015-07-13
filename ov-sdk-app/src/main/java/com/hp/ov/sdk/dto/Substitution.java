/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class Substitution implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String name;
    private String uri;

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }
}
