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

package com.hp.ov.sdk.rest.client.facilities;

import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.DeratingType;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class DataCenterClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String DATA_CENTER_RESOURCE_ID = "9955ffa6-fe4a-45ca-b42d-d25ca4fbae42";
    private static final String DATA_CENTER_NAME = "Sample DataCenter";
    // ================================

    private final DataCenterClient dataCenterClient;

    public DataCenterClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.dataCenterClient = oneViewClient.dataCenter();
    }

    private void getDataCenter() {
        DataCenter dataCenter = this.dataCenterClient.getById(DATA_CENTER_RESOURCE_ID);

        System.out.println("DataCenterClientSample : getDataCenter : " +
                "DataCenter object returned to client : " + dataCenter.toJsonString());
    }

    private void getAllDataCenters() {
        ResourceCollection<DataCenter> dataCenters = this.dataCenterClient.getAll();

        System.out.println("DataCenterClientSample : getAllDataCenters : " +
                "DataCenters returned to client : " + dataCenters.toJsonString());
    }

    private void getDataCenterByName() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);

        System.out.println("DataCenterClientSample : getDataCenterByName : " +
                "DataCenter object returned to client : " + dataCenter.toJsonString());
    }

    private void addDataCenter() {
        DataCenter dataCenter = new DataCenter();

        dataCenter.setName(DATA_CENTER_NAME);
        dataCenter.setCoolingCapacity(Integer.valueOf(5));
        dataCenter.setCostPerKilowattHour(Double.valueOf(0.10));
        dataCenter.setCurrency("USD");
        dataCenter.setDeratingType(DeratingType.NaJp);
        dataCenter.setDeratingPercentage(Double.valueOf(20.0));
        dataCenter.setDefaultPowerLineVoltage(Integer.valueOf(220));
        dataCenter.setCoolingMultiplier(Double.valueOf(1.5));
        dataCenter.setWidth(Integer.valueOf(4000));
        dataCenter.setDepth(Integer.valueOf(5000));

        DataCenter addedDataCenter = this.dataCenterClient.add(dataCenter);

        System.out.println("DataCenterClientSample : addDataCenter : " +
                "DataCenter object returned to client : " + addedDataCenter.toJsonString());
    }

    private void updateDataCenter() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);
        String resourceId = dataCenter.getResourceId();

        dataCenter.setCurrency("BRL");

        DataCenter updatedDataCenter = this.dataCenterClient.update(resourceId, dataCenter);

        System.out.println("DataCenterClientSample : updateDataCenter : " +
                "DataCenter object returned to client : " + updatedDataCenter.toJsonString());
    }

    private void getVisualContent() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);
        List<VisualContent> visualContent = this.dataCenterClient.getVisualContent(dataCenter.getResourceId());

        System.out.println("DataCenterClientSample : getVisualContent : " +
                "VisualContent list returned to client  (count) : " + visualContent.size());
    }

    private void removeDataCenter() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);
        String response = this.dataCenterClient.remove(dataCenter.getResourceId());

        System.out.println("DataCenterClientSample : removeDataCenter : " +
                "Response returned to client : " + response);
    }

    private void removeDataCenterByFilter() {
        String filter = "name='" + DATA_CENTER_NAME +"'";
        TaskResourceV2 task = this.dataCenterClient.removeByFilter(filter, false);

        System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                "Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        DataCenterClientSample sample = new DataCenterClientSample();

        sample.getDataCenter();
        sample.getAllDataCenters();
        sample.addDataCenter();
        sample.updateDataCenter();
        sample.getDataCenterByName();
        sample.getVisualContent();
        sample.removeDataCenter();

        sample.addDataCenter();
        sample.removeDataCenterByFilter();
    }

}
