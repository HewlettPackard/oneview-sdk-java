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

/**
 * The SupportDump data transfer object (DTO) contains the information
 * used to generate a support dump for a logical enclosure.
 */

public class SupportDump {
    private String errorCode;

    private boolean encrypt = true;

    // By default, appliance content is included
    private boolean excludeApplianceDump = false;

    public SupportDump()
    {
    }

    public SupportDump(final String errorCode, final boolean encrypt, final boolean excludeApplianceDump)
    {
        this.setErrorCode(errorCode);
        this.setEncrypt(encrypt);
        this.setExcludeApplianceDump(excludeApplianceDump);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(final boolean encrypt) {
        this.encrypt = encrypt;
    }

    public boolean getExcludeApplianceDump() {
        return excludeApplianceDump;
    }

    public void setExcludeApplianceDump(final boolean excludeApplianceDump) {
        this.excludeApplianceDump = excludeApplianceDump;
    }
}
