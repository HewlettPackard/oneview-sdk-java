package com.hp.ov.sdk.dto.security.login;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.hp.ov.sdk.dto.BaseModelResource;

public class LoginMessage extends BaseModelResource{	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -70715866435733249L;
	private Boolean acknowledgment;
	private String message;
	
	public Boolean getAcknowledgment() {
		return acknowledgment;
	}
	public void setAcknowledgment(Boolean acknowledgment) {
		this.acknowledgment = acknowledgment;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
