/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class BootTarget implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String arrayWwpn;
    private String lun;

    /**
     * 
     * @return
     *         The arrayWwpn
     */
    public String getArrayWwpn()
    {
        return arrayWwpn;
    }

    /**
     * 
     * @param arrayWwpn
     *        The arrayWwpn
     */
    public void setArrayWwpn(final String arrayWwpn)
    {
        this.arrayWwpn = arrayWwpn;
    }

    /**
     * 
     * @return
     *         The lun
     */
    public String getLun()
    {
        return lun;
    }

    /**
     * 
     * @param lun
     *        The lun
     */
    public void setLun(final String lun)
    {
        this.lun = lun;
    }

}
