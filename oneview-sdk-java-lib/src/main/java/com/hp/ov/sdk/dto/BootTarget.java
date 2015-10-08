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

public class BootTarget implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String arrayWwpn;
    private String lun;

    /**
     * 
     * @return The arrayWwpn
     */
    public String getArrayWwpn() {
        return arrayWwpn;
    }

    /**
     * 
     * @param arrayWwpn
     *            The arrayWwpn
     */
    public void setArrayWwpn(final String arrayWwpn) {
        this.arrayWwpn = arrayWwpn;
    }

    /**
     * 
     * @return The lun
     */
    public String getLun() {
        return lun;
    }

    /**
     * 
     * @param lun
     *            The lun
     */
    public void setLun(final String lun) {
        this.lun = lun;
    }

}
