/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StoragePoolAdaptor extends BaseAdaptor<StoragePool, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public StoragePool buildDto(final Object source)
    {
        // TODO - exceptions
        util.replaceQuotesAndBackSlash(converter
                .convertObjectToJsonString(source));
        // convert json object to DTO, replace quotes and back slash in the file
        final StoragePool storagePoolDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), StoragePool.class);
        return storagePoolDto;
    }

    public StoragePoolCollection buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        final StoragePoolCollection storagePoolCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                StoragePoolCollection.class);
        return storagePoolCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final StoragePool source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddStoragePool source)
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
