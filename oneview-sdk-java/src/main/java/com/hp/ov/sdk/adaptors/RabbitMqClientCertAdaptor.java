/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class RabbitMqClientCertAdaptor extends
        BaseAdaptor<RabbitMqClientCert, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public RabbitMqClientCert buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final RabbitMqClientCert rabbitMqClientCertDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                RabbitMqClientCert.class);
        return rabbitMqClientCertDto;
    }

    public JSONObject buildJsonObjectFromDto(final RabbitMqClientCert source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
