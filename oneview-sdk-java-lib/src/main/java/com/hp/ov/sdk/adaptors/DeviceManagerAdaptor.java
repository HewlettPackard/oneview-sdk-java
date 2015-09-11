/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.DeviceManagerResponseCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;
import com.hp.ov.sdk.dto.Property;

@Component
public class DeviceManagerAdaptor extends BaseAdaptor<DeviceManagerResponse, Object> {
    private ObjectToJsonConverter converter;

    @Override
    public DeviceManagerResponse buildDto(Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final DeviceManagerResponse deviceManagerResponseDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), DeviceManagerResponse.class);
        return deviceManagerResponseDto;
    }

    public DeviceManagerResponseCollection buildCollectionDto(Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final DeviceManagerResponseCollection deviceManagerResponseCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                DeviceManagerResponseCollection.class);
        return deviceManagerResponseCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final List<Property> source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final DeviceManagerResponse source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
