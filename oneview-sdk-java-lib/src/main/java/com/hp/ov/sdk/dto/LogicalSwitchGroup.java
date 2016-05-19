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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogicalSwitchGroup extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String fabricUri;
    private SwitchMapTemplate switchMapTemplate;

    public String getFabricUri() {
        return fabricUri;
    }

    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    public SwitchMapTemplate getSwitchMapTemplate() {
        return switchMapTemplate;
    }

    public void setSwitchMapTemplate(SwitchMapTemplate switchMapTemplate) {
        this.switchMapTemplate = switchMapTemplate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        LogicalSwitchGroup that = (LogicalSwitchGroup) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(fabricUri, that.fabricUri)
                .append(switchMapTemplate, that.switchMapTemplate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(fabricUri)
                .append(switchMapTemplate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("fabricUri", fabricUri)
                .append("switchMapTemplate", switchMapTemplate)
                .toString();
    }
}
