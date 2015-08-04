/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class TaskAdaptor extends BaseAdaptor<TaskResourceV2, Object>
{

    private ObjectToJsonConverter converter;

    @Override
    public TaskResourceV2 buildDto(final Object source)
    {
        // convert Object to DTO includes replace quotes and back slash
        converter = ConverterFactory.getConverter();
        final TaskResourceV2 taskResourceV2 = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source))),
                TaskResourceV2.class);
        return taskResourceV2;
    }

    public Object buildClassDto(final Object source, final Class<?> classObj)
    {
        // convert Object to DTO includes replace quotes and back slash
        converter = ConverterFactory.getConverter();
        return converter.convertJsonToObject(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), classObj);

    }

}
