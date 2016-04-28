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

import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class InterconnectTypeAdaptor extends BaseAdaptor<InterconnectType, Object> {

    @Override
    public InterconnectType buildDto(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final InterconnectType interconnectTypeDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), InterconnectType.class);
        return interconnectTypeDto;
    }

    public InterconnectTypeCollection buildCollectionDto(final Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json Object to DTO, replace quotes and back slash in the file
        final InterconnectTypeCollection interconnectTypeCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), InterconnectTypeCollection.class);
        return interconnectTypeCollectionDto;
    }

}
