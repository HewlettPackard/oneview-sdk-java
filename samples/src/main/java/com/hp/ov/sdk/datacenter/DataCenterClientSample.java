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

package com.hp.ov.sdk.datacenter;

import java.util.List;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.datacenter.DataCenter;
import com.hp.ov.sdk.dto.datacenter.DeratingType;
import com.hp.ov.sdk.dto.datacenter.VisualContent;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.DataCenterClient;
import com.hp.ov.sdk.rest.client.DataCenterClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class DataCenterClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String DATA_CENTER_RESOURCE_ID = "9955ffa6-fe4a-45ca-b42d-d25ca4fbae42";
    private static final String DATA_CENTER_NAME = "Sample DataCenter";
    // ================================

    private final DataCenterClient dataCenterClient;

    public DataCenterClientSample() {
        this.dataCenterClient = DataCenterClientImpl.getClient();
    }

    private void getDataCenter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            DataCenter dataCenter = this.dataCenterClient.getDataCenter(params, DATA_CENTER_RESOURCE_ID);

            System.out.println("DataCenterClientSample : getDataCenter : " +
                    "DataCenter object returned to client : " + dataCenter);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : getDataCenter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : getDataCenter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : getDataCenter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : getDataCenter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : getDataCenter : " +
                    "arguments are null ");
        }
    }

    private void getAllDataCenters() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<DataCenter> dataCenters = this.dataCenterClient.getAllDataCenters(params);

            System.out.println("DataCenterClientSample : getAllDataCenters : " +
                    "DataCenterCollection object returned to client (count) : " + dataCenters.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : getAllDataCenters : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : getAllDataCenters : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : getAllDataCenters : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : getAllDataCenters : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : getAllDataCenters : " +
                    "arguments are null ");
        }
    }

    private void getDataCenterByName() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            DataCenter dataCenter = this.dataCenterClient.getDataCenterByName(params, DATA_CENTER_NAME);

            System.out.println("DataCenterClientSample : getDataCenterByName : " +
                    "DataCenter object returned to client : " + dataCenter);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : getDataCenterByName : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : getDataCenterByName : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : getDataCenterByName : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : getDataCenterByName : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : getDataCenterByName : " +
                    "arguments are null ");
        }
    }

    private void addDataCenter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

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

            DataCenter addedDataCenter = this.dataCenterClient.addDataCenter(params, dataCenter);

            System.out.println("DataCenterClientSample : addDataCenter : " +
                    "DataCenter object returned to client : " + addedDataCenter);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : addDataCenter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : addDataCenter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : addDataCenter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : addDataCenter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : addDataCenter : " +
                    "arguments are null ");
        }
    }

    private void updateDataCenter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            DataCenter dataCenter = this.dataCenterClient.getDataCenterByName(params, DATA_CENTER_NAME);
            String resourceId = UrlUtils.getResourceIdFromUri(dataCenter.getUri());

            dataCenter.setCurrency("BRL");

            DataCenter updatedDataCenter = this.dataCenterClient.updateDataCenter(params, resourceId, dataCenter);

            System.out.println("DataCenterClientSample : updateDataCenter : " +
                    "DataCenter object returned to client : " + updatedDataCenter);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : updateDataCenter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : updateDataCenter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : updateDataCenter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : updateDataCenter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : updateDataCenter : " +
                    "arguments are null ");
        }
    }

    private void getVisualContent() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.dataCenterClient.getId(params, DATA_CENTER_NAME);
            List<VisualContent> visualContent = this.dataCenterClient.getVisualContent(params, resourceId);

            System.out.println("DataCenterClientSample : getVisualContent : " +
                    "VisualContent list returned to client  (count) : " + visualContent.size());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : getVisualContent : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : getVisualContent : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : getVisualContent : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : getVisualContent : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : getVisualContent : " +
                    "arguments are null ");
        }
    }

    private void removeDataCenter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.dataCenterClient.getId(params, DATA_CENTER_NAME);
            String response = this.dataCenterClient.removeDataCenter(params, resourceId);

            System.out.println("DataCenterClientSample : removeDataCenter : " +
                    "response returned to client : " + response);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : removeDataCenter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : removeDataCenter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : removeDataCenter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : removeDataCenter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : removeDataCenter : " +
                    "arguments are null ");
        }
    }

    private void removeDataCenterByFilter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String filter = "name='" + DATA_CENTER_NAME +"'";
            TaskResourceV2 task = this.dataCenterClient.removeDataCenterByFilter(params, filter, false);

            System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DataCenterClientSample : removeDataCenterByFilter : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("DataCenterClientSample : removeDataCenterByFilter : "
                    + "errors in task, please check task resource for more details");
        }
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

        /* sample.addDataCenter(); */
        sample.removeDataCenterByFilter();
    }

}
