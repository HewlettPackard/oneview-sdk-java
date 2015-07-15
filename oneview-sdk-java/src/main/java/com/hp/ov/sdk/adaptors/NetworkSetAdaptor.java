/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class NetworkSetAdaptor extends BaseAdaptor<NetworkSets, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public NetworkSets buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final NetworkSets networkSetDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), NetworkSets.class);
        return networkSetDto;
    }

    public NetworkSetCollection buildCollectionDto(final Object source)
    {
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final NetworkSetCollection networkSetCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                NetworkSetCollection.class);
        return networkSetCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final NetworkSets source)
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
