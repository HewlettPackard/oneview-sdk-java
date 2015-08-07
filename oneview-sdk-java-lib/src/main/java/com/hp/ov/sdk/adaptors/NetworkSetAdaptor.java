/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class NetworkSetAdaptor extends BaseAdaptor<NetworkSets, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public NetworkSets buildDto(final Object source) {
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final NetworkSets networkSetDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), NetworkSets.class);
        return networkSetDto;
    }

    public NetworkSetCollection buildCollectionDto(final Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final NetworkSetCollection networkSetCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), NetworkSetCollection.class);
        return networkSetCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final NetworkSets source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
