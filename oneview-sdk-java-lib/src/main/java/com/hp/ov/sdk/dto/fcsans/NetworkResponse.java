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
package com.hp.ov.sdk.dto.fcsans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class NetworkResponse implements Serializable {

    private static final long serialVersionUID = -975697196465803556L;

    private String interconnectUri;
    private String name;
    private NetworkType networkType;
    private String uri;

    public enum NetworkType {
        FC, FCoE, Hybrid
    }

    public String getInterconnectUri() {
        return interconnectUri;
    }

    public void setInterconnectUri(String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof NetworkResponse) {
            NetworkResponse that = (NetworkResponse) obj;

            return new EqualsBuilder()
                    .append(interconnectUri, that.interconnectUri)
                    .append(name, that.name)
                    .append(networkType, that.networkType)
                    .append(uri, that.uri)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(interconnectUri)
                .append(name)
                .append(networkType)
                .append(uri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("interconnectUri", interconnectUri)
                .append("name", name)
                .append("networkType", networkType)
                .append("uri", uri)
                .toString();
    }
}
