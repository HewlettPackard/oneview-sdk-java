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

public class InternalVlanAssociation extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String generalNetworkUri;
    private Integer internalVlanId;
    private String logicalInterconnectUri;

    /**
     * @return the internalVlanId
     */
    public Integer getInternalVlanId() {
        return internalVlanId;
    }
    /**
     * @param internalVlanId the internalVlanId to set
     */
    public void setInternalVlanId(Integer internalVlanId) {
        this.internalVlanId = internalVlanId;
    }
    /**
     * @return the generalNetworkUri
     */
    public String getGeneralNetworkUri() {
        return generalNetworkUri;
    }
    /**
     * @param generalNetworkUri the generalNetworkUri to set
     */
    public void setGeneralNetworkUri(String generalNetworkUri) {
        this.generalNetworkUri = generalNetworkUri;
    }
    /**
     * @return the logicalInterconnectUri
     */
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }
    /**
     * @param logicalInterconnectUri the logicalInterconnectUri to set
     */
    public void setLogicalInterconnectUri(String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

}
