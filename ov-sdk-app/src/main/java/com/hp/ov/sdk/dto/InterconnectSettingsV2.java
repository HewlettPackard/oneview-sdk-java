/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class InterconnectSettingsV2 extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private EthernetInterconnectSettingsV2 ethernetSettings;

    /**
     * 
     * @return
     *         The ethernetSettings
     */
    public EthernetInterconnectSettingsV2 getEthernetSettings()
    {
        return ethernetSettings;
    }

    /**
     * 
     * @param ethernetSettings
     *        The ethernetSettings
     */
    public void setEthernetSettings(EthernetInterconnectSettingsV2 ethernetSettings)
    {
        this.ethernetSettings = ethernetSettings;
    }

}
