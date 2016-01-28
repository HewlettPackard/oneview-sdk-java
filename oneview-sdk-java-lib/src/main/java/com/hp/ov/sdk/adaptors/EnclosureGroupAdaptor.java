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

import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;
import org.json.JSONObject;

public class EnclosureGroupAdaptor extends BaseAdaptor<EnclosureGroups, Object> {

    @Override
    public EnclosureGroups buildDto(final Object source) {
        // TODO - exceptions
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json Object to DTO, replace quotes and back slash in the file
        final EnclosureGroups enclosureGroupDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), EnclosureGroups.class);
        return enclosureGroupDto;
    }

    public EnclosureGroupCollectionV2 buildCollectionDto(final Object source) {
        // TODO - exceptions
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final EnclosureGroupCollectionV2 enclosureGroupCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), EnclosureGroupCollectionV2.class);
        return enclosureGroupCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final EnclosureGroups source, double version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version));
    }
}
