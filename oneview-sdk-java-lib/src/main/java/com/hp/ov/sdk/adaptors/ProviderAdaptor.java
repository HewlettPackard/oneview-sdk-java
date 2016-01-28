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



import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.SanProviderResponseCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;


public class ProviderAdaptor extends BaseAdaptor<SanProviderResponse, Object> {


    @Override
    public SanProviderResponse buildDto(Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // TODO Auto-generated method stub
        final SanProviderResponse sanProviderResponseDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), SanProviderResponse.class);

        return sanProviderResponseDto;
    }

    public SanProviderResponseCollection buildCollectionDto(Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // TODO Auto-generated method stub
        final SanProviderResponseCollection sanProviderResponseCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                SanProviderResponseCollection.class);

        return sanProviderResponseCollectionDto;
    }
}
