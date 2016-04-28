/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;

import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ObjectToJsonConverter;

public class LoginSessionAdaptor extends BaseAdaptor<LoginSessionDto, RestParams> {

    public JSONObject buildJSONObjectFromDto(final LoginSessionDto source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    @Override
    public LoginSessionDto buildDto(final RestParams params) {
        final LoginSessionDto target = new LoginSessionDto();

        target.setUserName(params.getUserName());
        target.setPassword(params.getPassword());
        target.setAuthLoginDomain(params.getDomain());

        return target;
    }

    public String retrieveSessionId(final String request) {
        String sessionId = null;
        String string = request.substring(1, request.length() - 1);

        final String[] keyValuePairs = string.split(",");
        for (final String pair : keyValuePairs) {
            final String[] entry = pair.split(":");
            if (entry[0].trim().contains("sessionID")) {
                sessionId = (entry[1].trim().replaceAll("\"", ""));
            }
        }
        return sessionId;
    }
}
