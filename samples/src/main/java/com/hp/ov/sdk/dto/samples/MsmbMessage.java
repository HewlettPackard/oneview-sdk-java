/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.dto.samples;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.ResourceDataList;


public class MsmbMessage {

    // This is a simple DTO, it can be extended as per user needs.
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
