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

package com.hp.ov.sdk.dto.networking.logicalswitchgroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

public class LogicalSwitchGroup extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String fabricUri;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private SwitchMapTemplate switchMapTemplate;

    /**
     * @return the fabricUri
     */
    public String getFabricUri() {
        return fabricUri;
    }

    /**
     * @param fabricUri the fabricUri to set
     */
    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    /**
     * @return the switchMapTemplate
     */
    public SwitchMapTemplate getSwitchMapTemplate() {
        return switchMapTemplate;
    }

    /**
     * @param switchMapTemplate the switchMapTemplate to set
     */
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
                .append(scopeUris, that.scopeUris)
                .append(switchMapTemplate, that.switchMapTemplate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(fabricUri)
                .append(scopeUris)
                .append(switchMapTemplate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
