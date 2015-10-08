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

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class TaskAdaptor extends BaseAdaptor<TaskResourceV2, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public TaskResourceV2 buildDto(final Object source) {
        // convert Object to DTO includes replace quotes and back slash
        converter = ConverterFactory.getConverter();
        final TaskResourceV2 taskResourceV2 = converter.convertJsonToObject(StringUtil.replaceQuotesBackSlashWithQuote(StringUtil
                .replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source))), TaskResourceV2.class);
        return taskResourceV2;
    }

    public Object buildClassDto(final Object source, final Class<?> classObj) {
        // convert Object to DTO includes replace quotes and back slash
        converter = ConverterFactory.getConverter();
        return converter.convertJsonToObject(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                classObj);

    }

}
