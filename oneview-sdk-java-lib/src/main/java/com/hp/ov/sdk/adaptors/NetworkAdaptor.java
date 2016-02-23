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

import java.lang.reflect.Type;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;
import org.json.JSONObject;

public class NetworkAdaptor extends BaseAdaptor<Network, Object> {

    @Override
    public Network buildDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // TODO - exceptions
        // convert Object to DTO includes replace quotes and back slash
        final Network networkDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), Network.class);

        return networkDto;
    }

    public BulkEthernetNetwork buildBulkEthernetDto(final Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
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
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // convert json object to DTO replace quotes and back slash in the file
        final NetworkCollection networkCollectionDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesBackSlashWithQuote(StringUtil.replaceQuotesAndBackSlash(converter
                        .convertObjectToJsonString(source))), NetworkCollection.class);

        return networkCollectionDto;
    }

    public JSONObject buildJsonObjectFromDto(final Network source, int apiVersion) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source, apiVersion));
    }

    public JSONObject buildJsonObjectFromBulkEthernetDto(final BulkEthernetNetwork source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public List<String> buildCollectionOfUris(String source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        Type listOfString = new TypeToken<List<String>>(){}.getType();

        return (List<String>) converter.convertJsonToListObject(source, listOfString);
    }
}
