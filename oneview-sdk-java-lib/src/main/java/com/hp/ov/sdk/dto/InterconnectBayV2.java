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

public class InterconnectBayV2 implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Integer bayNumber;
    private String interconnectModel;
    private String interconnectUri;
    private String logicalInterconnectUri;

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber
     *            the bayNumber to set
     */
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the interconnectModel
     */
    public String getInterconnectModel() {
        return interconnectModel;
    }

    /**
     * @param interconnectModel
     *            the interconnectModel to set
     */
    public void setInterconnectModel(final String interconnectModel) {
        this.interconnectModel = interconnectModel;
    }

    /**
     * @return the interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * @param interconnectUri
     *            the interconnectUri to set
     */
    public void setInterconnectUri(final String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the logicalInterconnectUri
     */
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }

    /**
     * @param logicalInterconnectUri
     *            the logicalInterconnectUri to set
     */
    public void setLogicalInterconnectUri(final String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

}
