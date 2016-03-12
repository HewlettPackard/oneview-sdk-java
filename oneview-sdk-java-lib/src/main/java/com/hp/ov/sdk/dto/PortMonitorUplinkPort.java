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

public class PortMonitorUplinkPort implements Serializable {

    private static final long serialVersionUID = 1L;

    private String portName;
    private String interconnectName;
    private String uri;

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
     * @return the interconnectName
     */
    public String getInterconnectName() {
        return interconnectName;
    }
    /**
     * @param interconnectName the interconnectName to set
     */
    public void setInterconnectName(String interconnectName) {
        this.interconnectName = interconnectName;
    }
    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }
    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }


}
