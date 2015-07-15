/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class TaskAdaptor extends BaseAdaptor<TaskResourceV2, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public TaskResourceV2 buildDto(final Object source)
    {
        // convert Object to DTO includes replace quotes and back slash
        final TaskResourceV2 taskResourceV2 = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                TaskResourceV2.class);
        return taskResourceV2;
    }

    public Object buildClassDto(final Object source, final Class<?> classObj)
    {
        // convert Object to DTO includes replace quotes and back slash
        return converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), classObj);

    }

}
