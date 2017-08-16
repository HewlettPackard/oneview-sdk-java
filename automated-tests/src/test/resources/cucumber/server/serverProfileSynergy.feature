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
Feature: In order to manage Server Profiles

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Server Profile

  @create
  Scenario: Creation of a new Storage System
    Given an instance of Storage System
    When OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | all-fc-bdd-sp |
      | fabricType              | FabricAttach  |
      | linkStabilityTime       |            30 |
      | autoLoginRedistribution | true          |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID
        
  @update
  Scenario: Update a Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | all-fc-bdd-sp |
      | port       | 0:1:1         |
      | domain     | TestDomain    |
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
      | name          | volume-bdd-storage-volume-sp |
      | description   | Storage Volume BDD           |
      | provisionType | Full                         |
      | shareable     | true                         |
      | capacity      |                  20480000000 |
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
      | name           | lig-bdd-sp |
      | state          | ACTIVE     |
      | baySet         |          2 |
      | redundancyType | Redundant  |
      | enclosureType  | SY12000    |
      | enclosureIndex |         -1 |
      And interconnection values as follows:
      | entries | type                                          |
      |       2 | Virtual Connect SE 16Gb FC Module for Synergy |
      |       5 | Virtual Connect SE 16Gb FC Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name             | enclosure-group-bdd-sp |
      | lig              | lig-bdd-sp             |
      | stackingMode     | Enclosure              |
      | enclosureCount   |                      3 |
      | ipAddressingMode | DHCP                   |
      | entryBayOne      |                      5 |
      | entryBayTwo      |                      2 |
    When Enclosure Group sets Uris
      And OneView runs Enclosure Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @createMultiple
  Scenario: Creation of a new Logical Enclosure Multiple
    Given an instance of Logical Enclosure Multiple
      And Resource values as follows:
      | name           | logical_enclosure_bdd-sp  |
      | enclosureGroup | enclosure-group-bdd-sp    |
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
      | name           | logical_enclosure_bdd-sp  |
      | enclosureGroup | enclosure-group-bdd       |
      | enclosureURI1  | 0000A66101                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs One enclosures creation
      And OneView gets Resource by Name
    Then I get an ID
    
  @create
  Scenario: Creation of a new Server Profile
    Given Resource values as follows:
      | name               | sp-bdd                       |
      | description        | sp-bdd                       |
      | firmware           | Service Pack for ProLiant    |
      | affinity           | Bay                          |
      | macType            | UserDefined                  |
      | wwnType            | UserDefined                  |
      | serialNumberType   | Physical                     |
      | enclosureGroup     | enclosure-group-bdd-sp       |
      | serverHardware     | 0000A66101, bay 4            |
      | serverHardwareType | SY 660 Gen9 1                |
      | volume             | volume-bdd-storage-volume-sp |
      | hostOSType         | Windows 2012 / WS2012 R2     |
      | requestBandwidth   |                        16000 |
      And an Enclosure Group Uri
    When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count   

  @getAll
  Scenario: Get all Server Profiles
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Server Profile by Name
    Given name "sp-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Server Profile by Id
    Given name "sp-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Available Servers For Server Profile
    Given name "sp-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Available Servers For ServerProfile
    Then I get a count

  @get
  Scenario: Get Available Servers For Server Profile using Profile
    Given name "sp-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Available Servers For ServerProfile using Profile
    Then I get a count

  @get
  Scenario: Get Server Profile Compliance Preview
    Given name "sp-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets ServerProfile Compliance Preview
    Then Resource is found

  @get
  Scenario: Get Server Profile Message
    Given name "sp-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets ServerProfile Message
    Then Resource is found

  @update
  Scenario: Update a Server Profile
    Given name "sp-bdd" for Resource
      And Resource values will be updated as follows:
      | name | sp-bdd_updated |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Server Profile
    Given name "sp-bdd_updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Enclosure
  	Given an instance of Logical Enclosure
      And name "logical_enclosure_bdd-sp" for Resource
   When OneView gets Resource by Name
     And OneView deletes the Resource
     And OneView gets Resource by ID
   Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-sp" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-sp" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Volume
  	Given an instance of Storage volume
     And name "volume-bdd-storage-volume-sp" for Resource
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
      And name "all-fc-bdd-sp" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
    