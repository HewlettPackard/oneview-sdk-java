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

public class SubportCommonStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;
    private BigInteger portNumber;
    private BigInteger subportNumber;
    private BigInteger resetTimeEpoch;
    private BigInteger rfc1213IfInOctets;
    private BigInteger rfc1213IfInUcastPkts;
    private BigInteger rfc1213IfInNUcastPkts;
    private BigInteger rfc1213IfOutOctets;
    private BigInteger rfc1213IfOutUcastPkts;
    private BigInteger rfc1213IfOutNUcastPkts;
    private BigInteger rfc1757StatsOctets;
    private BigInteger rfc1757StatsPkts;

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
     * @return the rfc1213IfInOctets
     */
    public BigInteger getRfc1213IfInOctets() {
        return rfc1213IfInOctets;
    }
    /**
     * @param rfc1213IfInOctets the rfc1213IfInOctets to set
     */
    public void setRfc1213IfInOctets(BigInteger rfc1213IfInOctets) {
        this.rfc1213IfInOctets = rfc1213IfInOctets;
    }
    /**
     * @return the rfc1213IfInUcastPkts
     */
    public BigInteger getRfc1213IfInUcastPkts() {
        return rfc1213IfInUcastPkts;
    }
    /**
     * @param rfc1213IfInUcastPkts the rfc1213IfInUcastPkts to set
     */
    public void setRfc1213IfInUcastPkts(BigInteger rfc1213IfInUcastPkts) {
        this.rfc1213IfInUcastPkts = rfc1213IfInUcastPkts;
    }
    /**
     * @return the rfc1213IfInNUcastPkts
     */
    public BigInteger getRfc1213IfInNUcastPkts() {
        return rfc1213IfInNUcastPkts;
    }
    /**
     * @param rfc1213IfInNUcastPkts the rfc1213IfInNUcastPkts to set
     */
    public void setRfc1213IfInNUcastPkts(BigInteger rfc1213IfInNUcastPkts) {
        this.rfc1213IfInNUcastPkts = rfc1213IfInNUcastPkts;
    }
    /**
     * @return the rfc1213IfOutOctets
     */
    public BigInteger getRfc1213IfOutOctets() {
        return rfc1213IfOutOctets;
    }
    /**
     * @param rfc1213IfOutOctets the rfc1213IfOutOctets to set
     */
    public void setRfc1213IfOutOctets(BigInteger rfc1213IfOutOctets) {
        this.rfc1213IfOutOctets = rfc1213IfOutOctets;
    }
    /**
     * @return the rfc1213IfOutUcastPkts
     */
    public BigInteger getRfc1213IfOutUcastPkts() {
        return rfc1213IfOutUcastPkts;
    }
    /**
     * @param rfc1213IfOutUcastPkts the rfc1213IfOutUcastPkts to set
     */
    public void setRfc1213IfOutUcastPkts(BigInteger rfc1213IfOutUcastPkts) {
        this.rfc1213IfOutUcastPkts = rfc1213IfOutUcastPkts;
    }
    /**
     * @return the rfc1213IfOutNUcastPkts
     */
    public BigInteger getRfc1213IfOutNUcastPkts() {
        return rfc1213IfOutNUcastPkts;
    }
    /**
     * @param rfc1213IfOutNUcastPkts the rfc1213IfOutNUcastPkts to set
     */
    public void setRfc1213IfOutNUcastPkts(BigInteger rfc1213IfOutNUcastPkts) {
        this.rfc1213IfOutNUcastPkts = rfc1213IfOutNUcastPkts;
    }
    /**
     * @return the rfc1757StatsOctets
     */
    public BigInteger getRfc1757StatsOctets() {
        return rfc1757StatsOctets;
    }
    /**
     * @param rfc1757StatsOctets the rfc1757StatsOctets to set
     */
    public void setRfc1757StatsOctets(BigInteger rfc1757StatsOctets) {
        this.rfc1757StatsOctets = rfc1757StatsOctets;
    }
    /**
     * @return the rfc1757StatsPkts
     */
    public BigInteger getRfc1757StatsPkts() {
        return rfc1757StatsPkts;
    }
    /**
     * @param rfc1757StatsPkts the rfc1757StatsPkts to set
     */
    public void setRfc1757StatsPkts(BigInteger rfc1757StatsPkts) {
        this.rfc1757StatsPkts = rfc1757StatsPkts;
    }

}
