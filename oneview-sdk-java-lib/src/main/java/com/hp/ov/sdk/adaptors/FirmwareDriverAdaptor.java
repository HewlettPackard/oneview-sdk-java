/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class FirmwareDriverAdaptor extends BaseAdaptor<FwBaseline, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public FwBaseline buildDto(final Object source) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final FwBaseline fwBaselineDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), FwBaseline.class);
        return fwBaselineDto;
    }

    public FwBaselineCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json object to DTO, replace quotes and back slash in the file
        final FwBaselineCollection fwBaselineCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), FwBaselineCollection.class);
        return fwBaselineCollectionDto;
    }

}
