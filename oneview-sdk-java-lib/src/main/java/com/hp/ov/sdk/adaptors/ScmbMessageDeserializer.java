/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.adaptors;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hp.ov.sdk.messaging.scmb.ScmbMessage;

public class ScmbMessageDeserializer implements JsonDeserializer<ScmbMessage> {

    @Override
    public ScmbMessage deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonElement resource = json.getAsJsonObject().remove("resource");
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter()).create();

        ScmbMessage message = gson.fromJson(json, ScmbMessage.class);

        message.setResource(resource.toString());

        return message;
    }

}
