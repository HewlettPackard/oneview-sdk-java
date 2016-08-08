/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.alerts;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AssociatedResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private AssociationType associationType;
    private String resourceCategory;
    private String resourceName;
    private String resourceUri;

    /**
     * @return the associationType
     */
    public AssociationType getAssociationType() {
        return associationType;
    }

    /**
     * @param associationType the associationType to set
     */
    public void setAssociationType(AssociationType associationType) {
        this.associationType = associationType;
    }

    /**
     * @return the resourceCategory
     */
    public String getResourceCategory() {
        return resourceCategory;
    }

    /**
     * @param resourceCategory the resourceCategory to set
     */
    public void setResourceCategory(String resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     * @param resourceUri the resourceUri to set
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
