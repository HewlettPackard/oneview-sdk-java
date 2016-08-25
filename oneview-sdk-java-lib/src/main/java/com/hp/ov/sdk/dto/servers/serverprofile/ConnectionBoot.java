/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.servers.BootTarget;
import com.hp.ov.sdk.dto.servers.BootVolumeSource;
import com.hp.ov.sdk.dto.servers.ChapLevel;
import com.hp.ov.sdk.dto.servers.InitiatorNameSource;

public class ConnectionBoot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Since(300)
    private String bootTargetLun;
    @Since(300)
    private String bootTargetName;
    @Since(300)
    private BootVolumeSource bootVolumeSource;
    @Since(300)
    private ChapLevel chapLevel;
    @Since(300)
    private String chapName;
    @Since(300)
    private String chapSecret;
    @Since(300)
    private String firstBootTargetIp;
    @Since(300)
    private String firstBootTargetPort;
    @Since(300)
    private String initiatorGateway;
    @Since(300)
    private String initiatorIp;
    @Since(300)
    private String initiatorName;
    @Since(300)
    private InitiatorNameSource initiatorNameSource;
    @Since(300)
    private String initiatorSubnetMask;
    @Since(300)
    private Integer initiatorVlanId;
    @Since(300)
    private String mutualChapName;
    @Since(300)
    private String mutualChapSecret;
    private BootControl priority;
    @Since(300)
    private String secondBootTargetIp;
    @Since(300)
    private String secondBootTargetPort;
    private List<BootTarget> targets = new ArrayList<>();

    /**
     * @return the bootTargetLun
     */
    public String getBootTargetLun() {
        return bootTargetLun;
    }

    /**
     * @param bootTargetLun the bootTargetLun to set
     */
    public void setBootTargetLun(String bootTargetLun) {
        this.bootTargetLun = bootTargetLun;
    }

    /**
     * @return the bootTargetName
     */
    public String getBootTargetName() {
        return bootTargetName;
    }

    /**
     * @param bootTargetName the bootTargetName to set
     */
    public void setBootTargetName(String bootTargetName) {
        this.bootTargetName = bootTargetName;
    }

    /**
     * @return the bootVolumeSource
     */
    public BootVolumeSource getBootVolumeSource() {
        return bootVolumeSource;
    }

    /**
     * @param bootVolumeSource the bootVolumeSource to set
     */
    public void setBootVolumeSource(BootVolumeSource bootVolumeSource) {
        this.bootVolumeSource = bootVolumeSource;
    }

    /**
     * @return the chapLevel
     */
    public ChapLevel getChapLevel() {
        return chapLevel;
    }

    /**
     * @param chapLevel the chapLevel to set
     */
    public void setChapLevel(ChapLevel chapLevel) {
        this.chapLevel = chapLevel;
    }

    /**
     * @return the chapName
     */
    public String getChapName() {
        return chapName;
    }

    /**
     * @param chapName the chapName to set
     */
    public void setChapName(String chapName) {
        this.chapName = chapName;
    }

    /**
     * @return the chapSecret
     */
    public String getChapSecret() {
        return chapSecret;
    }

    /**
     * @param chapSecret the chapSecret to set
     */
    public void setChapSecret(String chapSecret) {
        this.chapSecret = chapSecret;
    }

    /**
     * @return the firstBootTargetIp
     */
    public String getFirstBootTargetIp() {
        return firstBootTargetIp;
    }

    /**
     * @param firstBootTargetIp the firstBootTargetIp to set
     */
    public void setFirstBootTargetIp(String firstBootTargetIp) {
        this.firstBootTargetIp = firstBootTargetIp;
    }

    /**
     * @return the firstBootTargetPort
     */
    public String getFirstBootTargetPort() {
        return firstBootTargetPort;
    }

    /**
     * @param firstBootTargetPort the firstBootTargetPort to set
     */
    public void setFirstBootTargetPort(String firstBootTargetPort) {
        this.firstBootTargetPort = firstBootTargetPort;
    }

    /**
     * @return the initiatorGateway
     */
    public String getInitiatorGateway() {
        return initiatorGateway;
    }

    /**
     * @param initiatorGateway the initiatorGateway to set
     */
    public void setInitiatorGateway(String initiatorGateway) {
        this.initiatorGateway = initiatorGateway;
    }

    /**
     * @return the initiatorIp
     */
    public String getInitiatorIp() {
        return initiatorIp;
    }

    /**
     * @param initiatorIp the initiatorIp to set
     */
    public void setInitiatorIp(String initiatorIp) {
        this.initiatorIp = initiatorIp;
    }

    /**
     * @return the initiatorName
     */
    public String getInitiatorName() {
        return initiatorName;
    }

    /**
     * @param initiatorName the initiatorName to set
     */
    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    /**
     * @return the initiatorNameSource
     */
    public InitiatorNameSource getInitiatorNameSource() {
        return initiatorNameSource;
    }

    /**
     * @param initiatorNameSource the initiatorNameSource to set
     */
    public void setInitiatorNameSource(InitiatorNameSource initiatorNameSource) {
        this.initiatorNameSource = initiatorNameSource;
    }

    /**
     * @return the initiatorSubnetMask
     */
    public String getInitiatorSubnetMask() {
        return initiatorSubnetMask;
    }

    /**
     * @param initiatorSubnetMask the initiatorSubnetMask to set
     */
    public void setInitiatorSubnetMask(String initiatorSubnetMask) {
        this.initiatorSubnetMask = initiatorSubnetMask;
    }

    /**
     * @return the initiatorVlanId
     */
    public Integer getInitiatorVlanId() {
        return initiatorVlanId;
    }

    /**
     * @param initiatorVlanId the initiatorVlanId to set
     */
    public void setInitiatorVlanId(Integer initiatorVlanId) {
        this.initiatorVlanId = initiatorVlanId;
    }

    /**
     * @return the mutualChapName
     */
    public String getMutualChapName() {
        return mutualChapName;
    }

    /**
     * @param mutualChapName the mutualChapName to set
     */
    public void setMutualChapName(String mutualChapName) {
        this.mutualChapName = mutualChapName;
    }

    /**
     * @return the mutualChapSecret
     */
    public String getMutualChapSecret() {
        return mutualChapSecret;
    }

    /**
     * @param mutualChapSecret the mutualChapSecret to set
     */
    public void setMutualChapSecret(String mutualChapSecret) {
        this.mutualChapSecret = mutualChapSecret;
    }

    /**
     * @return the priority
     */
    public BootControl getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(BootControl priority) {
        this.priority = priority;
    }

    /**
     * @return the secondBootTargetIp
     */
    public String getSecondBootTargetIp() {
        return secondBootTargetIp;
    }

    /**
     * @param secondBootTargetIp the secondBootTargetIp to set
     */
    public void setSecondBootTargetIp(String secondBootTargetIp) {
        this.secondBootTargetIp = secondBootTargetIp;
    }

    /**
     * @return the secondBootTargetPort
     */
    public String getSecondBootTargetPort() {
        return secondBootTargetPort;
    }

    /**
     * @param secondBootTargetPort the secondBootTargetPort to set
     */
    public void setSecondBootTargetPort(String secondBootTargetPort) {
        this.secondBootTargetPort = secondBootTargetPort;
    }

    /**
     * @return the targets
     */
    public List<BootTarget> getTargets() {
        return targets;
    }

    /**
     * @param targets the targets to set
     */
    public void setTargets(List<BootTarget> targets) {
        this.targets = targets;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
