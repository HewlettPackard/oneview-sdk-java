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
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ManagedSanAdaptor extends BaseAdaptor<SanResponse, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public SanResponse buildDto(Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final SanResponse deviceManagerResponseDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), SanResponse.class);
        return deviceManagerResponseDto;
    }

    public SanResponseCollection buildCollectionDto(Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final SanResponseCollection deviceManagerResponseCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), SanResponseCollection.class);
        return deviceManagerResponseCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final SanResponse source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
