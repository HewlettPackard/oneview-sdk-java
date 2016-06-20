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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SettingOptionLink {

    private SettingOptionLinkAction action;
    private String optionId;
    private String settingId;

    public SettingOptionLinkAction getAction() {
        return action;
    }

    public void setAction(SettingOptionLinkAction action) {
        this.action = action;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof  SettingOptionLink) {
            SettingOptionLink that = (SettingOptionLink) obj;

            return new EqualsBuilder()
                    .append(action, that.action)
                    .append(optionId, that.optionId)
                    .append(settingId, that.settingId)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(action)
                .append(optionId)
                .append(settingId)
                .toHashCode();
    }
}
