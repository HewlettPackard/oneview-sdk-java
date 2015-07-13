/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RabbitMqClientCert extends BaseModelResource
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String aliasName;
    private String base64SSLCertData;
    private String base64SSLKeyData;
    private String commonName;
    private String countryName;
    private String emailAddress;
    private int expiresInDays;
    private int keysize;
    private String locality;
    private String organizationalUnitName;
    private String organizationName;
    private String pkcs12KeyPair;
    private boolean signedCert;
    private String stateOrProvinceName;

    public String getAliasName()
    {
        return aliasName;
    }

    public void setAliasName(String aliasName)
    {
        this.aliasName = aliasName;
    }

    public String getBase64SSLCertData()
    {
        return base64SSLCertData;
    }

    public void setBase64SSLCertData(String base64sslCertData)
    {
        base64SSLCertData = base64sslCertData;
    }

    public String getBase64SSLKeyData()
    {
        return base64SSLKeyData;
    }

    public void setBase64SSLKeyData(String base64sslKeyData)
    {
        base64SSLKeyData = base64sslKeyData;
    }

    public String getCommonName()
    {
        return commonName;
    }

    public void setCommonName(String commonName)
    {
        this.commonName = commonName;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public int getExpiresInDays()
    {
        return expiresInDays;
    }

    public void setExpiresInDays(int expiresInDays)
    {
        this.expiresInDays = expiresInDays;
    }

    public int getKeysize()
    {
        return keysize;
    }

    public void setKeysize(int keysize)
    {
        this.keysize = keysize;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality(String locality)
    {
        this.locality = locality;
    }

    public String getOrganizationalUnitName()
    {
        return organizationalUnitName;
    }

    public void setOrganizationalUnitName(String organizationalUnitName)
    {
        this.organizationalUnitName = organizationalUnitName;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public String getPkcs12KeyPair()
    {
        return pkcs12KeyPair;
    }

    public void setPkcs12KeyPair(String pkcs12KeyPair)
    {
        this.pkcs12KeyPair = pkcs12KeyPair;
    }

    public boolean isSignedCert()
    {
        return signedCert;
    }

    public void setSignedCert(boolean signedCert)
    {
        this.signedCert = signedCert;
    }

    public String getStateOrProvinceName()
    {
        return stateOrProvinceName;
    }

    public void setStateOrProvinceName(String stateOrProvinceName)
    {
        this.stateOrProvinceName = stateOrProvinceName;
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
