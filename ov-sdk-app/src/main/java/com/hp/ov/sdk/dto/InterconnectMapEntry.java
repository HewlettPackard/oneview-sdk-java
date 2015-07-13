/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class InterconnectMapEntry implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String interconnectUri;
    private Location location;
    private String logicalDownlinkUri;
    private String permittedInterconnectTypeUri;

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
    public void setInterconnectUri(String interconnectUri)
    {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * @return the logicalDownlinkUri
     */
    public String getLogicalDownlinkUri()
    {
        return logicalDownlinkUri;
    }

    /**
     * @param logicalDownlinkUri the logicalDownlinkUri to set
     */
    public void setLogicalDownlinkUri(String logicalDownlinkUri)
    {
        this.logicalDownlinkUri = logicalDownlinkUri;
    }

    /**
     * @return the permittedInterconnectTypeUri
     */
    public String getPermittedInterconnectTypeUri()
    {
        return permittedInterconnectTypeUri;
    }

    /**
     * @param permittedInterconnectTypeUri the permittedInterconnectTypeUri to
     *        set
     */
    public void setPermittedInterconnectTypeUri(String permittedInterconnectTypeUri)
    {
        this.permittedInterconnectTypeUri = permittedInterconnectTypeUri;
    }

}
