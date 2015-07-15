/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class InterconnectBayV2 implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Integer bayNumber;
    private String interconnectModel;
    private String interconnectUri;
    private String logicalInterconnectUri;

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
     * @return the interconnectModel
     */
    public String getInterconnectModel()
    {
        return interconnectModel;
    }

    /**
     * @param interconnectModel the interconnectModel to set
     */
    public void setInterconnectModel(final String interconnectModel)
    {
        this.interconnectModel = interconnectModel;
    }

    /**
     * @return the interconnectUri
     */
    public String getInterconnectUri()
    {
        return interconnectUri;
    }

    /**
     * @param interconnectUri the interconnectUri to set
     */
    public void setInterconnectUri(final String interconnectUri)
    {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the logicalInterconnectUri
     */
    public String getLogicalInterconnectUri()
    {
        return logicalInterconnectUri;
    }

    /**
     * @param logicalInterconnectUri the logicalInterconnectUri to set
     */
    public void setLogicalInterconnectUri(final String logicalInterconnectUri)
    {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

}
