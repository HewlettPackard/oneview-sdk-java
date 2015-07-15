/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class VirtualPort implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String mac;
    private String portFunction;
    private Integer portNumber;
    private String wwnn;
    private String wwpn;

    /**
     * @return the mac
     */
    public String getMac()
    {
        return mac;
    }

    /**
     * @param mac the mac to set
     */
    public void setMac(final String mac)
    {
        this.mac = mac;
    }

    /**
     * @return the portFunction
     */
    public String getPortFunction()
    {
        return portFunction;
    }

    /**
     * @param portFunction the portFunction to set
     */
    public void setPortFunction(final String portFunction)
    {
        this.portFunction = portFunction;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber()
    {
        return portNumber;
    }

    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(final Integer portNumber)
    {
        this.portNumber = portNumber;
    }

    /**
     * @return the wwnn
     */
    public String getWwnn()
    {
        return wwnn;
    }

    /**
     * @param wwnn the wwnn to set
     */
    public void setWwnn(final String wwnn)
    {
        this.wwnn = wwnn;
    }

    /**
     * @return the wwpn
     */
    public String getWwpn()
    {
        return wwpn;
    }

    /**
     * @param wwpn the wwpn to set
     */
    public void setWwpn(final String wwpn)
    {
        this.wwpn = wwpn;
    }

}
