/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class TaskProgress implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String statusUpdate;
    private String timestamp;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the statusUpdate
     */
    public String getStatusUpdate()
    {
        return statusUpdate;
    }

    /**
     * @param statusUpdate the statusUpdate to set
     */
    public void setStatusUpdate(String statusUpdate)
    {
        this.statusUpdate = statusUpdate;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp()
    {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

}
