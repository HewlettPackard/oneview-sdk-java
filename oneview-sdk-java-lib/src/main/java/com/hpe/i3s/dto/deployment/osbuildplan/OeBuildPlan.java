/*******************************************************************************
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
 *******************************************************************************/
package com.hpe.i3s.dto.deployment.osbuildplan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hpe.i3s.dto.deployment.DependentArtifacts;

public class OeBuildPlan extends BaseModelResource {

    public static final String TYPE = "OeBuildPlan";

    private static final long serialVersionUID = 1L;

    private String buildPlanid;
    private List<BuildStep> buildStep = new ArrayList<>();
    private List<CustomAttribute> customAttributes = new ArrayList<>();
    private List<DependentArtifacts> dependentArtifacts = new ArrayList<>();
    private boolean hpProvided;
    private String oeBuildPlanType;

    /**
     * @return the buildPlanid
     */
    public String getBuildPlanid() {
        return buildPlanid;
    }

    /**
     * @param buildPlanid the buildPlanid to set
     */
    public void setBuildPlanid(String buildPlanid) {
        this.buildPlanid = buildPlanid;
    }

    /**
     * @return the buildStep
     */
    public List<BuildStep> getBuildStep() {
        return buildStep;
    }

    /**
     * @param buildStep the buildStep to set
     */
    public void setBuildStep(List<BuildStep> buildStep) {
        this.buildStep = buildStep;
    }

    /**
     * @return the customAttributes
     */
    public List<CustomAttribute> getCustomAttributes() {
        return customAttributes;
    }

    /**
     * @param customAttributes the customAttributes to set
     */
    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
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
     * @return the oeBuildPlanType
     */
    public String getOeBuildPlanType() {
        return oeBuildPlanType;
    }

    /**
     * @param oeBuildPlanType the oeBuildPlanType to set
     */
    public void setOeBuildPlanType(String oeBuildPlanType) {
        this.oeBuildPlanType = oeBuildPlanType;
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
