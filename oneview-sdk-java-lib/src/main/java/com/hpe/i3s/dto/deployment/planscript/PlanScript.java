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
package com.hpe.i3s.dto.deployment.planscript;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hpe.i3s.dto.deployment.DependentArtifacts;

public class PlanScript extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String content;
    private String customAttributes;
    private List<DependentArtifacts> dependentArtifacts = new ArrayList<>();
    private boolean hpProvided;
    private String id;
    private PlanType planType;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the customAttributes
     */
    public String getCustomAttributes() {
        return customAttributes;
    }

    /**
     * @param customAttributes the customAttributes to set
     */
    public void setCustomAttributes(String customAttributes) {
        this.customAttributes = customAttributes;
    }

    /**
     * @return the dependentArtifacts
     */
    public List<DependentArtifacts> getDependentArtifacts() {
        return dependentArtifacts;
    }

    /**
     * @param dependentArtifacts the dependentArtifacts to set
     */
    public void setDependentArtifacts(List<DependentArtifacts> dependentArtifacts) {
        this.dependentArtifacts = dependentArtifacts;
    }

    /**
     * @return the hpProvided
     */
    public boolean isHpProvided() {
        return hpProvided;
    }

    /**
     * @param hpProvided the hpProvided to set
     */
    public void setHpProvided(boolean hpProvided) {
        this.hpProvided = hpProvided;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the planType
     */
    public PlanType getPlanType() {
        return planType;
    }

    /**
     * @param planType the planType to set
     */
    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
