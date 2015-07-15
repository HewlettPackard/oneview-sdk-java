/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class EnclosureAdaptor extends BaseAdaptor<Enclosures, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public Enclosures buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final Enclosures enclosureDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), Enclosures.class);
        return enclosureDto;
    }

    public EnclosureCollectionV2 buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final EnclosureCollectionV2 enclosureCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                EnclosureCollectionV2.class);
        return enclosureCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final Enclosures source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddEnclosureV2 source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
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
    public void setConverter(final ObjectJsonConverter converter)
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
    public void setUtil(final StringUtil util)
    {
        this.util = util;
    }

}
