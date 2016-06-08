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

package com.hp.ov.sdk.dto.rack;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class Rack extends BaseModelResource {

    private Integer depth;
    private Integer height;
    private String id;
    private String model;
    private String partNumber;
    private List<RackMount> rackMounts;
    private String serialNumber;
    private Integer thermalLimit;
    private Integer uHeight;
    private String uuid;
    private Integer width;

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public List<RackMount> getRackMounts() {
        return rackMounts;
    }

    public void setRackMounts(List<RackMount> rackMounts) {
        this.rackMounts = rackMounts;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getThermalLimit() {
        return thermalLimit;
    }

    public void setThermalLimit(Integer thermalLimit) {
        this.thermalLimit = thermalLimit;
    }

    public Integer getuHeight() {
        return uHeight;
    }

    public void setuHeight(Integer uHeight) {
        this.uHeight = uHeight;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof Rack);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof  Rack) {
            Rack that = (Rack) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(depth, that.depth)
                    .append(height, that.height)
                    .append(id, that.id)
                    .append(model, that.model)
                    .append(partNumber, that.partNumber)
                    .append(rackMounts, that.rackMounts)
                    .append(serialNumber, that.serialNumber)
                    .append(thermalLimit, that.thermalLimit)
                    .append(uHeight, that.uHeight)
                    .append(uuid, that.uuid)
                    .append(width, that.width)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(depth)
                .append(height)
                .append(id)
                .append(model)
                .append(partNumber)
                .append(rackMounts)
                .append(serialNumber)
                .append(thermalLimit)
                .append(uHeight)
                .append(uuid)
                .append(width)
                .toHashCode();
    }
}
