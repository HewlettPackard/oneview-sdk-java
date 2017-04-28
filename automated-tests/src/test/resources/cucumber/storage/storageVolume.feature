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
  In order to manage Storage Volume

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Storage volume

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
      | name                    | fc-network-bdd-storage-volume |
      | fabricType              | FabricAttach                  |
      | linkStabilityTime       |                            30 |
      | autoLoginRedistribution | true                          |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Update Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | fc-network-bdd-storage-volume |
      | port       | 0:1:1                         |
      | domain     | TestDomain                    |
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
    Given Resource values as follows:
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

  @create
  Scenario: Creation of a new Private Storage Volume
    Given Resource values as follows:
      | name          | volume-bdd-storage-volume-private |
      | description   | Storage Volume BDD                |
      | provisionType | Full                              |
      | shareable     | false                             |
      | capacity      |                       20480000000 |
      And a Storage System Uri
      And a Storage Pool Uri
     When StorageVolume sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count

  @create
  Scenario: Creation a Storage Volume Snapshot
    Given Resource values as follows:
      | volume      | volume-bdd-storage-volume    |
      | name        | {volumeName}_{timestamp}_BDD |
      | description | Custom description           |
      | type        | Snapshot                     |
     When Storage Volume runs a snapshot
     Then I get a count

  @remove
  Scenario: Remove a Storage Volume Snapshot
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And Storage Volume deletes a snapshot
     Then I get a count

  @get
  Scenario: Get all Storage Volume
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Storage Volume by Name
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a Storage Volume by Id
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @get
  Scenario: Get an Attachable Volumes
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Attachable Volumes
     Then I get a count

  @get
  Scenario: Get All Storage Volume Snapshots
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets All Storage Volume Snapshots
     Then I get a count

  @get
  Scenario: Get a Storage Volume Snapshot
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Storage Volume Snapshots
     Then Resource is found

  @get
  Scenario: Get an Extra Managed Storage Volume Paths
    Given name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Extra Managed Storage Volume Paths
     Then I get a count

  @edit
  Scenario: Edit a Storage Volume
    Given name "volume-bdd-storage-volume" for Resource
      And Resource values will be updated as follows:
      | name        | volume-bdd-storage-volume_updated |
      | description | Storage Volume BDD Update         |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
     Then I get previous values in Resource

  @repair
  Scenario: Repair an Extra Managed Storage Volume Path
    Given name "volume-bdd-storage-volume_updated" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And Storage Volume repair an Extra Managed
     Then I get a count

  @remove
  Scenario: Remove a Storage Volume
    Given name "volume-bdd-storage-volume_updated" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove a Storage Volume
    Given name "volume-bdd-storage-volume-private" for Resource
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
      And name "fc-network-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
