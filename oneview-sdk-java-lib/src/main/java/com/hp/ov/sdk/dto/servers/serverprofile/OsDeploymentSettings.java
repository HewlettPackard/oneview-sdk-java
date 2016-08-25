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

package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OsDeploymentSettings implements Serializable{

    private static final long serialVersionUID = 8145922189736788062L;

    private List<OsCustomAttribute> osCustomAttributes = new ArrayList<>();
    private String osDeploymentPlanUri;
    private String osVolumeUri;

    /**
     * @return the osCustomAttributes
     */
    public List<OsCustomAttribute> getOsCustomAttributes() {
        return osCustomAttributes;
    }

    /**
     * @param osCustomAttributes the osCustomAttributes to set
     */
    public void setOsCustomAttributes(List<OsCustomAttribute> osCustomAttributes) {
        this.osCustomAttributes = osCustomAttributes;
    }

    /**
     * @return the osDeploymentPlanUri
     */
    public String getOsDeploymentPlanUri() {
        return osDeploymentPlanUri;
    }

    /**
     * @param osDeploymentPlanUri the osDeploymentPlanUri to set
     */
    public void setOsDeploymentPlanUri(String osDeploymentPlanUri) {
        this.osDeploymentPlanUri = osDeploymentPlanUri;
    }

    /**
     * @return the osVolumeUri
     */
    public String getOsVolumeUri() {
        return osVolumeUri;
    }

    /**
     * @param osVolumeUri the osVolumeUri to set
     */
    public void setOsVolumeUri(String osVolumeUri) {
        this.osVolumeUri = osVolumeUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
