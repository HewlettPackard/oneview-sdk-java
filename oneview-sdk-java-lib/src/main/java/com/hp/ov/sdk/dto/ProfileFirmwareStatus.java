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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.Set;

import com.hp.ov.sdk.dto.ProfileConnectionStatus.ServerProfileStatus;

public class ProfileFirmwareStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String version;
    private ServerProfileStatus status;
    private Set<MessageInfo> messages;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the status
     */
    public ServerProfileStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ServerProfileStatus status) {
        this.status = status;
    }

    /**
     * @return the messages
     */
    public Set<MessageInfo> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(Set<MessageInfo> messages) {
        this.messages = messages;
    }

}
