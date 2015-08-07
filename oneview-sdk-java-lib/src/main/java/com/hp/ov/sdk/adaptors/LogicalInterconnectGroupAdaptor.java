/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class LogicalInterconnectGroupAdaptor extends BaseAdaptor<LogicalInterconnectGroups, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public LogicalInterconnectGroups buildDto(final Object source) {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LogicalInterconnectGroups logicalInterconnectGroupDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LogicalInterconnectGroups.class);
        return logicalInterconnectGroupDto;
    }

    public InterconnectSettingsV2 buildInterconnectSettingsDto(final Object source) {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final InterconnectSettingsV2 interconnectSettingsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), InterconnectSettingsV2.class);
        return interconnectSettingsDto;
    }

    public LogicalInterconnectGroupCollectionV2 buildCollectionDto(final Object source) {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), LogicalInterconnectGroupCollectionV2.class);
        return logicalInterconnectGroupCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final LogicalInterconnectGroups source) {
        // return the JSON object.
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
