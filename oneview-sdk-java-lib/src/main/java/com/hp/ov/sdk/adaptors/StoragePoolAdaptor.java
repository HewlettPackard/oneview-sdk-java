/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import org.json.JSONObject;

import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;


public class StoragePoolAdaptor extends BaseAdaptor<StoragePool, Object> {

    @Override
    public StoragePool buildDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source));
        // convert json object to DTO, replace quotes and back slash in the file
        final StoragePool storagePoolDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), StoragePool.class);

        return storagePoolDto;
    }

    public JSONObject buildJsonObjectFromDto(final StoragePool source, int apiVersion) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source, apiVersion));
    }

    public JSONObject buildJsonObjectFromDto(final AddStoragePool source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
