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

package com.hpe.i3s.dto.deployment.deploymentplan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class DeploymentPlan extends BaseModelResource {

    private static final long serialVersionUID = 6976109023570274870L;

    private List<CustomAttribute> customAttributes = new ArrayList<>();
    private String goldenImageURI;
    private Boolean hpProvided;
    private String id;
    private String oeBuildPlanURI;

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
     * @return the goldenImageURI
     */
    public String getGoldenImageURI() {
        return goldenImageURI;
    }

    /**
     * @param goldenImageURI the goldenImageURI to set
     */
    public void setGoldenImageURI(String goldenImageURI) {
        this.goldenImageURI = goldenImageURI;
    }

    /**
     * @return the hpProvided
     */
    public Boolean getHpProvided() {
        return hpProvided;
    }

    /**
     * @param hpProvided the hpProvided to set
     */
    public void setHpProvided(Boolean hpProvided) {
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
     * @return the oeBuildPlanURI
     */
    public String getOeBuildPlanURI() {
        return oeBuildPlanURI;
    }

    /**
     * @param oeBuildPlanURI the oeBuildPlanURI to set
     */
    public void setOeBuildPlanURI(String oeBuildPlanURI) {
        this.oeBuildPlanURI = oeBuildPlanURI;
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
