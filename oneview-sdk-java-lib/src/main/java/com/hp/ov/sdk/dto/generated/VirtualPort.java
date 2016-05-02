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


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class VirtualPort implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String mac;
    private String portFunction;
    private Integer portNumber;
    private String wwnn;
    private String wwpn;

    /**
     * 
     * @return The mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * 
     * @param mac
     *            The mac
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * 
     * @return The portFunction
     */
    public String getPortFunction() {
        return portFunction;
    }

    /**
     * 
     * @param portFunction
     *            The portFunction
     */
    public void setPortFunction(final String portFunction) {
        this.portFunction = portFunction;
    }

    /**
     * 
     * @return The portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * 
     * @param portNumber
     *            The portNumber
     */
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * 
     * @return The wwnn
     */
    public String getWwnn() {
        return wwnn;
    }

    /**
     * 
     * @param wwnn
     *            The wwnn
     */
    public void setWwnn(final String wwnn) {
        this.wwnn = wwnn;
    }

    /**
     * 
     * @return The wwpn
     */
    public String getWwpn() {
        return wwpn;
    }

    /**
     * 
     * @param wwpn
     *            The wwpn
     */
    public void setWwpn(final String wwpn) {
        this.wwpn = wwpn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(mac).append(portFunction).append(portNumber).append(wwnn).append(wwpn).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VirtualPort) == false) {
            return false;
        }
        final VirtualPort rhs = ((VirtualPort) other);
        return new EqualsBuilder().append(mac, rhs.mac).append(portFunction, rhs.portFunction).append(portNumber, rhs.portNumber)
                .append(wwnn, rhs.wwnn).append(wwpn, rhs.wwpn).isEquals();
    }

}
