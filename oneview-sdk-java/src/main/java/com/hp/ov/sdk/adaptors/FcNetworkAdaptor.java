/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.FcNetworkCollection;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class FcNetworkAdaptor extends BaseAdaptor<FcNetwork, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public FcNetwork buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final FcNetwork fcNetworkDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), FcNetwork.class);
        return fcNetworkDto;
    }

    public FcNetworkCollection buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        final FcNetworkCollection fcNetworkCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                FcNetworkCollection.class);
        return fcNetworkCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final FcNetwork source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    /**
     * 
     * @return The converter
     */
    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    /**
     * 
     * @param converter
     *        The converter
     */
    public void setConverter(final ObjectJsonConverter converter)
    {
        this.converter = converter;
    }

    /**
     * 
     * @return The util
     */
    public StringUtil getUtil()
    {
        return util;
    }

    /**
     * 
     * @param util
     *        The util
     */
    public void setUtil(final StringUtil util)
    {
        this.util = util;
    }

}
