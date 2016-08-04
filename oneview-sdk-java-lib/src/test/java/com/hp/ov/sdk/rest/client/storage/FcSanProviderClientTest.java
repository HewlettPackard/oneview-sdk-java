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

package com.hp.ov.sdk.rest.client.storage;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.rest.client.BaseClient;

@RunWith(MockitoJUnitRunner.class)
public class FcSanProviderClientTest {

    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private FcSanProviderClient client;

    @Test
    public void shouldGetAllFcSanDeviceManager() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.FC_SANS_PROVIDER_URI,
                SanProviderResponse.class);
    }

    @Test
    public void shouldGetFcSanDeviceManagerByName() {
        ResourceCollection<SanProviderResponse> collection = new ResourceCollection<>();
        SanProviderResponse sanProvider = new SanProviderResponse();

        sanProvider.setDisplayName(ANY_RESOURCE_NAME);
        collection.setMembers(Lists.newArrayList(sanProvider));

        given(baseClient.getResourceCollection(anyString(), eq(SanProviderResponse.class))).willReturn(collection);

        SanProviderResponse sanProviderResponse = client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FC_SANS_PROVIDER_URI,
                SanProviderResponse.class);
        assertThat(sanProviderResponse, sameInstance(sanProvider));
    }

}
