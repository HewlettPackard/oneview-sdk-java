/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.dto.ConnectionTemplateCollection;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ConnectionTemplateAdaptor extends
        BaseAdaptor<ConnectionTemplate, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    private JSONObject jsonObject;

    @Override
    public ConnectionTemplate buildDto(Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        ConnectionTemplate connectionTemplateDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                ConnectionTemplate.class);
        return connectionTemplateDto;
    }

    public ConnectionTemplateCollection buildCollectionDto(Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        ConnectionTemplateCollection connectionTemplateCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                ConnectionTemplateCollection.class);
        return connectionTemplateCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final ConnectionTemplate source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    public void setConverter(ObjectJsonConverter converter)
    {
        this.converter = converter;
    }

    public StringUtil getUtil()
    {
        return util;
    }

    public void setUtil(StringUtil util)
    {
        this.util = util;
    }

    public JSONObject getJsonObject()
    {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject)
    {
        this.jsonObject = jsonObject;
    }

}
