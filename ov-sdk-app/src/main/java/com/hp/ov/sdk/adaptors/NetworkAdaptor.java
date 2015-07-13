/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class NetworkAdaptor extends BaseAdaptor<Network, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public Network buildDto(Object source)
    {
        // TODO - exceptions
        // convert Object to DTO includes replace quotes and back slash
        Network networkDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), Network.class);
        return networkDto;
    }

    public BulkEthernetNetwork buildBulkEthernetDto(Object source)
    {
        // TODO - exceptions
        // convert Object to DTO Collection includes replace quotes and back
        // slash
        BulkEthernetNetwork bulkEthernetNetworkDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                BulkEthernetNetwork.class);
        return bulkEthernetNetworkDto;
    }

    public NetworkCollection buildCollectionDto(Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }

        // convert json object to DTO replace quotes and back slash in the file
        NetworkCollection networkCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                NetworkCollection.class);
        return networkCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final Network source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFrombulkEthernetDto(
            final BulkEthernetNetwork source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    public void setConverter(ObjectJsonConverter converter)
    {
        this.converter = converter;
    }

    public StringUtil getUtil()
    {
        return util;
    }

    public void setUtil(StringUtil util)
    {
        this.util = util;
    }

}
