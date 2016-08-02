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

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class EnclosureAdaptor extends BaseAdaptor<Enclosure, Object> {

    @Override
    public Enclosure buildDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json Object to DTO, replace quotes and back slash in the file
        final Enclosure enclosureDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), Enclosure.class);
        return enclosureDto;
    }

    public AddEnclosure buildAddEnclosureDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json Object to DTO, replace quotes and back slash in the file
        final AddEnclosure enclosureDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), AddEnclosure.class);
        return enclosureDto;
    }

    public EnvironmentalConfiguration buildEnvironmentalConfigurationDto(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final EnvironmentalConfiguration environmentalConfigurationDto = converter
                .convertJsonToObject(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                        EnvironmentalConfiguration.class);
        return environmentalConfigurationDto;
    }

    public SsoUrlData buildSsoUrlData(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final SsoUrlData ssoUrlData = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), SsoUrlData.class);
        return ssoUrlData;
    }

    public UtilizationData buildUtilizationData(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final UtilizationData utilizationData = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), UtilizationData.class);
        return utilizationData;
    }

    public JSONObject buildJsonObjectFromDto(final Enclosure source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONArray buildJsonArrayDto(final Patch source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONArray(converter.convertObjectToJsonString(Arrays.asList(source)));
    }

    public JSONObject buildJsonObjectFromDto(final FwBaselineConfig source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final RefreshStateConfig source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final EnvironmentalConfigurationUpdate source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddEnclosure source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }
}
