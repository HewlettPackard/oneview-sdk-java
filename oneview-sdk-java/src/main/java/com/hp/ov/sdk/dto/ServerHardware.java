/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import org.codehaus.jackson.annotate.JsonTypeName;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.constants.SdkConstants;

@JsonTypeName(ResourceCategory.RC_SERVERHARDWARE)
public class ServerHardware extends BaseModelResource
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String assetTag;
    private String formFactor;
    private LicensingIntent licensingIntent;
    private String locationUri;
    private String memoryMb;
    private String model;
    private String mpDnsName;
    private String mpFirmwareVersion;
    private String mpIpAddress;
    private String mpModel;
    private String partNumber;
    private PortMap portMap = new PortMap();
    private Integer position;
    private Boolean powerLock;
    private String powerState;
    private Integer processorCoreCount;
    private Integer processorCount;
    private Integer processorSpeedMhz;
    private String processorType;
    private String refreshState;
    private String romVersion;
    private String serialNumber;
    private String serverGroupUri;
    private String serverHardwareTypeUri;
    private String serverProfileUri;
    private String shortModel;
    private Signature signature;
    private String stateReason;
    private String uuid;
    private String virtualSerialNumber;
    private String virtualUuid;

    private JsonRequest jsonRequest;

    public ServerHardware()
    {
        super();
        setCategory(SdkConstants.SERVER_HARDWARE);
    }

    public JsonRequest getJsonRequest()
    {
        return jsonRequest;
    }

    public void setJsonRequest(final JsonRequest jsonRequest)
    {
        this.jsonRequest = jsonRequest;
    }

    /**
     * @return the licensingIntent
     */
    public LicensingIntent getLicensingIntent()
    {
        return licensingIntent;
    }

    /**
     * @param licensingIntent the licensingIntent to set
     */
    public void setLicensingIntent(final LicensingIntent licensingIntent)
    {
        this.licensingIntent = licensingIntent;
    }

    /**
     * @return the locationUri
     */
    public String getLocationUri()
    {
        return locationUri;
    }

    /**
     * @param locationUri the locationUri to set
     */
    public void setLocationUri(final String locationUri)
    {
        this.locationUri = locationUri;
    }

    /**
     * @return the model
     */
    public String getModel()
    {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(final String model)
    {
        this.model = model;
    }

    /**
     * @return the mpFirmwareVersion
     */
    public String getMpFirmwareVersion()
    {
        return mpFirmwareVersion;
    }

    /**
     * @param mpFirmwareVersion the mpFirmwareVersion to set
     */
    public void setMpFirmwareVersion(final String mpFirmwareVersion)
    {
        this.mpFirmwareVersion = mpFirmwareVersion;
    }

    /**
     * @return the mpModel
     */
    public String getMpModel()
    {
        return mpModel;
    }

    /**
     * @param mpModel the mpModel to set
     */
    public void setMpModel(final String mpModel)
    {
        this.mpModel = mpModel;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber()
    {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(final String partNumber)
    {
        this.partNumber = partNumber;
    }

    /**
     * @return the portMap
     */
    public PortMap getPortMap()
    {
        return portMap;
    }

    /**
     * @param portMap the portMap to set
     */
    public void setPortMap(final PortMap portMap)
    {
        this.portMap = portMap;
    }

    /**
     * @return the position
     */
    public Integer getPosition()
    {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(final Integer position)
    {
        this.position = position;
    }

    /**
     * @return the powerLock
     */
    public Boolean getPowerLock()
    {
        return powerLock;
    }

    /**
     * @param powerLock the powerLock to set
     */
    public void setPowerLock(final Boolean powerLock)
    {
        this.powerLock = powerLock;
    }

    /**
     * @return the powerState
     */
    public String getPowerState()
    {
        return powerState;
    }

    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(final String powerState)
    {
        this.powerState = powerState;
    }

    /**
     * @return the serverGroupUri
     */
    public String getServerGroupUri()
    {
        return serverGroupUri;
    }

    /**
     * @param serverGroupUri the serverGroupUri to set
     */
    public void setServerGroupUri(final String serverGroupUri)
    {
        this.serverGroupUri = serverGroupUri;
    }

    /**
     * @return the serverHardwareTypeUri
     */
    public String getServerHardwareTypeUri()
    {
        return serverHardwareTypeUri;
    }

    /**
     * @param serverHardwareTypeUri the serverHardwareTypeUri to set
     */
    public void setServerHardwareTypeUri(final String serverHardwareTypeUri)
    {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     * @return the serverProfileUri
     */
    public String getServerProfileUri()
    {
        return serverProfileUri;
    }

    /**
     * @param serverProfileUri the serverProfileUri to set
     */
    public void setServerProfileUri(final String serverProfileUri)
    {
        this.serverProfileUri = serverProfileUri;
    }

    /**
     * @return the assetTag
     */
    public String getAssetTag()
    {
        return assetTag;
    }

    /**
     * @param assetTag the assetTag to set
     */
    public void setAssetTag(final String assetTag)
    {
        this.assetTag = assetTag;
    }

    /**
     * @return the formFactor
     */
    public String getFormFactor()
    {
        return formFactor;
    }

    /**
     * @param formFactor the formFactor to set
     */
    public void setFormFactor(final String formFactor)
    {
        this.formFactor = formFactor;
    }

    /**
     * @return the memoryMb
     */
    public String getMemoryMb()
    {
        return memoryMb;
    }

    /**
     * @param memoryMb the memoryMb to set
     */
    public void setMemoryMb(final String memoryMb)
    {
        this.memoryMb = memoryMb;
    }

    /**
     * @return the mpDnsName
     */
    public String getMpDnsName()
    {
        return mpDnsName;
    }

    /**
     * @param mpDnsName the mpDnsName to set
     */
    public void setMpDnsName(final String mpDnsName)
    {
        this.mpDnsName = mpDnsName;
    }

    /**
     * @return the mpIpAddress
     */
    public String getMpIpAddress()
    {
        return mpIpAddress;
    }

    /**
     * @param mpIpAddress the mpIpAddress to set
     */
    public void setMpIpAddress(final String mpIpAddress)
    {
        this.mpIpAddress = mpIpAddress;
    }

    /**
     * @return the signature
     */
    public Signature getSignature()
    {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(final Signature signature)
    {
        this.signature = signature;
    }

    /**
     * @return the processorCoreCount
     */
    public Integer getProcessorCoreCount()
    {
        return processorCoreCount;
    }

    /**
     * @param processorCoreCount the processorCoreCount to set
     */
    public void setProcessorCoreCount(final Integer processorCoreCount)
    {
        this.processorCoreCount = processorCoreCount;
    }

    /**
     * @return the processorCount
     */
    public Integer getProcessorCount()
    {
        return processorCount;
    }

    /**
     * @param processorCount the processorCount to set
     */
    public void setProcessorCount(final Integer processorCount)
    {
        this.processorCount = processorCount;
    }

    /**
     * @return the processorSpeedMhz
     */
    public Integer getProcessorSpeedMhz()
    {
        return processorSpeedMhz;
    }

    /**
     * @param processorSpeedMhz the processorSpeedMhz to set
     */
    public void setProcessorSpeedMhz(final Integer processorSpeedMhz)
    {
        this.processorSpeedMhz = processorSpeedMhz;
    }

    /**
     * @return the processorType
     */
    public String getProcessorType()
    {
        return processorType;
    }

    /**
     * @param processorType the processorType to set
     */
    public void setProcessorType(final String processorType)
    {
        this.processorType = processorType;
    }

    /**
     * @return the refreshState
     */
    public String getRefreshState()
    {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(final String refreshState)
    {
        this.refreshState = refreshState;
    }

    /**
     * @return the romVersion
     */
    public String getRomVersion()
    {
        return romVersion;
    }

    /**
     * @param romVersion the romVersion to set
     */
    public void setRomVersion(final String romVersion)
    {
        this.romVersion = romVersion;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber()
    {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(final String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the shortModel
     */
    public String getShortModel()
    {
        return shortModel;
    }

    /**
     * @param shortModel the shortModel to set
     */
    public void setShortModel(final String shortModel)
    {
        this.shortModel = shortModel;
    }

    /**
     * @return the stateReason
     */
    public String getStateReason()
    {
        return stateReason;
    }

    /**
     * @param stateReason the stateReason to set
     */
    public void setStateReason(final String stateReason)
    {
        this.stateReason = stateReason;
    }

    /**
     * @return the uuid
     */
    public String getUuid()
    {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(final String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return the virtualSerialNumber
     */
    public String getVirtualSerialNumber()
    {
        return virtualSerialNumber;
    }

    /**
     * @param virtualSerialNumber the virtualSerialNumber to set
     */
    public void setVirtualSerialNumber(final String virtualSerialNumber)
    {
        this.virtualSerialNumber = virtualSerialNumber;
    }

    /**
     * @return the virtualUuid
     */
    public String getVirtualUuid()
    {
        return virtualUuid;
    }

    /**
     * @param virtualUuid the virtualUuid to set
     */
    public void setVirtualUuid(final String virtualUuid)
    {
        this.virtualUuid = virtualUuid;
    }

}
