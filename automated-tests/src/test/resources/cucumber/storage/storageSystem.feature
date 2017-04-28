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
  In order to manage Storage System

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Storage System

  @create
  Scenario: Creation of a new Storage System
    When OneView runs Resource creation
     And OneView lists all
    Then I get a count

  @getAll
  Scenario: Get all Storage System
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Storage System by Name
    When OneView gets Storage Name
     And OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Storage System by Id
    When OneView gets Storage Name
     And OneView gets Resource by Name
     And OneView gets Resource by ID
    Then I get a Resource Name

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-network-bdd-storage-sys |
      | fabricType              | FabricAttach               |
      | linkStabilityTime       |                         30 |
      | autoLoginRedistribution | true                       |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Update Storage System
    Given Resource values as follows:
      | fc-network | fc-network-bdd-storage-sys |
      | port       | 0:1:1                      |
      | domain     | TestDomain                 |
     When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView runs Resource update
      And OneView lists all
     Then I get a count

  @get
  Scenario: Get a Storage System Managed Ports
    When OneView gets Storage Name
     And OneView gets Resource by Name
     And OneView gets Resource by ID
     And gets Storage System Managed Ports
    Then I get a count

  @get
  Scenario: Get a Storage System Managed Port
    When OneView gets Storage Name
     And OneView gets Resource by Name
     And OneView gets Resource by ID
     And gets Storage System Managed Port
    Then Resource is found

  @get
  Scenario: Get a Storage System Host Types
    When gets Storage System Host Types
    Then I get a count

  @remove
  Scenario: Remove a Storage System
    When OneView gets Storage Name
     And OneView gets Resource by Name
     And OneView deletes the Resource
     And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-network-bdd-storage-sys" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
