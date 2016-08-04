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

package com.hp.ov.sdk.dto.networking.logicalswitches;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class StackingPortPair implements Serializable {

    private static final long serialVersionUID = 6351482554483028146L;

    private String destPortUri;
    private String srcPortUri;

    public String getDestPortUri() {
        return destPortUri;
    }

    public void setDestPortUri(String destPortUri) {
        this.destPortUri = destPortUri;
    }

    public String getSrcPortUri() {
        return srcPortUri;
    }

    public void setSrcPortUri(String srcPortUri) {
        this.srcPortUri = srcPortUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        StackingPortPair that = (StackingPortPair) obj;

        return new EqualsBuilder()
                .append(destPortUri, that.destPortUri)
                .append(srcPortUri, that.srcPortUri)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(destPortUri)
                .append(srcPortUri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("destPortUri", destPortUri)
                .append("srcPortUri", srcPortUri)
                .toString();
    }
}
