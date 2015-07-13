/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.generated.NetworkSets;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class NetworkSetCollection extends BaseCollectionResource<NetworkSets>
{

    private static final long serialVersionUID = 1L;

    private List<NetworkSets> members = new ArrayList<NetworkSets>();

    @Override
    public List<NetworkSets> getMembers()
    {
        List<NetworkSets> _members = new ArrayList<NetworkSets>();
        if (this.members != null && !this.members.isEmpty())
        {
            _members.addAll(this.members);
        }
        return _members;
    }

    @Override
    public void setMembers(List<NetworkSets> _members)
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
