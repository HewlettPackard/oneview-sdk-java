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
package com.hp.ov.sdk.dto;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;


public class ExtraStorageVolumeTest {

    private static String extraAccessList;

    private final ResourceAdaptor adaptor = new ResourceAdaptor();

    @BeforeClass
    public static void setupTest() throws IOException {
        Class<ExtraStorageVolumeTest> clazz = ExtraStorageVolumeTest.class;

        extraAccessList = IOUtils.toString(clazz.getResourceAsStream("ExtraAccessList.json"), "UTF-8");
    }

    @Test
    public void shouldExtraStorageVolumeCollectionContainsThreeEntries() {
        ExtraStorageVolumeCollection collection = adaptor.buildResourceObject(
                extraAccessList, ExtraStorageVolumeCollection.class);

        assertThat(collection.getTotal(), is(Integer.valueOf(3)));
        assertThat(collection.getCount(), is(Integer.valueOf(3)));

        assertThat(collection.getMembers(), containsInAnyOrder(
                new ExtraStorageVolume("AlertVolume1", "UnmanagedVolume", "3parArray",
                        "/rest/storage-systems/111111", "/rest/server-profiles/123-45-67-89-123"),
                new ExtraStorageVolume("AlertVolume2", "UnmanagedVolume", "3parArray",
                        "/rest/storage-systems/111111", "/rest/server-profiles/123-45-67-89-124"),
                new ExtraStorageVolume("AlertVolume3", "UnmanagedVolume", "3parArray",
                        "/rest/storage-systems/111111", "/rest/server-profiles/123-45-67-89-124")
        ));
    }

    @Test
    public void shouldRespectEqualsContract() {
        EqualsVerifier.forClass(ExtraStorageVolume.class)
                .suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS)
                .verify();
    }

}