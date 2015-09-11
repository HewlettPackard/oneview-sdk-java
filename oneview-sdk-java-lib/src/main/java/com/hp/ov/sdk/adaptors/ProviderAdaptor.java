/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.SanProviderResponseCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ProviderAdaptor extends BaseAdaptor<SanProviderResponse, Object> {
    private ObjectToJsonConverter converter;

    @Override
    public SanProviderResponse buildDto(Object source) {
        converter = ConverterFactory.getConverter();
        // TODO Auto-generated method stub
        final SanProviderResponse sanProviderResponseDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), SanProviderResponse.class);

        return sanProviderResponseDto;
    }

    public SanProviderResponseCollection buildCollectionDto(Object source) {
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // TODO Auto-generated method stub
        final SanProviderResponseCollection sanProviderResponseCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                SanProviderResponseCollection.class);

        return sanProviderResponseCollectionDto;
    }
}
