/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.samples;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ResourceDataList;

@Component
public class MsmbMessage {

    // TODO - this is sample properties. This can be extended as per user needs.
    private String resourceUri;
    private List<ResourceDataList> resourceDataList = new ArrayList<ResourceDataList>();

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(final String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public List<ResourceDataList> getResourceDataList() {
        return resourceDataList;
    }

    public void setResourceDataList(final List<ResourceDataList> resourceDataList) {
        this.resourceDataList = resourceDataList;
    }

}
