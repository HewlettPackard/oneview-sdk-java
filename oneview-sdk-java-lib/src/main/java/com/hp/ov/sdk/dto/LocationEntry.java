/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class LocationEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocationType type;
    private String value;

    /**
     * @return the type
     */
    public LocationType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final LocationType type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

}
