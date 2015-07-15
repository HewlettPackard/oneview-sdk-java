/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.generated.ServerHardware;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ServerHardwareAdaptor extends BaseAdaptor<ServerHardware, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public ServerHardware buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        final ServerHardware serverHardwareDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                ServerHardware.class);
        return serverHardwareDto;
    }

    public ServerHardwareCollection buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json Object to DTO, replace quotes and back slash in the file
        final ServerHardwareCollection serverHardwareCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                ServerHardwareCollection.class);
        return serverHardwareCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(
            final ServerPowerControlRequest source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

}
