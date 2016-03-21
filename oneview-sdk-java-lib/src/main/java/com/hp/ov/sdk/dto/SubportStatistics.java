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

public class SubportStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    protected BigInteger subportNumber;
    protected SubportCommonStatistics subportCommonStatistics;
    protected SubportAdvancedStatistics subportAdvancedStatistics;
    @Since(200)
    protected List<FipSnoopingInfo> fipSnoopingInfo;

    public BigInteger getSubportNumber() {
        return subportNumber;
    }

    public void setSubportNumber(BigInteger subportNumber) {
        this.subportNumber = subportNumber;
    }

    public SubportCommonStatistics getSubportCommonStatistics() {
        return subportCommonStatistics;
    }

    public void setSubportCommonStatistics(SubportCommonStatistics subportCommonStatistics) {
        this.subportCommonStatistics = subportCommonStatistics;
    }

    public SubportAdvancedStatistics getSubportAdvancedStatistics() {
        return subportAdvancedStatistics;
    }

    public void setSubportAdvancedStatistics(SubportAdvancedStatistics subportAdvancedStatistics) {
        this.subportAdvancedStatistics = subportAdvancedStatistics;
    }

    public List<FipSnoopingInfo> getFipSnoopingInfo() {
        return fipSnoopingInfo;
    }

    public void setFipSnoopingInfo(List<FipSnoopingInfo> fipSnoopingInfo) {
        this.fipSnoopingInfo = fipSnoopingInfo;
    }
}
