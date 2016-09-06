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

package com.hp.ov.sdk.rest.http.core;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class UrlParameter {

    private final String key;
    private final String value;

    public UrlParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static UrlParameter getFilterByNameParameter(String name) {
        // Commas are known to cause problems on filters, so we replace it with a "any char" value (_)
        String nameWithoutCommas = name.replaceAll(",", "_");
        return new UrlParameter("filter", "name matches '" + nameWithoutCommas + "'");
    }

    public static UrlParameter getCountParameter(int count) {
        return new UrlParameter("count", String.valueOf(count));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof UrlParameter) {
            UrlParameter urlParameter = (UrlParameter) obj;

            return new EqualsBuilder()
                    .append(key, urlParameter.key)
                    .append(value, urlParameter.value)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(key)
                .append(value)
                .toHashCode();
    }
}
