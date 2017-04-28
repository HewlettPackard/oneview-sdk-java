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
  In order to create an environment

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"

  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | all-net-bdd-1 |
      | ethernetType | Tagged        |
      | vlanId       |           301 |
      | purpose      | General       |
      | private      | false         |
      | smartLink    | true          |
      And bandwidth values as follows:
      | maxBandwidth     | 1000 |
      | typicalBandwidth | 1000 |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | all-net-bdd-2 |
      | ethernetType | Tagged        |
      | vlanId       |           302 |
      | purpose      | General       |
      | private      | false         |
      | smartLink    | true          |
      And bandwidth values as follows:
      | maxBandwidth     | 1000 |
      | typicalBandwidth | 1000 |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | all-fc-bdd-1 |
      | fabricType              | FabricAttach |
      | linkStabilityTime       |           30 |
      | autoLoginRedistribution | true         |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | all-fc-bdd-2 |
      | fabricType              | FabricAttach |
      | linkStabilityTime       |           30 |
      | autoLoginRedistribution | true         |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Creation of a new Storage System
    Given an instance of Storage System
     When OneView runs Resource creation
      And OneView lists all
     Then I get a count

  Scenario: Update Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | all-fc-bdd-1, all-fc-bdd-2 |
      | port       | 0:1:1, 0:1:2               |
      | domain     | TestDomain                 |
     When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView runs Resource update
      And OneView lists all
     Then I get a count

  Scenario: Creation of a new Storage Pool
    Given an instance of Storage Pool
      And Resource values as follows:
      | name | FST_CPG1 |
      And a Storage System Uri
     When StoragePool sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count

  Scenario: Creation of a new Storage Volume
    Given an instance of Storage volume
      And Resource values as follows:
      | name          | volume-bdd-storage-volume |
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

  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-4-sp |
      | state | ACTIVE       |
      And interconnection values as follows:
      | entries | type                                 |
      |       1 | HP VC FlexFabric 10Gb/24-Port Module |
      |       2 | HP VC FlexFabric 10Gb/24-Port Module |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Edit a Logical Interconnect Group
    Given name "lig-bdd-4-sp" for Resource
      And Uplink values as follows:
      | name              | type         | networks                     | bayPort |
      | EthernetUplinkSet | Ethernet     | all-net-bdd-1, all-net-bdd-2 | X5, X6  |
      | FCUplinkSet       | FibreChannel | all-fc-bdd-1                 | X1      |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
     Then I get an ID

  Scenario: Creation of a new Enclosure Groups
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | enclosure-group-bdd-4-sp |
      | lig          | lig-bdd-4-sp             |
      | stackingMode | Enclosure                |
     When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | enclosure-group-bdd-4-sp  |
      | licensing            | OneView                   |
      | force                | false                     |
      | firmware             | Service Pack for ProLiant |
      | updateFirmware       | EnclosureOnly             |
      | forceInstallFirmware | false                     |
     When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
     Then I get an ID

  Scenario: Creation of a new Server Profile
    Given an instance of Server Profile
      And Resource values as follows:
      | name             | sp-bdd                       |
      | description      | sp-bdd                       |
      | affinity         | Bay                          |
      | macType          | Virtual                      |
      | wwnType          | Virtual                      |
      | serialNumberType | Physical                     |
      | enclosureGroup   | enclosure-group-bdd-4-sp     |
      | networks         | all-net-bdd-1, all-net-bdd-2 |
      | fcNetworks       | all-fc-bdd-1                 |
      | hostOSType       | Windows 2012 / WS2012 R2     |
      | volume           | volume-bdd-storage-volume    |
      And an Enclosure Group Uri
     When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count
