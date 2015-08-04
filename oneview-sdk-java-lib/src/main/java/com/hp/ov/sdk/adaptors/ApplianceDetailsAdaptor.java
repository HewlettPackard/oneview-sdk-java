/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ApplianceDetailsAdaptor
{

    private ObjectToJsonConverter converter;
    
    public ApplianceDetailsDto buildDto(final Object source)
    {

        converter = ConverterFactory.getConverter();
        // convert json object to DTO replace quotes and back slash in the file
        final ApplianceDetailsDto applianceDetailsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), ApplianceDetailsDto.class);
        return applianceDetailsDto;
    }
}
