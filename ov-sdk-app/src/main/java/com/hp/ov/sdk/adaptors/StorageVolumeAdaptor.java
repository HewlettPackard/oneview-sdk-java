/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StorageVolumeAdaptor extends BaseAdaptor<StorageVolumeV2, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public StorageVolumeV2 buildDto(Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        StorageVolumeV2 storageVolumeDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                StorageVolumeV2.class);
        return storageVolumeDto;
    }

    public StorageVolumeCollection buildCollectionDto(Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        StorageVolumeCollection storageVolumeCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                StorageVolumeCollection.class);
        return storageVolumeCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final StorageVolumeV2 source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddStorageVolumeV2 source)
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
    public void setConverter(ObjectJsonConverter converter)
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
    public void setUtil(StringUtil util)
    {
        this.util = util;
    }

}
