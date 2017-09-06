/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.FcoeInterconnectSettings;

public class InterconnectSettings extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private EthernetInterconnectSettings ethernetSettings;
    @Since(200)
    private FcoeInterconnectSettings fcoeSettings;

    /**
     *
     * @return The ethernetSettings
     */
    public EthernetInterconnectSettings getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     *
     * @param ethernetSettings
     *            The ethernetSettings to set
     */
    public void setEthernetSettings(final EthernetInterconnectSettings ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     * @return the fcoeSettings
     */
    public FcoeInterconnectSettings getFcoeSettings() {
        return fcoeSettings;
    }

    /**
     * @param fcoeSettings the fcoeSettings to set
     */
    public void setFcoeSettings(FcoeInterconnectSettings fcoeSettings) {
        this.fcoeSettings = fcoeSettings;
    }

}
