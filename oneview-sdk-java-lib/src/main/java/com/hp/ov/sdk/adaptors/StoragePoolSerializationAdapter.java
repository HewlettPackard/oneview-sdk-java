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
package com.hp.ov.sdk.adaptors;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.AllocatedCapacity;
import com.hp.ov.sdk.dto.StoragePool;


public class StoragePoolSerializationAdapter implements JsonSerializer<StoragePool>,
        JsonDeserializer<StoragePool> {

    @Override
    public StoragePool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        JsonElement allocatedCapacity = json.getAsJsonObject().remove(StoragePool.ALLOCATED_CAPACITY_FIELD);
        StoragePool storagePool = gson.fromJson(json, StoragePool.class);

        if (allocatedCapacity.isJsonPrimitive()) {
            storagePool.setAllocatedCapacity(allocatedCapacity.getAsString());
        } else {
            storagePool.setAllocatedCapacityDetails(gson.fromJson(allocatedCapacity, AllocatedCapacity.class));
        }
        return storagePool;
    }

    @Override
    public JsonElement serialize(StoragePool src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement jsonElement = new Gson().toJsonTree(src);

        if (ResourceCategory.RC_STORAGE_POOL.equalsIgnoreCase(src.getType())) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            jsonObject.remove(StoragePool.ALLOCATED_CAPACITY_FIELD);
            jsonObject.addProperty(StoragePool.ALLOCATED_CAPACITY_FIELD, src.getAllocatedCapacity());
        }
        return jsonElement;
    }

}
