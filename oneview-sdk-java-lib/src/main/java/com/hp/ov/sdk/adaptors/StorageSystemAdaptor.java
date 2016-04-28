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

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class StorageSystemAdaptor extends BaseAdaptor<StorageSystemV2, Object> {

    @Override
    public StorageSystemV2 buildDto(final Object source) {
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final StorageSystemV2 storageSystemDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), StorageSystemV2.class);
        return storageSystemDto;
    }

    public StorageSystemCollection buildCollectionDto(final Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageSystemCollection storageSystemCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StorageSystemCollection.class);
        return storageSystemCollectionDto;
    }

    public StoragePoolCollection buildStoragePoolCollectionDto(final Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        final StoragePoolCollection storagePoolCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StoragePoolCollection.class);
        return storagePoolCollectionDto;
    }

    public List<String> buildHostTypesCollectionDto(final String source) {
        if (null == source || source.equals("")) {
            return Collections.emptyList();
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        Type listOfString = new TypeToken<List<String>>(){}.getType();
        return (List<String>) converter.convertJsonToListObject(source, listOfString);
    }

    public StorageTargetPortCollection buildManagedPortsCollectionDto(final Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageTargetPortCollection storageTargetPortCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StorageTargetPortCollection.class);
        return storageTargetPortCollectionDto;
    }

    public StorageTargetPortV2 buildManagedPortsDto(final Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageTargetPortV2 storageTargetPortDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StorageTargetPortV2.class);
        return storageTargetPortDto;
    }

    public JSONObject buildJsonObjectFromDto(final AddStorageSystemCredentials source, final double version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version));
    }

    public JSONObject buildJsonObjectFromDto(final StorageSystemV2 source, final double version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version));
    }

}
