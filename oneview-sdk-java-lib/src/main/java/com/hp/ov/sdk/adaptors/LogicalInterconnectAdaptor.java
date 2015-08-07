/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class LogicalInterconnectAdaptor extends BaseAdaptor<LogicalInterconnects, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public LogicalInterconnects buildDto(final Object source) {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LogicalInterconnects logicalInterconnectsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LogicalInterconnects.class);
        return logicalInterconnectsDto;
    }

    public LiFirmware buildFirmwareDto(final Object source) {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LiFirmware liFirmwareDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LiFirmware.class);
        return liFirmwareDto;
    }

    public LogicalInterconnectCollectionV2 buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), LogicalInterconnectCollectionV2.class);
        return logicalInterconnectCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final LiFirmware source) {
        // return the JSON object.
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final SnmpConfiguration source) {
        // return the JSON object.
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
