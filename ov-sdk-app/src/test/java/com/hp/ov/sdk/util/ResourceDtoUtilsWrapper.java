/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.LocalStorage;
import com.hp.ov.sdk.dto.generated.SanStorage;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.dto.generated.VolumeAttachment;
import com.hp.ov.sdk.rest.dto.NetworkForServerProfile;
import com.hp.ov.sdk.rest.dto.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.rest.dto.ServerProfileValue;
import com.hp.ov.sdk.rest.dto.UplinkSetValue;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

@Component
public class ResourceDtoUtilsWrapper
{

    @Autowired
    private ResourceDtoUtils resourceDtoUtils;

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        resourceDtoUtils = (ResourceDtoUtils) ctx.getBean("resourceDtoUtils");
    }

    public List<UplinkSet> buildUplinkSetGroupDto(final RestParams params,
            List<UplinkSetValue> uplinkSetValues)
    {
        List<UplinkSet> uplinkSetGroupDto = new ArrayList<UplinkSet>();

        for (UplinkSetValue uplinkSetValue : uplinkSetValues)
        {
            uplinkSetGroupDto.add(resourceDtoUtils.buildUplinkSetDto(params,
                    uplinkSetValue.getLigName(),
                    uplinkSetValue.getUplinkSetName(),
                    uplinkSetValue.getUplinkSetType(),
                    uplinkSetValue.getBayPortMap(),
                    uplinkSetValue.getNetworkNames()));
        }

        return uplinkSetGroupDto;
    }

    public ServerProfile buildServerProfile(final RestParams params, ServerProfileValue serverProfileValue)
    {
        Integer j = 1;
        HashMap<String, Integer> fcId = new HashMap<String, Integer>();
        List<ProfileConnectionV3> connections = new ArrayList<ProfileConnectionV3>();
        SanStorage sanStorage;

        if (serverProfileValue.getNetworkForServerProfile().size() > 0)
        {
            for (NetworkForServerProfile networkForServerProfile : serverProfileValue.getNetworkForServerProfile())
            {
                connections.add(resourceDtoUtils.buildProfileConnection(
                        params,
                        j,
                        networkForServerProfile.getNetworkName(),
                        networkForServerProfile.getRequestedMbps(),
                        networkForServerProfile.getAllocatedMbps(),
                        networkForServerProfile.getMaximumMbps(),
                        networkForServerProfile.getNetworkType(),
                        networkForServerProfile.getBoot()));
                if ((networkForServerProfile.getNetworkType().toString()).equalsIgnoreCase("FibreChannel"))
                {
                    fcId.put(networkForServerProfile.getNetworkName(), j);
                }
                j++;
            }
        }
        else
        {
            connections = null;
        }
        if (serverProfileValue.getStorageVolumeForServerProfile().getStorageVolume().size() > 0)
        {
            List<VolumeAttachment> volumeAttachments = new ArrayList<VolumeAttachment>();
            for (StorageVolume storageVolume : serverProfileValue.getStorageVolumeForServerProfile().getStorageVolume())
            {
                volumeAttachments.add(resourceDtoUtils.buildVolumeAttachment(
                        params,
                        storageVolume.getVolumeName(),
                        serverProfileValue.getUseBayNameForServerHardwareUri(),
                        j,
                        storageVolume.getIsEnabled(),
                        storageVolume.getStorageTargets(),
                        storageVolume.getStorageTargetType(),
                        storageVolume.getLunType(),
                        fcId));
                j++;
            }
            sanStorage = resourceDtoUtils.buildSanStorage(params, serverProfileValue.getStorageVolumeForServerProfile()
                    .getHostOSType(), volumeAttachments);
        }
        else
        {
            sanStorage = null;
        }

        return resourceDtoUtils.buildServerProfile(
                params,
                serverProfileValue.getTemplateName(),
                serverProfileValue.getBayName(),
                serverProfileValue.getUseBayNameForServerHardwareUri(),
                serverProfileValue.getEnclosureGroupName(),
                serverProfileValue.getAffinity(),
                serverProfileValue.getWwnType(),
                serverProfileValue.getMacType(),
                serverProfileValue.getSerialNumberType(),
                sanStorage,
                connections,
                serverProfileValue.getLocalStorage(),
                serverProfileValue.getBoot(),
                serverProfileValue.getBios(),
                serverProfileValue.getFirmware());
    }
}
