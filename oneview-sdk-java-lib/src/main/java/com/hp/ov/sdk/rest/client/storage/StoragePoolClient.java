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

import com.hp.ov.sdk.dto.networking.AddStoragePool;
import com.hp.ov.sdk.dto.storage.StoragePool;
import com.hp.ov.sdk.rest.client.common.AddableResource;
import com.hp.ov.sdk.rest.client.common.RemovableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.reflect.Api;

@Api(StoragePoolClient.STORAGE_POOL_URI)
public interface StoragePoolClient extends
        SearchableResource<StoragePool>,
        AddableResource<AddStoragePool>,
        UpdatableResource<StoragePool>,
        RemovableResource {

    String STORAGE_POOL_URI = "/rest/storage-pools";

}
