/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class StorageTargetPort extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String actualNetworkUri;
    private String expectedNetworkName;
    private String expectedNetworkUri;
    private String groupName;
    private String portName;
    private String portWwn;
    private RefreshState refreshState;
    private String stateReason;

    /**
     * 
     * @return
     *         The actualNetworkUri
     */
    public String getActualNetworkUri()
    {
        return actualNetworkUri;
    }

    /**
     * 
     * @param actualNetworkUri
     *        The actualNetworkUri
     */
    public void setActualNetworkUri(String actualNetworkUri)
    {
        this.actualNetworkUri = actualNetworkUri;
    }

    /**
     * 
     * @return
     *         The expectedNetworkName
     */
    public String getExpectedNetworkName()
    {
        return expectedNetworkName;
    }

    /**
     * 
     * @param expectedNetworkName
     *        The expectedNetworkName
     */
    public void setExpectedNetworkName(String expectedNetworkName)
    {
        this.expectedNetworkName = expectedNetworkName;
    }

    /**
     * 
     * @return
     *         The expectedNetworkUri
     */
    public String getExpectedNetworkUri()
    {
        return expectedNetworkUri;
    }

    /**
     * 
     * @param expectedNetworkUri
     *        The expectedNetworkUri
     */
    public void setExpectedNetworkUri(String expectedNetworkUri)
    {
        this.expectedNetworkUri = expectedNetworkUri;
    }

    /**
     * 
     * @return
     *         The groupName
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     * 
     * @param groupName
     *        The groupName
     */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * 
     * @return
     *         The portName
     */
    public String getPortName()
    {
        return portName;
    }

    /**
     * 
     * @param portName
     *        The portName
     */
    public void setPortName(String portName)
    {
        this.portName = portName;
    }

    /**
     * 
     * @return
     *         The portWwn
     */
    public String getPortWwn()
    {
        return portWwn;
    }

    /**
     * 
     * @param portWwn
     *        The portWwn
     */
    public void setPortWwn(String portWwn)
    {
        this.portWwn = portWwn;
    }

    /**
     * 
     * @return
     *         The refreshState
     */
    public RefreshState getRefreshState()
    {
        return refreshState;
    }

    /**
     * 
     * @param refreshState
     *        The refreshState
     */
    public void setRefreshState(RefreshState refreshState)
    {
        this.refreshState = refreshState;
    }

    /**
     * 
     * @return
     *         The stateReason
     */
    public String getStateReason()
    {
        return stateReason;
    }

    /**
     * 
     * @param stateReason
     *        The stateReason
     */
    public void setStateReason(String stateReason)
    {
        this.stateReason = stateReason;
    }

}
