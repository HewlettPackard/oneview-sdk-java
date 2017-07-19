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
Feature: 
  In order to manage Uplink Set Synergy

  Background: 
    Given an instance of OneView
    And OneView credentials located in "src/test/resources/oneView.properties"
    And an instance of Uplink Set

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
    And Resource values as follows:
      | name                    | fc-uplink-bdd |
      | fabricType              | FabricAttach  |
      | linkStabilityTime       |            30 |
      | autoLoginRedistribution | true          |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    And Resource values as follows:
      | name           | lig-uplink-bdd |
      | state          | ACTIVE         |
      | baySet         |              1 |
      | redundancyType | Redundant      |
      | enclosureType  | SY12000        |
    And interconnection values as follows:
      | entries | type                                          |
      |       1 | Virtual Connect SE 40Gb F8 Module for Synergy |
      |       4 | Virtual Connect SE 40Gb F8 Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Groups
    Given an instance of Enclosure Groups
    And Resource values as follows:
      | name             | enclosure-uplink-bdd |
      | lig              | lig-uplink-bdd       |
      | stackingMode     | Enclosure            |
      | enclosureCount   |                    1 |
      | ipAddressingMode | DHCP                 |
    When Enclosure Group sets Uris
    And OneView runs Enclosure Synergy creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of an new Uplink Set
    Given Resource values as follows:
      | name                           | uplink-bdd                           |
      | category                       | logical-interconnects                |
      | enclosure                      | 0000A66101                           |
      | bay                            |                                    2 |
      | port                           |                                    1 |
      | connectionMode                 | Auto                                 |
      | ethernetNetworkType            | NotApplicable                        |
      | networkType                    | FibreChannel                         |
      | manualLoginRedistributionState | Supported                            |
      | fc_network                     | fc-uplink-bdd                        |
      | desiredSpeed                   | Auto                                 |
      | logicalInterconnectUri         | da1bfbe9-5ced-4daf-b162-00f2184d7fe3 |
    When OneView runs Uplink Set creation
    And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Uplink Set
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Uplink Set
    Given name "uplink-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Uplink Set
    Given name "uplink-bdd" for Resource
    When OneView gets Resource by Name
    And OneView gets Resource by ID
    Then I get a Resource Name

  @edit
  Scenario: Edit an Uplink Set
    Given name "uplink-bdd" for Resource
    And Resource values will be updated as follows:
      | name | uplink-bdd_updated |
    When OneView gets Resource by Name
    And OneView runs Resource update
    And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove an Uplink Set
    Given name "uplink-bdd_updated" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Groups
    Given an instance of Enclosure Groups
    And name "enclosure-uplink-bdd" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    And name "lig-uplink-bdd" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
    And name "fc-uplink-bdd" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found
