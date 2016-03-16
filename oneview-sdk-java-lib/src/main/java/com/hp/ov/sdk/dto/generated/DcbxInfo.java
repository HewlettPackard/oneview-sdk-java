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

package com.hp.ov.sdk.dto.generated;

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

    public DcbxReason getDcbxApReason() {
        return dcbxApReason;
    }

    public void setDcbxApReason(DcbxReason dcbxApReason) {
        this.dcbxApReason = dcbxApReason;
    }

    public DcbxReason getDcbxPfcReason() {
        return dcbxPfcReason;
    }

    public void setDcbxPfcReason(DcbxReason dcbxPfcReason) {
        this.dcbxPfcReason = dcbxPfcReason;
    }

    public DcbxReason getDcbxPgReason() {
        return dcbxPgReason;
    }

    public void setDcbxPgReason(DcbxReason dcbxPgReason) {
        this.dcbxPgReason = dcbxPgReason;
    }

    public DcbxStatus getDcbxStatus() {
        return dcbxStatus;
    }

    public void setDcbxStatus(DcbxStatus dcbxStatus) {
        this.dcbxStatus = dcbxStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        DcbxInfo dcbxInfo = (DcbxInfo) obj;

        return new EqualsBuilder()
                .append(dcbxApReason, dcbxInfo.dcbxApReason)
                .append(dcbxPfcReason, dcbxInfo.dcbxPfcReason)
                .append(dcbxPgReason, dcbxInfo.dcbxPgReason)
                .append(dcbxStatus, dcbxInfo.dcbxStatus)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(dcbxApReason)
                .append(dcbxPfcReason)
                .append(dcbxPgReason)
                .append(dcbxStatus)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("dcbxApReason", dcbxApReason)
                .append("dcbxPfcReason", dcbxPfcReason)
                .append("dcbxPgReason", dcbxPgReason)
                .append("dcbxStatus", dcbxStatus)
                .toString();
    }

}
