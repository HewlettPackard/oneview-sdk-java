/*
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
 */
package com.hp.ov.sdk.dto.networking.networkset;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

/**
 * The NetworkSet data transfer object (DTO) contains the information used to
 * represent a collection of networks that represent a network set in the
 * system. It is passed in to the add/update networkSet REST api, as well as the
 * add/update networkSet through java client api.
 */
public class NetworkSet extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String connectionTemplateUri;
    private String nativeNetworkUri;
    private List<String> networkUris = new ArrayList<>();
    @Since(300)
    private List<String> scopeUris = new ArrayList<>();

    /**
     * @return the connectionTemplateUri
     */
    public String getConnectionTemplateUri() {
        return connectionTemplateUri;
    }

    /**
     * @param connectionTemplateUri the connectionTemplateUri to set
     */
    public void setConnectionTemplateUri(String connectionTemplateUri) {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    /**
     * @return the nativeNetworkUri
     */
    public String getNativeNetworkUri() {
        return nativeNetworkUri;
    }

    /**
     * @param nativeNetworkUri the nativeNetworkUri to set
     */
    public void setNativeNetworkUri(String nativeNetworkUri) {
        this.nativeNetworkUri = nativeNetworkUri;
    }

    /**
     * @return the networkUris
     */
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     * @param networkUris the networkUris to set
     */
    public void setNetworkUris(List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof NetworkSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof NetworkSet) {
            NetworkSet that = (NetworkSet) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(connectionTemplateUri, that.connectionTemplateUri)
                    .append(nativeNetworkUri, that.nativeNetworkUri)
                    .append(networkUris, that.networkUris)
                    .append(scopeUris, that.scopeUris)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(connectionTemplateUri)
                .append(nativeNetworkUri)
                .append(networkUris)
                .append(scopeUris)
                .toHashCode();
    }
}
