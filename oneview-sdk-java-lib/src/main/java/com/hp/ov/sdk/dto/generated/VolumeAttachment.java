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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "storagePaths", "permanent", "state", "lun", "volumeUri", "lunType", "volumeStorageSystemUri", "volumeName",
        "volumeProvisionedCapacityBytes", "volumeProvisionType", "volumeStoragePoolUri", "volumeShareable", "volumeDescription",
        "status", "id" })
public class VolumeAttachment implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("storagePaths")
    private List<StoragePath> storagePaths = new ArrayList<StoragePath>();
    @JsonProperty("permanent")
    private Boolean permanent;
    @JsonProperty("state")
    private VolumeAttachment.State state;
    @JsonProperty("lun")
    private String lun;
    @JsonProperty("volumeUri")
    private String volumeUri;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("lunType")
    private String lunType;
    @JsonProperty("volumeStorageSystemUri")
    private String volumeStorageSystemUri;
    @JsonProperty("volumeName")
    private String volumeName;
    @JsonProperty("volumeProvisionedCapacityBytes")
    private String volumeProvisionedCapacityBytes;
    @JsonProperty("volumeProvisionType")
    private String volumeProvisionType;
    @JsonProperty("volumeStoragePoolUri")
    private String volumeStoragePoolUri;
    @JsonProperty("volumeShareable")
    private Boolean volumeShareable;
    @JsonProperty("volumeDescription")
    private String volumeDescription;
    @JsonProperty("status")
    private String status;
    @JsonProperty("id")
    private Double id;

    /**
     * 
     * @return The storagePaths
     */
    @JsonProperty("storagePaths")
    public List<StoragePath> getStoragePaths() {
        return storagePaths;
    }

    /**
     * 
     * @param storagePaths
     *            The storagePaths
     */
    @JsonProperty("storagePaths")
    public void setStoragePaths(final List<StoragePath> storagePaths) {
        this.storagePaths = storagePaths;
    }

    /**
     * 
     * @return The permanent
     */
    @JsonProperty("permanent")
    public Boolean getPermanent() {
        return permanent;
    }

    /**
     * 
     * @param permanent
     *            The permanent
     */
    @JsonProperty("permanent")
    public void setPermanent(final Boolean permanent) {
        this.permanent = permanent;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public VolumeAttachment.State getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            The state
     */
    @JsonProperty("state")
    public void setState(final VolumeAttachment.State state) {
        this.state = state;
    }

    /**
     * 
     * @return The lun
     */
    @JsonProperty("lun")
    public String getLun() {
        return lun;
    }

    /**
     * 
     * @param lun
     *            The lun
     */
    @JsonProperty("lun")
    public void setLun(final String lun) {
        this.lun = lun;
    }

    /**
     * 
     * @return The volumeUri
     */
    @JsonProperty("volumeUri")
    public String getVolumeUri() {
        return volumeUri;
    }

    /**
     * 
     * @param volumeUri
     *            The volumeUri
     */
    @JsonProperty("volumeUri")
    public void setVolumeUri(final String volumeUri) {
        this.volumeUri = volumeUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The lunType
     */
    @JsonProperty("lunType")
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
    @JsonProperty("lunType")
    public void setLunType(final String lunType) {
        this.lunType = lunType;
    }

    /**
     * 
     * @return The volumeStorageSystemUri
     */
    @JsonProperty("volumeStorageSystemUri")
    public String getVolumeStorageSystemUri() {
        return volumeStorageSystemUri;
    }

    /**
     * 
     * @param volumeStorageSystemUri
     *            The volumeStorageSystemUri
     */
    @JsonProperty("volumeStorageSystemUri")
    public void setVolumeStorageSystemUri(final String volumeStorageSystemUri) {
        this.volumeStorageSystemUri = volumeStorageSystemUri;
    }

    /**
     * 
     * @return The volumeName
     */
    @JsonProperty("volumeName")
    public String getVolumeName() {
        return volumeName;
    }

    /**
     * 
     * @param volumeName
     *            The volumeName
     */
    @JsonProperty("volumeName")
    public void setVolumeName(final String volumeName) {
        this.volumeName = volumeName;
    }

    /**
     * 
     * @return The volumeProvisionedCapacityBytes
     */
    @JsonProperty("volumeProvisionedCapacityBytes")
    public String getVolumeProvisionedCapacityBytes() {
        return volumeProvisionedCapacityBytes;
    }

    /**
     * 
     * @param volumeProvisionedCapacityBytes
     *            The volumeProvisionedCapacityBytes
     */
    @JsonProperty("volumeProvisionedCapacityBytes")
    public void setVolumeProvisionedCapacityBytes(final String volumeProvisionedCapacityBytes) {
        this.volumeProvisionedCapacityBytes = volumeProvisionedCapacityBytes;
    }

    /**
     * 
     * @return The volumeProvisionType
     */
    @JsonProperty("volumeProvisionType")
    public String getVolumeProvisionType() {
        return volumeProvisionType;
    }

    /**
     * 
     * @param volumeProvisionType
     *            The volumeProvisionType
     */
    @JsonProperty("volumeProvisionType")
    public void setVolumeProvisionType(final String volumeProvisionType) {
        this.volumeProvisionType = volumeProvisionType;
    }

    /**
     * 
     * @return The volumeStoragePoolUri
     */
    @JsonProperty("volumeStoragePoolUri")
    public String getVolumeStoragePoolUri() {
        return volumeStoragePoolUri;
    }

    /**
     * 
     * @param volumeStoragePoolUri
     *            The volumeStoragePoolUri
     */
    @JsonProperty("volumeStoragePoolUri")
    public void setVolumeStoragePoolUri(final String volumeStoragePoolUri) {
        this.volumeStoragePoolUri = volumeStoragePoolUri;
    }

    /**
     * 
     * @return The volumeShareable
     */
    @JsonProperty("volumeShareable")
    public Boolean getVolumeShareable() {
        return volumeShareable;
    }

    /**
     * 
     * @param volumeShareable
     *            The volumeShareable
     */
    @JsonProperty("volumeShareable")
    public void setVolumeShareable(final Boolean volumeShareable) {
        this.volumeShareable = volumeShareable;
    }

    /**
     * 
     * @return The volumeDescription
     */
    @JsonProperty("volumeDescription")
    public String getVolumeDescription() {
        return volumeDescription;
    }

    /**
     * 
     * @param volumeDescription
     *            The volumeDescription
     */
    @JsonProperty("volumeDescription")
    public void setVolumeDescription(final String volumeDescription) {
        this.volumeDescription = volumeDescription;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * @return The id
     */
    @JsonProperty("id")
    public Double getId() {
        return id;
    }

    /**
     * 
     * @param id
     *            The id
     */
    @JsonProperty("id")
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

    @Generated("org.jsonschema2pojo")
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

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
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
