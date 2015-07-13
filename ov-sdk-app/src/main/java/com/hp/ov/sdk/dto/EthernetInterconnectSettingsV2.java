/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class EthernetInterconnectSettingsV2 extends BaseModelResource
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String dependentResourceUri;
    private Boolean enableFastMacCacheFailover;
    private Boolean enableIgmpSnooping;
    private Boolean enableNetworkLoopProtection;
    private Boolean enablePauseFloodProtection;
    private String id;
    private Integer igmpIdleTimeoutInterval;
    private NetworkType interconnectType;
    private Integer macRefreshInterval;

    /**
     * @return the dependentResourceUri
     */
    public String getDependentResourceUri()
    {
        return dependentResourceUri;
    }

    /**
     * @param dependentResourceUri the dependentResourceUri to set
     */
    public void setDependentResourceUri(String dependentResourceUri)
    {
        this.dependentResourceUri = dependentResourceUri;
    }

    /**
     * @return the enableFastMacCacheFailover
     */
    public Boolean getEnableFastMacCacheFailover()
    {
        return enableFastMacCacheFailover;
    }

    /**
     * @param enableFastMacCacheFailover the enableFastMacCacheFailover to set
     */
    public void setEnableFastMacCacheFailover(Boolean enableFastMacCacheFailover)
    {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     * @return the enableIgmpSnooping
     */
    public Boolean getEnableIgmpSnooping()
    {
        return enableIgmpSnooping;
    }

    /**
     * @param enableIgmpSnooping the enableIgmpSnooping to set
     */
    public void setEnableIgmpSnooping(Boolean enableIgmpSnooping)
    {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     * @return the enableNetworkLoopProtection
     */
    public Boolean getEnableNetworkLoopProtection()
    {
        return enableNetworkLoopProtection;
    }

    /**
     * @param enableNetworkLoopProtection the enableNetworkLoopProtection to set
     */
    public void setEnableNetworkLoopProtection(Boolean enableNetworkLoopProtection)
    {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     * @return the enablePauseFloodProtection
     */
    public Boolean getEnablePauseFloodProtection()
    {
        return enablePauseFloodProtection;
    }

    /**
     * @param enablePauseFloodProtection the enablePauseFloodProtection to set
     */
    public void setEnablePauseFloodProtection(Boolean enablePauseFloodProtection)
    {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the igmpIdleTimeoutInterval
     */
    public Integer getIgmpIdleTimeoutInterval()
    {
        return igmpIdleTimeoutInterval;
    }

    /**
     * @param igmpIdleTimeoutInterval the igmpIdleTimeoutInterval to set
     */
    public void setIgmpIdleTimeoutInterval(Integer igmpIdleTimeoutInterval)
    {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     * @return the networkType
     */
    public NetworkType getNetworkType()
    {
        return interconnectType;
    }

    /**
     * @param networkType the networkType to set
     */
    public void setNetworkType(NetworkType interconnectType)
    {
        this.interconnectType = interconnectType;
    }

    /**
     * @return the macRefreshInterval
     */
    public Integer getMacRefreshInterval()
    {
        return macRefreshInterval;
    }

    /**
     * @param macRefreshInterval the macRefreshInterval to set
     */
    public void setMacRefreshInterval(Integer macRefreshInterval)
    {
        this.macRefreshInterval = macRefreshInterval;
    }

}
