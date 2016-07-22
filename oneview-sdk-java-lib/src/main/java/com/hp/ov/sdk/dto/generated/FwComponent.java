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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class FwComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String componentVersion;
    private List<String> swKeyNameList = new ArrayList<String>();
    private String name;
    private String fileName;

    /**
     *
     * @return The componentVersion
     */
    public String getComponentVersion() {
        return componentVersion;
    }

    /**
     *
     * @param componentVersion
     *            The componentVersion
     */
    public void setComponentVersion(final String componentVersion) {
        this.componentVersion = componentVersion;
    }

    /**
     *
     * @return The swKeyNameList
     */
    public List<String> getSwKeyNameList() {
        return swKeyNameList;
    }

    /**
     *
     * @param swKeyNameList
     *            The swKeyNameList
     */
    public void setSwKeyNameList(final List<String> swKeyNameList) {
        this.swKeyNameList = swKeyNameList;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return The fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName
     *            The fileName
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(componentVersion).append(swKeyNameList).append(name).append(fileName).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FwComponent) == false) {
            return false;
        }
        final FwComponent rhs = ((FwComponent) other);
        return new EqualsBuilder().append(componentVersion, rhs.componentVersion).append(swKeyNameList, rhs.swKeyNameList)
                .append(name, rhs.name).append(fileName, rhs.fileName).isEquals();
    }

}
