/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ApplianceDetailsAdaptor
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    public ApplianceDetailsDto buildDto(Object source)
    {

        // convert json object to DTO replace quotes and back slash in the file
        ApplianceDetailsDto applianceDetailsDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                ApplianceDetailsDto.class);
        return applianceDetailsDto;
    }
}
