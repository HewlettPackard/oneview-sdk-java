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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.google.gson.annotations.Since;

public class SubportStatistics implements Serializable {

    private final static long serialVersionUID = 1L;

    @Since(200)
    private List<FipSnoopingInfo> fipSnoopingInfo;
    private SubportAdvancedStatistics subportAdvancedStatistics;
    private SubportCommonStatistics subportCommonStatistics;
    private BigInteger subportNumber;
    private String type;

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
     * @return the subportAdvancedStatistics
     */
    public SubportAdvancedStatistics getSubportAdvancedStatistics() {
        return subportAdvancedStatistics;
    }

    /**
     * @param subportAdvancedStatistics the subportAdvancedStatistics to set
     */
    public void setSubportAdvancedStatistics(SubportAdvancedStatistics subportAdvancedStatistics) {
        this.subportAdvancedStatistics = subportAdvancedStatistics;
    }

    /**
     * @return the subportCommonStatistics
     */
    public SubportCommonStatistics getSubportCommonStatistics() {
        return subportCommonStatistics;
    }

    /**
     * @param subportCommonStatistics the subportCommonStatistics to set
     */
    public void setSubportCommonStatistics(SubportCommonStatistics subportCommonStatistics) {
        this.subportCommonStatistics = subportCommonStatistics;
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
