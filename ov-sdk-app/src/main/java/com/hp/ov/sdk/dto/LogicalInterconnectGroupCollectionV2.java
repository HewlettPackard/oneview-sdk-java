/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogicalInterconnectGroupCollectionV2 extends BaseCollectionResource<LogicalInterconnectGroups>
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    List<LogicalInterconnectGroups> members = new ArrayList<LogicalInterconnectGroups>();

    @Override
    public List<LogicalInterconnectGroups> getMembers()
    {
        List<LogicalInterconnectGroups> _members = new ArrayList<LogicalInterconnectGroups>();
        if (this.members != null && !this.members.isEmpty())
        {
            _members.addAll(this.members);
        }
        return _members;
    }

    @Override
    public void setMembers(List<LogicalInterconnectGroups> _members)
    {
        this.members.clear();
        if (_members != null && !_members.isEmpty())
        {
            this.members.addAll(_members);
            this.setCount(this.members.size());
        }

    }

    /*
     * @see java.lang.Object#hashCode()
     */
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
