/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.facilities;

import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.DeratingType;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.facilities.DataCenterClient;

public class DataCenterResource extends BasicResource implements CreateResource, UpdateResource, RemoveResource {

    private static DataCenterResource instance;

    private DataCenterClient client;

    public DataCenterResource() {
        client = oneViewClient.dataCenter();
    }

    public static DataCenterResource getInstance() {
        if (instance == null) {
            instance = new DataCenterResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        DataCenter dataCenter = (DataCenter) getResource(client.getByName(name));
        return dataCenter == null ? "" : dataCenter.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        client.add(builder());
    }

    @Override
    public DataCenter builder() {
        DataCenter dataCenter = new DataCenter();
        dataCenter.setName(resourceProperties.get("name"));
        dataCenter.setCoolingCapacity(Integer.valueOf(resourceProperties.get("coolingCapacity")));
        dataCenter.setCostPerKilowattHour(Double.valueOf(resourceProperties.get("costPerKilowattHour")));
        dataCenter.setCurrency(resourceProperties.get("currency"));
        dataCenter.setDeratingType(DeratingType.valueOf(resourceProperties.get("deratingType")));
        dataCenter.setDeratingPercentage(Double.valueOf(resourceProperties.get("deratingPercentage")));
        dataCenter.setDefaultPowerLineVoltage(Integer.valueOf(resourceProperties.get("defaultPowerLineVoltage")));
        dataCenter.setCoolingMultiplier(Double.valueOf(resourceProperties.get("coolingMultiplier")));
        dataCenter.setWidth(Integer.valueOf(resourceProperties.get("width")));
        dataCenter.setDepth(Integer.valueOf(resourceProperties.get("depth")));

        return dataCenter;
    }

    public String getDataCenterVisualContent(String id) {
        List<VisualContent> visualContent = client.getVisualContent(id);
        return objToString(visualContent);
    }

    @Override
    public String update(String id) {
        DataCenter dataCenter = client.getById(id);
        dataCenter.setCurrency(resourceProperties.get("currency"));
        return objToString(client.update(id, dataCenter));
    }

    @Override
    public String remove(String id) {
        return client.remove(id);
    }

    public String removeByFilter() {
        String filter = "'name' = '" + resourceProperties.get("name") + "'";
        return taskToString(client.removeByFilter(filter));
    }

}
