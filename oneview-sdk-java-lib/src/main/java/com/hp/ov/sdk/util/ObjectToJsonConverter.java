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
package com.hp.ov.sdk.util;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.hp.ov.sdk.adaptors.DateAdapter;
import com.hp.ov.sdk.adaptors.PortTelemetrySerializationAdapter;
import com.hp.ov.sdk.adaptors.StorageCapabilitiesDeserializer;
import com.hp.ov.sdk.adaptors.StoragePoolSerializationAdapter;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.PortTelemetry;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.StorageCapabilities;
import com.hp.ov.sdk.dto.storage.StoragePool;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInternalException;

public class ObjectToJsonConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectToJsonConverter.class);

    private static final Map<Class<?>, Object> GSON_TYPE_ADAPTERS = ImmutableMap.<Class<?>, Object>builder()
            .put(StoragePool.class, new StoragePoolSerializationAdapter())
            .put(PortTelemetry.class, new PortTelemetrySerializationAdapter())
            .put(StorageCapabilities.class, new StorageCapabilitiesDeserializer())
            .put(Date.class, new DateAdapter())
            .build();

    private Gson gson;
    private Gson versionedGson;

    private static final class ObjectToJsonConverterHolder {
        private static final ObjectToJsonConverter INSTANCE = new ObjectToJsonConverter();
    }

    private ObjectToJsonConverter() {}

    public static ObjectToJsonConverter getInstance() {
        return ObjectToJsonConverterHolder.INSTANCE;
    }

    public String resourceToJson(Object inputObject, int apiVersion) {
        return this.versionedGson(apiVersion).toJson(inputObject);
    }

    public Object jsonToResource(final String jsonInput, Type resourceType) {
        try {
            LOGGER.info("JSON successfully converted to a Resource of type {}", resourceType);

            return this.gson().fromJson(jsonInput, resourceType);
        } catch (final JsonParseException e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
    }

    public <T> T jsonToResource(final String jsonInput, final Class<T> resourceType) {
        try {
            LOGGER.info("JSON successfully converted to a Resource of type {}", resourceType.getSimpleName());

            return this.gson().fromJson(jsonInput, resourceType);
        } catch (final JsonParseException e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
    }

    public <T> ResourceCollection<T> jsonToResourceCollection(String jsonInput, Class<T> resourceType) {
        try {
            Type type = new TypeToken<ResourceCollection<T>>() {}
                    .where(new TypeParameter<T>() {}, TypeToken.of(resourceType)).getType();

            LOGGER.info("JSON successfully converted to a ResourceCollection of {}", resourceType.getSimpleName());

            return this.gson().fromJson(jsonInput, type);
        } catch (final JsonParseException e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
    }

    public <T> List<T> jsonToList(String jsonInput, Class<T> resourceType) {
        try {
            Type type = new TypeToken<List<T>>() {}
                    .where(new TypeParameter<T>() {}, TypeToken.of(resourceType)).getType();

            LOGGER.info("JSON successfully converted to a List of {}", resourceType.getSimpleName());

            return this.gson().fromJson(jsonInput, type);
        } catch (final JsonParseException e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
    }

    /**
     * Process the JSON string received from OneView in order to remove/include unsupported characters.
     *
     * @param inputJson the JSON string received from OneView.
     *
     * @return string with some characters replaced and properly formatted to execute the deserialization process.
     */
    public String processJsonFromOneView(String inputJson) {
        return this.gson().toJson(inputJson);
    }

    private Gson gson() {
        if (this.gson == null) {
            this.gson = gsonBuilder().create();
        }
        return this.gson;
    }

    private Gson versionedGson(int apiVersion) {
        if (this.versionedGson == null) {
            this.versionedGson = gsonBuilder().setVersion(apiVersion).create();
        }
        return this.versionedGson;
    }

    private GsonBuilder gsonBuilder() {
        GsonBuilder builder = new GsonBuilder();

        for (Map.Entry<Class<?>, Object> adapter : GSON_TYPE_ADAPTERS.entrySet()) {
            builder.registerTypeAdapter(adapter.getKey(), adapter.getValue());
        }
        return builder;
    }

}
