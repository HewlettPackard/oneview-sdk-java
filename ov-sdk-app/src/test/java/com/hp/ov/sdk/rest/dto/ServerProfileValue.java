/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.dto;

import java.util.List;

import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.LocalStorage;
import com.hp.ov.sdk.dto.generated.ServerProfile;

public class ServerProfileValue
{

    private String templateName;
    private String bayName;
    private String description;
    private Boolean useBayNameForServerHardwareUri;
    private String enclosureGroupName;
    private ServerProfile.ProfileAffinity affinity;
    private SanStorageForServerProfile sanStorageForServerProfile;
    private LocalStorage localStorage;
    private List<NetworkForServerProfile> networkForServerProfile;
    private Boot boot;
    private Bios bios;
    private Firmware firmware;
    private ServerProfile.AssignmentType macType;
    private ServerProfile.AssignmentType wwnType;
    private ServerProfile.AssignmentType serialNumberType;

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getBayName()
    {
        return bayName;
    }

    public void setBayName(String bayName)
    {
        this.bayName = bayName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getUseBayNameForServerHardwareUri()
    {
        return useBayNameForServerHardwareUri;
    }

    public void setUseBayNameForServerHardwareUri(
            Boolean useBayNameForServerHardwareUri)
    {
        this.useBayNameForServerHardwareUri = useBayNameForServerHardwareUri;
    }

    public String getEnclosureGroupName()
    {
        return enclosureGroupName;
    }

    public void setEnclosureGroupName(String enclosureGroupName)
    {
        this.enclosureGroupName = enclosureGroupName;
    }

    public ServerProfile.ProfileAffinity getAffinity()
    {
        return affinity;
    }

    public void setAffinity(ServerProfile.ProfileAffinity affinity)
    {
        this.affinity = affinity;
    }

    public SanStorageForServerProfile getStorageVolumeForServerProfile()
    {
        return sanStorageForServerProfile;
    }

    public void setStorageVolumeForServerProfile(
            SanStorageForServerProfile sanStorageForServerProfile)
    {
        this.sanStorageForServerProfile = sanStorageForServerProfile;
    }

    public LocalStorage getLocalStorage()
    {
        return localStorage;
    }

    public void setLocalStorage(LocalStorage localStorage)
    {
        this.localStorage = localStorage;
    }

    public List<NetworkForServerProfile> getNetworkForServerProfile()
    {
        return networkForServerProfile;
    }

    public void setNetworkForServerProfile(
            List<NetworkForServerProfile> networkForServerProfile)
    {
        this.networkForServerProfile = networkForServerProfile;
    }

    public Boot getBoot()
    {
        return boot;
    }

    public void setBoot(Boot boot)
    {
        this.boot = boot;
    }

    public Bios getBios()
    {
        return bios;
    }

    public void setBios(Bios bios)
    {
        this.bios = bios;
    }

    public Firmware getFirmware()
    {
        return firmware;
    }

    public void setFirmware(Firmware firmware)
    {
        this.firmware = firmware;
    }

    public ServerProfile.AssignmentType getMacType()
    {
        return macType;
    }

    public void setMacType(ServerProfile.AssignmentType macType)
    {
        this.macType = macType;
    }

    public ServerProfile.AssignmentType getWwnType()
    {
        return wwnType;
    }

    public void setWwnType(ServerProfile.AssignmentType wwnType)
    {
        this.wwnType = wwnType;
    }

    public ServerProfile.AssignmentType getSerialNumberType()
    {
        return serialNumberType;
    }

    public void setSerialNumberType(ServerProfile.AssignmentType serialNumberType)
    {
        this.serialNumberType = serialNumberType;
    }

}
