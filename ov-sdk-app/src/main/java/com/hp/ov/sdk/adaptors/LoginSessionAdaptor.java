/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ObjectJsonConverter;

@Component
public class LoginSessionAdaptor extends
        BaseAdaptor<LoginSessionDto, RestParams>
{

    @Autowired
    private ObjectJsonConverter converter;

    public JSONObject buildJSONObjectFromDto(final LoginSessionDto source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    @Override
    public LoginSessionDto buildDto(final RestParams params)
    {
        LoginSessionDto target = new LoginSessionDto();
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
        String[] keyValuePairs = string.split(",");
        for (String pair : keyValuePairs)
        {
            String[] entry = pair.split(":");
            if (entry[0].trim().contains("sessionID"))
            {
                sessionId = (entry[1].trim().replaceAll("\"", ""));
            }
        }

        return sessionId;
    }
}
