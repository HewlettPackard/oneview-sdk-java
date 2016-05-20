/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ApplianceDetailsAdaptor;
import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKApiVersionMismatchException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClientImpl;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.FcNetworkClientImpl;
import com.hp.ov.sdk.rest.client.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.InterconnectTypeClientImpl;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClientImpl;
import com.hp.ov.sdk.rest.client.NetworkClient;
import com.hp.ov.sdk.rest.client.NetworkClientImpl;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClientImpl;
import com.hp.ov.sdk.rest.client.StorageVolumeClient;
import com.hp.ov.sdk.rest.client.StorageVolumeClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.ApplianceDetails;
import com.hp.ov.sdk.rest.login.LoginSessions;

public class SdkUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SdkUtils.class);

    private final FcNetworkClient fcNetworkClient;
    private final LoginSessions loginSessions;
    private final ApplianceDetails applianceDetails;
    private final NetworkClient networkClient;
    private final InterconnectTypeClient interconnectTypeClient;
    private final LogicalInterconnectGroupClient logicalInterconnectGroupClient;
    private final ServerHardwareClient serverHardwareClient;
    private final EnclosureGroupClient enclosureGroupClient;
    private final StorageVolumeClient storageVolumeClient;

    private static final class SdkUtilsHolder {
        private static final SdkUtils INSTANCE = new SdkUtils(
                new LoginSessions(new LoginSessionAdaptor()),
                new ApplianceDetails(new ApplianceDetailsAdaptor()),
                FcNetworkClientImpl.getClient(),
                NetworkClientImpl.getClient(),
                InterconnectTypeClientImpl.getClient(),
                LogicalInterconnectGroupClientImpl.getClient(),
                ServerHardwareClientImpl.getClient(),
                EnclosureGroupClientImpl.getClient(),
                StorageVolumeClientImpl.getClient()
        );
    }

    private SdkUtils(LoginSessions loginSessions, ApplianceDetails applianceDetails,
        FcNetworkClient fcNetworkClient,
        NetworkClient networkClient,
        InterconnectTypeClient interconnectTypeClient,
        LogicalInterconnectGroupClient logicalInterconnectGroupClient,
        ServerHardwareClient serverHardwareClient,
        EnclosureGroupClient enclosureGroupClient,
        StorageVolumeClient storageVolumeClient) {

        this.loginSessions = loginSessions;
        this.applianceDetails = applianceDetails;
        this.fcNetworkClient = fcNetworkClient;
        this.networkClient = networkClient;
        this.interconnectTypeClient = interconnectTypeClient;
        this.logicalInterconnectGroupClient = logicalInterconnectGroupClient;
        this.serverHardwareClient = serverHardwareClient;
        this.enclosureGroupClient = enclosureGroupClient;
        this.storageVolumeClient = storageVolumeClient;
    }

    public static SdkUtils getInstance() {
        return SdkUtilsHolder.INSTANCE;
    }

    public RestParams createRestParams(final RestParams params) {
        // Get version
        final ApplianceDetailsDto applianceDetailsDto = applianceDetails.getVersion(params);
        // validate the API version
        validateApiVersion(params.getApiVersion(), applianceDetailsDto.getMinimumVersion(), applianceDetailsDto.getCurrentVersion());

        // Get login session
        final String sessionId = loginSessions.getLoginSessionId(params);

        params.setSessionId(sessionId);

        return params;
    }

    private void validateApiVersion(final int requestedVersion, final int applianceMinimumVersion, final int applianceCurrentVersion) {
        LOGGER.info("########### Checking API Version Start ####################");

        if (requestedVersion >= applianceMinimumVersion && requestedVersion  <= applianceCurrentVersion) {
            LOGGER.info("API version : You are trying to connect to appliance verion: "
                    + requestedVersion  + " and it is matching with the minimum and current version ("
                    + applianceMinimumVersion + " to " + applianceCurrentVersion + ")");

            LOGGER.info("########### Checking API Version End ####################");
            return;
        }
        throw new SDKApiVersionMismatchException(SDKErrorEnum.apiMismatchError, null, null, null, SdkConstants.APPLIANCE, null);
    }

    public List<String> getNetworkUris(RestParams params, List<String> networkNames) {
        List<String> networkUris = new ArrayList<String>();

        for (String networkName : networkNames) {
            Network dto = networkClient.getNetworkByName(params, networkName);

            if (dto.getUri() != null) {
                String networkUri = dto.getUri();

                networkUris.add(networkUri);
            }
        }
        return networkUris;
    }

    public String getNetworkUri(final RestParams params, final String networkName) {
        return networkClient.getNetworkByName(params, networkName).getUri();
    }

    public String getFcNetworkUri(final RestParams params, final String networkName) {
        return fcNetworkClient.getFcNetworkByName(params, networkName).getUri();
    }

    public List<String> getFcNetworkUris(final RestParams params, final List<String> networkNames) {
        List<String> networkUris = new ArrayList<String>();
        FcNetwork dto = null;
        String fcNetworkUri = null;

        for (String networkName : networkNames) {
            dto = fcNetworkClient.getFcNetworkByName(params, networkName);

            if (null != dto.getUri()) {
                fcNetworkUri = dto.getUri();
                networkUris.add(fcNetworkUri);
            }

        }
        return networkUris;
    }

    public String getPermittedInterconnectTypeUri(final RestParams params, final String permittedInterconnectType) {
        return interconnectTypeClient.getInterconnectTypeByName(params, permittedInterconnectType).getUri();
    }

    public String getPermittedInterconnectTypeUriForLigBasedOnBay(final RestParams params, final String ligName, final Integer bay) {
        LogicalInterconnectGroups logicalInterconnectGroupsDto = logicalInterconnectGroupClient.getLogicalInterconnectGroupByName(params, ligName);
        if (logicalInterconnectGroupsDto == null) {
            return null;
        }

        if (logicalInterconnectGroupsDto.getInterconnectMapTemplate() == null) {
            return null;
        }

        for (InterconnectMapEntryTemplate mapTemplate : logicalInterconnectGroupsDto.getInterconnectMapTemplate()
                .getInterconnectMapEntryTemplates()) {
            for (LocationEntry locationEntry : mapTemplate.getLogicalLocation().getLocationEntries()) {
                if (locationEntry.getType().equals(LocationEntry.Type.Bay) && locationEntry.getRelativeValue() == bay) {
                    return mapTemplate.getPermittedInterconnectTypeUri();
                }
            }
        }
        return null;
    }

    public String getServerHardwareUri(final RestParams params, final String serverHardwareName) {
        return serverHardwareClient.getServerHardwareByName(params, serverHardwareName).getUri();
    }

    public String getServerHardwareTypeUri(final RestParams params, final String serverHardwareName) {
        return serverHardwareClient.getServerHardwareByName(params, serverHardwareName).getServerHardwareTypeUri();
    }

    public String getEnclosureGroupUri(final RestParams params, final String enclosureGroupName) {
        return enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();
    }

    public Boolean isVolumeSharable(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getShareable();
    }

    public String getVolumeUri(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getUri();
    }

    public String getStoragePoolFromVolume(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getStoragePoolUri();
    }

    public String getStorageSystemFromVolume(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getStorageSystemUri();
    }
}
