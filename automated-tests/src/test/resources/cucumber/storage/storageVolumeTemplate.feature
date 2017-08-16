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
Feature: In order to manage Storage Volume Templates

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Storage Volume Template

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
      | name                    | fc-network-bdd-svt |
      | fabricType              | FabricAttach       |
      | linkStabilityTime       |                 30 |
      | autoLoginRedistribution | true               |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Update a Storage System
    Given an instance of Storage System
      And Resource values as follows:
      | fc-network | fc-network-bdd-svt |
      | port       | 0:1:1              |
      | domain     | TestDomain         |
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
  Scenario: Creation of a new Storage Volume Template
    Given Resource values as follows:
      | name          | svt-bdd          |
      | state         | Normal           |
      | description   | Example Template |
      | stateReason   | None             |
      | provisionType | Thin             |
      | shareable     | true             |
      | capacity      |     235834383322 |
      And a Storage System Uri
      And a Storage Pool Uri
    When Storage Volume Template sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @getAll
  Scenario: Get all Storage Volume Templates
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Storage Volume Template by Name
    Given name "svt-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Storage Volume Template by Id
    Given name "svt-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Connectable Storage Volume Template
    When gets a Connectable Storage Volume Template
    Then I get a count

  @update
  Scenario: Update a Storage Volume Template
    Given name "svt-bdd" for Resource
      And Resource values will be updated as follows:
      | name | svt-bdd_updated |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource
    
  @remove
  Scenario: Remove a Storage Volume Template
    Given name "svt-bdd_updated" for Resource
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
      And name "fc-network-bdd-svt" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
