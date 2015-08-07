/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class RabbitMqClientCertAdaptor extends BaseAdaptor<RabbitMqClientCert, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public RabbitMqClientCert buildDto(final Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final RabbitMqClientCert rabbitMqClientCertDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), RabbitMqClientCert.class);
        return rabbitMqClientCertDto;
    }

    public JSONObject buildJsonObjectFromDto(final RabbitMqClientCert source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
