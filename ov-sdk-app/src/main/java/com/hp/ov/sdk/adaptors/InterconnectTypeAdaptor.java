/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.InterconnectTypeCollectionV2;
import com.hp.ov.sdk.dto.generated.InterconnectTypes;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class InterconnectTypeAdaptor extends
        BaseAdaptor<InterconnectTypes, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public InterconnectTypes buildDto(Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        InterconnectTypes interconnectTypeDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                InterconnectTypes.class);
        return interconnectTypeDto;
    }

    public InterconnectTypeCollectionV2 buildCollectionDto(Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        InterconnectTypeCollectionV2 interconnectTypeCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                InterconnectTypeCollectionV2.class);
        return interconnectTypeCollectionDto;
    }

    /**
     * @return the converter
     */
    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    /**
     * @param converter
     *        the converter to set
     */
    public void setConverter(ObjectJsonConverter converter)
    {
        this.converter = converter;
    }

    /**
     * @return the util
     */
    public StringUtil getUtil()
    {
        return util;
    }

    /**
     * @param util
     *        the util to set
     */
    public void setUtil(StringUtil util)
    {
        this.util = util;
    }

}
