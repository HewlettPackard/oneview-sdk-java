/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class StorageVolumeTemplateAdaptor extends
        BaseAdaptor<StorageVolumeTemplate, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public StorageVolumeTemplate buildDto(Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        StorageVolumeTemplate storageVolumeTemplateDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                StorageVolumeTemplate.class);
        return storageVolumeTemplateDto;
    }

    public StorageVolumeTemplateCollection buildCollectionDto(Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                StorageVolumeTemplateCollection.class);
        return storageVolumeTemplateCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final StorageVolumeTemplate source)
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
