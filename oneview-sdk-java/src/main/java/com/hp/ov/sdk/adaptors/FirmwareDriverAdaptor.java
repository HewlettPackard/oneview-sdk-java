/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class FirmwareDriverAdaptor extends BaseAdaptor<FwBaseline, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public FwBaseline buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final FwBaseline fwBaselineDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), FwBaseline.class);
        return fwBaselineDto;
    }

    public FwBaselineCollection buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json object to DTO, replace quotes and back slash in the file
        final FwBaselineCollection fwBaselineCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                FwBaselineCollection.class);
        return fwBaselineCollectionDto;
    }

    /**
     * @return the converter
     */
    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    /**
     * @param converter the converter to set
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
     * @param util the util to set
     */
    public void setUtil(final StringUtil util)
    {
        this.util = util;
    }

}
