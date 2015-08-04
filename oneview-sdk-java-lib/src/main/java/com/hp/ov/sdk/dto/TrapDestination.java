/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrapDestination implements Serializable {

    private static final long serialVersionUID = 1L;

    private String communityString;
    private List<EnetTrapCategory> enetTrapCategories = new ArrayList<EnetTrapCategory>();
    private List<FcTrapCategory> fcTrapCategories = new ArrayList<FcTrapCategory>();
    private String trapDestination;
    private SnmpVersion trapFormat;
    private List<TrapSeverity> trapSeverities = new ArrayList<TrapSeverity>();
    private List<VcmTrapCategory> vcmTrapCategories = new ArrayList<VcmTrapCategory>();

    /**
     * @return the communityString
     */
    public String getCommunityString() {
        return communityString;
    }

    /**
     * @param communityString
     *            the communityString to set
     */
    public void setCommunityString(final String communityString) {
        this.communityString = communityString;
    }

    /**
     * @return the enetTrapCategories
     */
    public List<EnetTrapCategory> getEnetTrapCategories() {
        return enetTrapCategories;
    }

    /**
     * @param enetTrapCategories
     *            the enetTrapCategories to set
     */
    public void setEnetTrapCategories(final List<EnetTrapCategory> enetTrapCategories) {
        this.enetTrapCategories = enetTrapCategories;
    }

    /**
     * @return the fcTrapCategories
     */
    public List<FcTrapCategory> getFcTrapCategories() {
        return fcTrapCategories;
    }

    /**
     * @param fcTrapCategories
     *            the fcTrapCategories to set
     */
    public void setFcTrapCategories(final List<FcTrapCategory> fcTrapCategories) {
        this.fcTrapCategories = fcTrapCategories;
    }

    /**
     * @return the trapDestination
     */
    public String getTrapDestination() {
        return trapDestination;
    }

    /**
     * @param trapDestination
     *            the trapDestination to set
     */
    public void setTrapDestination(final String trapDestination) {
        this.trapDestination = trapDestination;
    }

    /**
     * @return the trapFormat
     */
    public SnmpVersion getTrapFormat() {
        return trapFormat;
    }

    /**
     * @param trapFormat
     *            the trapFormat to set
     */
    public void setTrapFormat(final SnmpVersion trapFormat) {
        this.trapFormat = trapFormat;
    }

    /**
     * @return the trapSeverities
     */
    public List<TrapSeverity> getTrapSeverities() {
        return trapSeverities;
    }

    /**
     * @param trapSeverities
     *            the trapSeverities to set
     */
    public void setTrapSeverities(final List<TrapSeverity> trapSeverities) {
        this.trapSeverities = trapSeverities;
    }

    /**
     * @return the vcmTrapCategories
     */
    public List<VcmTrapCategory> getVcmTrapCategories() {
        return vcmTrapCategories;
    }

    /**
     * @param vcmTrapCategories
     *            the vcmTrapCategories to set
     */
    public void setVcmTrapCategories(final List<VcmTrapCategory> vcmTrapCategories) {
        this.vcmTrapCategories = vcmTrapCategories;
    }

}
