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

import com.hp.ov.sdk.dto.ConnectionTemplateCollection;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;
import org.json.JSONObject;

public class ConnectionTemplateAdaptor extends BaseAdaptor<ConnectionTemplate, Object> {

    @Override
    public ConnectionTemplate buildDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final ConnectionTemplate connectionTemplateDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), ConnectionTemplate.class);
        return connectionTemplateDto;
    }

    public ConnectionTemplateCollection buildCollectionDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final ConnectionTemplateCollection connectionTemplateCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), ConnectionTemplateCollection.class);
        return connectionTemplateCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final ConnectionTemplate source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source));
    }
}
