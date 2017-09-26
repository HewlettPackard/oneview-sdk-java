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

package com.hp.ov.sdk.dto.settings;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.util.URIUtils;

public class License implements Serializable {

    private static final long serialVersionUID = -3015621926143496846L;

    private String category;
    private Date created;
    private String eTag;
    private Date modified;
    private String type;
    private String uri;
    @Until(199)
    private Integer consumedCapacity;
    @Until(199)
    private Integer hwm;
    @Until(199)
    private BigDecimal hwmPercentageCompliance;
    @Until(199)
    private BigDecimal percentageCompliance;
    @Until(199)
    private Integer unlicensedCount;
    private List<String> additionalKeys = new ArrayList<>();
    private Integer availableCapacity;
    private String eon;
    @Since(200)
    private String expiryDate;
    private String key;
    private LicenseType licenseType;
    private List<Node> nodes = new ArrayList<>();
    private String product;
    private String productDescription;
    private String productNumber;
    private String salesOrder;
    private Integer totalCapacity;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }

    /**
     * @param eTag
     *            the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified
     *            the modified to set
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri
     *            the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the consumedCapacity
     */
    public Integer getConsumedCapacity() {
        return consumedCapacity;
    }

    /**
     * @param consumedCapacity
     *            the consumedCapacity to set
     */
    public void setConsumedCapacity(Integer consumedCapacity) {
        this.consumedCapacity = consumedCapacity;
    }

    /**
     * @return the hwm
     */
    public Integer getHwm() {
        return hwm;
    }

    /**
     * @param hwm
     *            the hwm to set
     */
    public void setHwm(Integer hwm) {
        this.hwm = hwm;
    }

    /**
     * @return the hwmPercentageCompliance
     */
    public BigDecimal getHwmPercentageCompliance() {
        return hwmPercentageCompliance;
    }

    /**
     * @param hwmPercentageCompliance
     *            the hwmPercentageCompliance to set
     */
    public void setHwmPercentageCompliance(BigDecimal hwmPercentageCompliance) {
        this.hwmPercentageCompliance = hwmPercentageCompliance;
    }

    /**
     * @return the percentageCompliance
     */
    public BigDecimal getPercentageCompliance() {
        return percentageCompliance;
    }

    /**
     * @param percentageCompliance
     *            the percentageCompliance to set
     */
    public void setPercentageCompliance(BigDecimal percentageCompliance) {
        this.percentageCompliance = percentageCompliance;
    }

    /**
     * @return the unlicensedCount
     */
    public Integer getUnlicensedCount() {
        return unlicensedCount;
    }

    /**
     * @param unlicensedCount
     *            the unlicensedCount to set
     */
    public void setUnlicensedCount(Integer unlicensedCount) {
        this.unlicensedCount = unlicensedCount;
    }

    /**
     * @return the additionalKeys
     */
    public List<String> getAdditionalKeys() {
        return additionalKeys;
    }

    /**
     * @param additionalKeys
     *            the additionalKeys to set
     */
    public void setAdditionalKeys(List<String> additionalKeys) {
        this.additionalKeys = additionalKeys;
    }

    /**
     * @return the availableCapacity
     */
    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    /**
     * @param availableCapacity
     *            the availableCapacity to set
     */
    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    /**
     * @return the eon
     */
    public String getEon() {
        return eon;
    }

    /**
     * @param eon
     *            the eon to set
     */
    public void setEon(String eon) {
        this.eon = eon;
    }

    /**
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate
     *            the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the licenseType
     */
    public LicenseType getLicenseType() {
        return licenseType;
    }

    /**
     * @param licenseType
     *            the licenseType to set
     */
    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * @return the nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * @param nodes
     *            the nodes to set
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription
     *            the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the productNumber
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * @param productNumber
     *            the productNumber to set
     */
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * @return the salesOrder
     */
    public String getSalesOrder() {
        return salesOrder;
    }

    /**
     * @param salesOrder
     *            the salesOrder to set
     */
    public void setSalesOrder(String salesOrder) {
        this.salesOrder = salesOrder;
    }

    /**
     * @return the totalCapacity
     */
    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    /**
     * @param totalCapacity
     *            the totalCapacity to set
     */
    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    /**
     * @return the licenseId
     *            used to identify a license
     */
    public String getLicenseId() {
        return URIUtils.getResourceIdFromUri(this.getUri());
    }

    public String toJsonString() {
        return System.getProperty("line.separator")
                + new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create()
                .toJson(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof License) == false) {
            return false;
        }
        final License that = (License) obj;
        return new EqualsBuilder()
                .append(category, that.category)
                .append(created, that.created)
                .append(eTag, that.eTag)
                .append(modified, that.modified)
                .append(type, that.type)
                .append(uri, that.uri)
                .append(consumedCapacity, that.consumedCapacity)
                .append(hwm, that.hwm)
                .append(hwmPercentageCompliance, that.hwmPercentageCompliance)
                .append(percentageCompliance, that.percentageCompliance)
                .append(unlicensedCount, that.unlicensedCount)
                .append(additionalKeys, that.additionalKeys)
                .append(availableCapacity, that.availableCapacity)
                .append(eon, that.eon)
                .append(expiryDate, that.expiryDate)
                .append(key, that.key)
                .append(licenseType, that.licenseType)
                .append(nodes, that.nodes)
                .append(product, that.product)
                .append(productDescription, that.productDescription)
                .append(productNumber, that.productNumber)
                .append(salesOrder, that.salesOrder)
                .append(totalCapacity, that.totalCapacity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(category)
                .append(created)
                .append(eTag)
                .append(modified)
                .append(type)
                .append(uri)
                .append(consumedCapacity)
                .append(hwm)
                .append(hwmPercentageCompliance)
                .append(percentageCompliance)
                .append(unlicensedCount)
                .append(additionalKeys)
                .append(availableCapacity)
                .append(eon)
                .append(expiryDate)
                .append(key)
                .append(licenseType)
                .append(nodes)
                .append(product)
                .append(productDescription)
                .append(productNumber)
                .append(salesOrder)
                .append(totalCapacity)
                .toHashCode();
    }

}
