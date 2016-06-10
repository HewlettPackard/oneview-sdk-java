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

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class PowerDeliveryDeviceAdaptor extends BaseAdaptor<PowerDeliveryDevice, Object> {

    @Override
    public PowerDeliveryDevice buildDto(final Object source) {
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final PowerDeliveryDevice powerDeliveryDeviceDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), PowerDeliveryDevice.class);
        return powerDeliveryDeviceDto;
    }

    public PowerDeliveryDevice buildDto(final Object source, final ApiVersion version) {
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final PowerDeliveryDevice powerDeliveryDeviceDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version.getValue())), PowerDeliveryDevice.class);
        return powerDeliveryDeviceDto;
    }

    public UtilizationData buildUtilizationDataDto(final Object source, final ApiVersion version) {
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final UtilizationData utilizationDataDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version.getValue())), UtilizationData.class);
        return utilizationDataDto;
    }

    public PowerDeliveryDevice buildPowerDeliveryDeviceDto(final String source, final ApiVersion version) {
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final PowerDeliveryDevice powerDeliveryDeviceDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version.getValue())),
                PowerDeliveryDevice.class);
        return powerDeliveryDeviceDto;
    }

    public JSONObject buildJsonObjectFromDto(final PowerDeliveryDevice source, final ApiVersion version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version.getValue()));
    }

    public JSONObject buildJsonObjectFromDto(final OutletState source, final ApiVersion version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version.getValue()));
    }

    public JSONObject buildJsonObjectFromDto(ImportPdd source, ApiVersion version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version.getValue()));
    }

    public JSONArray buildJsonArrayDto(Patch source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONArray(converter.convertObjectToJsonString(Arrays.asList(source)));
    }

}
