/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class UplinkSetAdaptor extends BaseAdaptor<UplinkSets, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public UplinkSets buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final UplinkSets uplinkSetDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), UplinkSets.class);
        return uplinkSetDto;
    }

    public UplinkSetCollectionV2 buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        final UplinkSetCollectionV2 uplinkSetCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                UplinkSetCollectionV2.class);
        return uplinkSetCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final UplinkSets source)
    {
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
