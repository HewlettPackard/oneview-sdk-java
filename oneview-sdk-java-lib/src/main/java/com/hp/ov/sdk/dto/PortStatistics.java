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

import java.math.BigInteger;
import java.util.List;

import com.google.gson.annotations.Since;

public class PortStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private String portName;
    private BigInteger portNumber;
    private CommonStatistics commonStatistics;
    private AdvancedStatistics advancedStatistics;
    private List<SubportStatistics> subportStatistics;
    private FcStatistics fcStatistics;
    @Since(200)
    private List<FipSnoopingInfo> fipSnoopingInfo;
    @Since(200)
    private FexPortStatistics fexStatistics;
    @Since(200)
    private QosPortStatistics qosPortStatistics;

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public BigInteger getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(BigInteger portNumber) {
        this.portNumber = portNumber;
    }

    public CommonStatistics getCommonStatistics() {
        return commonStatistics;
    }

    public void setCommonStatistics(CommonStatistics commonStatistics) {
        this.commonStatistics = commonStatistics;
    }

    public AdvancedStatistics getAdvancedStatistics() {
        return advancedStatistics;
    }

    public void setAdvancedStatistics(AdvancedStatistics advancedStatistics) {
        this.advancedStatistics = advancedStatistics;
    }

    public List<SubportStatistics> getSubportStatistics() {
        return subportStatistics;
    }

    public void setSubportStatistics(List<SubportStatistics> subportStatistics) {
        this.subportStatistics = subportStatistics;
    }

    public FcStatistics getFcStatistics() {
        return fcStatistics;
    }

    public void setFcStatistics(FcStatistics fcStatistics) {
        this.fcStatistics = fcStatistics;
    }

    public List<FipSnoopingInfo> getFipSnoopingInfo() {
        return fipSnoopingInfo;
    }

    public void setFipSnoopingInfo(List<FipSnoopingInfo> fipSnoopingInfo) {
        this.fipSnoopingInfo = fipSnoopingInfo;
    }

    public FexPortStatistics getFexStatistics() {
        return fexStatistics;
    }

    public void setFexStatistics(FexPortStatistics fexStatistics) {
        this.fexStatistics = fexStatistics;
    }

    public QosPortStatistics getQosPortStatistics() {
        return qosPortStatistics;
    }

    public void setQosPortStatistics(QosPortStatistics qosPortStatistics) {
        this.qosPortStatistics = qosPortStatistics;
    }
}
