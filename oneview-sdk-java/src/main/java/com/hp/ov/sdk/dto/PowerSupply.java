/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class PowerSupply implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer bayNumber;
    private DevicePresence devicePresence;
    private String model;
    private String partNumber;
    private String serialNumber;
    private String sparePartNumber;
    private HealthStatus status;

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber()
    {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(final Integer bayNumber)
    {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the devicePresence
     */
    public DevicePresence getDevicePresence()
    {
        return devicePresence;
    }

    /**
     * @param devicePresence the devicePresence to set
     */
    public void setDevicePresence(final DevicePresence devicePresence)
    {
        this.devicePresence = devicePresence;
    }

    /**
     * @return the model
     */
    public String getModel()
    {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(final String model)
    {
        this.model = model;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber()
    {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(final String partNumber)
    {
        this.partNumber = partNumber;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber()
    {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(final String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the sparePartNumber
     */
    public String getSparePartNumber()
    {
        return sparePartNumber;
    }

    /**
     * @param sparePartNumber the sparePartNumber to set
     */
    public void setSparePartNumber(final String sparePartNumber)
    {
        this.sparePartNumber = sparePartNumber;
    }

    /**
     * @return the status
     */
    public HealthStatus getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final HealthStatus status)
    {
        this.status = status;
    }

}
