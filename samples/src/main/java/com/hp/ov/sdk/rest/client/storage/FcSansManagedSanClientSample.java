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
package com.hp.ov.sdk.rest.client.storage;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.EndpointResponse;
import com.hp.ov.sdk.dto.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.FcIssueResponse;
import com.hp.ov.sdk.dto.FcSansManagedSanTask;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.fcsans.SanPolicy;
import com.hp.ov.sdk.dto.fcsans.SanRequest;
import com.hp.ov.sdk.dto.fcsans.SanResponse;
import com.hp.ov.sdk.dto.fcsans.ZoningPolicy;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * ManagedSanClientSample is a sample program capture/consume the physical or logical fibre channel SAN or a Flat SAN
 * managed by HPE OneView. It invokes APIs of ManagedSanClientSample which is in sdk library to perform GET/PUT
 * operations on san resource
 */
public class FcSansManagedSanClientSample {

    private final FcSanManagedSanClient fcSanManagedSanClient;

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "e9d79564-1244-4f0e-83a3-215dcb1f6b1f";
    private static final String RESOURCE_NAME = "SAN1_0";
    // ================================

    private FcSansManagedSanClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.fcSanManagedSanClient = oneViewClient.fcSanManagedSan();
    }

    private void getFcSanManagedSanById() {
        SanResponse san = this.fcSanManagedSanClient.getById(RESOURCE_ID);

        System.out.println("FcSanManagedSanClientSample : getFcSanManagedSanById : " +
                "SanResponse object returned to client : " + san.toJsonString());
    }

    private void getAllFcSanManagedSans() {
        ResourceCollection<SanResponse> sans = this.fcSanManagedSanClient.getAll();

        System.out.println("FcSanManagedSanClientSample : getAllFcSanManagedSans : " +
                "DeviceManagers returned to client : " + sans.toJsonString());
    }

    private void getFcSanManagedSanByName() {
        SanResponse san = this.fcSanManagedSanClient.getByName(RESOURCE_NAME).get(0);

        System.out.println("FcSanManagedSanClientSample : getFcSanManagedSanByName : " +
                "SanResponse object returned to client : " + san.toJsonString());
    }

    private void updateFcSanManagedSan() {
        SanResponse san = this.fcSanManagedSanClient.getByName(RESOURCE_NAME).get(0);
        SanRequest sanRequest = new SanRequest();
        SanPolicy sanPolicy = new SanPolicy();

        sanPolicy.setEnableAliasing(Boolean.TRUE);
        sanPolicy.setZoningPolicy(ZoningPolicy.SingleInitiatorAllTargets);
        sanPolicy.setInitiatorNameFormat("sample_initiator_name_format");
        sanPolicy.setTargetGroupNameFormat("sample_target_group_name_format");
        sanPolicy.setTargetNameFormat("sample_target_name_format");
        sanPolicy.setZoneNameFormat("sample_zone_name_format");

        sanRequest.setSanPolicy(sanPolicy);
        sanRequest.setRefreshState(RefreshState.RefreshPending);

        SanResponse updatedSan = this.fcSanManagedSanClient.update(san.getResourceId(), sanRequest);

        System.out.println("FcSanManagedSanClientSample : updateFcSanManagedSan : " +
                "SanResponse object returned to client : " + updatedSan.toJsonString());
    }

    private void getFcSanManagedSanEndpoints() {
        SanResponse san = this.fcSanManagedSanClient.getByName(RESOURCE_NAME).get(0);
        ResourceCollection<EndpointResponse> endpoints = fcSanManagedSanClient.getEndpoints(san.getResourceId());

        System.out.println("FcSanManagedSanClientSample : getFcSanManagedSanEndpoints : " +
                "Endpoints returned to client : " + endpoints.toJsonString());
    }

    private void createFcSanManagedSanIssuesReport() {
        SanResponse san = this.fcSanManagedSanClient.getByName(RESOURCE_NAME).get(0);
        FcSansManagedSanTask task = this.fcSanManagedSanClient.createIssuesReport(san.getResourceId(), false);

        System.out.println("FcSansManagedSanClientSample : createFcSanManagedSanIssuesReport : " +
                "Task object returned to client : " + task.getTask().toJsonString());

        for (FcIssueResponse issue : task.getIssues()) {
            System.out.println("FcSansManagedSanClientSample : createFcSanManagedSanIssuesReport : " +
                    "Issue returned to client : " + JsonPrettyPrinter.print(issue));
        }
    }

    private void createFcSanManagedSanEndpointsCsv() {
        SanResponse san = this.fcSanManagedSanClient.getByName(RESOURCE_NAME).get(0);
        EndpointsCsvFileResponse csvFileResponse = fcSanManagedSanClient.createEndpointsCsv(san.getResourceId());

        System.out.println("FcSanManagedSanClientSample : createFcSanManagedSanEndpointsCsv : " +
                "EndpointsCsvFileResponse returned to client : " + JsonPrettyPrinter.print(csvFileResponse));
    }

    public static void main(final String[] args) throws Exception {
        FcSansManagedSanClientSample client = new FcSansManagedSanClientSample();

        client.getFcSanManagedSanById();
        client.getAllFcSanManagedSans();
        client.getFcSanManagedSanByName();

        client.getFcSanManagedSanEndpoints();
        client.createFcSanManagedSanEndpointsCsv();
        client.createFcSanManagedSanIssuesReport();

        client.updateFcSanManagedSan();
    }
}
