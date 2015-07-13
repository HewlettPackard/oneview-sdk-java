/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import org.springframework.stereotype.Component;

@Component
public class CaCert
{

    private String caCert;

    public String getCaCert()
    {
        return caCert;
    }

    public void setCaCert(String caCert)
    {
        this.caCert = caCert;
    }

}
