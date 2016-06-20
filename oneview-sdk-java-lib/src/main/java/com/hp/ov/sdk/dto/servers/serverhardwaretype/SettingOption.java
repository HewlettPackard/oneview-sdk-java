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

package com.hp.ov.sdk.dto.servers.serverhardwaretype;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SettingOption {

    private String id;
    private String name;
    private List<SettingOptionLink> optionLinks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SettingOptionLink> getOptionLinks() {
        return optionLinks;
    }

    public void setOptionLinks(List<SettingOptionLink> optionLinks) {
        this.optionLinks = optionLinks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof SettingOption) {
            SettingOption that = (SettingOption) obj;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .append(name, that.name)
                    .append(optionLinks, that.optionLinks)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(optionLinks)
                .toHashCode();
    }
}
