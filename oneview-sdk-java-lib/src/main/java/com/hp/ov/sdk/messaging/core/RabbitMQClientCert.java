/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.messaging.core;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class RabbitMQClientCert extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String aliasName;
    private String base64SSLCertData;
    private String base64SSLKeyData;
    private String commonName;
    private String countryName;
    private String emailAddress;
    private Integer expiresInDays;
    private Integer keysize;
    private String locality;
    private String organizationalUnitName;
    private String organizationName;
    private String pkcs12KeyPair;
    private Boolean signedCert;
    private String stateOrProvinceName;

    /**
     * @return the aliasName
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * @param aliasName the aliasName to set
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /**
     * @return the base64SSLCertData
     */
    public String getBase64SSLCertData() {
        return base64SSLCertData;
    }

    /**
     * @param base64SSLCertData the base64SSLCertData to set
     */
    public void setBase64SSLCertData(String base64SSLCertData) {
        this.base64SSLCertData = base64SSLCertData;
    }

    /**
     * @return the base64SSLKeyData
     */
    public String getBase64SSLKeyData() {
        return base64SSLKeyData;
    }

    /**
     * @param base64SSLKeyData the base64SSLKeyData to set
     */
    public void setBase64SSLKeyData(String base64SSLKeyData) {
        this.base64SSLKeyData = base64SSLKeyData;
    }

    /**
     * @return the commonName
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * @param commonName the commonName to set
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the expiresInDays
     */
    public Integer getExpiresInDays() {
        return expiresInDays;
    }

    /**
     * @param expiresInDays the expiresInDays to set
     */
    public void setExpiresInDays(Integer expiresInDays) {
        this.expiresInDays = expiresInDays;
    }

    /**
     * @return the keysize
     */
    public Integer getKeysize() {
        return keysize;
    }

    /**
     * @param keysize the keysize to set
     */
    public void setKeysize(Integer keysize) {
        this.keysize = keysize;
    }

    /**
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * @return the organizationalUnitName
     */
    public String getOrganizationalUnitName() {
        return organizationalUnitName;
    }

    /**
     * @param organizationalUnitName the organizationalUnitName to set
     */
    public void setOrganizationalUnitName(String organizationalUnitName) {
        this.organizationalUnitName = organizationalUnitName;
    }

    /**
     * @return the organizationName
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * @param organizationName the organizationName to set
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * @return the pkcs12KeyPair
     */
    public String getPkcs12KeyPair() {
        return pkcs12KeyPair;
    }

    /**
     * @param pkcs12KeyPair the pkcs12KeyPair to set
     */
    public void setPkcs12KeyPair(String pkcs12KeyPair) {
        this.pkcs12KeyPair = pkcs12KeyPair;
    }

    /**
     * @return the signedCert
     */
    public Boolean getSignedCert() {
        return signedCert;
    }

    /**
     * @param signedCert the signedCert to set
     */
    public void setSignedCert(Boolean signedCert) {
        this.signedCert = signedCert;
    }

    /**
     * @return the stateOrProvinceName
     */
    public String getStateOrProvinceName() {
        return stateOrProvinceName;
    }

    /**
     * @param stateOrProvinceName the stateOrProvinceName to set
     */
    public void setStateOrProvinceName(String stateOrProvinceName) {
        this.stateOrProvinceName = stateOrProvinceName;
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
