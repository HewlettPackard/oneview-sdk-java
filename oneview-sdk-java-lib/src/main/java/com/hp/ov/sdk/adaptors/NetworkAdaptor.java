/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class NetworkAdaptor extends BaseAdaptor<Network, Object> {

    private ObjectToJsonConverter converter;

    @Override
    public Network buildDto(final Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert Object to DTO includes replace quotes and back slash
        final Network networkDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), Network.class);
        return networkDto;
    }

    public BulkEthernetNetwork buildBulkEthernetDto(final Object source) {
        converter = ConverterFactory.getConverter();
        // TODO - exceptions
        // convert Object to DTO Collection includes replace quotes and back
        // slash
        final BulkEthernetNetwork bulkEthernetNetworkDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), BulkEthernetNetwork.class);
        return bulkEthernetNetworkDto;
    }

    public NetworkCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        converter = ConverterFactory.getConverter();
        // convert json object to DTO replace quotes and back slash in the file
        final NetworkCollection networkCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), NetworkCollection.class);
        return networkCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final Network source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFrombulkEthernetDto(final BulkEthernetNetwork source) {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
