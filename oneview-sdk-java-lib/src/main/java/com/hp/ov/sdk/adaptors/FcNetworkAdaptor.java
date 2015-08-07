/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.FcNetworkCollection;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class FcNetworkAdaptor extends BaseAdaptor<FcNetwork, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public FcNetwork buildDto(final Object source) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final FcNetwork fcNetworkDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), FcNetwork.class);
        return fcNetworkDto;
    }

    public FcNetworkCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json object to DTO, replace quotes and back slash in the file
        final FcNetworkCollection fcNetworkCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), FcNetworkCollection.class);
        return fcNetworkCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final FcNetwork source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
