/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class LogicalInterconnectGroupAdaptor extends
        BaseAdaptor<LogicalInterconnectGroups, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public LogicalInterconnectGroups buildDto(final Object source)
    {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        final LogicalInterconnectGroups logicalInterconnectGroupDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                LogicalInterconnectGroups.class);
        return logicalInterconnectGroupDto;
    }

    public LogicalInterconnectGroupCollectionV2 buildCollectionDto(final Object source)
    {
        // write json object to a file
        // convert json Object to DTO, replace quotes and back slash in the file
        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = converter.convertJsonToObject(
                util.replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                LogicalInterconnectGroupCollectionV2.class);
        return logicalInterconnectGroupCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final LogicalInterconnectGroups source)
    {
        // return the JSON object.
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    public void setConverter(final ObjectJsonConverter converter)
    {
        this.converter = converter;
    }

    public StringUtil getUtil()
    {
        return util;
    }

    public void setUtil(final StringUtil util)
    {
        this.util = util;
    }

}
