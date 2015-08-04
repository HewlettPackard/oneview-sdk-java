/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class UnmanagedDevice extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String deviceType;
    private Integer height;
    private Integer id;
    private String ipv4Address;
    private String ipv6Address;
    private String mac;
    private String maxPwrConsumed;
    private String model;
    private String uuid;

    /**
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     *            the deviceType to set
     */
    public void setDeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height
     *            the height to set
     */
    public void setHeight(final Integer height) {
        this.height = height;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the ipv4Address
     */
    public String getIpv4Address() {
        return ipv4Address;
    }

    /**
     * @param ipv4Address
     *            the ipv4Address to set
     */
    public void setIpv4Address(final String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    /**
     * @return the ipv6Address
     */
    public String getIpv6Address() {
        return ipv6Address;
    }

    /**
     * @param ipv6Address
     *            the ipv6Address to set
     */
    public void setIpv6Address(final String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    /**
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac
     *            the mac to set
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * @return the maxPwrConsumed
     */
    public String getMaxPwrConsumed() {
        return maxPwrConsumed;
    }

    /**
     * @param maxPwrConsumed
     *            the maxPwrConsumed to set
     */
    public void setMaxPwrConsumed(final String maxPwrConsumed) {
        this.maxPwrConsumed = maxPwrConsumed;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
}
