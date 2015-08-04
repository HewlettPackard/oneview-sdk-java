/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ObjectToJsonConverter;

@Component
public class LoginSessionAdaptor extends BaseAdaptor<LoginSessionDto, RestParams>
{

    private ObjectToJsonConverter converter;

    public JSONObject buildJSONObjectFromDto(final LoginSessionDto source)
    {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    @Override
    public LoginSessionDto buildDto(final RestParams params)
    {
        final LoginSessionDto target = new LoginSessionDto();
        target.setUserName(params.getUserName());
        target.setPassword(params.getPassword());
        target.setAuthLoginDomain(params.getDomain());

        return target;
    }

    // TODO - add exception
    public String retrieveSessionId(final String request)
    {
        String sessionId = null;
        String string = null;

        string = request.substring(1, request.length() - 1);
        final String[] keyValuePairs = string.split(",");
        for (final String pair : keyValuePairs)
        {
            final String[] entry = pair.split(":");
            if (entry[0].trim().contains("sessionID"))
            {
                sessionId = (entry[1].trim().replaceAll("\"", ""));
            }
        }

        return sessionId;
    }
}
