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
package com.hp.ov.sdk.dto.generated;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogicalEnclosureContainedEnclosure {

    @Expose
    private String enclosureUri;

    @SerializedName("interconnectBays")
    @Expose
    private Set<LogicalEnclosureInterconnectBay> interconnectBays = new HashSet<LogicalEnclosureInterconnectBay>();

    public String getEnclosureUri() {
        return enclosureUri;
    }

    public void setEnclosureUri(final String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    public Set<LogicalEnclosureInterconnectBay> getInterconnectBays() {
        return interconnectBays;
    }

    public void setInterconnectBays(final Set<LogicalEnclosureInterconnectBay> interconnectBays) {
        this.interconnectBays = interconnectBays;
    }

    public void addInterconnectBay(final LogicalEnclosureInterconnectBay bay) {
        if (interconnectBays == null) {
            interconnectBays = new HashSet<LogicalEnclosureInterconnectBay>();
        }
        if (bay != null) {
            interconnectBays.add(bay);
        }
    }
}
