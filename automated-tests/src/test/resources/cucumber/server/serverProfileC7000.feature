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
      | name                    | fcn-bdd-server-profile |
      | fabricType              | FabricAttach           |
      | linkStabilityTime       |                     30 |
      | autoLoginRedistribution | true                   |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @update
  Scenario: Update a Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | fcn-bdd-server-profile |
      | port       | 0:1:1                  |
      | domain     | TestDomain             |
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
      | name          | volume-bdd-server-profile |
      | description   | Storage Volume BDD        |
      | provisionType | Full                      |
      | shareable     | true                      |
      | capacity      |               20480000000 |
      And a Storage System Uri
      And a Storage Pool Uri
    When StorageVolume sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | en-bdd-server-profile |
      | ethernetType | Tagged                |
      | vlanId       |                   300 |
      | purpose      | General               |
      | private      | false                 |
      | smartLink    | true                  |
      And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-server-profile |
      | state | ACTIVE                 |
      And interconnection values as follows:
      | entries | type                             |
      |       1 | HP VC FlexFabric-20/40 F8 Module |
      |       2 | HP VC FlexFabric-20/40 F8 Module |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @update
  Scenario: Update a Logical Interconnect Group adding an Uplink Set
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name   | lig-bdd-server-profile |
      | baySet |                      1 |
      And Uplink values as follows:
      | name              | type         | networks               | bayPort |
      | EthernetUplinkSet | Ethernet     | en-bdd-server-profile  | Q1.1    |
      | FCUplinkSet       | FibreChannel | fcn-bdd-server-profile | X2      |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | eg-bdd-server-profile  |
      | lig          | lig-bdd-server-profile |
      | stackingMode | Enclosure              |
    When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | eg-bdd-server-profile     |
      | licensing            | OneView                   |
      | force                | false                     |
      | firmware             | Service Pack for ProLiant |
      | updateFirmware       | EnclosureOnly             |
      | forceInstallFirmware | false                     |
    When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Server Profile
    Given Resource values as follows:
      | name             | server-profile-bdd        |
      | description      | sp-bdd                    |
      | firmware         | Service Pack for ProLiant |
      | affinity         | Bay                       |
      | macType          | UserDefined               |
      | wwnType          | UserDefined               |
      | serialNumberType | Physical                  |
      | enclosureGroup   | eg-bdd-server-profile     |
      | serverHardware   | Encl1, bay 2              |
      | volume           | volume-bdd-server-profile |
      | hostOSType       | Windows 2012 / WS2012 R2  |
      | requestBandwidth |                     10000 |
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
    Given name "server-profile-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Server Profile by Id
    Given name "server-profile-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Available Servers For Server Profile
    Given name "server-profile-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Available Servers For ServerProfile
    Then I get a count

  @get
  Scenario: Get Available Servers For Server Profile using Profile
    Given name "server-profile-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Available Servers For ServerProfile using Profile
    Then I get a count

  @get
  Scenario: Get Server Profile Compliance Preview
    Given name "server-profile-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets ServerProfile Compliance Preview
    Then Resource is found

  @get
  Scenario: Get Server Profile Message
    Given name "server-profile-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets ServerProfile Message
    Then Resource is found

  @update
  Scenario: Update a Server Profile
    Given name "server-profile-bdd" for Resource
      And Resource values will be updated as follows:
      | name | server-profile-bdd_updated |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Server Profile
    Given name "server-profile-bdd_updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure
    Given an instance of Enclosure
    When OneView gets Enclosure Name
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "eg-bdd-server-profile" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-server-profile" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Volume
  	Given an instance of Storage volume
     And name "volume-bdd-server-profile" for Resource
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
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "en-bdd-server-profile" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fcn-bdd-server-profile" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
