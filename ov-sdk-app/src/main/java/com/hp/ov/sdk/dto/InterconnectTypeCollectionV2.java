/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.generated.InterconnectTypes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class InterconnectTypeCollectionV2 extends BaseCollectionResource<InterconnectTypes>
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<InterconnectTypes> members = new ArrayList<InterconnectTypes>();

    @Override
    public List<InterconnectTypes> getMembers()
    {
        List<InterconnectTypes> _members = new ArrayList<InterconnectTypes>();
        if (this.members != null && !this.members.isEmpty())
        {
            _members.addAll(this.members);
        }
        return _members;
    }

    @Override
    public void setMembers(List<InterconnectTypes> _members)
    {
        this.members.clear();
        if (_members != null && !_members.isEmpty())
        {
            this.members.addAll(_members);
            this.setCount(this.members.size());
        }

    }

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

    public InterconnectTypes searchName(String value)
    {
        if (this.members != null && !this.members.isEmpty())
        {
            for (InterconnectTypes member : this.members)
            {
                if (member.getName().equalsIgnoreCase(value))
                {
                    return member;
                }
            }
        }
        return null;
    }

}
