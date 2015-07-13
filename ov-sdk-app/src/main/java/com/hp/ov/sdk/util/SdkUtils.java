/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKApiVersionMismatchException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.rest.client.ConnectionTemplateClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.NetworkClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.StorageVolumeClient;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.ApplianceDetails;
import com.hp.ov.sdk.rest.login.LoginSessions;

@Component
public class SdkUtils
{

    private static final Logger logger = LoggerFactory
            .getLogger(SdkUtils.class);

    @Autowired
    private ApplianceDetails applianceDetails;

    @Autowired
    private LoginSessions loginSessions;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private HttpSslProperties props;

    @Autowired
    private SslPropertiesManager util;

    @Autowired
    private NetworkClient networkClient;

    @Autowired
    private FcNetworkClient fcNetworkClient;

    @Autowired
    private InterconnectTypeClient interconnectTypeClient;

    @Autowired
    private LogicalInterconnectGroupClient logicalInterconnectGroupClient;

    @Autowired
    private ConnectionTemplateClient connectionTemplateClient;

    @Autowired
    private ServerHardwareClient serverHardwareClient;

    @Autowired
    private EnclosureGroupClient enclosureGroupClient;

    @Autowired
    private StorageVolumeClient storageVolumeClient;

    private LogicalInterconnectGroups logicalInterconnectGroupsDto;

    public String getResourceIdFromUri(final String uri)
    {
        final String resourceId = uri.substring(uri.lastIndexOf("/") + 1);
        return resourceId;
    }

    public String createQueryString(final String name)
    {
        return SdkConstants.QUERY_PREFIX + name + SdkConstants.QUERY_APPEND;
    }

    public String createRestUrl(final String hostname, final String uri)
    {

        return SdkConstants.HTTPS + hostname + uri;
    }

    public String createRestUrl(final String hostname, final String uri,
            final String resourceId)
    {

        return SdkConstants.HTTPS + hostname + uri + "/" + resourceId;
    }

    public String createRestUrl(final String hostname, final String uri,
            final String resourceId, final String subElement)
    {

        return SdkConstants.HTTPS + hostname + uri + "/" + resourceId + "/"
                + subElement;
    }

    public String createRestUrl(final String hostname, final String uri,
            final String resourceId, final String subElement,
            final String targetId)
    {

        return SdkConstants.HTTPS + hostname + uri + "/" + resourceId + "/"
                + subElement + "/" + targetId;
    }

    public String createRestQueryUrl(final String hostname, final String uri,
            final String query)
    {

        return SdkConstants.HTTPS + hostname + uri + "?"
                + query.replaceAll(" ", "%20");
    }

    public RestParams createRestParams(final RestParams params)
    {

        // Get version
        final ApplianceDetailsDto applianceDetailsDto = applianceDetails.getVersion(params);
        // validate the API version
        validateApiVersion(params.getApiVersion(),
                applianceDetailsDto.getCurrentVersion());

        params.setApiVersion(applianceDetailsDto.getCurrentVersion());
        // Create LoginSessionDto;
        final LoginSessionDto loginSessionDto = loginSessionAdaptor.buildDto(params);
        // Get login session
        final String sessionId = loginSessions.getLoginSessionId(params,
                loginSessionDto);
        params.setSessionId(sessionId);

        return params;

    }

    public RestParams createRestParamsWithoutSessionId(final RestParams params)
    {
        // Get version
        final ApplianceDetailsDto applianceDetailsDto = applianceDetails.getVersion(params);
        params.setApiVersion(applianceDetailsDto.getCurrentVersion());

        return params;

    }

