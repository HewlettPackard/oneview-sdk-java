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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ProfileConnectionStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private long connectionId = -1;
    private ServerProfileStatus status;
    private Set<MessageInfo> messages;

    /**
     * @return the connectionId
     */
    public long getConnectionId() {
        return connectionId;
    }

    /**
     * @param connectionId the connectionId to set
     */
    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
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

    public static enum ServerProfileStatus {

        Unknown("Unknown"),
        OK("OK"),
        Warning("Warning"),
        Critical("Critical");

        private final String value;
        private static Map<String, ProfileConnectionStatus.ServerProfileStatus> constants = new HashMap<String, ProfileConnectionStatus.ServerProfileStatus>();

        static {
            for (final ProfileConnectionStatus.ServerProfileStatus c : values()) {
                constants.put(c.value, c);
            }
        }

        private ServerProfileStatus(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static ProfileConnectionStatus.ServerProfileStatus fromValue(final String value) {
            final ProfileConnectionStatus.ServerProfileStatus constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }
}
