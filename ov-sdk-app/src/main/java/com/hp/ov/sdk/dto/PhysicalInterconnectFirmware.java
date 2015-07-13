/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class PhysicalInterconnectFirmware implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String desiredFw;
    private String deviceType;
    private String installedFw;
    private String interconnectBayNo;
    private String interconnectName;
    private String interconnectUri;
    private String sppName;
    private String sppUri;
    private FirmwareState state;
    private String updateFlagDesc;

    /**
     * 
     * @return
     *         The desiredFw
     */
    public String getDesiredFw()
    {
        return desiredFw;
    }

    /**
     * 
     * @param desiredFw
     *        The desiredFw
     */
    public void setDesiredFw(String desiredFw)
    {
        this.desiredFw = desiredFw;
    }

    /**
     * 
     * @return
     *         The deviceType
     */
    public String getDeviceType()
    {
        return deviceType;
    }

    /**
     * 
     * @param deviceType
     *        The deviceType
     */
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    /**
     * 
     * @return
     *         The installedFw
     */
    public String getInstalledFw()
    {
        return installedFw;
    }

    /**
     * 
     * @param installedFw
     *        The installedFw
     */
    public void setInstalledFw(String installedFw)
    {
        this.installedFw = installedFw;
    }

    /**
     * 
     * @return
     *         The interconnectBayNo
     */
    public String getInterconnectBayNo()
    {
        return interconnectBayNo;
    }

    /**
     * 
     * @param interconnectBayNo
     *        The interconnectBayNo
     */
    public void setInterconnectBayNo(String interconnectBayNo)
    {
        this.interconnectBayNo = interconnectBayNo;
    }

    /**
     * 
     * @return
     *         The interconnectName
     */
    public String getInterconnectName()
    {
        return interconnectName;
    }

    /**
     * 
     * @param interconnectName
     *        The interconnectName
     */
    public void setInterconnectName(String interconnectName)
    {
        this.interconnectName = interconnectName;
    }

    /**
     * 
     * @return
     *         The interconnectUri
     */
    public String getInterconnectUri()
    {
        return interconnectUri;
    }

    /**
     * 
     * @param interconnectUri
     *        The interconnectUri
     */
    public void setInterconnectUri(String interconnectUri)
    {
        this.interconnectUri = interconnectUri;
    }

    /**
     * 
     * @return
     *         The sppName
     */
    public String getSppName()
    {
        return sppName;
    }

    /**
     * 
     * @param sppName
     *        The sppName
     */
    public void setSppName(String sppName)
    {
        this.sppName = sppName;
    }

    /**
     * 
     * @return
     *         The sppUri
     */
    public String getSppUri()
    {
        return sppUri;
    }

    /**
     * 
     * @param sppUri
     *        The sppUri
     */
    public void setSppUri(String sppUri)
    {
        this.sppUri = sppUri;
    }

    /**
     * 
     * @return
     *         The state
     */
    public FirmwareState getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    public void setState(FirmwareState state)
    {
        this.state = state;
    }

    /**
     * 
     * @return
     *         The updateFlagDesc
     */
    public String getUpdateFlagDesc()
    {
        return updateFlagDesc;
    }

    /**
     * 
     * @param updateFlagDesc
     *        The updateFlagDesc
     */
    public void setUpdateFlagDesc(String updateFlagDesc)
    {
        this.updateFlagDesc = updateFlagDesc;
    }

}
