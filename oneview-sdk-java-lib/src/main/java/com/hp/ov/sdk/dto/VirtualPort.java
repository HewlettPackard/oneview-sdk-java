/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac
     *            the mac to set
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * @return the portFunction
     */
    public String getPortFunction() {
        return portFunction;
    }

    /**
     * @param portFunction
     *            the portFunction to set
     */
    public void setPortFunction(final String portFunction) {
        this.portFunction = portFunction;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber
     *            the portNumber to set
     */
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return the wwnn
     */
    public String getWwnn() {
        return wwnn;
    }

    /**
     * @param wwnn
     *            the wwnn to set
     */
    public void setWwnn(final String wwnn) {
        this.wwnn = wwnn;
    }

    /**
     * @return the wwpn
     */
    public String getWwpn() {
        return wwpn;
    }

    /**
     * @param wwpn
     *            the wwpn to set
     */
    public void setWwpn(final String wwpn) {
        this.wwpn = wwpn;
    }

}
