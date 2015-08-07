/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class Signature implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer personalityChecksum;
    private Integer serverHwChecksum;

    /**
     * @return the personalityChecksum
     */
    public Integer getPersonalityChecksum() {
        return personalityChecksum;
    }

    /**
     * @param personalityChecksum
     *            the personalityChecksum to set
     */
    public void setPersonalityChecksum(final Integer personalityChecksum) {
        this.personalityChecksum = personalityChecksum;
    }

    /**
     * @return the serverHwChecksum
     */
    public Integer getServerHwChecksum() {
        return serverHwChecksum;
    }

    /**
     * @param serverHwChecksum
     *            the serverHwChecksum to set
     */
    public void setServerHwChecksum(final Integer serverHwChecksum) {
        this.serverHwChecksum = serverHwChecksum;
    }

}
