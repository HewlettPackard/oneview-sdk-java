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

package com.hp.ov.sdk.serverhardware;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.ServerHardwareTypeClient;
import com.hp.ov.sdk.rest.client.ServerHardwareTypeClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class ServerHardwareTypeClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "BL460c Gen8 1";
    private static final String resourceId = "53E2C267-CEDC-4A0B-961E-10034F5B7098";
    // ================================

    private final ServerHardwareTypeClient client;

    private ServerHardwareTypeClientSample() {
        this.client = ServerHardwareTypeClientImpl.getClient();
    }

    private void getServerHardwareType() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            ServerHardwareType serverHardwareType = client.getServerHardwareType(params, resourceId);

            System.out.println("ServerHardwareTypeClientSample : getServerHardwareType : "
                    + "ServerHardwareType object returned to client : " + serverHardwareType.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareType : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareType : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareType : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareType : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareType : " + "arguments are null ");
            return;
        }
    }

    private void getAllServerHardwareTypes() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<ServerHardwareType> collection = client.getAllServerHardwareTypes(params);

            System.out.println("ServerHardwareTypeClientSample : getAllServerHardwareTypes : "
                    + "server hardware collection returned to client (count) : " + collection.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareTypeClientSample : getAllServerHardwareTypes : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareTypeClientSample : getAllServerHardwareTypes : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareTypeClientSample : getAllServerHardwareTypes : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareTypeClientSample : getAllServerHardwareTypes : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareTypeClientSample : getAllServerHardwareTypes : arguments are null ");
            return;
        }
    }

    private void getServerHardwareTypeByName() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            ServerHardwareType serverHardwareType = client.getServerHardwareTypeByName(params, resourceName);

            if (serverHardwareType != null) {
                System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : "
                        + "server hardware object returned to client : " + serverHardwareType.toString());
            } else {
                System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : "
                        + "server hardware object returned to client : no server hardware found for the name" + resourceName);
            }
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareTypeClientSample : getServerHardwareTypeByName : " + "arguments are null ");
            return;
        }
    }

    private void updateServerHardwareType() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = client.getId(params, resourceName);
            ServerHardwareTypeUpdate update = new ServerHardwareTypeUpdate();

            update.setDescription("Sample description update");

            ServerHardwareType serverHardwareType = client.updateServerHardwareType(params, resourceId, update);

            System.out.println("ServerHardwareTypeClientSample : updateServerHardwareType : object returned to client : "
                    + serverHardwareType.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareTypeClientSample : updateServerHardwareType : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareTypeClientSample : updateServerHardwareType : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareTypeClientSample : updateServerHardwareType : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareTypeClientSample : updateServerHardwareType : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareTypeClientSample : updateServerHardwareType : arguments are null ");
            return;
        }
    }

    private void deleteServerHardwareType() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = client.getId(params, resourceName);

            TaskResourceV2 taskResourceV2 = client.deleteServerHardwareType(params, resourceId, false);

            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : resource you are looking is not found for delete");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerHardwareTypeClientSample : deleteServerHardwareType : errors in task, please check task resource for more details ");
            return;
        }
    }

    public static void main(String[] args) {
        ServerHardwareTypeClientSample sample = new ServerHardwareTypeClientSample();

        sample.getServerHardwareType();
        sample.getAllServerHardwareTypes();
        sample.getServerHardwareTypeByName();
        sample.updateServerHardwareType();

        /* available from OV 2.0 */
        sample.deleteServerHardwareType();
    }

}
