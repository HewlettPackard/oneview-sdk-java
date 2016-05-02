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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class Signature implements Serializable {

    private static final long serialVersionUID = -5090438335783648169L;

    private Integer personalityChecksum;
    private Integer serverHwChecksum;

    public Integer getPersonalityChecksum() {
        return personalityChecksum;
    }

    public void setPersonalityChecksum(final Integer personalityChecksum) {
        this.personalityChecksum = personalityChecksum;
    }

    public Integer getServerHwChecksum() {
        return serverHwChecksum;
    }

    public void setServerHwChecksum(final Integer serverHwChecksum) {
        this.serverHwChecksum = serverHwChecksum;
    }

}
