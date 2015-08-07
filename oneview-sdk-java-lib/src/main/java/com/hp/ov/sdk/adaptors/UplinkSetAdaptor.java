/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class UplinkSetAdaptor extends BaseAdaptor<UplinkSets, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public UplinkSets buildDto(final Object source) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final UplinkSets uplinkSetDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), UplinkSets.class);
        return uplinkSetDto;
    }

    public UplinkSetCollectionV2 buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json object to DTO, replace quotes and back slash in the file
        final UplinkSetCollectionV2 uplinkSetCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), UplinkSetCollectionV2.class);
        return uplinkSetCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final UplinkSets source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
