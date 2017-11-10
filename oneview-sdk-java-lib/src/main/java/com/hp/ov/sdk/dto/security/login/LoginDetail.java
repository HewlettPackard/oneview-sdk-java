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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.GsonBuilder;

public class LoginDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String type;
    private String defaultLoginDomain;
    private List<LoginDomain> configuredLoginDomains = new ArrayList<LoginDomain>();
    private Boolean allowLocalLogin;
    private Boolean technicianEnabled;
    private LoginMessage loginMessage;
    private String eTag;
    private Date created;
    private Date modified;
    private String category;   
    private String uri;
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return the defaultLoginDomain
     */
    public String getDefaultLoginDomain() {
        return defaultLoginDomain;
    }
    
    /**
     * @param defaultLoginDomain the defaultLoginDomain to set
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
     * @param configuredLoginDomains the configuredLoginDomains to set
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
     * @param allowLocalLogin the allowLocalLogin to set
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
     * @param technicianEnabled the technicianEnabled to set
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
     * @param loginMessage the loginMessage to set
     */
    public void setLoginMessage(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
    }
    
    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }

    /**
     * @param eTag the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }
    
    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    
    /**
     * @return the modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
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
    
    public String toJsonString() {
        return System.getProperty("line.separator")
            + new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create().toJson(this);
    }
}
