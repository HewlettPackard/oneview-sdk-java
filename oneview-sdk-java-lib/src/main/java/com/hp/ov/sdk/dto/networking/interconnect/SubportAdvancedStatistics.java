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
package com.hp.ov.sdk.dto.networking.interconnect;

import java.math.BigInteger;

import com.hp.ov.sdk.dto.BaseModelResource;

public class SubportAdvancedStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private BigInteger portNumber;
    private BigInteger subportNumber;
    private BigInteger resetTimeEpoch;
    private BigInteger linkChanges;
    private String receiveKilobitsPerSec;
    private String transmitKilobitsPerSec;
    private String receiveKilobytesPerSec;
    private String transmitKilobytesPerSec;
    private String receivePacketsPerSec;
    private String transmitPacketsPerSec;
    private String receiveNonunicastPacketsPerSec;
    private String transmitNonunicastPacketsPerSec;

    /**
     * @return the portNumber
     */
    public BigInteger getPortNumber() {
        return portNumber;
    }
    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(BigInteger portNumber) {
        this.portNumber = portNumber;
    }
    /**
     * @return the subportNumber
     */
    public BigInteger getSubportNumber() {
        return subportNumber;
    }
    /**
     * @param subportNumber the subportNumber to set
     */
    public void setSubportNumber(BigInteger subportNumber) {
        this.subportNumber = subportNumber;
    }
    /**
     * @return the resetTimeEpoch
     */
    public BigInteger getResetTimeEpoch() {
        return resetTimeEpoch;
    }
    /**
     * @param resetTimeEpoch the resetTimeEpoch to set
     */
    public void setResetTimeEpoch(BigInteger resetTimeEpoch) {
        this.resetTimeEpoch = resetTimeEpoch;
    }
    /**
     * @return the linkChanges
     */
    public BigInteger getLinkChanges() {
        return linkChanges;
    }
    /**
     * @param linkChanges the linkChanges to set
     */
    public void setLinkChanges(BigInteger linkChanges) {
        this.linkChanges = linkChanges;
    }
    /**
     * @return the receiveKilobitsPerSec
     */
    public String getReceiveKilobitsPerSec() {
        return receiveKilobitsPerSec;
    }
    /**
     * @param receiveKilobitsPerSec the receiveKilobitsPerSec to set
     */
    public void setReceiveKilobitsPerSec(String receiveKilobitsPerSec) {
        this.receiveKilobitsPerSec = receiveKilobitsPerSec;
    }
    /**
     * @return the transmitKilobitsPerSec
     */
    public String getTransmitKilobitsPerSec() {
        return transmitKilobitsPerSec;
    }
    /**
     * @param transmitKilobitsPerSec the transmitKilobitsPerSec to set
     */
    public void setTransmitKilobitsPerSec(String transmitKilobitsPerSec) {
        this.transmitKilobitsPerSec = transmitKilobitsPerSec;
    }
    /**
     * @return the receiveKilobytesPerSec
     */
    public String getReceiveKilobytesPerSec() {
        return receiveKilobytesPerSec;
    }
    /**
     * @param receiveKilobytesPerSec the receiveKilobytesPerSec to set
     */
    public void setReceiveKilobytesPerSec(String receiveKilobytesPerSec) {
        this.receiveKilobytesPerSec = receiveKilobytesPerSec;
    }
    /**
     * @return the transmitKilobytesPerSec
     */
    public String getTransmitKilobytesPerSec() {
        return transmitKilobytesPerSec;
    }
    /**
     * @param transmitKilobytesPerSec the transmitKilobytesPerSec to set
     */
    public void setTransmitKilobytesPerSec(String transmitKilobytesPerSec) {
        this.transmitKilobytesPerSec = transmitKilobytesPerSec;
    }
    /**
     * @return the receivePacketsPerSec
     */
    public String getReceivePacketsPerSec() {
        return receivePacketsPerSec;
    }
    /**
     * @param receivePacketsPerSec the receivePacketsPerSec to set
     */
    public void setReceivePacketsPerSec(String receivePacketsPerSec) {
        this.receivePacketsPerSec = receivePacketsPerSec;
    }
    /**
     * @return the transmitPacketsPerSec
     */
    public String getTransmitPacketsPerSec() {
        return transmitPacketsPerSec;
    }
    /**
     * @param transmitPacketsPerSec the transmitPacketsPerSec to set
     */
    public void setTransmitPacketsPerSec(String transmitPacketsPerSec) {
        this.transmitPacketsPerSec = transmitPacketsPerSec;
    }
    /**
     * @return the receiveNonunicastPacketsPerSec
     */
    public String getReceiveNonunicastPacketsPerSec() {
        return receiveNonunicastPacketsPerSec;
    }
    /**
     * @param receiveNonunicastPacketsPerSec the receiveNonunicastPacketsPerSec to set
     */
    public void setReceiveNonunicastPacketsPerSec(String receiveNonunicastPacketsPerSec) {
        this.receiveNonunicastPacketsPerSec = receiveNonunicastPacketsPerSec;
    }
    /**
     * @return the transmitNonunicastPacketsPerSec
     */
    public String getTransmitNonunicastPacketsPerSec() {
        return transmitNonunicastPacketsPerSec;
    }
    /**
     * @param transmitNonunicastPacketsPerSec the transmitNonunicastPacketsPerSec to set
     */
    public void setTransmitNonunicastPacketsPerSec(String transmitNonunicastPacketsPerSec) {
        this.transmitNonunicastPacketsPerSec = transmitNonunicastPacketsPerSec;
    }

}
