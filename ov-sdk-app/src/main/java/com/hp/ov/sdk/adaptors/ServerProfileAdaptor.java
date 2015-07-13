/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.util.ObjectJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ServerProfileAdaptor extends BaseAdaptor<ServerProfile, Object>
{

    @Autowired
    private ObjectJsonConverter converter;

    @Autowired
    private StringUtil util;

    @Override
    public ServerProfile buildDto(Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ServerProfile serverProfileDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                ServerProfile.class);
        return serverProfileDto;
    }

    public ServerProfileCollection buildCollectionDto(Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json file to DTO, replace quotes and back slash in the file
        ServerProfileCollection serverProfileCollectionDto = converter.convertJsonToObject(util
                .replaceQuotesBackSlashWithQuote(util
                        .replaceQuotesAndBackSlash(converter
                                .convertObjectToJsonString(source))),
                ServerProfileCollection.class);
        return serverProfileCollectionDto;
    }

    public AvailableNetworks buildAvailableNetworkDto(Object source)
    {
        // convert json file to DTO, replace quote and back slash in file
        AvailableNetworks availableNetworksDto = converter.convertJsonToObject(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)),
                AvailableNetworks.class);
        return availableNetworksDto;
    }

    public List<AvailableServers> buildAvailableServerDto(Object source)
    {
        converter.convertObjectToJsonString(source);
        // replace quotes and back slash in the file
        util.replaceQuotesAndBackSlash(converter
                .convertObjectToJsonString(source));
        util.replaceQuotesBackSlashWithQuote(util
                .replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)));
        // convert json object to DTO, replace quotes and back slash in the file

        List<AvailableServers> availableServersCollectionDto = (List<AvailableServers>) converter
                .convertJsonToListObject(util
                        .replaceQuotesBackSlashWithQuote(util
                                .replaceQuotesAndBackSlash(converter
                                        .convertObjectToJsonString(source))),
                        new TypeToken<List<AvailableServers>>()
                        {
                        }.getType());
        return (List<AvailableServers>) availableServersCollectionDto;
    }

    public ProfilePorts buildProfilePortsDto(Object source)
    {
        // convert json object to DTO, replace quotes and back slash in the file
        ProfilePorts profilePortsDto = converter
                .convertJsonToObject(util.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source)), ProfilePorts.class);
        return profilePortsDto;
    }

    public JSONObject buildJsonObjectFromDto(final ServerProfile source)
    {
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    /**
     * 
     * @return The converter
     */
    public ObjectJsonConverter getConverter()
    {
        return converter;
    }

    /**
     * 
     * @param converter
     *        The converter
     */
    public void setConverter(ObjectJsonConverter converter)
    {
        this.converter = converter;
    }

    /**
     * 
     * @return The util
     */
    public StringUtil getUtil()
    {
        return util;
    }

    /**
     * 
     * @param util
     *        The util
     */
    public void setUtil(StringUtil util)
    {
        this.util = util;
    }

}