    private void validateApiVersion(final int actualVersion, final int expectedVersion)
    {

        logger.info("########### Checking API Version Start ####################");
        if (expectedVersion == actualVersion)
        { // e.g. v 120 == v 120
            logger.info("API version : "
                    + "You are trying to connect to appliance verion: "
                    + actualVersion
                    + " and it is matching with your expected version:"
                    + expectedVersion);
        }
        else if (actualVersion > expectedVersion)
        {
            logger.error("API Version Mismatch :" + "actual Version is : "
                    + actualVersion + "expected version is : "
                    + expectedVersion);
            throw new SDKApiVersionMismatchException(
                    SDKErrorEnum.apiMismatchError, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        else if (actualVersion < expectedVersion)
        {
            logger.error("API Version Mismatch :" + "actual Version is : "
                    + actualVersion + "expected version is : "
                    + expectedVersion);
            throw new SDKApiVersionMismatchException(
                    SDKErrorEnum.apiMismatchError, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        logger.info("########### Checking API Version End ####################");
    }

    public String getConnectionTemplateUri(final RestParams params,
            final String connectionTemplateName)
    {
        return connectionTemplateClient.getConnectionTemplateByName(params,
                connectionTemplateName).getUri();
    }

    public List<String> getNetworkUris(final RestParams params,
            final List<String> networkNames)
    {
        final List<String> networkUris = new ArrayList<String>();
        String networkUri = null;
        for (int i = 0; i < networkNames.size(); i++)
        {
            final Network dto = networkClient.getNetworkByName(params,
                    networkNames.get(i));
            if (dto.getUri() != null)
            {
                networkUri = dto.getUri();
                networkUris.add(networkUri);
            }
        }
        return networkUris;
    }

    public String getNetworkUri(final RestParams params, final String networkName)
    {
        return networkClient.getNetworkByName(params, networkName).getUri();
    }

    public String getFcNetworkUri(final RestParams params, final String networkName)
    {
        return fcNetworkClient.getFcNetworkByName(params, networkName).getUri();
    }

    public List<String> getFcNetworkUris(final RestParams params,
            final List<String> networkNames)
    {
        final List<String> networkUris = new ArrayList<String>();
        FcNetwork dto = null;
        String fcNetowrkUri = null;
        for (int i = 0; i < networkNames.size(); i++)
        {
            dto = fcNetworkClient.getFcNetworkByName(params,
                    networkNames.get(i));
            if (null != dto.getUri())
            {
                fcNetowrkUri = dto.getUri();
                networkUris.add(fcNetowrkUri);
            }
        }
        return networkUris;
    }

    public String getPermittedInterconnectTypeUri(final RestParams params,
            final String permittedInterconnectType)
    {
        return interconnectTypeClient.getInterconnectTypeByName(params,
                permittedInterconnectType).getUri();
    }

    public String getPermittedInterconnectTypeUriForLigBasedOnBay(
            final RestParams params, final String ligName, final Integer bay)
    {
        logicalInterconnectGroupsDto = logicalInterconnectGroupClient
                .getLogicalInterconnectGroupByName(params, ligName);
        if (logicalInterconnectGroupsDto == null)
        {
            return null;
        }

        if (logicalInterconnectGroupsDto.getInterconnectMapTemplate() == null)
        {
            return null;
        }

        for (final InterconnectMapEntryTemplate mapTemplate : logicalInterconnectGroupsDto
                .getInterconnectMapTemplate()
                .getInterconnectMapEntryTemplates())
        {
            for (final LocationEntry locationEntry : mapTemplate.getLogicalLocation()
                    .getLocationEntries())
            {
                if (locationEntry.getType().equals(LocationEntry.Type.Bay)
                        && locationEntry.getRelativeValue() == bay)
                {
                    return mapTemplate.getPermittedInterconnectTypeUri();
                }
            }
        }

        return null;
    }

    public String getServerHardwareUri(final RestParams params,
            final String serverHardwareName)
    {
        return serverHardwareClient.getServerHardwareByName(params,
                serverHardwareName).getUri();
    }

    public String getServerHardwareTypeUri(final RestParams params,
            final String serverHardwareName)
    {
        return serverHardwareClient.getServerHardwareByName(params,
                serverHardwareName).getServerHardwareTypeUri();
    }

    public String getEnclosureGroupUri(final RestParams params,
            final String enclosureGroupName)
    {
        return enclosureGroupClient.getEnclosureGroupByName(params,
                enclosureGroupName).getUri();
    }

    public Boolean isVolumeSharable(final RestParams params,
            final String volumeName)
    {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName)
                .getShareable();
    }

    public String getVolumeUri(final RestParams params, final String volumeName)
    {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName)
                .getUri();
    }

    public String getStoragePoolFromVolume(final RestParams params,
            final String volumeName)
    {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName)
                .getStoragePoolUri();
    }

    public String getStorageSystemFromVolume(final RestParams params,
            final String volumeName)
    {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName)
                .getStorageSystemUri();
    }
}
