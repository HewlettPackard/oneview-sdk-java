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

import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplateCollection;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class StorageVolumeTemplateAdaptor extends BaseAdaptor<StorageVolumeTemplate, Object> {

    @Override
    public StorageVolumeTemplate buildDto(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final StorageVolumeTemplate storageVolumeTemplateDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), StorageVolumeTemplate.class);
        return storageVolumeTemplateDto;
    }

    public StorageVolumeTemplateCollection buildStorageVolumeTemplateCollection(String source) {
        return this.buildCollectionDto(source, StorageVolumeTemplateCollection.class);
    }

    public ConnectableStorageVolumeTemplateCollection buildConnectableStorageVolumeTemplateCollection(String source) {
        return this.buildCollectionDto(source, ConnectableStorageVolumeTemplateCollection.class);
    }

    private <T> T buildCollectionDto(Object source, Class<T> clazz) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        // convert json Object to DTO, replace quotes and back slash in the file
        T collection = converter.convertJsonToObject(StringUtil.replaceQuotesBackSlashWithQuote(
                StringUtil.replaceQuotesAndBackSlash(
                        converter.convertObjectToJsonString(source))), clazz);

        return collection;
    }

    public JSONObject buildJsonObjectFromDto(final StorageVolumeTemplate source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
