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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.math.BigInteger;

import com.hp.ov.sdk.dto.BaseModelResource;

public class QosPortStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private BigInteger portNumber;

    private BigInteger cosq0_ucast_DroppedPkts;
    private BigInteger cosq0_ucast_OutBytes;
    private BigInteger cosq0_ucast_OutPkts;

    private BigInteger cosq1_ucast_DroppedPkts;
    private BigInteger cosq1_ucast_OutBytes;
    private BigInteger cosq1_ucast_OutPkts;

    private BigInteger cosq2_ucast_DroppedPkts;
    private BigInteger cosq2_ucast_OutBytes;
    private BigInteger cosq2_ucast_OutPkts;

    private BigInteger cosq3_ucast_DroppedPkts;
    private BigInteger cosq3_ucast_OutBytes;
    private BigInteger cosq3_ucast_OutPkts;

    private BigInteger cosq4_ucast_DroppedPkts;
    private BigInteger cosq4_ucast_OutBytes;
    private BigInteger cosq4_ucast_OutPkts;

    private BigInteger cosq5_ucast_DroppedPkts;
    private BigInteger cosq5_ucast_OutBytes;
    private BigInteger cosq5_ucast_OutPkts;

    private BigInteger cosq6_ucast_DroppedPkts;
    private BigInteger cosq6_ucast_OutBytes;
    private BigInteger cosq6_ucast_OutPkts;

    private BigInteger cosq7_ucast_DroppedPkts;
    private BigInteger cosq7_ucast_OutBytes;
    private BigInteger cosq7_ucast_OutPkts;

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
     * @return the cosq0_ucast_DroppedPkts
     */
    public BigInteger getCosq0_ucast_DroppedPkts() {
        return cosq0_ucast_DroppedPkts;
    }
    /**
     * @param cosq0_ucast_DroppedPkts the cosq0_ucast_DroppedPkts to set
     */
    public void setCosq0_ucast_DroppedPkts(BigInteger cosq0_ucast_DroppedPkts) {
        this.cosq0_ucast_DroppedPkts = cosq0_ucast_DroppedPkts;
    }
    /**
     * @return the cosq0_ucast_OutBytes
     */
    public BigInteger getCosq0_ucast_OutBytes() {
        return cosq0_ucast_OutBytes;
    }
    /**
     * @param cosq0_ucast_OutBytes the cosq0_ucast_OutBytes to set
     */
    public void setCosq0_ucast_OutBytes(BigInteger cosq0_ucast_OutBytes) {
        this.cosq0_ucast_OutBytes = cosq0_ucast_OutBytes;
    }
    /**
     * @return the cosq0_ucast_OutPkts
     */
    public BigInteger getCosq0_ucast_OutPkts() {
        return cosq0_ucast_OutPkts;
    }
    /**
     * @param cosq0_ucast_OutPkts the cosq0_ucast_OutPkts to set
     */
    public void setCosq0_ucast_OutPkts(BigInteger cosq0_ucast_OutPkts) {
        this.cosq0_ucast_OutPkts = cosq0_ucast_OutPkts;
    }
    /**
     * @return the cosq1_ucast_DroppedPkts
     */
    public BigInteger getCosq1_ucast_DroppedPkts() {
        return cosq1_ucast_DroppedPkts;
    }
    /**
     * @param cosq1_ucast_DroppedPkts the cosq1_ucast_DroppedPkts to set
     */
    public void setCosq1_ucast_DroppedPkts(BigInteger cosq1_ucast_DroppedPkts) {
        this.cosq1_ucast_DroppedPkts = cosq1_ucast_DroppedPkts;
    }
    /**
     * @return the cosq1_ucast_OutBytes
     */
    public BigInteger getCosq1_ucast_OutBytes() {
        return cosq1_ucast_OutBytes;
    }
    /**
     * @param cosq1_ucast_OutBytes the cosq1_ucast_OutBytes to set
     */
    public void setCosq1_ucast_OutBytes(BigInteger cosq1_ucast_OutBytes) {
        this.cosq1_ucast_OutBytes = cosq1_ucast_OutBytes;
    }
    /**
     * @return the cosq1_ucast_OutPkts
     */
    public BigInteger getCosq1_ucast_OutPkts() {
        return cosq1_ucast_OutPkts;
    }
    /**
     * @param cosq1_ucast_OutPkts the cosq1_ucast_OutPkts to set
     */
    public void setCosq1_ucast_OutPkts(BigInteger cosq1_ucast_OutPkts) {
        this.cosq1_ucast_OutPkts = cosq1_ucast_OutPkts;
    }
    /**
     * @return the cosq2_ucast_DroppedPkts
     */
    public BigInteger getCosq2_ucast_DroppedPkts() {
        return cosq2_ucast_DroppedPkts;
    }
    /**
     * @param cosq2_ucast_DroppedPkts the cosq2_ucast_DroppedPkts to set
     */
    public void setCosq2_ucast_DroppedPkts(BigInteger cosq2_ucast_DroppedPkts) {
        this.cosq2_ucast_DroppedPkts = cosq2_ucast_DroppedPkts;
    }
    /**
     * @return the cosq2_ucast_OutBytes
     */
    public BigInteger getCosq2_ucast_OutBytes() {
        return cosq2_ucast_OutBytes;
    }
    /**
     * @param cosq2_ucast_OutBytes the cosq2_ucast_OutBytes to set
     */
    public void setCosq2_ucast_OutBytes(BigInteger cosq2_ucast_OutBytes) {
        this.cosq2_ucast_OutBytes = cosq2_ucast_OutBytes;
    }
    /**
     * @return the cosq2_ucast_OutPkts
     */
    public BigInteger getCosq2_ucast_OutPkts() {
        return cosq2_ucast_OutPkts;
    }
    /**
     * @param cosq2_ucast_OutPkts the cosq2_ucast_OutPkts to set
     */
    public void setCosq2_ucast_OutPkts(BigInteger cosq2_ucast_OutPkts) {
        this.cosq2_ucast_OutPkts = cosq2_ucast_OutPkts;
    }
    /**
     * @return the cosq3_ucast_DroppedPkts
     */
    public BigInteger getCosq3_ucast_DroppedPkts() {
        return cosq3_ucast_DroppedPkts;
    }
    /**
     * @param cosq3_ucast_DroppedPkts the cosq3_ucast_DroppedPkts to set
     */
    public void setCosq3_ucast_DroppedPkts(BigInteger cosq3_ucast_DroppedPkts) {
        this.cosq3_ucast_DroppedPkts = cosq3_ucast_DroppedPkts;
    }
    /**
     * @return the cosq3_ucast_OutBytes
     */
    public BigInteger getCosq3_ucast_OutBytes() {
        return cosq3_ucast_OutBytes;
    }
    /**
     * @param cosq3_ucast_OutBytes the cosq3_ucast_OutBytes to set
     */
    public void setCosq3_ucast_OutBytes(BigInteger cosq3_ucast_OutBytes) {
        this.cosq3_ucast_OutBytes = cosq3_ucast_OutBytes;
    }
    /**
     * @return the cosq3_ucast_OutPkts
     */
    public BigInteger getCosq3_ucast_OutPkts() {
        return cosq3_ucast_OutPkts;
    }
    /**
     * @param cosq3_ucast_OutPkts the cosq3_ucast_OutPkts to set
     */
    public void setCosq3_ucast_OutPkts(BigInteger cosq3_ucast_OutPkts) {
        this.cosq3_ucast_OutPkts = cosq3_ucast_OutPkts;
    }
    /**
     * @return the cosq4_ucast_DroppedPkts
     */
    public BigInteger getCosq4_ucast_DroppedPkts() {
        return cosq4_ucast_DroppedPkts;
    }
    /**
     * @param cosq4_ucast_DroppedPkts the cosq4_ucast_DroppedPkts to set
     */
    public void setCosq4_ucast_DroppedPkts(BigInteger cosq4_ucast_DroppedPkts) {
        this.cosq4_ucast_DroppedPkts = cosq4_ucast_DroppedPkts;
    }
    /**
     * @return the cosq4_ucast_OutBytes
     */
    public BigInteger getCosq4_ucast_OutBytes() {
        return cosq4_ucast_OutBytes;
    }
    /**
     * @param cosq4_ucast_OutBytes the cosq4_ucast_OutBytes to set
     */
    public void setCosq4_ucast_OutBytes(BigInteger cosq4_ucast_OutBytes) {
        this.cosq4_ucast_OutBytes = cosq4_ucast_OutBytes;
    }
    /**
     * @return the cosq4_ucast_OutPkts
     */
    public BigInteger getCosq4_ucast_OutPkts() {
        return cosq4_ucast_OutPkts;
    }
    /**
     * @param cosq4_ucast_OutPkts the cosq4_ucast_OutPkts to set
     */
    public void setCosq4_ucast_OutPkts(BigInteger cosq4_ucast_OutPkts) {
        this.cosq4_ucast_OutPkts = cosq4_ucast_OutPkts;
    }
    /**
     * @return the cosq5_ucast_DroppedPkts
     */
    public BigInteger getCosq5_ucast_DroppedPkts() {
        return cosq5_ucast_DroppedPkts;
    }
    /**
     * @param cosq5_ucast_DroppedPkts the cosq5_ucast_DroppedPkts to set
     */
    public void setCosq5_ucast_DroppedPkts(BigInteger cosq5_ucast_DroppedPkts) {
        this.cosq5_ucast_DroppedPkts = cosq5_ucast_DroppedPkts;
    }
    /**
     * @return the cosq5_ucast_OutBytes
     */
    public BigInteger getCosq5_ucast_OutBytes() {
        return cosq5_ucast_OutBytes;
    }
    /**
     * @param cosq5_ucast_OutBytes the cosq5_ucast_OutBytes to set
     */
    public void setCosq5_ucast_OutBytes(BigInteger cosq5_ucast_OutBytes) {
        this.cosq5_ucast_OutBytes = cosq5_ucast_OutBytes;
    }
    /**
     * @return the cosq5_ucast_OutPkts
     */
    public BigInteger getCosq5_ucast_OutPkts() {
        return cosq5_ucast_OutPkts;
    }
    /**
     * @param cosq5_ucast_OutPkts the cosq5_ucast_OutPkts to set
     */
    public void setCosq5_ucast_OutPkts(BigInteger cosq5_ucast_OutPkts) {
        this.cosq5_ucast_OutPkts = cosq5_ucast_OutPkts;
    }
    /**
     * @return the cosq6_ucast_DroppedPkts
     */
    public BigInteger getCosq6_ucast_DroppedPkts() {
        return cosq6_ucast_DroppedPkts;
    }
    /**
     * @param cosq6_ucast_DroppedPkts the cosq6_ucast_DroppedPkts to set
     */
    public void setCosq6_ucast_DroppedPkts(BigInteger cosq6_ucast_DroppedPkts) {
        this.cosq6_ucast_DroppedPkts = cosq6_ucast_DroppedPkts;
    }
    /**
     * @return the cosq6_ucast_OutBytes
     */
    public BigInteger getCosq6_ucast_OutBytes() {
        return cosq6_ucast_OutBytes;
    }
    /**
     * @param cosq6_ucast_OutBytes the cosq6_ucast_OutBytes to set
     */
    public void setCosq6_ucast_OutBytes(BigInteger cosq6_ucast_OutBytes) {
        this.cosq6_ucast_OutBytes = cosq6_ucast_OutBytes;
    }
    /**
     * @return the cosq6_ucast_OutPkts
     */
    public BigInteger getCosq6_ucast_OutPkts() {
        return cosq6_ucast_OutPkts;
    }
    /**
     * @param cosq6_ucast_OutPkts the cosq6_ucast_OutPkts to set
     */
    public void setCosq6_ucast_OutPkts(BigInteger cosq6_ucast_OutPkts) {
        this.cosq6_ucast_OutPkts = cosq6_ucast_OutPkts;
    }
    /**
     * @return the cosq7_ucast_DroppedPkts
     */
    public BigInteger getCosq7_ucast_DroppedPkts() {
        return cosq7_ucast_DroppedPkts;
    }
    /**
     * @param cosq7_ucast_DroppedPkts the cosq7_ucast_DroppedPkts to set
     */
    public void setCosq7_ucast_DroppedPkts(BigInteger cosq7_ucast_DroppedPkts) {
        this.cosq7_ucast_DroppedPkts = cosq7_ucast_DroppedPkts;
    }
    /**
     * @return the cosq7_ucast_OutBytes
     */
    public BigInteger getCosq7_ucast_OutBytes() {
        return cosq7_ucast_OutBytes;
    }
    /**
     * @param cosq7_ucast_OutBytes the cosq7_ucast_OutBytes to set
     */
    public void setCosq7_ucast_OutBytes(BigInteger cosq7_ucast_OutBytes) {
        this.cosq7_ucast_OutBytes = cosq7_ucast_OutBytes;
    }
    /**
     * @return the cosq7_ucast_OutPkts
     */
    public BigInteger getCosq7_ucast_OutPkts() {
        return cosq7_ucast_OutPkts;
    }
    /**
     * @param cosq7_ucast_OutPkts the cosq7_ucast_OutPkts to set
     */
    public void setCosq7_ucast_OutPkts(BigInteger cosq7_ucast_OutPkts) {
        this.cosq7_ucast_OutPkts = cosq7_ucast_OutPkts;
    }

}
