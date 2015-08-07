/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class MsmbAlertsMessageAdaptor extends BaseAdaptor<MsmbAlertsMessageDto, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public MsmbAlertsMessageDto buildDto(final Object source) {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final MsmbAlertsMessageDto msmbAlertsMessageDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), MsmbAlertsMessageDto.class);
        return msmbAlertsMessageDto;
    }

}
