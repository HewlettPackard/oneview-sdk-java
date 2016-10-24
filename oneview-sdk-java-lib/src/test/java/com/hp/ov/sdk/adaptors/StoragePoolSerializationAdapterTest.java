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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.storage.AllocatedCapacity;
import com.hp.ov.sdk.dto.storage.StoragePool;

public class StoragePoolSerializationAdapterTest {

    private static String storagePoolJson;
    private static String storagePoolV2Json;

    @BeforeClass
    public static void setupTest() throws IOException {
        Class<StoragePoolSerializationAdapterTest> clazz = StoragePoolSerializationAdapterTest.class;

        storagePoolJson = IOUtils.toString(clazz.getResourceAsStream("StoragePool.json"), "UTF-8");
        storagePoolV2Json = IOUtils.toString(clazz.getResourceAsStream("StoragePoolV2.json"), "UTF-8");
    }

    @Test
    public void shouldDeserializeStoragePool() {
        StoragePoolSerializationAdapter adapter = new StoragePoolSerializationAdapter();
        JsonParser parser = new JsonParser();

        StoragePool storagePool = adapter.deserialize(parser.parse(storagePoolJson), null, null);

        assertThat(storagePool.getType(), is(equalTo(ResourceCategory.RC_STORAGE_POOL)));
        assertThat(storagePool.getAllocatedCapacityDetails().getSnapshotAllocatedCapacity(), is(nullValue()));
        assertThat(storagePool.getAllocatedCapacityDetails().getVolumeAllocatedCapacity(), is(nullValue()));
    }

    @Test
    public void shouldDeserializeStoragePoolV2() {
        StoragePoolSerializationAdapter adapter = new StoragePoolSerializationAdapter();
        JsonParser parser = new JsonParser();

        StoragePool storagePool = adapter.deserialize(parser.parse(storagePoolV2Json), null, null);

        assertThat(storagePool.getType(), is(equalTo(ResourceCategory.RC_STORAGE_POOL_V200)));
        assertThat(storagePool.getAllocatedCapacityDetails().getSnapshotAllocatedCapacity(), is(notNullValue()));
        assertThat(storagePool.getAllocatedCapacityDetails().getVolumeAllocatedCapacity(), is(notNullValue()));
    }

    @Test
    public void shouldSerializeStoragePool() throws ParseException {
        StoragePoolSerializationAdapter adapter = new StoragePoolSerializationAdapter();
        StoragePool storagePool = this.buildStoragePool();

        JsonObject serializedStoragePool = adapter.serialize(storagePool, null, null).getAsJsonObject();

        assertThat(serializedStoragePool.getAsJsonPrimitive("type").getAsString(),
                is(equalTo(ResourceCategory.RC_STORAGE_POOL)));
        assertThat(serializedStoragePool.get(StoragePool.ALLOCATED_CAPACITY_FIELD).isJsonPrimitive(),
                is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void shouldSerializeStoragePoolV2() throws ParseException {
        StoragePoolSerializationAdapter adapter = new StoragePoolSerializationAdapter();
        StoragePool storagePool = this.buildStoragePoolV2();

        JsonObject serializedStoragePool = adapter.serialize(storagePool, null, null).getAsJsonObject();

        assertThat(serializedStoragePool.getAsJsonPrimitive("type").getAsString(),
                is(equalTo(ResourceCategory.RC_STORAGE_POOL_V200)));
        assertThat(serializedStoragePool.get(StoragePool.ALLOCATED_CAPACITY_FIELD).isJsonPrimitive(),
                is(equalTo(Boolean.FALSE)));
    }

    private StoragePool buildStoragePoolV2() throws ParseException {
        StoragePool storagePool = this.buildCommonStoragePool();

        AllocatedCapacity allocatedCapacity = new AllocatedCapacity();

        allocatedCapacity.setSnapshotAllocatedCapacity("153545080832");
        allocatedCapacity.setTotalAllocatedCapacity("238370684928");
        allocatedCapacity.setVolumeAllocatedCapacity("84825604096");

        storagePool.setType(ResourceCategory.RC_STORAGE_POOL_V200);
        storagePool.setAllocatedCapacityDetails(allocatedCapacity);

        return storagePool;
    }

    private StoragePool buildStoragePool() throws ParseException {
        StoragePool storagePool = this.buildCommonStoragePool();

        storagePool.setType(ResourceCategory.RC_STORAGE_POOL);
        storagePool.setAllocatedCapacity("238370684928");

        return storagePool;
    }

    private StoragePool buildCommonStoragePool() throws ParseException {
        StoragePool storagePool = new StoragePool();

        storagePool.setDeviceType("FC");
        storagePool.setStorageSystemUri("/rest/storage-systems/TXQ1000307");
        storagePool.setFreeCapacity("861140942848");
        storagePool.setSupportedRAIDLevel("RAID5");
        storagePool.setDeviceSpeed("Default");
        storagePool.setCapacityLimit("1099511627776");
        storagePool.setCapacityWarningLimit("1099511627776");
        storagePool.setTotalCapacity("1099511627776");
        storagePool.setDomain("TestDomain");
        storagePool.setStateReason("None");
        storagePool.setRefreshState(RefreshState.NotRefreshing);
        storagePool.setDescription("FST_CPG1");
        storagePool.setStatus("OK");
        storagePool.setName("FST_CPG1");
        storagePool.setState("Configured");
        storagePool.setCreated(new SimpleDateFormat(DateAdapter.DEFAULT_DATE_FORMAT).parse("2016-02-26T16:41:13.396Z"));
        storagePool.setETag("2016-02-26T16:41:13.397Z");
        storagePool.setModified(new SimpleDateFormat(DateAdapter.DEFAULT_DATE_FORMAT).parse("2016-02-26T16:41:13.396Z"));
        storagePool.setCategory("storage-pools");
        storagePool.setUri("/rest/storage-pools/33DCD9C3-ADE7-41B6-8E20-A50F51786CF0");

        return storagePool;
    }

}
