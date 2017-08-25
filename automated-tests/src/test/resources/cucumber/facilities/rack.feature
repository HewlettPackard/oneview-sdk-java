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
Feature: In order to manage Racks

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Rack

  @create
  Scenario: Creation of a new Rack
    Given Resource values as follows:
      | name | Rack BDD |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Racks
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Rack by Name
    Given name "Rack BDD" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Rack by Id
    Given name "Rack BDD" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Device Topology
    Given name "Rack BDD" for Resource
    When OneView gets Resource by Name
      And OneView gets a Device Topology
    Then Resource is found

  @update
  Scenario: Update a Rack
    Given name "Rack BDD" for Resource
      And Resource values will be updated as follows:
      | thermalLimit | 1000 |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Rack
    Given name "Rack BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @create
  Scenario: Creation of a new Rack
    Given Resource values as follows:
      | name | Rack BDD 2 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @removeByFilter
  Scenario: Remove a Remove a Rack by Filter
    Given Resource values as follows:
      | name | Rack BDD 2 |
    When OneView deletes Rack by Filter
    Then I get a success status
