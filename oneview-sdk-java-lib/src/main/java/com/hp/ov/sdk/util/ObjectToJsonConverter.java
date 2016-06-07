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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hp.ov.sdk.adaptors.PortTelemetrySerializationAdapter;
import com.hp.ov.sdk.adaptors.StorageCapabilitiesDeserializer;
import com.hp.ov.sdk.adaptors.StoragePoolSerializationAdapter;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.PortTelemetry;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.serverhardwaretype.StorageCapabilities;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInternalException;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

public class ObjectToJsonConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectToJsonConverter.class);

    private static final class ObjectToJsonConverterHolder {
        private static final ObjectToJsonConverter INSTANCE = new ObjectToJsonConverter();
    }

    private ObjectToJsonConverter() {}

    public static ObjectToJsonConverter getInstance() {
        return ObjectToJsonConverterHolder.INSTANCE;
    }

    public String convertObjectToJsonString(final Object inObj) {
        Gson gson = this.getGson();

        return (gson.toJson(inObj));
    }

    public String convertObjectToJsonString(final Object inObj, double version) {
        Gson gson = this.getGson(version);

        return (gson.toJson(inObj));
    }

    public <T> T convertJsonToObject(final String inStr, final Class<T> target) {
        Gson gson = this.getGson();

        T retObj = null;
        try {
            retObj = gson.fromJson(inStr, target);
        } catch (final Exception e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
        LOGGER.debug("ObjectJsonConverter : convertObjectToJson: json to object =" + retObj.toString());

        return retObj;
    }

    public <T> ResourceCollection<T> convertJsonToResourceCollection(String jsonInput, Class<T> resourceClass) {
        Gson gson = this.getGson();
        Type type = ParameterizedTypeImpl.make(ResourceCollection.class, new Class<?>[] {resourceClass}, null);

        try {
            ResourceCollection<T> resourceCollection = gson.fromJson(jsonInput, type);

            LOGGER.info("JSON successfully converted to resource collection of type {} with {} members",
                    resourceClass.getSimpleName(), Integer.valueOf(resourceCollection.getCount()));

            return resourceCollection;
        } catch (final Exception e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
    }

    public List<?> convertJsonToListObject(final String inStr, final Type mtype) {
        Gson gson = this.getGson();
        List<?> retObj = null;

        try {
            retObj = gson.fromJson(inStr, mtype);
        } catch (final Exception e) {
            throw new SDKInternalException(SDKErrorEnum.internalError, null, null, null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION, e);
        }
        LOGGER.info("ObjectJsonConverter : convertObjectToJson: json to object =" + retObj.toString());
        return retObj;
    }

    private Gson getGson() {
        return this.getGson(Double.MAX_VALUE);
    }

    private Gson getGson(double apiVersion) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(StoragePool.class, new StoragePoolSerializationAdapter())
                .registerTypeAdapter(PortTelemetry.class, new PortTelemetrySerializationAdapter())
                .registerTypeAdapter(StorageCapabilities.class, new StorageCapabilitiesDeserializer())
                .setVersion(apiVersion)
                .create();

        return gson;
    }

}
