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

import org.json.JSONObject;

import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class LogicalInterconnectAdaptor extends BaseAdaptor<LogicalInterconnects, Object> {

    @Override
    public LogicalInterconnects buildDto(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final LogicalInterconnects logicalInterconnectsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LogicalInterconnects.class);
        return logicalInterconnectsDto;
    }

    public LogicalInterconnects buildDto(final Object source, final ApiVersion version) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final LogicalInterconnects logicalInterconnectsDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source, version.getValue())), LogicalInterconnects.class);
        return logicalInterconnectsDto;
    }

    public LiFirmware buildFirmwareDto(final Object source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final LiFirmware liFirmwareDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), LiFirmware.class);
        return liFirmwareDto;
    }

    public JSONObject buildJsonObjectFromDto(final LiFirmware source) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(final SnmpConfiguration source) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(PortMonitor source) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(TelemetryConfiguration source) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(EthernetInterconnectSettingsV2 source, final ApiVersion version) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version.getValue()));
    }

    public JSONObject buildJsonObjectFromDto(Location source) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(QosAggregatedConfiguration source) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source));
    }

    public JSONObject buildJsonObjectFromDto(InterconnectSettingsV2 source, final ApiVersion version) {
        // return the JSON object.
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        return new JSONObject(converter.convertObjectToJsonString(source, version.getValue()));
    }

    public InterconnectFibDataInfo buildInterconnectFibDataInfoDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final InterconnectFibDataInfo fibDataDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                InterconnectFibDataInfo.class);
        return fibDataDto;
    }

    public SnmpConfiguration buildSnmpConfigurationDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final SnmpConfiguration snmpConfigDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                SnmpConfiguration.class);
        return snmpConfigDto;
    }

    public PortMonitor buildPortMonitorDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final PortMonitor portMonitorDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                PortMonitor.class);
        return portMonitorDto;
    }

    public TelemetryConfiguration buildTelemetryConfigurationsDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final TelemetryConfiguration portMonitorDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)), TelemetryConfiguration.class);
        return portMonitorDto;
    }

    public QosAggregatedConfiguration buildQosConfigurationDto(String source) {
        // convert json Object to DTO, replace quotes and back slash in the file
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        final QosAggregatedConfiguration qosConfigurationDto = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                QosAggregatedConfiguration.class);
        return qosConfigurationDto;
    }

}
