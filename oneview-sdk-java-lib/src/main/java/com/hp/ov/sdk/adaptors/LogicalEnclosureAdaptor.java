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

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hp.ov.sdk.dto.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.generated.LogicalEnclosure;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class LogicalEnclosureAdaptor extends BaseAdaptor<LogicalEnclosure, Object> {

    @Override
    public LogicalEnclosure buildDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json Object to DTO, replace quotes and back slash in the file
        final LogicalEnclosure enclosureDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LogicalEnclosure.class);
        return enclosureDto;
    }

    public AddLogicalEnclosure buildAddEnclosureDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json Object to DTO, replace quotes and back slash in the file
        final AddLogicalEnclosure enclosureDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), AddLogicalEnclosure.class);
        return enclosureDto;
    }

    public JSONObject buildJsonObjectFromDto(final LogicalEnclosure source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final SupportDump supportDumpDto) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(supportDumpDto));
    }

    public JSONArray buildJsonArrayDto(final Patch source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONArray(converter.convertObjectToJsonString(Arrays.asList(source)));
    }

    public JSONObject buildJsonObjectFromDto(final AddLogicalEnclosure source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }
}
