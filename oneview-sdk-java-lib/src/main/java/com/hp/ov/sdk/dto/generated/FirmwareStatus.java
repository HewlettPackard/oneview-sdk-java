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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class FirmwareStatus implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    private String status;
    /**
     * 
     * (Required)
     * 
     */
    private List<Message> messages = new ArrayList<Message>();
    /**
     * 
     * (Required)
     * 
     */
    private String version;
    /**
     * 
     * (Required)
     * 
     */
    private String name;

    /**
     * 
     * (Required)
     * 
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * (Required)
     * 
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * (Required)
     * 
     * @return The messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * 
     * (Required)
     * 
     * @param messages
     *            The messages
     */
    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }

    /**
     * 
     * (Required)
     * 
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * (Required)
     * 
     * @param version
     *            The version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * 
     * (Required)
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(messages).append(version).append(name).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FirmwareStatus) == false) {
            return false;
        }
        final FirmwareStatus rhs = ((FirmwareStatus) other);
        return new EqualsBuilder().append(status, rhs.status).append(messages, rhs.messages).append(version, rhs.version)
                .append(name, rhs.name).isEquals();
    }

}
