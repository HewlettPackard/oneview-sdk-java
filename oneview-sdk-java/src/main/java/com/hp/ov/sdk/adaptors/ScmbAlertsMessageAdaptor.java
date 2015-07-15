/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ScmbAlertsMessageAdaptor extends
        BaseAdaptor<ScmbAlertsMessageDto, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public ScmbAlertsMessageDto buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO replace quotes and back slash in the file
        final ScmbAlertsMessageDto scmbAlertsMessageDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                ScmbAlertsMessageDto.class);
        return scmbAlertsMessageDto;
    }

}
