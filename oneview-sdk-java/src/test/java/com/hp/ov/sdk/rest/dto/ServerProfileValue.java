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

    public void setTemplateName(final String templateName)
    {
        this.templateName = templateName;
    }

    public String getBayName()
    {
        return bayName;
    }

    public void setBayName(final String bayName)
    {
        this.bayName = bayName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public Boolean getUseBayNameForServerHardwareUri()
    {
        return useBayNameForServerHardwareUri;
    }

    public void setUseBayNameForServerHardwareUri(
            final Boolean useBayNameForServerHardwareUri)
    {
        this.useBayNameForServerHardwareUri = useBayNameForServerHardwareUri;
    }

    public String getEnclosureGroupName()
    {
        return enclosureGroupName;
    }

    public void setEnclosureGroupName(final String enclosureGroupName)
    {
        this.enclosureGroupName = enclosureGroupName;
    }

    public ServerProfile.ProfileAffinity getAffinity()
    {
        return affinity;
    }

    public void setAffinity(final ServerProfile.ProfileAffinity affinity)
    {
        this.affinity = affinity;
    }

    public SanStorageForServerProfile getStorageVolumeForServerProfile()
    {
        return sanStorageForServerProfile;
    }

    public void setStorageVolumeForServerProfile(
            final SanStorageForServerProfile sanStorageForServerProfile)
    {
        this.sanStorageForServerProfile = sanStorageForServerProfile;
    }

    public LocalStorage getLocalStorage()
    {
        return localStorage;
    }

    public void setLocalStorage(final LocalStorage localStorage)
    {
        this.localStorage = localStorage;
    }

    public List<NetworkForServerProfile> getNetworkForServerProfile()
    {
        return networkForServerProfile;
    }

    public void setNetworkForServerProfile(
            final List<NetworkForServerProfile> networkForServerProfile)
    {
        this.networkForServerProfile = networkForServerProfile;
    }

    public Boot getBoot()
    {
        return boot;
    }

    public void setBoot(final Boot boot)
    {
        this.boot = boot;
    }

    public Bios getBios()
    {
        return bios;
    }

    public void setBios(final Bios bios)
    {
        this.bios = bios;
    }

    public Firmware getFirmware()
    {
        return firmware;
    }

    public void setFirmware(final Firmware firmware)
    {
        this.firmware = firmware;
    }

    public ServerProfile.AssignmentType getMacType()
    {
        return macType;
    }

    public void setMacType(final ServerProfile.AssignmentType macType)
    {
        this.macType = macType;
    }

    public ServerProfile.AssignmentType getWwnType()
    {
        return wwnType;
    }

    public void setWwnType(final ServerProfile.AssignmentType wwnType)
    {
        this.wwnType = wwnType;
    }

    public ServerProfile.AssignmentType getSerialNumberType()
    {
        return serialNumberType;
    }

    public void setSerialNumberType(final ServerProfile.AssignmentType serialNumberType)
    {
        this.serialNumberType = serialNumberType;
    }

}
