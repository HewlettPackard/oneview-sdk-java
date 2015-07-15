/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StorageSystemAdaptor extends BaseAdaptor<StorageSystemV2, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public StorageSystemV2 buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageSystemV2 storageSystemDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                StorageSystemV2.class);
        return storageSystemDto;
    }

    public StorageSystemCollection buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }

        // convert json object to DTO, replace quotes and back slash in the file
        final StorageSystemCollection storageSystemCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                StorageSystemCollection.class);
        return storageSystemCollectionDto;
    }

    public StoragePoolCollection buildStoragePoolCollectionDto(final Object source)
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

    public StorageTargetPortCollection buildManagedPortsCollectionDto(
            final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageTargetPortCollection storageTargetPortCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                StorageTargetPortCollection.class);
        return storageTargetPortCollectionDto;
    }

    public StorageTargetPortV2 buildManagedPortsDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        final StorageTargetPortV2 storageTargetPortDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                StorageTargetPortV2.class);
        return storageTargetPortDto;
    }

    public JSONObject buildJsonObjectFromDto(
            final AddStorageSystemCredentials source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final StorageSystemV2 source)
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
