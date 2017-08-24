################################################################################
# (C) Copyright 2016 Hewlett Packard Enterprise Development LP
#
# Licensed under the Apache License, Version 2.0 (the "License");
# You may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
################################################################################
Feature: In order to manage Logical Interconnects

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Interconnect

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-logical-interconnect |
      | state | ACTIVE                       |
      And interconnection values as follows:
      | entries | type                                 |
      |       1 | HP VC FlexFabric 10Gb/24-Port Module |
      |       2 | HP VC FlexFabric 10Gb/24-Port Module |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | eg-bdd-logical-interconnect  |
      | lig          | lig-bdd-logical-interconnect |
      | stackingMode | Enclosure                    |
    When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | eg-bdd-logical-interconnect          |
      | licensing            | OneView                              |
      | force                | false                                |
      | firmware             | Service Pack for ProLiant, 2017.07.0 |
      | updateFirmware       | EnclosureOnly                        |
      | forceInstallFirmware | false                                |
    When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Create a Logical Interconnect Forwarding Information Base
    Given Resource values as follows:
      | name | Encl1-lig-bdd-logical-interconnect |
    When OneView gets Resource by Name
      And OneView create a Logical Interconnect Forwarding Information Base
    Then I get a success status

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | net-bdd-logical-interconnect |
      | ethernetType | Tagged                       |
      | vlanId       |                          301 |
      | purpose      | General                      |
      | private      | false                        |
      | smartLink    | true                         |
      And bandwidth values as follows:
      | maxBandwidth     | 1000 |
      | typicalBandwidth | 1000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Logical Interconnects
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Interconnect by Name
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Logical Interconnect by Id
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Logical Interconnect Firmware
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Firmware
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Snmp Configuration
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Snmp Configuration
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Forwarding Information Base
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Forwarding Information Base
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Unassigned Uplink Ports For Port Monitor
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Unassigned Uplink Ports For Port Monitor
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Port Monitor Configuration
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Port Monitor Configuration
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Telemetry Configuration
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Telemetry Configuration
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Internal Vlans
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Internal Vlans
    Then Resource is found

  @get
  Scenario: Get a Logical Interconnect Qos Aggregated Configuration
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Interconnect Qos Aggregated Configuration
    Then Resource is found

  @update
  Scenario: Update a Logical Interconnect Compliance
    Given name "Encl1-lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Compliance update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Firmware
    Given Resource values as follows:
      | name    | Encl1-lig-bdd-logical-interconnect   |
      | command | Stage                                |
      | spp     | Service Pack for ProLiant, 2017.07.0 |
      | force   | true                                 |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Firmware update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Snmp Configuration
    Given Resource values as follows:
      | name          | Encl1-lig-bdd-logical-interconnect |
      | readCommunity | private                            |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Snmp Configuration
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Configuration
    Given Resource values as follows:
      | name | Encl1-lig-bdd-logical-interconnect |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Configuration
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Port Monitor Configuration
    Given Resource values as follows:
      | name              | Encl1-lig-bdd-logical-interconnect |
      | enablePortMonitor | false                              |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Port Monitor Configuration update
    Then I get a success status

  @update
  Scenario: Update an Ethernet Settings
    Given Resource values as follows:
      | name                       | Encl1-lig-bdd-logical-interconnect |
      | enablePauseFloodProtection | false                              |
    When OneView gets Resource by Name
      And OneView runs Ethernet Settings update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Internal Networks
    Given Resource values as follows:
      | name    | Encl1-lig-bdd-logical-interconnect |
      | network | net-bdd-logical-interconnect       |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Internal Networks update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Qos Aggregated Configuration
    Given Resource values as follows:
      | name       | Encl1-lig-bdd-logical-interconnect |
      | configType | Passthrough                        |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Qos Aggregated Configuration update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Settings
    Given Resource values as follows:
      | name               | Encl1-lig-bdd-logical-interconnect |
      | type               | InterconnectSettingsV201           |
      | macRefreshInterval |                                  6 |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Settings update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Firmware Active
    Given Resource values as follows:
      | name              | Encl1-lig-bdd-logical-interconnect   |
      | command           | Stage                                |
      | spp               | Service Pack for ProLiant, 2017.07.0 |
      | interconnectName1 | Encl1, interconnect 1                |
      | interconnectName2 | Encl1, interconnect 2                |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Firmware Active update
    Then I get a success status

  @update @onlyOV2
  Scenario: Update a Logical Interconnect Telemetry Configuration V2
    Given Resource values as follows:
      | name            | Encl1-lig-bdd-logical-interconnect |
      | enableTelemetry | false                              |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Telemetry Configuration version two update
    Then I get a success status

  @update @onlyOV1.2
  Scenario: Update a Logical Interconnect Telemetry Configuration V1.2
    Given Resource values as follows:
      | name            | Encl1-lig-bdd-logical-interconnect |
      | enableTelemetry | false                              |
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Telemetry Configuration version one dot two update
    Then I get a success status

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "net-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure
    Given an instance of Enclosure
      And name "Encl1" for Resource 
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "eg-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-logical-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
