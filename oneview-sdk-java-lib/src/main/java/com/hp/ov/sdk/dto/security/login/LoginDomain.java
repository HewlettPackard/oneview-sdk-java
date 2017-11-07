package com.hp.ov.sdk.dto.security.login;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.hp.ov.sdk.dto.BaseModelResource;

public class LoginDomain extends BaseModelResource{	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4735709887424866950L;
	private String loginDomain;
	private String authProtocol;
	
	public String getLoginDomain() {
		return loginDomain;
	}
	
	public void setLoginDomain(String loginDomain) {
		this.loginDomain = loginDomain;
	}
	
	public String getAuthProtocol() {
		return authProtocol;
	}
	
	public void setAuthProtocol(String authProtocol) {
		this.authProtocol = authProtocol;
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
