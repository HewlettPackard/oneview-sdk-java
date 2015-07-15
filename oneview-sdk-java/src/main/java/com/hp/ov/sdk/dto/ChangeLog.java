/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class ChangeLog
{

    private String created;
    private String username;
    private String notes;
    private String userEntered;
    private String uri;

    public String getCreated()
    {
        return created;
    }

    public void setCreated(final String created)
    {
        this.created = created;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(final String notes)
    {
        this.notes = notes;
    }

    public String getUserEntered()
    {
        return userEntered;
    }

    public void setUserEntered(final String userEntered)
    {
        this.userEntered = userEntered;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(final String uri)
    {
        this.uri = uri;
    }

}
