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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hp.ov.sdk.dto.serverhardwaretype.ControllerMode;
import com.hp.ov.sdk.dto.serverhardwaretype.DriveTechnology;
import com.hp.ov.sdk.dto.serverhardwaretype.RaidLevel;
import com.hp.ov.sdk.dto.serverhardwaretype.StorageCapabilities;

public class StorageCapabilitiesDeserializer implements JsonDeserializer<StorageCapabilities> {

    @Override
    public StorageCapabilities deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonArray()) {
            StorageCapabilities storageCapabilities = new StorageCapabilities();

            storageCapabilities.setControllerModes(Lists.newArrayList(ControllerMode.RAID));
            storageCapabilities.setDriveTechnologies(new ArrayList<DriveTechnology>());
            storageCapabilities.setRaidLevels(new ArrayList<RaidLevel>());

            Iterator<JsonElement> iterator = json.getAsJsonArray().iterator();

            while (iterator.hasNext()) {
                JsonElement element = iterator.next();

                if (!element.isJsonPrimitive()) {
                    throw new JsonParseException("Expected a primitive (String) value but found " + element.toString());
                }

                try {
                    storageCapabilities.getRaidLevels().add(RaidLevel.valueOf(element.getAsString()));
                } catch (IllegalArgumentException e) {
                    throw new JsonParseException("Unknown RAID Level", e);
                }
            }
            return storageCapabilities;
        }
        return new Gson().fromJson(json, StorageCapabilities.class);
    }

}
