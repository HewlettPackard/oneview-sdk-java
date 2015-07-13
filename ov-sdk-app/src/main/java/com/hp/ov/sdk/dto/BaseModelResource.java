/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class BaseModelResource implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String state;
    private String status;
    private String uri;
    private String category;
    private String type;
    private String eTag;
    private String created;
    private String modified;

    public BaseModelResource()
    {

    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String geteTag()
    {
        return eTag;
    }

    public void seteTag(String eTag)
    {
        this.eTag = eTag;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getModified()
    {
        return modified;
    }

    public void setModified(String modified)
    {
        this.modified = modified;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

}
