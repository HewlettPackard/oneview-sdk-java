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
Feature: In order to manage Logical Interconnect Groups

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Interconnect Group

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given Resource values as follows:
      | name           | lig-bdd-1 |
      | state          | ACTIVE    |
      | baySet         |         1 |
      | redundancyType | Redundant |
      | enclosureType  | SY12000   |
      | enclosureIndex |         1 |
      And interconnection values as follows:
      | entries | type                                          |
      |       1 | Virtual Connect SE 40Gb F8 Module for Synergy |
      |       4 | Virtual Connect SE 40Gb F8 Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | network-bdd-1 |
      | ethernetType | Tagged        |
      | vlanId       |           301 |
      | purpose      | General       |
      | private      | false         |
      | smartLink    | true          |
      And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | network-bdd-2 |
      | ethernetType | Tagged        |
      | vlanId       |           302 |
      | purpose      | General       |
      | private      | false         |
      | smartLink    | true          |
      And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-network-bdd-1 |
      | fabricType              | FabricAttach     |
      | linkStabilityTime       |               30 |
      | autoLoginRedistribution | true             |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Logical Interconnect Groups
    When OneView lists all
    Then I get a count
    
  @get
  Scenario: Get a Logical Interconnect Group by Name
    Given name "lig-bdd-1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Logical Interconnect Group by Id
    Given name "lig-bdd-1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Id of Default Interconnect Settings
    When OneView gets Default Interconnect Settings
    Then I get an ID

  @get
  Scenario: Get Id of an Interconnect Settings
    Given name "lig-bdd-1" for Resource
    When OneView gets Default Interconnect Settings
      And OneView gets Resource by Name
      And OneView gets Interconnect Settings
    Then I get a Resource Name

  @update
  Scenario: Update a Logical Interconnect Group Adding an Uplink Set
    Given Resource values as follows:
      | name   | lig-bdd-1 |
      | baySet |         1 |
      And Uplink values as follows:
      | name              | type         | networks         | bayPort |
      | EthernetUplinkSet | Ethernet     | network-bdd-1    | Q1:1    |
      | FCUplinkSet       | FibreChannel | fc-network-bdd-1 | Q2:3    |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
    Then I get an ID

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given name "lig-bdd-1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "network-bdd-1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "network-bdd-2" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-network-bdd-1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
