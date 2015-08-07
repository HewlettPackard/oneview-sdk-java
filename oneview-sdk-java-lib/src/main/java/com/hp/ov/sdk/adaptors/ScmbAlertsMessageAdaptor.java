/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class ScmbAlertsMessageAdaptor extends BaseAdaptor<ScmbAlertsMessageDto, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public ScmbAlertsMessageDto buildDto(final Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert json object to DTO replace quotes and back slash in the file
        final ScmbAlertsMessageDto scmbAlertsMessageDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), ScmbAlertsMessageDto.class);
        return scmbAlertsMessageDto;
    }

}
