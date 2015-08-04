/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.hp.ov.sdk.bean.factory.ConverterFactory;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class ServerProfileAdaptor extends BaseAdaptor<ServerProfile, Object>
{

    private ObjectToJsonConverter converter;
    
    @Override
    public ServerProfile buildDto(final Object source)
    {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final ServerProfile serverProfileDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), ServerProfile.class);
        return serverProfileDto;
    }

    public ServerProfileCollection buildCollectionDto(final Object source)
    {
        // TODO - exceptions
        if (null == source || source.equals(""))
        {
            return null;
        }
        // convert json file to DTO, replace quotes and back slash in the file
        converter = ConverterFactory.getConverter();
        final ServerProfileCollection serverProfileCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source))),
                ServerProfileCollection.class);
        return serverProfileCollectionDto;
    }

    public AvailableNetworks buildAvailableNetworkDto(final Object source)
    {
        // convert json file to DTO, replace quote and back slash in file
        converter = ConverterFactory.getConverter();
        final AvailableNetworks availableNetworksDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), AvailableNetworks.class);
        return availableNetworksDto;
    }

    public List<AvailableServers> buildAvailableServerDto(final Object source)
    {
        converter = ConverterFactory.getConverter();
        converter.convertObjectToJsonString(source);
        // replace quotes and back slash in the file
        StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source));
        StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)));
        // convert json object to DTO, replace quotes and back slash in the file

        final List<AvailableServers> availableServersCollectionDto = (List<AvailableServers>) converter.convertJsonToListObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source))),
                new TypeToken<List<AvailableServers>>()
                {
                }.getType());
        return availableServersCollectionDto;
    }

    public ProfilePorts buildProfilePortsDto(final Object source)
    {
        converter = ConverterFactory.getConverter();
        // convert json object to DTO, replace quotes and back slash in the file
        final ProfilePorts profilePortsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), ProfilePorts.class);
        return profilePortsDto;
    }

    public JSONObject buildJsonObjectFromDto(final ServerProfile source)
    {
        converter = ConverterFactory.getConverter();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }
   
}
