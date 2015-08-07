/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StoragePoolAdaptor extends BaseAdaptor<StoragePool, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public StoragePool buildDto(final Object source) {
        // TODO - exceptions
        converter = ConverterFactory.getConverter();
        StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source));
        // convert json object to DTO, replace quotes and back slash in the file
        final StoragePool storagePoolDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), StoragePool.class);
        return storagePoolDto;
    }

    public StoragePoolCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json object to DTO, replace quotes and back slash in the file
        final StoragePoolCollection storagePoolCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StoragePoolCollection.class);
        return storagePoolCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final StoragePool source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddStoragePool source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
