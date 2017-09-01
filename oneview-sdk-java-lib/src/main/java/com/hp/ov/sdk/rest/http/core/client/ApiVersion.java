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
package com.hp.ov.sdk.rest.http.core.client;

import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;

public enum ApiVersion {
    V_120 (120),
    V_200 (200),
    V_201 (201),
    V_300 (300),
    V_500 (500);

    private final int value;

    ApiVersion(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApiVersion fromStringValue(String value) {
        try {
            for (ApiVersion element : ApiVersion.values()) {
                if (element.value == Integer.parseInt(value)) {
                    return element;
                }
            }
        } catch (NumberFormatException e) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.apiMismatchError, "Invalid API version format" + value, e);
        }
        throw new SDKInvalidArgumentException(SDKErrorEnum.apiMismatchError, "Unsupported API version " + value);
    }
}
