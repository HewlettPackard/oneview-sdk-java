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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.networking.FexPortStatistics;

public class PortStatistics implements Serializable {

    private final static long serialVersionUID = 1L;

    private AdvancedStatistics advancedStatistics;
    private CommonStatistics commonStatistics;
    private FcStatistics fcStatistics;
    @Since(200)
    private List<FipSnoopingInfo> fipSnoopingInfo;
    @Since(200)
    private FexPortStatistics fexStatistics;
    private String portName;
    private BigInteger portNumber;
    @Since(200)
    private QosPortStatistics qosPortStatistics;
    private List<SubportStatistics> subportStatistics;
    private String type;

    /**
     * @return the advancedStatistics
     */
    public AdvancedStatistics getAdvancedStatistics() {
        return advancedStatistics;
    }

    /**
     * @param advancedStatistics the advancedStatistics to set
     */
    public void setAdvancedStatistics(AdvancedStatistics advancedStatistics) {
        this.advancedStatistics = advancedStatistics;
    }

    /**
     * @return the commonStatistics
     */
    public CommonStatistics getCommonStatistics() {
        return commonStatistics;
    }

    /**
     * @param commonStatistics the commonStatistics to set
     */
    public void setCommonStatistics(CommonStatistics commonStatistics) {
        this.commonStatistics = commonStatistics;
    }

    /**
     * @return the fcStatistics
     */
    public FcStatistics getFcStatistics() {
        return fcStatistics;
    }

    /**
     * @param fcStatistics the fcStatistics to set
     */
    public void setFcStatistics(FcStatistics fcStatistics) {
        this.fcStatistics = fcStatistics;
    }

    /**
     * @return the fipSnoopingInfo
     */
    public List<FipSnoopingInfo> getFipSnoopingInfo() {
        return fipSnoopingInfo;
    }

    /**
     * @param fipSnoopingInfo the fipSnoopingInfo to set
     */
    public void setFipSnoopingInfo(List<FipSnoopingInfo> fipSnoopingInfo) {
        this.fipSnoopingInfo = fipSnoopingInfo;
    }

    /**
     * @return the fexStatistics
     */
    public FexPortStatistics getFexStatistics() {
        return fexStatistics;
    }

    /**
     * @param fexStatistics the fexStatistics to set
     */
    public void setFexStatistics(FexPortStatistics fexStatistics) {
        this.fexStatistics = fexStatistics;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

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
     * @return the qosPortStatistics
     */
    public QosPortStatistics getQosPortStatistics() {
        return qosPortStatistics;
    }

    /**
     * @param qosPortStatistics the qosPortStatistics to set
     */
    public void setQosPortStatistics(QosPortStatistics qosPortStatistics) {
        this.qosPortStatistics = qosPortStatistics;
    }

    /**
     * @return the subportStatistics
     */
    public List<SubportStatistics> getSubportStatistics() {
        return subportStatistics;
    }

    /**
     * @param subportStatistics the subportStatistics to set
     */
    public void setSubportStatistics(List<SubportStatistics> subportStatistics) {
        this.subportStatistics = subportStatistics;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
