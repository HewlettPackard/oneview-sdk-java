/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class ResourceAdaptor {

    public <T> List<T> buildListOfResourceObject(String source, Type listAndResourceType) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceReplaced = this.applyReplacementsOnSource(source);

        return (List<T>) converter.convertJsonToListObject(sourceReplaced, listAndResourceType);
    }

    public <T> T buildResourceObject(String source, Class<T> resourceClass) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceReplaced = this.applyReplacementsOnSource(source);

        return converter.convertJsonToObject(sourceReplaced, resourceClass);
    }

    public JSONObject buildJsonRequest(Object resourceObject, double apiVersion) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(resourceObject, apiVersion));
    }

    private String applyReplacementsOnSource(String source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceAsJson = converter.convertObjectToJsonString(source);
        String sourceReplacedQuotesAndBackSlash = StringUtil.replaceQuotesAndBackSlash(sourceAsJson);
        String sourceReplacedQuotesBackSlashWithQuote = StringUtil.replaceQuotesBackSlashWithQuote(
                sourceReplacedQuotesAndBackSlash);

        return sourceReplacedQuotesBackSlashWithQuote;
    }

}
