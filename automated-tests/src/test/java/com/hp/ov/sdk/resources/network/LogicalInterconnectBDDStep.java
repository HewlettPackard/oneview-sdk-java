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

package com.hp.ov.sdk.resources.network;

import com.hp.ov.sdk.network.LogicalInterconnectResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;
import com.hp.ov.sdk.server.EnclosureResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LogicalInterconnectBDDStep extends Background {

    @Given("^an instance of Logical Interconnect$")
    public void an_instance_of_Logical_Interconnect() throws Throwable {
        OneView.setResource(LogicalInterconnectResource.getInstance());
    }

    @When("^OneView sets Enclosure uri to Logical Interconnect$")
    public void oneview_sets_Enclosure_uri_to_Logical_Interconnect() throws Throwable {
        String enclosureUri = EnclosureResource.getInstance().getUri();
        LogicalInterconnectResource.getInstance().setEnclosureUri(enclosureUri);
    }

    @When("^OneView gets Logical Interconnect Name$")
    public void oneview_gets_Logical_Interconnect_Name() throws Throwable {
        resourceName = LogicalInterconnectResource.getInstance().getLogicalInterconnectName();
    }

    @When("^OneView runs Logical Interconnect Compliance update$")
    public void oneview_runs_Logical_Interconnect_Compliance_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateCompliance(resourceID);
    }

    @When("^OneView gets Logical Interconnect Firmware$")
    public void oneview_gets_Logical_Interconnect_Firmware() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getFirmware(resourceID);
    }

    @When("^OneView gets Logical Interconnect Snmp Configuration$")
    public void oneview_gets_Logical_Interconnect_Snmp_Configuration() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getSnmpConfiguration(resourceID);
    }

    @When("^OneView gets Logical Interconnect Forwarding Information Base$")
    public void oneview_gets_Logical_Interconnect_Forwarding_Information_Base() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getForwardingInformationBase(resourceID);
    }

    @When("^OneView gets Logical Interconnect Unassigned Uplink Ports For Port Monitor$")
    public void oneview_gets_Logical_Interconnect_Unassigned_Uplink_Ports_For_Port_Monitor() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getUnassignedUplinkPortsForPortMonitor(resourceID);
    }

    @When("^OneView gets Logical Interconnect Port Monitor Configuration$")
    public void oneview_gets_Logical_Interconnect_Port_Monitor_Configuration() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getPortMonitorConfiguration(resourceID);
    }

    @When("^OneView gets Logical Interconnect Telemetry Configuration$")
    public void oneview_gets_Logical_Interconnect_Telemetry_Configuration() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getTelemetryConfiguration(resourceID);
    }

    @When("^OneView gets Logical Interconnect Internal Vlans$")
    public void oneview_gets_Logical_Interconnect_Internal_Vlans() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getInternalVlans(resourceID);
    }

    @When("^OneView gets Logical Interconnect Qos Aggregated Configuration$")
    public void oneview_gets_Logical_Interconnect_Qos_Aggregated_Configuration() throws Throwable {
        resourceStr = LogicalInterconnectResource.getInstance().getQosAggregatedConfiguration(resourceID);
    }

    @When("^OneView runs Logical Interconnect Firmware update$")
    public void oneview_runs_Logical_Interconnect_Firmware_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateFirmware(resourceID);
    }

    @When("^OneView runs Logical Interconnect Snmp Configuration$")
    public void oneview_runs_Logical_Interconnect_Snmp_Configuration() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateSnmpConfiguration(resourceID);
    }

    @When("^OneView create a Logical Interconnect Forwarding Information Base$")
    public void oneview_create_a_Logical_Interconnect_Forwarding_Information_Base() throws Throwable {
        status = LogicalInterconnectResource.getInstance().createForwardingInformationBase(resourceID);
    }

    @When("^OneView runs Logical Interconnect Configuration$")
    public void oneview_runs_Logical_Interconnect_Configuration() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateForwardingInformationBase(resourceID);
    }

    @When("^OneView runs Logical Interconnect Port Monitor Configuration update$")
    public void oneview_runs_Logical_Interconnect_Port_Monitor_Configuration_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updatePortMonitorConfiguration(resourceID);
    }

    @When("^OneView runs Ethernet Settings update$")
    public void oneview_runs_Ethernet_Settings_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateEthernetSettings(resourceID);
    }

    @When("^OneView runs Logical Interconnect Internal Networks update$")
    public void oneview_runs_Logical_Interconnect_Internal_Networks_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateLogicalInterconnectInternalNetworks(resourceID);
    }

    @When("^OneView runs Logical Interconnect Qos Aggregated Configuration update$")
    public void oneview_runs_Logical_Interconnect_Qos_Aggregated_Configuration_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateQosAggregatedConfiguration(resourceID);
    }

    @When("^OneView runs Logical Interconnect Settings update$")
    public void oneview_runs_Logical_Interconnect_Settings_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateLogicalInterconnectSettings(resourceID);
    }

    @When("^OneView runs Logical Interconnect Firmware Active update$")
    public void oneview_runs_Logical_Interconnect_Firmware_Active_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateFirmwareActive(resourceID);
    }

    @When("^OneView runs Logical Interconnect Telemetry Configuration version two update$")
    public void oneview_runs_Logical_Interconnect_Telemetry_Configuration_version_two_update() throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateTelemetryConfigurationV2(resourceID);
    }

    @When("^OneView runs Logical Interconnect Telemetry Configuration version one dot two update$")
    public void oneview_runs_Logical_Interconnect_Telemetry_Configuration_version_one_dot_two_update()
            throws Throwable {
        status = LogicalInterconnectResource.getInstance().updateTelemetryConfigurationV1_2(resourceID);
    }

}
