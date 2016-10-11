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

package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.RefreshState;

public class RefreshStateRequest implements Serializable {

    private static final long serialVersionUID = -4044039478220689482L;

    private Boolean force;
    private String hostname;
    private String password;
    private List<RefreshAction> refreshActions = new ArrayList<>();
    private final RefreshState refreshState = RefreshState.RefreshPending;
    private Boolean restore;
    private String username;

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RefreshAction> getRefreshActions() {
        return refreshActions;
    }

    public void setRefreshActions(List<RefreshAction> refreshActions) {
        this.refreshActions = refreshActions;
    }

    public Boolean getRestore() {
        return restore;
    }

    public void setRestore(Boolean restore) {
        this.restore = restore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
