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
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class LogicalInterconnectGroupAdaptor extends BaseAdaptor<LogicalInterconnectGroups, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public LogicalInterconnectGroups buildDto(final Object source) {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LogicalInterconnectGroups logicalInterconnectGroupDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LogicalInterconnectGroups.class);
        return logicalInterconnectGroupDto;
    }

    public InterconnectSettingsV2 buildInterconnectSettingsDto(final Object source) {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final InterconnectSettingsV2 interconnectSettingsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), InterconnectSettingsV2.class);
        return interconnectSettingsDto;
    }

    public LogicalInterconnectGroupCollectionV2 buildCollectionDto(final Object source) {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), LogicalInterconnectGroupCollectionV2.class);
        return logicalInterconnectGroupCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final LogicalInterconnectGroups source) {
        // return the JSON object.
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
