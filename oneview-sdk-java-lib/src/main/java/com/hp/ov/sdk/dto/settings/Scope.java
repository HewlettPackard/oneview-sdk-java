/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.dto.settings;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class Scope extends BaseModelResource {

    private static final long serialVersionUID = -4533787839183576143L;

    public static final String TYPE = "Scope";

    private String applianceId;
    private Map<String, Object> extAttributes = new HashMap<>();
    private String oldUri;

    /**
     * @return the applianceId
     */
    public String getApplianceId() {
        return applianceId;
    }

    /**
     * @param applianceId the applianceId to set
     */
    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    /**
     * @return the extAttributes
     */
    public Map<String, Object> getExtAttributes() {
        return extAttributes;
    }

    /**
     * @param extAttributes the extAttributes to set
     */
    public void setExtAttributes(Map<String, Object> extAttributes) {
        this.extAttributes = extAttributes;
    }

    /**
     * @return the oldUri
     */
    public String getOldUri() {
        return oldUri;
    }

    /**
     * @param oldUri the oldUri to set
     */
    public void setOldUri(String oldUri) {
        this.oldUri = oldUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
