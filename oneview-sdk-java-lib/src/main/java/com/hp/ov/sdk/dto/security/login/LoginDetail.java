package com.hp.ov.sdk.dto.security.login;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.hp.ov.sdk.dto.BaseModelResource;

public class LoginDetail extends BaseModelResource{
	
	private static final long serialVersionUID = 1L;

	private List<LoginDomain> configuredLoginDomains = new ArrayList<LoginDomain>();
	private String defaultLoginDomain;
	private LoginMessage loginMessage;
	private Boolean allowLocalLogin;
	private Boolean technicianEnabled;	
	
	public List<LoginDomain> getConfiguredLoginDomains() {
		return configuredLoginDomains;
	}
	public void setConfiguredLoginDomains(List<LoginDomain> configuredLoginDomains) {
		this.configuredLoginDomains = configuredLoginDomains;
	}
	public String getDefaultLoginDomain() {
		return defaultLoginDomain;
	}
	public void setDefaultLoginDomain(String defaultLoginDomain) {
		this.defaultLoginDomain = defaultLoginDomain;
	}
	public LoginMessage getLoginMessage() {
		return loginMessage;
	}
	public void setLoginMessage(LoginMessage loginMessage) {
		this.loginMessage = loginMessage;
	}
	public Boolean getAllowLocalLogin() {
		return allowLocalLogin;
	}
	public void setAllowLocalLogin(Boolean allowLocalLogin) {
		this.allowLocalLogin = allowLocalLogin;
	}
	public Boolean getTechnicianEnabled() {
		return technicianEnabled;
	}
	public void setTechnicianEnabled(Boolean technicianEnabled) {
		this.technicianEnabled = technicianEnabled;
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
