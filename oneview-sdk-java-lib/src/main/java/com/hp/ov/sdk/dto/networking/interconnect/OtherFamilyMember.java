/*******************************************************************************
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.networking.interconnect;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OtherFamilyMember implements Serializable {

    private static final long serialVersionUID = 4281089940388982524L;

    private String modelName;
    private String partNumber;
    private Boolean taaCompliant;

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName
     *            the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber
     *            the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the taaCompliant
     */
    public Boolean getTaaCompliant() {
        return taaCompliant;
    }

    /**
     * @param taaCompliant
     *            the taaCompliant to set
     */
    public void setTaaCompliant(Boolean taaCompliant) {
        this.taaCompliant = taaCompliant;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof OtherFamilyMember) == false) {
            return false;
        }
        final OtherFamilyMember that = (OtherFamilyMember) obj;
        return new EqualsBuilder()
                .append(modelName, that.modelName)
                .append(partNumber, that.partNumber)
                .append(taaCompliant, that.taaCompliant)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(modelName)
                .append(partNumber)
                .append(taaCompliant)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
