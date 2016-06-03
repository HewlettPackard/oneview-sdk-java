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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ConnectionBootTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String priority;
    private Boolean specifyBootTarget;
    private List<BootTargetTemplate> targets;

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the specifyBootTarget
     */
    public Boolean getSpecifyBootTarget() {
        return specifyBootTarget;
    }

    /**
     * @param specifyBootTarget the specifyBootTarget to set
     */
    public void setSpecifyBootTarget(Boolean specifyBootTarget) {
        this.specifyBootTarget = specifyBootTarget;
    }

    /**
     * @return the targets
     */
    public List<BootTargetTemplate> getTargets() {
        return targets;
    }

    /**
     * @param targets the targets to set
     */
    public void setTargets(List<BootTargetTemplate> targets) {
        this.targets = targets;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        ConnectionBootTemplate that = (ConnectionBootTemplate) obj;

        return new EqualsBuilder()
                .append(priority, that.priority)
                .append(specifyBootTarget, that.specifyBootTarget)
                .append(targets, that.targets)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(priority)
                .append(specifyBootTarget)
                .append(targets)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("priority", priority)
                .append("specifyBootTarget", specifyBootTarget)
                .append("targets", targets)
                .toString();
    }

}
