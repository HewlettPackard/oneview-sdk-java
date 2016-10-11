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
package com.hp.ov.sdk.dto.networking;

import java.util.List;

import com.hp.ov.sdk.dto.BaseModelResource;

public class FexPortStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private FexAdvancedStatistics fexAdvancedStatistics;
    private FexCommonStatistics commonStatistics;
    private boolean isUplink;
    private List<String> downlinks;
    private List<VlanInfo> vlanId;

    /**
     * @return the fexAdvancedStatistics
     */
    public FexAdvancedStatistics getFexAdvancedStatistics() {
        return fexAdvancedStatistics;
    }
    
    /**
     * @param fexAdvancedStatistics the fexAdvancedStatistics to set
     */
    public void setFexAdvancedStatistics(FexAdvancedStatistics fexAdvancedStatistics) {
        this.fexAdvancedStatistics = fexAdvancedStatistics;
    }
    
    /**
     * @return the commonStatistics
     */
    public FexCommonStatistics getCommonStatistics() {
        return commonStatistics;
    }
    
    /**
     * @param commonStatistics the commonStatistics to set
     */
    public void setCommonStatistics(FexCommonStatistics commonStatistics) {
        this.commonStatistics = commonStatistics;
    }
    
    /**
     * @return the isUplink
     */
    public boolean isUplink() {
        return isUplink;
    }
    
    /**
     * @param isUplink the isUplink to set
     */
    
    public void setUplink(boolean isUplink) {
        this.isUplink = isUplink;
    }
    
    /**
     * @return the downlinks
     */
    public List<String> getDownlinks() {
        return downlinks;
    }
    
    /**
     * @param downlinks the downlinks to set
     */
    public void setDownlinks(List<String> downlinks) {
        this.downlinks = downlinks;
    }
    
    /**
     * @return the vlanId
     */
    public List<VlanInfo> getVlanId() {
        return vlanId;
    }
    
    /**
     * @param vlanId the vlanId to set
     */
    public void setVlanId(List<VlanInfo> vlanId) {
        this.vlanId = vlanId;
    }

}
