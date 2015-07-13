/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class Fan implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer bayNumber;
    private DevicePresence devicePresence;
    private Boolean deviceRequired;
    private String model;
    private String partNumber;
    private String sparePartNumber;
    private FanState state;
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
    public void setBayNumber(Integer bayNumber)
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
    public void setDevicePresence(DevicePresence devicePresence)
    {
        this.devicePresence = devicePresence;
    }

    /**
     * @return the deviceRequired
     */
    public Boolean getDeviceRequired()
    {
        return deviceRequired;
    }

    /**
     * @param deviceRequired the deviceRequired to set
     */
    public void setDeviceRequired(Boolean deviceRequired)
    {
        this.deviceRequired = deviceRequired;
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
    public void setModel(String model)
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
    public void setPartNumber(String partNumber)
    {
        this.partNumber = partNumber;
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
    public void setSparePartNumber(String sparePartNumber)
    {
        this.sparePartNumber = sparePartNumber;
    }

    /**
     * @return the state
     */
    public FanState getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(FanState state)
    {
        this.state = state;
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
    public void setStatus(HealthStatus status)
    {
        this.status = status;
    }

}
