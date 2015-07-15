/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Location implements Serializable
{

    private static final long serialVersionUID = 1L;

    List<LocationEntry> locationEntries = new ArrayList<LocationEntry>();

    /**
     * @return the locationEntries
     */
    public List<LocationEntry> getLocationEntries()
    {
        return locationEntries;
    }

    /**
     * @param locationEntries the locationEntries to set
     */
    public void setLocationEntries(final List<LocationEntry> locationEntries)
    {
        this.locationEntries = locationEntries;
    }
}
