/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class DeviceBay extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Boolean availableForFullHeightProfile;
    private Boolean availableForHalfHeightProfile;
    private Integer bayNumber;
    private String coveredByDevice;
    private String coveredByProfile;
    private String devicePresence;
    private String deviceUri;
    private String enclosureUri;
    private String model;
    private String profileUri;

    /**
     * @return the availableForFullHeightProfile
     */
    public Boolean getAvailableForFullHeightProfile()
    {
        return availableForFullHeightProfile;
    }

    /**
     * @param availableForFullHeightProfile the availableForFullHeightProfile to
     *        set
     */
    public void setAvailableForFullHeightProfile(
            final Boolean availableForFullHeightProfile)
    {
        this.availableForFullHeightProfile = availableForFullHeightProfile;
    }

    /**
     * @return the availableForHalfHeightProfile
     */
    public Boolean getAvailableForHalfHeightProfile()
    {
        return availableForHalfHeightProfile;
    }

    /**
     * @param availableForHalfHeightProfile the availableForHalfHeightProfile to
     *        set
     */
    public void setAvailableForHalfHeightProfile(
            final Boolean availableForHalfHeightProfile)
    {
        this.availableForHalfHeightProfile = availableForHalfHeightProfile;
    }

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber()
    {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(final Integer bayNumber)
    {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the coveredByDevice
     */
    public String getCoveredByDevice()
    {
        return coveredByDevice;
    }

    /**
     * @param coveredByDevice the coveredByDevice to set
     */
    public void setCoveredByDevice(final String coveredByDevice)
    {
        this.coveredByDevice = coveredByDevice;
    }

    /**
     * @return the coveredByProfile
     */
    public String getCoveredByProfile()
    {
        return coveredByProfile;
    }

    /**
     * @param coveredByProfile the coveredByProfile to set
     */
    public void setCoveredByProfile(final String coveredByProfile)
    {
        this.coveredByProfile = coveredByProfile;
    }

    /**
     * @return the devicePresence
     */
    public String getDevicePresence()
    {
        return devicePresence;
    }

    /**
     * @param devicePresence the devicePresence to set
     */
    public void setDevicePresence(final String devicePresence)
    {
        this.devicePresence = devicePresence;
    }

    /**
     * @return the deviceUri
     */
    public String getDeviceUri()
    {
        return deviceUri;
    }

    /**
     * @param deviceUri the deviceUri to set
     */
    public void setDeviceUri(final String deviceUri)
    {
        this.deviceUri = deviceUri;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri()
    {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(final String enclosureUri)
    {
        this.enclosureUri = enclosureUri;
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
     * @return the profileUri
     */
    public String getProfileUri()
    {
        return profileUri;
    }

    /**
     * @param profileUri the profileUri to set
     */
    public void setProfileUri(final String profileUri)
    {
        this.profileUri = profileUri;
    }

}
