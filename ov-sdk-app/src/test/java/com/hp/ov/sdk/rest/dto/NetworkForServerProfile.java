/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.dto;

import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;

public class NetworkForServerProfile
{
    private String networkName;
    private String requestedMbps;
    private Integer allocatedMbps;
    private Integer maximumMbps;
    private ProfileConnectionV3.FunctionType networkType;
    private BootControl boot;

    public String getNetworkName()
    {
        return networkName;
    }

    public void setNetworkName(String networkName)
    {
        this.networkName = networkName;
    }

    public String getRequestedMbps()
    {
        return requestedMbps;
    }

    public void setRequestedMbps(String requestedMbps)
    {
        this.requestedMbps = requestedMbps;
    }

    public Integer getAllocatedMbps()
    {
        return allocatedMbps;
    }

    public void setAllocatedMbps(Integer allocatedMbps)
    {
        this.allocatedMbps = allocatedMbps;
    }

    public Integer getMaximumMbps()
    {
        return maximumMbps;
    }

    public void setMaximumMbps(Integer maximumMbps)
    {
        this.maximumMbps = maximumMbps;
    }

    public ProfileConnectionV3.FunctionType getNetworkType()
    {
        return networkType;
    }

    public void setNetworkType(ProfileConnectionV3.FunctionType networkType)
    {
        this.networkType = networkType;
    }

    public BootControl getBoot()
    {
        return boot;
    }

    public void setBoot(BootControl boot)
    {
        this.boot = boot;
    }

}
