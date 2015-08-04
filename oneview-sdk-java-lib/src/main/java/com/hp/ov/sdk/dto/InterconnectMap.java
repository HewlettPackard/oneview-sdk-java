/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InterconnectMap implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<InterconnectMapEntry> interconnectMapEntries = new ArrayList<InterconnectMapEntry>();

    public List<InterconnectMapEntry> getInterconnectMapEntries() {
        return interconnectMapEntries;
    }

    public void setInterconnectMapEntries(final List<InterconnectMapEntry> interconnectMapEntries) {
        this.interconnectMapEntries = interconnectMapEntries;
    }

}
