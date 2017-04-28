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
  In order to manage Storage Pool

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Storage Pool

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
      | name                    | fc-network-bdd-storage-pool |
      | fabricType              | FabricAttach                |
      | linkStabilityTime       |                          30 |
      | autoLoginRedistribution | true                        |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create @c700
  Scenario: Update Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | fc-network-bdd-storage-pool |
      | port       | 0:1:1                       |
      | domain     | TestDomain                  |
     When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView runs Resource update
      And OneView lists all
     Then I get a count

  @create @c700
  Scenario: Creation of a new Storage Pool
    Given Resource values as follows:
      | name | FST_CPG1 |
      And a Storage System Uri
     When StoragePool sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count

  @create @synergy
  Scenario: Creation of a new Storage Pool
    Given Resource values as follows:
      | name | ScaleTestingDomain_CPG_35 |
      And a Storage System Uri
     When StoragePool sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count

  @getAll
  Scenario: Get all Storage Pool
    When OneView lists all
    Then I get a count

  @get @c700
  Scenario: Get a Storage Pool by Name
    Given name "FST_CPG1" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get @synergy
  Scenario: Get a Storage Pool by Name
    Given name "ScaleTestingDomain_CPG_35" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get @c700
  Scenario: Get a Storage Pool by Id
    Given name "FST_CPG1" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @get @synergy
  Scenario: Get a Storage Pool by Id
    Given name "ScaleTestingDomain_CPG_35" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @remove @c700
  Scenario: Remove a Storage Pool
    Given name "FST_CPG1" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove @synergy
  Scenario: Remove a Storage Pool
    Given name "ScaleTestingDomain_CPG_3" for Resource
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
      And name "fc-network-bdd-storage-pool" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
