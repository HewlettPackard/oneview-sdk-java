/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class EnclosureGroupAdaptor extends BaseAdaptor<EnclosureGroups, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public EnclosureGroups buildDto(final Object source) {
        // TODO - exceptions
        converter = ConverterFactory.getConverter();
        // convert json Object to DTO, replace quotes and back slash in the file
        final EnclosureGroups enclosureGroupDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), EnclosureGroups.class);
        return enclosureGroupDto;
    }

    public EnclosureGroupCollectionV2 buildCollectionDto(final Object source) {
        // TODO - exceptions
        converter = ConverterFactory.getConverter();
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final EnclosureGroupCollectionV2 enclosureGroupCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), EnclosureGroupCollectionV2.class);
        return enclosureGroupCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final EnclosureGroups source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }
}
