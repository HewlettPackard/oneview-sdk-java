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

import org.json.JSONObject;

import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class UplinkSetAdaptor extends BaseAdaptor<UplinkSets, Object> {

    @Override
    public UplinkSets buildDto(final Object source) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final UplinkSets uplinkSetDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), UplinkSets.class);
        return uplinkSetDto;
    }

    public UplinkSets buildDto(final Object source, final double version) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final UplinkSets uplinkSetDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)), UplinkSets.class);
        return uplinkSetDto;
    }

    public UplinkSetCollectionV2 buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        // convert json object to DTO, replace quotes and back slash in the file
        final UplinkSetCollectionV2 uplinkSetCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(
                        StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source))), UplinkSetCollectionV2.class);
        return uplinkSetCollectionDto;
    }

    public UplinkSetCollectionV2 buildCollectionDto(final Object source, final double version) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        // convert json object to DTO, replace quotes and back slash in the file
        UplinkSetCollectionV2 uplinkSetCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(
                        StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version))),
                UplinkSetCollectionV2.class);
        return uplinkSetCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final UplinkSets source, double apiVersion) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source, apiVersion));
    }

}
