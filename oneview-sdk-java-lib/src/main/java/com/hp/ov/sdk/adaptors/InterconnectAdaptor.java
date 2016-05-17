/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Port;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class InterconnectAdaptor extends BaseAdaptor<Interconnects, Object> {

    @Override
    public Interconnects buildDto(Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final Interconnects interconnectDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), Interconnects.class);
        return interconnectDto;
    }

    public Interconnects buildDto(Object source, final double version) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final Interconnects interconnectDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)), Interconnects.class);
        return interconnectDto;
    }

    public JSONArray buildJsonArrayDto(Patch source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONArray(converter.convertObjectToJsonString(Arrays.asList(source)));
    }

    public JSONArray buildJsonArrayDto(List<Port> source, final double version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONArray(converter.convertObjectToJsonString(source, version));
    }

    public JSONObject buildJsonObjectFromDto(Port source, final double version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version));
    }

    public InterconnectsStatistics buildInterconnectStatisticsDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final InterconnectsStatistics interconnectsStatisticsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                InterconnectsStatistics.class);
        return interconnectsStatisticsDto;
    }

    public PortStatistics buildInterconnectPortStatisticsDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final PortStatistics interconnectsPortStatisticsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                PortStatistics.class);
        return interconnectsPortStatisticsDto;
    }

    public SubportStatistics buildInterconnectSubportStatisticsDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final SubportStatistics interconnectsSubportStatisticsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                SubportStatistics.class);
        return interconnectsSubportStatisticsDto;
    }

    public List<NameServer> buildInterconnectNameServersCollection(String source) {
        if (null == source || source.equals("")) {
            return Collections.emptyList();
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        Type listOfNameServer = new TypeToken<List<NameServer>>(){}.getType();
        return (List<NameServer>) converter.convertJsonToListObject(source, listOfNameServer);
    }

}
