/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class RefreshStateConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private RefreshForceOptions refreshForceOptions;
    private RefreshState refreshState;

    public class RefreshForceOptions implements Serializable {
        private static final long serialVersionUID = 1L;
        private String address;
        private String password;
        private String username;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public RefreshForceOptions getRefreshForceOptions() {
        return refreshForceOptions;
    }

    public void setRefreshForceOptions(RefreshForceOptions refreshForceOptions) {
        this.refreshForceOptions = refreshForceOptions;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public RefreshForceOptions getNewRefreshForceOptions() {
        return new RefreshForceOptions();
    }

}
