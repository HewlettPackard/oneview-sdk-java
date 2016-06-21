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

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ControllerMode;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.DriveTechnology;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.RaidLevel;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.StorageCapabilities;

public class StorageCapabilitiesDeserializerTest {

    private static final String STORAGE_CAPABILITIES_V3
            = "[\"RAID0\",\"RAID1\",\"RAID1ADM\",\"RAID10\",\"RAID5\",\"RAID6\"]";

    private static final String STORAGE_CAPABILITIES_V3_WITH_INVALID_RAID_LEVEL
            = "[\"RAID0\",\"UnknownRaidLevel\"]";

    private static final String STORAGE_CAPABILITIES_V3_WITH_OBJECT_RAID_LEVEL
            = "[\"RAID0\", {\"someAttribute\":\"someValue\"}]";

    private static final String STORAGE_CAPABILITIES_V4
            = "{\"driveTechnologies\":[\"SasHdd\",\"SataHdd\",\"SasSsd\",\"SataSsd\"]," +
            "\"maximumDrives\":4," +
            "\"raidLevels\":[\"RAID0\",\"RAID1\",\"RAID1ADM\",\"RAID10\",\"RAID5\",\"RAID6\"]," +
            "\"controllerModes\":[\"RAID\",\"HBA\"]}";

    private final StorageCapabilitiesDeserializer deserializer = new StorageCapabilitiesDeserializer();

    @Test(expected = JsonParseException.class)
    public void shouldThrowParserExceptionWhenDeserializeStorageCapabilitiesV3ContainingInvalidRaidLevel() {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(STORAGE_CAPABILITIES_V3_WITH_INVALID_RAID_LEVEL);

        deserializer.deserialize(jsonElement, StorageCapabilities.class, null);
    }

    @Test(expected = JsonParseException.class)
    public void shouldThrowParserExceptionWhenDeserializeStorageCapabilitiesV3ContainingObjectRaidLevel() {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(STORAGE_CAPABILITIES_V3_WITH_OBJECT_RAID_LEVEL);

        deserializer.deserialize(jsonElement, StorageCapabilities.class, null);
    }

    @Test
    public void shouldDeserializeStorageCapabilitiesV3() {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(STORAGE_CAPABILITIES_V3);

        StorageCapabilities capabilities = deserializer.deserialize(jsonElement, StorageCapabilities.class, null);

        assertThat(capabilities, is(notNullValue()));
        assertThat(capabilities.getRaidLevels(), containsInAnyOrder(RaidLevel.values()));
        assertThat(capabilities.getControllerModes(), contains(ControllerMode.RAID));
        assertThat(capabilities.getDriveTechnologies(), is(Matchers.<DriveTechnology>empty()));
    }

    @Test
    public void shouldDeserializeStorageCapabilitiesV4() {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(STORAGE_CAPABILITIES_V4);

        StorageCapabilities capabilities = deserializer.deserialize(jsonElement, StorageCapabilities.class, null);

        assertThat(capabilities, is(notNullValue()));
        assertThat(capabilities.getRaidLevels(), containsInAnyOrder(RaidLevel.values()));
        assertThat(capabilities.getControllerModes(), containsInAnyOrder(ControllerMode.values()));
        assertThat(capabilities.getDriveTechnologies(), containsInAnyOrder(DriveTechnology.values()));
        assertThat(capabilities.getMaximumDrives(), is(Integer.valueOf(4)));
    }

}
