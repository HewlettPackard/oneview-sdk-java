/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class SanPolicy implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Boolean enableAliasing;
    private String initiatorNameFormat;
    private String targetGroupNameFormat;
    private String targetNameFormat;
    private String zoneNameFormat;
    private ZoningPolicy zoningPolicy;

    /**
     * 
     * @return
     *         The enableAliasing
     */
    public Boolean getEnableAliasing()
    {
        return enableAliasing;
    }

    /**
     * 
     * @param enableAliasing
     *        The enableAliasing
     */
    public void setEnableAliasing(final Boolean enableAliasing)
    {
        this.enableAliasing = enableAliasing;
    }

    /**
     * 
     * @return
     *         The initiatorNameFormat
     */
    public String getInitiatorNameFormat()
    {
        return initiatorNameFormat;
    }

    /**
     * 
     * @param initiatorNameFormat
     *        The initiatorNameFormat
     */
    public void setInitiatorNameFormat(final String initiatorNameFormat)
    {
        this.initiatorNameFormat = initiatorNameFormat;
    }

    /**
     * 
     * @return
     *         The targetGroupNameFormat
     */
    public String getTargetGroupNameFormat()
    {
        return targetGroupNameFormat;
    }

    /**
     * 
     * @param targetGroupNameFormat
     *        The targetGroupNameFormat
     */
    public void setTargetGroupNameFormat(final String targetGroupNameFormat)
    {
        this.targetGroupNameFormat = targetGroupNameFormat;
    }

    /**
     * 
     * @return
     *         The targetNameFormat
     */
    public String getTargetNameFormat()
    {
        return targetNameFormat;
    }

    /**
     * 
     * @param targetNameFormat
     *        The targetNameFormat
     */
    public void setTargetNameFormat(final String targetNameFormat)
    {
        this.targetNameFormat = targetNameFormat;
    }

    /**
     * 
     * @return
     *         The zoneNameFormat
     */
    public String getZoneNameFormat()
    {
        return zoneNameFormat;
    }

    /**
     * 
     * @param zoneNameFormat
     *        The zoneNameFormat
     */
    public void setZoneNameFormat(final String zoneNameFormat)
    {
        this.zoneNameFormat = zoneNameFormat;
    }

    /**
     * 
     * @return
     *         The zoningPolicy
     */
    public ZoningPolicy getZoningPolicy()
    {
        return zoningPolicy;
    }

    /**
     * 
     * @param zoningPolicy
     *        The zoningPolicy
     */
    public void setZoningPolicy(final ZoningPolicy zoningPolicy)
    {
        this.zoningPolicy = zoningPolicy;
    }

}
