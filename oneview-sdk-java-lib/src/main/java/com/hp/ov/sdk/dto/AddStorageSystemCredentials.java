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

public class AddStorageSystemCredentials implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String ip_hostname;
    private String password;
    private String username;

    /**
     * 
     * @return The ip_hostname
     */
    public String getIp_hostname() {
        return ip_hostname;
    }

    /**
     * 
     * @param ip_hostname
     *            The ip_hostname
     */
    public void setIp_hostname(final String ip_hostname) {
        this.ip_hostname = ip_hostname;
    }

    /**
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *            The password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *            The username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

}
