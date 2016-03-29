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

package com.hp.ov.sdk.adaptors;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hp.ov.sdk.dto.PortTelemetry;

public class PortTelemetrySerializationAdapter implements JsonSerializer<PortTelemetry>,
        JsonDeserializer<PortTelemetry> {

    @Override
    public PortTelemetry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        if (json.isJsonPrimitive()) {
            return PortTelemetry.valueOf(json.getAsString());
        } else if (json.isJsonArray()) {
            for (JsonElement element : json.getAsJsonArray()) {
                String elementAsString = element.getAsString();

                if (PortTelemetry.contains(elementAsString)) {
                    return PortTelemetry.valueOf(elementAsString);
                }
            }
        }
        throw new JsonParseException("Unknown port telemetry value type. " +
                "Expected value types are String or String[] (String array)");
    }

    @Override
    public JsonElement serialize(PortTelemetry src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }
}
