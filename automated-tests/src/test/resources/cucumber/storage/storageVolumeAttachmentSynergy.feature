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
Feature: In order to manage Storage Volume Attachments

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Storage Volume Attachment

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-network-bdd-sva1 |
      | fabricType              | FabricAttach        |
      | linkStabilityTime       |                  30 |
      | autoLoginRedistribution | true                |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-network-bdd-sva2 |
      | fabricType              | FabricAttach        |
      | linkStabilityTime       |                  30 |
      | autoLoginRedistribution | true                |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Storage System
    Given an instance of Storage System
    When OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @update
  Scenario: Update a Storage System Associating a FC
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | fc-network-bdd-sva1 |
      | port       | 0:1:1               |
      | domain     | TestDomain          |
    When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView runs Resource update
      And OneView lists all
    Then I get a count

  @update
  Scenario: Update a Storage System Associating a FC
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | fc-network-bdd-sva2 |
      | port       | 0:1:2               |
      | domain     | TestDomain          |
    When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView runs Resource update
      And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Storage Pool
    Given an instance of Storage Pool
      And Resource values as follows:
      | name | FST_CPG1 |
      And a Storage System Uri
    When StoragePool sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Storage Volume
    Given an instance of Storage volume
      And Resource values as follows:
      | name          | storage-volume-bdd-sva |
      | description   | Storage Volume Att BDD |
      | provisionType | Full                   |
      | shareable     | true                   |
      | capacity      |            20480000000 |
      And a Storage System Uri
      And a Storage Pool Uri
    When StorageVolume sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name           | lig-bdd-sva |
      | state          | ACTIVE      |
      | baySet         |           2 |
      | redundancyType | Redundant   |
      | enclosureType  | SY12000     |
      | enclosureIndex |          -1 |
      And interconnection values as follows:
      | entries | type                                          |
      |       2 | Virtual Connect SE 16Gb FC Module for Synergy |
      |       5 | Virtual Connect SE 16Gb FC Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @update
  Scenario: Update a Logical Interconnect Group Adding an Uplink Set 
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name           | lig-bdd-sva |
      | baySet         |           2 |
      And Uplink values as follows:
      | name         | type         | networks            | bayPort |
      | FCUplinkSet1 | FibreChannel | fc-network-bdd-sva1 | Q1:1    |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
    Then I get an ID

  @update
  Scenario: Update a Logical Interconnect Group Adding an Uplink Set
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name           | lig-bdd-sva |
      | baySet         |           5 |
      And Uplink values as follows:
      | name         | type         | networks            | bayPort |
      | FCUplinkSet2 | FibreChannel | fc-network-bdd-sva2 | Q1:2    |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name             | enclosure-group-bdd-sva |
      | lig              | lig-bdd-sva             |
      | stackingMode     | Enclosure               |
      | enclosureCount   |                       3 |
      | ipAddressingMode | DHCP                    |
      | entryBayOne      |                       5 |
      | entryBayTwo      |                       2 |
    When Enclosure Group sets Uris
      And OneView runs Enclosure Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @createMultiple
  Scenario: Creation of a new Logical Enclosure Multiple
    Given an instance of Logical Enclosure Multiple
      And Resource values as follows:
      | name           | logical_enclosure_bdd-sva |
      | enclosureGroup | enclosure-group-bdd-sva   |
      | enclosureURI1  | 0000A66101                |
      | enclosureURI2  | 0000A66102                |
      | enclosureURI3  | 0000A66103                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs Multiple enclosures creation
      And OneView gets Resource by Name
    Then I get an ID

  @createOne @disabled
  Scenario: Creation of a new Logical Enclosure One
    Given an instance of Logical Enclosure One
      And Resource values as follows:
      | name           | logical_enclosure_bdd-sva |
      | enclosureGroup | enclosure-group-bdd-sva   |
      | enclosureURI1  | 0000A66101                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs One enclosures creation
      And OneView gets Resource by Name
    Then I get an ID

  @update
  Scenario: Update a Logical Interconnect Compliance
    Given an instance of Logical Interconnect
      And name "logical_enclosure_bdd-sva-lig-bdd-sva-1" for Resource
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Compliance update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Compliance
    Given an instance of Logical Interconnect
      And name "logical_enclosure_bdd-sva-lig-bdd-sva-2" for Resource
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Compliance update
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Compliance
    Given an instance of Logical Interconnect
      And name "logical_enclosure_bdd-sva-lig-bdd-sva-3" for Resource
    When OneView gets Resource by Name
      And OneView runs Logical Interconnect Compliance update
    Then I get a success status
            
  @create
  Scenario: Creation of a new Server Profile
    Given an instance of Server Profile
      And Resource values as follows:
      | name               | sp-bdd-sva                              |
      | description        | sp-bdd                                  |
      | firmware           | Service Pack for ProLiant               |
      | affinity           | Bay                                     |
      | macType            | UserDefined                             |
      | wwnType            | UserDefined                             |
      | serialNumberType   | Physical                                |
      | enclosureGroup     | enclosure-group-bdd-sva                 |
      | serverHardware     | 0000A66101, bay 3                       |
      | serverHardwareType | SY 660 Gen9 1                           |
      | volume             | storage-volume-bdd-sva                  |
      | hostOSType         | Windows 2012 / WS2012 R2                |
      | fcNetworks         | fc-network-bdd-sva1,fc-network-bdd-sva2 |
      | requestBandwidth   |                                   16000 |
      And an Enclosure Group Uri
    When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @getAll
  Scenario: Get all Storage Volume Attachments
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Storage Volume Attachment by Volume Name
    Given name "storage-volume-bdd-sva" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Storage Volume Attachment Paths
    Given name "storage-volume-bdd-sva" for Resource
    When OneView gets Resource by Name
      And OneView gets All Volume Attachment Path
    Then I get a count

  @get
  Scenario: Get a Storage Volume Attachment Path by Id
    When OneView gets Resource by ID
    Then I get an ID

  @get
  Scenario: Get Extra Unmanaged Storage Volume Attachments
    Given name "storage-volume-bdd-sva" for Resource
    When OneView gets Resource by Name
      And OneView gets Extra Unmanaged Storage Volume Attachments
    Then I get an ID

  @repair
  Scenario: Repair Extra Unmanaged Storage Volume Attachments
    Given Resource values as follows:
      | serverProfile | sp-bdd-sva |
    When OneView runs Repair Unmanaged Storage Volume Attachments
    Then I get a success status

  @remove
  Scenario: Remove a Server Profile
    Given an instance of Server Profile
      And name "sp-bdd-sva" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Enclosure
    Given name "logical_enclosure_bdd-sva" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-sva" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-sva" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Volume
    Given an instance of Storage volume
      And name "storage-volume-bdd-sva" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Pool
    Given an instance of Storage Pool
      And name "FST_CPG1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage System
    Given an instance of Storage System
    When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-network-bdd-sva1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-network-bdd-sva2" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
