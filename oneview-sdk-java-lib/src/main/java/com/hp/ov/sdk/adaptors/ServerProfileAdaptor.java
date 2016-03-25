/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.reflect.TypeToken;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableStorageSystems;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class ServerProfileAdaptor extends BaseAdaptor<ServerProfile, Object> {

    @Override
    public ServerProfile buildDto(final Object source) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final ServerProfile serverProfileDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), ServerProfile.class);
        return serverProfileDto;
    }

    public ServerProfile buildDto(final Object source, final double version) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final ServerProfile serverProfileDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)), ServerProfile.class);
        return serverProfileDto;
    }

    public ServerProfileCompliancePreview buildCompliancePreviewDto(final Object source, final double version) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final ServerProfileCompliancePreview compliancePreviewDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)),
                ServerProfileCompliancePreview.class);
        return compliancePreviewDto;
    }

    public ServerProfileHealth buildHealthDto(final String source, final double version) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final ServerProfileHealth compliancePreviewDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)),
                ServerProfileHealth.class);
        return compliancePreviewDto;
    }

    public ServerProfile buildServerProfileDto(final String source, final double version) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final ServerProfile serverProfileDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)),
                ServerProfile.class);
        return serverProfileDto;
    }

    public AvailableStorageSystem buildAvailableStorageSystemDto(final String source, final double version) {
        // TODO - exceptions
        // convert json object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        final AvailableStorageSystem storageSystemDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)),
                AvailableStorageSystem.class);
        return storageSystemDto;
    }

    public ServerProfileCollection buildCollectionDto(String source, int version) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json file to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final ServerProfileCollection serverProfileCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source, version))), ServerProfileCollection.class);
        return serverProfileCollectionDto;
    }

    public ServerProfileCollection buildCollectionDto(final Object source) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json file to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final ServerProfileCollection serverProfileCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), ServerProfileCollection.class);
        return serverProfileCollectionDto;
    }

    public AvailableStorageSystems buildAvailableStorageSystemsDto(final String source, final double version) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json file to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final AvailableStorageSystems storageSystemsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source, version))), AvailableStorageSystems.class);
        return storageSystemsDto;
    }

    public AvailableTargets buildAvailableTargetsDto(final String source, final double version) {
        // TODO - exceptions
        if (null == source || source.equals("")) {
            return null;
        }
        // convert json file to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final AvailableTargets storageSystemsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source, version))), AvailableTargets.class);
        return storageSystemsDto;
    }

    public AvailableNetworks buildAvailableNetworkDto(final Object source) {
        // convert json file to DTO, replace quote and back slash in file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final AvailableNetworks availableNetworksDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), AvailableNetworks.class);
        return availableNetworksDto;
    }

    public List<AvailableServers> buildAvailableServerDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        converter.convertObjectToJsonString(source);
        // replace quotes and back slash in the file
        StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source));
        StringUtil
                .replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)));
        // convert json object to DTO, replace quotes and back slash in the file

        final List<AvailableServers> availableServersCollectionDto = (List<AvailableServers>) converter.convertJsonToListObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), new TypeToken<List<AvailableServers>>() {
                }.getType());
        return availableServersCollectionDto;
    }

    public ProfilePorts buildProfilePortsDto(final Object source, final double version) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO, replace quotes and back slash in the file
        final ProfilePorts profilePortsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version)), ProfilePorts.class);
        return profilePortsDto;
    }

    public JSONObject buildJsonObjectFromDto(final ServerProfile source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONArray buildJsonArrayDto(Patch source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONArray(converter.convertObjectToJsonString(Arrays.asList(source)));
    }

}
