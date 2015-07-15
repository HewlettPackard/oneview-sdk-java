/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class LogicalInterconnectAdaptor extends
        BaseAdaptor<LogicalInterconnects, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public LogicalInterconnects buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final LogicalInterconnects logicalInterconnectsDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                LogicalInterconnects.class);
        return logicalInterconnectsDto;
    }

    public LiFirmware buildFirmwareDto(final Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final LiFirmware liFirmwareDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                LiFirmware.class);
        return liFirmwareDto;
    }

    public LogicalInterconnectCollectionV2 buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                LogicalInterconnectCollectionV2.class);
        return logicalInterconnectCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final LiFirmware source)
    {
        // return the JSON object.
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final SnmpConfiguration source)
    {
        // return the JSON object.
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
