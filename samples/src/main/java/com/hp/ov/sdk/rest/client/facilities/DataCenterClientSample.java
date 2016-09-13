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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.DeratingType;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class DataCenterClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCenterClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String DATA_CENTER_RESOURCE_ID = "5adaa715-4572-4e3d-99f3-8d320ca27dfd";
    private static final String DATA_CENTER_NAME = "Sample DataCenter";
    // ================================

    private final DataCenterClient dataCenterClient;

    public DataCenterClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.dataCenterClient = oneViewClient.dataCenter();
    }

    private void getDataCenterById() {
        DataCenter dataCenter = this.dataCenterClient.getById(DATA_CENTER_RESOURCE_ID);

        LOGGER.info("DataCenter object returned to client : " + dataCenter.toJsonString());
    }

    private void getAllDataCenters() {
        ResourceCollection<DataCenter> dataCenters = this.dataCenterClient.getAll();

        LOGGER.info("DataCenters returned to client : " + dataCenters.toJsonString());
    }

    private void getDataCenterByName() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);

        LOGGER.info("DataCenter object returned to client : " + dataCenter.toJsonString());
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

        LOGGER.info("DataCenter object returned to client : " + addedDataCenter.toJsonString());
    }

    private void updateDataCenter() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);
        String resourceId = dataCenter.getResourceId();

        dataCenter.setCurrency("BRL");

        DataCenter updatedDataCenter = this.dataCenterClient.update(resourceId, dataCenter);

        LOGGER.info("DataCenter object returned to client : " + updatedDataCenter.toJsonString());
    }

    private void getVisualContent() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);
        List<VisualContent> visualContent = this.dataCenterClient.getVisualContent(dataCenter.getResourceId());

        LOGGER.info("Visual content list returned to client : " + JsonPrettyPrinter.print(visualContent));
    }

    private void removeDataCenter() {
        DataCenter dataCenter = this.dataCenterClient.getByName(DATA_CENTER_NAME).get(0);
        String response = this.dataCenterClient.remove(dataCenter.getResourceId());

        LOGGER.info("Response returned to client : " + response);
    }

    private void removeDataCenterByFilter() {
        String filter = "name='" + DATA_CENTER_NAME +"'";
        TaskResourceV2 task = this.dataCenterClient.removeByFilter(filter, false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        DataCenterClientSample sample = new DataCenterClientSample();

        sample.getDataCenterById();
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
