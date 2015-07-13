/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class LocationEntry implements Serializable
{
    private static final long serialVersionUID = 1L;

    private LocationType type;
    private String value;

    /**
     * @return the type
     */
    public LocationType getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(LocationType type)
    {
        this.type = type;
    }

    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }

}
