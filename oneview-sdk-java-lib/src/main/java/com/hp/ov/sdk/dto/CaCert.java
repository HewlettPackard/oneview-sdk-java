/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import org.springframework.stereotype.Component;

@Component
public class CaCert {

    private String caCert;

    public String getCaCert() {
        return caCert;
    }

    public void setCaCert(final String caCert) {
        this.caCert = caCert;
    }

}
