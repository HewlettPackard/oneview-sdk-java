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
package com.hp.ov.sdk.switches;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SwitchPortStatistics;
import com.hp.ov.sdk.dto.SwitchStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ConnectionProperty;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.dto.generated.SwitchManagementConnection;
import com.hp.ov.sdk.dto.generated.ValueFormat;
import com.hp.ov.sdk.dto.generated.ValueType;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.SwitchClient;
import com.hp.ov.sdk.rest.client.SwitchClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class SwitchClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String SWITCH_ID = "fbc7dffe-e020-4efc-b89a-9db5b4469da4";
    private static final String SWITCH_NAME = "172.18.16.1";
    private static final String SWITCH_PORT_NAME = "1.5";
    // ================================

    private final SwitchClient switchClient;

    public SwitchClientSample() {
        this.switchClient = SwitchClientImpl.getClient();
    }

    private void getSwitch() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            Switch switchObj = this.switchClient.getSwitch(params, SWITCH_ID);

            System.out.println("SwitchClient : getSwitch : " +
                    "Switch object returned to client : " + switchObj);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : getSwitch : the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : getSwitch : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : getSwitch : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : getSwitch : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : getSwitch : arguments are null");
        }
    }

    private void getAllSwitches() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<Switch> switches = this.switchClient.getAllSwitches(params);

            System.out.println("SwitchClient : getAllSwitches : " +
                    "SwitchCollection object returned to client : " + switches);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : getAllSwitches : the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : getAllSwitches : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : getAllSwitches : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : getAllSwitches : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : getAllSwitches : arguments are null");
        }
    }

    private void getSwitchEnvironmentalConfiguration() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            EnvironmentalConfiguration environmentalConfiguration
                    = this.switchClient.getSwitchEnvironmentalConfiguration(params, SWITCH_ID);

            System.out.println("SwitchClient : getSwitchEnvironmentalConfiguration : " +
                    "EnvironmentalConfiguration object returned to client : " + environmentalConfiguration);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : getSwitchEnvironmentalConfiguration : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : getSwitchEnvironmentalConfiguration : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : getSwitchEnvironmentalConfiguration : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : getSwitchEnvironmentalConfiguration : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : getSwitchEnvironmentalConfiguration : arguments are null");
        }
    }

    private void getSwitchStatistics() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            SwitchStatistics statistics = this.switchClient.getSwitchStatistics(params, SWITCH_ID);

            System.out.println("SwitchClient : getSwitchStatistics : " +
                    "SwitchStatistics object returned to client : " + statistics);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : getSwitchStatistics : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : getSwitchStatistics : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : getSwitchStatistics : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : getSwitchStatistics : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : getSwitchStatistics : arguments are null");
        }
    }

    private void getSwitchPortStatistics() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            SwitchPortStatistics portStatistics = this.switchClient.getSwitchPortStatistics(params,
                    SWITCH_ID, SWITCH_PORT_NAME);

            System.out.println("SwitchClient : getSwitchPortStatistics : " +
                    "SwitchPortStatistics object returned to client : " + portStatistics);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : getSwitchPortStatistics : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : getSwitchPortStatistics : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : getSwitchPortStatistics : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : getSwitchPortStatistics : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : getSwitchPortStatistics : arguments are null");
        }
    }

    private void deleteSwitch() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.switchClient.getId(params, SWITCH_NAME);

            TaskResourceV2 task = this.switchClient.deleteSwitch(params, resourceId, false);

            System.out.println("SwitchClient : deleteSwitch : task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : deleteSwitch : resource you are looking is not found");
        } catch (final SDKBadRequestException ex) {
            System.out.println("SwitchClient : deleteSwitch : " +
                    "bad request, may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : deleteSwitch : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : deleteSwitch : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : deleteSwitch : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : deleteSwitch : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("SwitchClient : deleteSwitch : "
                    + "errors in task, please check task resource for more details ");
        }
    }

    private void refreshSwitch() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            TaskResourceV2 task = this.switchClient.refreshSwitch(params, SWITCH_ID, false);

            System.out.println("SwitchClient : refreshSwitch : task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : refreshSwitch : resource you are looking is not found");
        } catch (final SDKBadRequestException ex) {
            System.out.println("SwitchClient : refreshSwitch : " +
                    "bad request, may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : refreshSwitch : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : refreshSwitch : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : refreshSwitch : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : refreshSwitch : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("SwitchClient : refreshSwitch : "
                    + "errors in task, please check task resource for more details ");
        }
    }

    private Switch buildSwitch() {
        Switch switchObj = new Switch();
        SwitchManagementConnection mgmt = new SwitchManagementConnection();
        List<ConnectionProperty> properties = new ArrayList<>();

        properties.add(new ConnectionProperty("userName", "dcs", ValueFormat.Unknown, ValueType.String));
        properties.add(new ConnectionProperty("hostname", "172.18.16.1", ValueFormat.Unknown, ValueType.String));
        properties.add(new ConnectionProperty("password", "dcs", ValueFormat.SecuritySensitive, ValueType.String));

        mgmt.setConnectionProperties(properties);

        switchObj.setSwitchManagementConnection(mgmt);
        switchObj.setType(ResourceCategory.RC_SWITCH);

        return switchObj;
    }

    private void createSwitch() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            TaskResourceV2 task = this.switchClient.createSwitch(params, buildSwitch(), false);

            System.out.println("SwitchClient : createSwitch : task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : createSwitch : resource you are looking is not found");
        } catch (final SDKBadRequestException ex) {
            System.out.println("SwitchClient : createSwitch : " +
                    "bad request, may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : createSwitch : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : createSwitch : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : createSwitch : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : createSwitch : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("SwitchClient : createSwitch : "
                    + "errors in task, please check task resource for more details ");
        }
    }

    private void updateSwitch() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            Switch switchObj = this.switchClient.getSwitch(params, SWITCH_ID);

            //FIXME no changes due to problems after executing an update

            TaskResourceV2 task = this.switchClient.updateSwitch(params, SWITCH_ID, switchObj, false);

            System.out.println("SwitchClient : updateSwitch : task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : updateSwitch : resource you are looking is not found");
        } catch (final SDKBadRequestException ex) {
            System.out.println("SwitchClient : updateSwitch : " +
                    "bad request, may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : updateSwitch : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : updateSwitch : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : updateSwitch : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : updateSwitch : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("SwitchClient : updateSwitch : "
                    + "errors in task, please check task resource for more details ");
        }
    }

    private void getSwitchByName() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            Switch switchObj = this.switchClient.getSwitchByName(params, SWITCH_NAME);

            System.out.println("SwitchClient : getSwitchByName : " +
                    "Switch object returned to client : " + switchObj);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchClient : getSwitchByName : the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchClient : getSwitchByName : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchClient : getSwitchByName : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchClient : getSwitchByName : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchClient : getSwitchByName : arguments are null");
        }
    }

    public static void main(String[] args) {
        SwitchClientSample client = new SwitchClientSample();

        client.getSwitch();
        client.getAllSwitches();
        client.getSwitchEnvironmentalConfiguration();
        client.getSwitchByName();

        /* methods available only in version 1.2 */
        client.refreshSwitch();
        client.createSwitch();
        client.updateSwitch();
        client.deleteSwitch();

        /* methods available only in version 200 */
        client.getSwitchStatistics();
        client.getSwitchPortStatistics();
    }

}
