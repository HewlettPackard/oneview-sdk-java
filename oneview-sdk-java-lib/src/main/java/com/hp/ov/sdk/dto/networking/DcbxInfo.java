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

package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class DcbxInfo implements Serializable {

    private static final long serialVersionUID = 7637393361536445599L;

    private DcbxReason dcbxApReason;
    private DcbxReason dcbxPfcReason;
    private DcbxReason dcbxPgReason;
    private DcbxStatus dcbxStatus;

    /**
     * @return the dcbxApReason
     */
    public DcbxReason getDcbxApReason() {
        return dcbxApReason;
    }

    /**
     * @param dcbxApReason the dcbxApReason to set
     */
    public void setDcbxApReason(DcbxReason dcbxApReason) {
        this.dcbxApReason = dcbxApReason;
    }

    /**
     * @return the dcbxPfcReason
     */
    public DcbxReason getDcbxPfcReason() {
        return dcbxPfcReason;
    }

    /**
     * @param dcbxPfcReason the dcbxPfcReason to set
     */
    public void setDcbxPfcReason(DcbxReason dcbxPfcReason) {
        this.dcbxPfcReason = dcbxPfcReason;
    }

    /**
     * @return the dcbxPgReason
     */
    public DcbxReason getDcbxPgReason() {
        return dcbxPgReason;
    }

    /**
     * @param dcbxPgReason the dcbxPgReason to set
     */
    public void setDcbxPgReason(DcbxReason dcbxPgReason) {
        this.dcbxPgReason = dcbxPgReason;
    }

    /**
     * @return the dcbxStatus
     */
    public DcbxStatus getDcbxStatus() {
        return dcbxStatus;
    }

    /**
     * @param dcbxStatus the dcbxStatus to set
     */
    public void setDcbxStatus(DcbxStatus dcbxStatus) {
        this.dcbxStatus = dcbxStatus;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
