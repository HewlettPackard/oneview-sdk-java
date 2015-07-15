/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class MsmbAlertsMessageAdaptor extends
        BaseAdaptor<MsmbAlertsMessageDto, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public MsmbAlertsMessageDto buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final MsmbAlertsMessageDto msmbAlertsMessageDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                MsmbAlertsMessageDto.class);
        return msmbAlertsMessageDto;
    }

}
