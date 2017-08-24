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
Feature: In order to manage Enclosure Groups

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Enclosure Groups

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-enclosure-group |
      | state | ACTIVE                  |
      And interconnection values as follows:
      | entries | type                                 |
      |       1 | HP VC FlexFabric 10Gb/24-Port Module |
      |       2 | HP VC FlexFabric 10Gb/24-Port Module |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given Resource values as follows:
      | name         | enclosure-group-bdd     |
      | lig          | lig-bdd-enclosure-group |
      | stackingMode | Enclosure               |
    When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Enclosure Groups
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Enclosure Group by Name
    Given name "enclosure-group-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Enclosure Group by Id
    Given name "enclosure-group-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Configuration Script of Enclosure Group
    Given name "enclosure-group-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Configuration Script of Enclosure Group
    Then Resource is found

  @update
  Scenario: Update Configuration Script of Enclosure Group
    Given name "enclosure-group-bdd" for Resource
      And a Configuration Script "name=resource" for Enclosure Group
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And Enclosure Group updates its Configuration Script
      And gets Configuration Script of Enclosure Group
    Then Resource is found

  @update
  Scenario: Update an Enclosure Group
    Given name "enclosure-group-bdd" for Resource
      And Resource values will be updated as follows:
      | name | enclosure-group-bdd_updated |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove an Enclosure Group
    Given name "enclosure-group-bdd_updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-enclosure-group" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
