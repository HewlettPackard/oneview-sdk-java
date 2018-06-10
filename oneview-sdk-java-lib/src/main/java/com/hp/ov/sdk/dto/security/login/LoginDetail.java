/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hp.ov.sdk.dto.security.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.GsonBuilder;

public class LoginDetail extends BaseLoginResource {

    private static final long serialVersionUID = 1L;

    private String defaultLoginDomain;
    private List<LoginDomain> configuredLoginDomains = new ArrayList<LoginDomain>();
    private Boolean allowLocalLogin;
    private Boolean technicianEnabled;
    private LoginMessage loginMessage;

    /**
     * @return the defaultLoginDomain
     */
    public String getDefaultLoginDomain() {
        return defaultLoginDomain;
    }

    /**
     * @param defaultLoginDomain
     *            the defaultLoginDomain to set
     */
    public void setDefaultLoginDomain(String defaultLoginDomain) {
        this.defaultLoginDomain = defaultLoginDomain;
    }

    /**
     * @return the configuredLoginDomains
     */
    public List<LoginDomain> getConfiguredLoginDomains() {
        return configuredLoginDomains;
    }

    /**
     * @param configuredLoginDomains
     *            the configuredLoginDomains to set
     */
    public void setConfiguredLoginDomains(List<LoginDomain> configuredLoginDomains) {
        this.configuredLoginDomains = configuredLoginDomains;
    }

    /**
     * @return the allowLocalLogin
     */
    public Boolean getAllowLocalLogin() {
        return allowLocalLogin;
    }

    /**
     * @param allowLocalLogin
     *            the allowLocalLogin to set
     */
    public void setAllowLocalLogin(Boolean allowLocalLogin) {
        this.allowLocalLogin = allowLocalLogin;
    }

    /**
     * @return the technicianEnabled
     */
    public Boolean getTechnicianEnabled() {
        return technicianEnabled;
    }

    /**
     * @param technicianEnabled
     *            the technicianEnabled to set
     */
    public void setTechnicianEnabled(Boolean technicianEnabled) {
        this.technicianEnabled = technicianEnabled;
    }

    /**
     * @return the loginMessage
     */
    public LoginMessage getLoginMessage() {
        return loginMessage;
    }

    /**
     * @param loginMessage
     *            the loginMessage to set
     */
    public void setLoginMessage(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
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

    @Override
    public String toJsonString() {
        return System.getProperty("line.separator")
                + new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(this);
    }
}
