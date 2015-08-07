/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StorageVolumeAdaptor extends BaseAdaptor<StorageVolumeV2, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public StorageVolumeV2 buildDto(final Object source) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final StorageVolumeV2 storageVolumeDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), StorageVolumeV2.class);
        return storageVolumeDto;
    }

    public StorageVolumeCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageVolumeCollection storageVolumeCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StorageVolumeCollection.class);
        return storageVolumeCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final StorageVolumeV2 source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddStorageVolumeV2 source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
