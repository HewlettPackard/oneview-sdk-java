/*
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
 */
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BootTargetTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String arrayWwpn;
    private String lun;

    /**
     * @return the arrayWwpn
     */
    public String getArrayWwpn() {
        return arrayWwpn;
    }

    /**
     * @param arrayWwpn the arrayWwpn to set
     */
    public void setArrayWwpn(String arrayWwpn) {
        this.arrayWwpn = arrayWwpn;
    }

    /**
     * @return the lun
     */
    public String getLun() {
        return lun;
    }

    /**
     * @param lun the lun to set
     */
    public void setLun(String lun) {
        this.lun = lun;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        BootTargetTemplate that = (BootTargetTemplate) obj;

        return new EqualsBuilder()
                .append(arrayWwpn, that.arrayWwpn)
                .append(lun, that.lun)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(arrayWwpn)
                .append(lun)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("arrayWwpn", arrayWwpn)
                .append("lun", lun)
                .toString();
    }

}
