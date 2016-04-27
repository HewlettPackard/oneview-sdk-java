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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class VolumeAttachment implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private List<StoragePath> storagePaths = new ArrayList<StoragePath>();
    private Boolean permanent;
    private VolumeAttachment.State state;
    private String lun;
    private String volumeUri;
    /**
     *
     * (Required)
     *
     */
    private String lunType;
    private String volumeStorageSystemUri;
    private String volumeName;
    private String volumeProvisionedCapacityBytes;
    private String volumeProvisionType;
    private String volumeStoragePoolUri;
    private Boolean volumeShareable;
    private String volumeDescription;
    private String status;
    private Double id;

    /**
     *
     * @return The storagePaths
     */
    public List<StoragePath> getStoragePaths() {
        return storagePaths;
    }

    /**
     *
     * @param storagePaths
     *            The storagePaths
     */
    public void setStoragePaths(final List<StoragePath> storagePaths) {
        this.storagePaths = storagePaths;
    }

    /**
     *
     * @return The permanent
     */
    public Boolean getPermanent() {
        return permanent;
    }

    /**
     *
     * @param permanent
     *            The permanent
     */
    public void setPermanent(final Boolean permanent) {
        this.permanent = permanent;
    }

    /**
     *
     * @return The state
     */
    public VolumeAttachment.State getState() {
        return state;
    }

    /**
     *
     * @param state
     *            The state
     */
    public void setState(final VolumeAttachment.State state) {
        this.state = state;
    }

    /**
     *
     * @return The lun
     */
    public String getLun() {
        return lun;
    }

    /**
     *
     * @param lun
     *            The lun
     */
    public void setLun(final String lun) {
        this.lun = lun;
    }

    /**
     *
     * @return The volumeUri
     */
    public String getVolumeUri() {
        return volumeUri;
    }

    /**
     *
     * @param volumeUri
     *            The volumeUri
     */
    public void setVolumeUri(final String volumeUri) {
        this.volumeUri = volumeUri;
    }

    /**
     *
     * (Required)
     *
     * @return The lunType
     */
    public String getLunType() {
        return lunType;
    }

    /**
     *
     * (Required)
     *
     * @param lunType
     *            The lunType
     */
    public void setLunType(final String lunType) {
        this.lunType = lunType;
    }

    /**
     *
     * @return The volumeStorageSystemUri
     */
    public String getVolumeStorageSystemUri() {
        return volumeStorageSystemUri;
    }

    /**
     *
     * @param volumeStorageSystemUri
     *            The volumeStorageSystemUri
     */
    public void setVolumeStorageSystemUri(final String volumeStorageSystemUri) {
        this.volumeStorageSystemUri = volumeStorageSystemUri;
    }

    /**
     *
     * @return The volumeName
     */
    public String getVolumeName() {
        return volumeName;
    }

    /**
     *
     * @param volumeName
     *            The volumeName
     */
    public void setVolumeName(final String volumeName) {
        this.volumeName = volumeName;
    }

    /**
     *
     * @return The volumeProvisionedCapacityBytes
     */
    public String getVolumeProvisionedCapacityBytes() {
        return volumeProvisionedCapacityBytes;
    }

    /**
     *
     * @param volumeProvisionedCapacityBytes
     *            The volumeProvisionedCapacityBytes
     */
    public void setVolumeProvisionedCapacityBytes(final String volumeProvisionedCapacityBytes) {
        this.volumeProvisionedCapacityBytes = volumeProvisionedCapacityBytes;
    }

    /**
     *
     * @return The volumeProvisionType
     */
    public String getVolumeProvisionType() {
        return volumeProvisionType;
    }

    /**
     *
     * @param volumeProvisionType
     *            The volumeProvisionType
     */
    public void setVolumeProvisionType(final String volumeProvisionType) {
        this.volumeProvisionType = volumeProvisionType;
    }

    /**
     *
     * @return The volumeStoragePoolUri
     */
    public String getVolumeStoragePoolUri() {
        return volumeStoragePoolUri;
    }

    /**
     *
     * @param volumeStoragePoolUri
     *            The volumeStoragePoolUri
     */
    public void setVolumeStoragePoolUri(final String volumeStoragePoolUri) {
        this.volumeStoragePoolUri = volumeStoragePoolUri;
    }

    /**
     *
     * @return The volumeShareable
     */
    public Boolean getVolumeShareable() {
        return volumeShareable;
    }

    /**
     *
     * @param volumeShareable
     *            The volumeShareable
     */
    public void setVolumeShareable(final Boolean volumeShareable) {
        this.volumeShareable = volumeShareable;
    }

    /**
     *
     * @return The volumeDescription
     */
    public String getVolumeDescription() {
        return volumeDescription;
    }

    /**
     *
     * @param volumeDescription
     *            The volumeDescription
     */
    public void setVolumeDescription(final String volumeDescription) {
        this.volumeDescription = volumeDescription;
    }

    /**
     *
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     *
     * @return The id
     */
    public Double getId() {
        return id;
    }

    /**
     *
     * @param id
     *            The id
     */
    public void setId(final Double id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(storagePaths).append(permanent).append(state).append(lun).append(volumeUri)
                .append(lunType).append(volumeStorageSystemUri).append(volumeName).append(volumeProvisionedCapacityBytes)
                .append(volumeProvisionType).append(volumeStoragePoolUri).append(volumeShareable).append(volumeDescription)
                .append(status).append(id).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VolumeAttachment) == false) {
            return false;
        }
        final VolumeAttachment rhs = ((VolumeAttachment) other);
        return new EqualsBuilder().append(storagePaths, rhs.storagePaths).append(permanent, rhs.permanent).append(state, rhs.state)
                .append(lun, rhs.lun).append(volumeUri, rhs.volumeUri).append(lunType, rhs.lunType)
                .append(volumeStorageSystemUri, rhs.volumeStorageSystemUri).append(volumeName, rhs.volumeName)
                .append(volumeProvisionedCapacityBytes, rhs.volumeProvisionedCapacityBytes)
                .append(volumeProvisionType, rhs.volumeProvisionType).append(volumeStoragePoolUri, rhs.volumeStoragePoolUri)
                .append(volumeShareable, rhs.volumeShareable).append(volumeDescription, rhs.volumeDescription)
                .append(status, rhs.status).append(id, rhs.id).isEquals();
    }

    public static enum State {

        AttachFailed("AttachFailed"), Attached("Attached"), Attaching("Attaching"), Creating("Creating"), DeleteFailed(
                "DeleteFailed"), Deleted("Deleted"), Deleting("Deleting"), ReserveFailed("ReserveFailed"), Reserved("Reserved"), Reserving(
                "Reserving"), Updating("Updating"), UserDeleted("UserDeleted"), VolumeCreateFailed("VolumeCreateFailed"), VolumeCreating(
                "VolumeCreating"), VolumeDeleteFailed("VolumeDeleteFailed"), VolumeDeleting("VolumeDeleting");
        private final String value;
        private static Map<String, VolumeAttachment.State> constants = new HashMap<String, VolumeAttachment.State>();

        static {
            for (final VolumeAttachment.State c : values()) {
                constants.put(c.value, c);
            }
        }

        private State(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static VolumeAttachment.State fromValue(final String value) {
            final VolumeAttachment.State constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
