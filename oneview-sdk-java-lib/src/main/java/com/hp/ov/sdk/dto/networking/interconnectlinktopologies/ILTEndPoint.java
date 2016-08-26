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
package com.hp.ov.sdk.dto.networking.interconnectlinktopologies;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ILTEndPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private int bayNumber;
    private int baySetNumber;
    private String baySetSide;
    private String enclosureUri;
    private ILTError errorCode;
    private String interconnectProductName;
    private String interconnectUri;
    private int portId;

    /**
     * @return the bayNumber
     */
    public int getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(int bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the baySetNumber
     */
    public int getBaySetNumber() {
        return baySetNumber;
    }

    /**
     * @param baySetNumber the baySetNumber to set
     */
    public void setBaySetNumber(int baySetNumber) {
        this.baySetNumber = baySetNumber;
    }

    /**
     * @return the baySetSide
     */
    public String getBaySetSide() {
        return baySetSide;
    }

    /**
     * @param baySetSide the baySetSide to set
     */
    public void setBaySetSide(String baySetSide) {
        this.baySetSide = baySetSide;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     * @return the errorCode
     */
    public ILTError getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(ILTError errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the interconnectProductName
     */
    public String getInterconnectProductName() {
        return interconnectProductName;
    }

    /**
     * @param interconnectProductName the interconnectProductName to set
     */
    public void setInterconnectProductName(String interconnectProductName) {
        this.interconnectProductName = interconnectProductName;
    }

    /**
     * @return the interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * @param interconnectUri the interconnectUri to set
     */
    public void setInterconnectUri(String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the portId
     */
    public int getPortId() {
        return portId;
    }

    /**
     * @param portId the portId to set
     */
    public void setPortId(int portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(final Object obj) {
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
