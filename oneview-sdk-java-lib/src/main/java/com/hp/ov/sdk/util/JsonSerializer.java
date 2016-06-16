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

package com.hp.ov.sdk.util;

import java.util.Arrays;

import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;

public class JsonSerializer {

    public String toJson(Object source, ApiVersion apiVersion) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return converter.convertObjectToJsonString(source, apiVersion.getValue());
    }

    public String toJsonArray(Patch source, ApiVersion apiVersion) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return converter.convertObjectToJsonString(Arrays.asList(source), apiVersion.getValue());
    }

}
