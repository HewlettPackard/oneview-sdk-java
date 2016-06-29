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
import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.networking.switches.SwitchManagementConnection;

public class AddLogicalSwitch implements Serializable {

    private static final long serialVersionUID = -7039518459093853177L;

    private LogicalSwitch logicalSwitch;
    private List<SwitchManagementConnection> logicalSwitchCredentials = new ArrayList<>();

    public LogicalSwitch getLogicalSwitch() {
        return logicalSwitch;
    }

    public void setLogicalSwitch(LogicalSwitch logicalSwitch) {
        this.logicalSwitch = logicalSwitch;
    }

    public List<SwitchManagementConnection> getLogicalSwitchCredentials() {
        return logicalSwitchCredentials;
    }

    public void setLogicalSwitchCredentials(List<SwitchManagementConnection> logicalSwitchCredentials) {
        this.logicalSwitchCredentials = logicalSwitchCredentials;
    }
}
