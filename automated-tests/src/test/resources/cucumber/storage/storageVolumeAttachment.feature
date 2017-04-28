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
  In order to manage Storage Volume Attachment

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Storage Volume Attachment

  @create
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

  @create
  Scenario: Creation of a new Storage System
    Given an instance of Storage System
     When OneView runs Resource creation
      And OneView lists all
     Then I get a count

  @create
  Scenario: Update Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | all-fc-bdd-1 |
      | port       | 0:1:1        |
      | domain     | TestDomain   |
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
      | name          | storage-volume-attachment-bdd |
      | description   | Storage Volume BDD            |
      | provisionType | Full                          |
      | shareable     | true                          |
      | capacity      |                   20480000000 |
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
      | name  | lig-stva-bdd |
      | state | ACTIVE       |
      And interconnection values as follows:
      | entries | type                             |
      |       1 | HP VC FlexFabric-20/40 F8 Module |
      |       2 | HP VC FlexFabric-20/40 F8 Module |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @lig
  Scenario: Edit a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-stva-bdd" for Resource
      And Uplink values as follows:
      | name        | type         | networks      | bayPort |
      | FCUplinkSet | FibreChannel | stva-fc-bdd-1 | X1      |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
     Then I get an ID

  @lig
  Scenario: Edit a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-stva-bdd" for Resource
      And Uplink values as follows:
      | name         | type         | networks     | bayPort |
      | FCUplinkSet2 | FibreChannel | all-fc-bdd-1 | X2      |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
     Then I get an ID

  @li
  Scenario: Update a Logical Interconnect Internal Networks
    Given an instance of Logical Interconnect
      And Resource values as follows:
      | name      | Encl1-lig-bdd-enclosure |
      | fcNetwork | all-fc-bdd-1            |
     When OneView gets Resource by Name
      And OneView runs Logical Interconnect Internal Networks update
     Then I get a success status

  @li
  Scenario: Update a Logical Interconnect Internal Networks
    Given an instance of Logical Interconnect
      And Resource values as follows:
      | name      | Encl1-lig-bdd-enclosure |
      | fcNetwork | all-fc-bdd-1            |
     When OneView gets Resource by Name
      And OneView runs Logical Interconnect Internal Networks update
     Then I get a success status

  @create
  Scenario: Creation of a new Enclosure Groups
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | enclosure-group-bdd-enclosure |
      | lig          | lig-stva-bdd                  |
      | stackingMode | Enclosure                     |
     When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | enclosure-group-bdd-enclosure |
      | licensing            | OneView                       |
      | force                | false                         |
      | firmware             | Service Pack for ProLiant     |
      | updateFirmware       | EnclosureOnly                 |
      | forceInstallFirmware | false                         |
     When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Creation of a new Server Profile
    Given an instance of Server Profile
      And Resource values as follows:
      | name                   | sp-bdd                        |
      | description            | sp-bdd                        |
      | affinity               | Bay                           |
      | macType                | Virtual                       |
      | wwnType                | Virtual                       |
      | serialNumberType       | Virtual                       |
      | iscsiInitiatorNameType | AutoGenerated                 |
      | enclosureGroup         | enclosure-group-bdd-enclosure |
      | serverHardware         | Encl1, bay 2                  |
      | fcNetworks             | all-fc-bdd-1                  |
      | hostOSType             | Windows 2012 / WS2012 R2      |
      | volume                 | volume                        |
      And an Enclosure Group Uri
     When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count

  @getAll
  Scenario: Get all Storage Volume Attachment
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Storage Volume Attachment by Volume Name
    Given name "volume" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get all Storage Volume Attachment Path
    Given name "volume" for Resource
     When OneView gets Resource by Name
      And OneView gets All Volume Attachment Path
     Then I get a count

  @get
  Scenario: Get Storage Volume Attachment Path
    Given name "volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Volume Attachment Path
     Then I get an ID

  @get
  Scenario: Get Extra Unmanaged Storage Volume Attachments
    Given name "volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Extra Unmanaged Storage Volume Attachments
     Then I get an ID

  @get
  Scenario: Repair Extra Unmanaged Storage Volume Attachments
    Given Resource values as follows:
      | serverProfile | spt |
     When OneView runs Repair Unmanaged Storage Volume Attachments
     Then I get a success status

  @remove
  Scenario: Remove a Server Profile
    Given an instance of Server Profile
      And name "sp-bdd" for Resource
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
  Scenario: Remove an Enclosure Groups
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-4-sp" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect
    Given an instance of Logical Interconnect
      And name "lig-stva-bdd" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove a Storage volume
    Given an instance of Storage volume
      And name "lig-stva-bdd" for Resource
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
      And name "stva-fc-bdd-1" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
