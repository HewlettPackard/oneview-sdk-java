/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SnmpV3Configuration implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum SnmpV3AuthorizationProtocol {
        MD5, NA, SHA, Unknown
    }

    public enum SnmpV3PrivacyProtocol {
        AES128, DES56, NA, Unknown
    }

    public enum SnmpV3SecurityLevel {
        Auth, AuthPrivacy, NA, Unknown
    }

    private SnmpV3AuthorizationProtocol authorizationProtocol;
    private SnmpV3PrivacyProtocol privacyProtocol;
    private SnmpV3SecurityLevel securityLevel;
    private String snmpV3UserName;

    public SnmpV3AuthorizationProtocol getAuthorizationProtocol() {
        return authorizationProtocol;
    }

    public void setAuthorizationProtocol(SnmpV3AuthorizationProtocol authorizationProtocol) {
        this.authorizationProtocol = authorizationProtocol;
    }

    public SnmpV3PrivacyProtocol getPrivacyProtocol() {
        return privacyProtocol;
    }

    public void setPrivacyProtocol(SnmpV3PrivacyProtocol privacyProtocol) {
        this.privacyProtocol = privacyProtocol;
    }

    public SnmpV3SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SnmpV3SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getSnmpV3UserName() {
        return snmpV3UserName;
    }

    public void setSnmpV3UserName(String snmpV3UserName) {
        this.snmpV3UserName = snmpV3UserName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SnmpV3Configuration that = (SnmpV3Configuration) obj;

        return new EqualsBuilder()
                .append(authorizationProtocol, that.authorizationProtocol)
                .append(privacyProtocol, that.privacyProtocol)
                .append(securityLevel, that.securityLevel)
                .append(snmpV3UserName, that.snmpV3UserName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(authorizationProtocol)
                .append(privacyProtocol)
                .append(securityLevel)
                .append(snmpV3UserName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("authorizationProtocol", authorizationProtocol)
                .append("privacyProtocol", privacyProtocol)
                .append("securityLevel", securityLevel)
                .append("snmpV3UserName", snmpV3UserName)
                .toString();
    }
}
