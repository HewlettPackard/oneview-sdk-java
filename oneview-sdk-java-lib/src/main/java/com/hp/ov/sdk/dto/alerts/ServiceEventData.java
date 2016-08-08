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
package com.hp.ov.sdk.dto.alerts;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ServiceEventData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String caseId;
    private String primaryContact;
    private RemoteSupportState remoteSupportState = RemoteSupportState.None;

    /**
     * @return the caseId
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * @param caseId the caseId to set
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * @return the primaryContact
     */
    public String getPrimaryContact() {
        return primaryContact;
    }

    /**
     * @param primaryContact the primaryContact to set
     */
    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    /**
     * @return the remoteSupportState
     */
    public RemoteSupportState getRemoteSupportState() {
        return remoteSupportState;
    }

    /**
     * @param remoteSupportState the remoteSupportState to set
     */
    public void setRemoteSupportState(RemoteSupportState remoteSupportState) {
        this.remoteSupportState = remoteSupportState;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
