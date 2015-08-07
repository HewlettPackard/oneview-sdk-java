/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StorageVolumeTemplateAdaptor extends BaseAdaptor<StorageVolumeTemplate, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public StorageVolumeTemplate buildDto(final Object source) {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final StorageVolumeTemplate storageVolumeTemplateDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), StorageVolumeTemplate.class);
        return storageVolumeTemplateDto;
    }

    public StorageVolumeTemplateCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json Object to DTO, replace quotes and back slash in the file
        final StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), StorageVolumeTemplateCollection.class);
        return storageVolumeTemplateCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final StorageVolumeTemplate source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
