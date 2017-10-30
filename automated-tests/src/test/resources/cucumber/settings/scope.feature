################################################################################
# (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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
Feature: In order to manage Scopes

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Scope

  @create
  Scenario: Creation of a new Scope
    Given Resource values as follows:
      | name        | BDD Scope             |
      | description | BDD Scope description |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Scopes
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Scope by Name
    Given name "BDD Scope" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Scope by Id
    Given name "BDD Scope" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-network-bdd |
      | fabricType              | FabricAttach   |
      | linkStabilityTime       |             30 |
      | autoLoginRedistribution | true           |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @patch
  Scenario: Update a Scope by Patch
    Given name "BDD Scope" for Resource
      And Resource values will be updated as follows:
      | resourceName | fc-network-bdd |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  @update
  Scenario: Update a Scope
    Given name "BDD Scope" for Resource
      And Resource values will be updated as follows:
      | name        | BDD Scope Updated             |
      | description | BDD Scope Updated description |
    When OneView gets Resource by Name
      And OneView runs Resource update
    Then I get a success status

  @remove
  Scenario: Remove a Scope
    Given name "BDD Scope Updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-network-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
