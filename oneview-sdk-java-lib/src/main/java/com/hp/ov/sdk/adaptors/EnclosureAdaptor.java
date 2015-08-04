/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class EnclosureAdaptor extends BaseAdaptor<Enclosures, Object>
{

    private ObjectToJsonConverter converter;

    @Override
    public Enclosures buildDto(final Object source)
    {
        // TODO - exceptions
        converter = ConverterFactory.getConverter();
        // convert json Object to DTO, replace quotes and back slash in the file
        final Enclosures enclosureDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), Enclosures.class);
        return enclosureDto;
    }

    public EnclosureCollectionV2 buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        converter = ConverterFactory.getConverter();
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final EnclosureCollectionV2 enclosureCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source))),
                EnclosureCollectionV2.class);
        return enclosureCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final Enclosures source)
    {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final AddEnclosureV2 source)
    {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }
}
