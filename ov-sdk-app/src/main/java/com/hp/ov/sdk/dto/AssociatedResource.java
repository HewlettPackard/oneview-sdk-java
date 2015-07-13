/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AssociatedResource implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String resourceName;
    private String resourceUri;
    private String resourceCategory;
    private AssociationType associationType;

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceUri()
    {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri)
    {
        this.resourceUri = resourceUri;
    }

    public String getResourceCategory()
    {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory)
    {
        this.resourceCategory = resourceCategory;
    }

    public AssociationType getAssociationType()
    {
        return associationType;
    }

    public void setAssociationType(AssociationType associationType)
    {
        this.associationType = associationType;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
