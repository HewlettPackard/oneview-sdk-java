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
Feature: In order to manage Data Centers

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Data Center

  @create
  Scenario: Creation of a new Data Center
    Given Resource values as follows:
      | name                    | Data Center BDD |
      | coolingCapacity         |               5 |
      | costPerKilowattHour     |             0.5 |
      | currency                | USD             |
      | deratingType            | NaJp            |
      | deratingPercentage      |            20.0 |
      | defaultPowerLineVoltage |             220 |
      | coolingMultiplier       |             1.5 |
      | width                   |            4000 |
      | depth                   |            5000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Data Centers
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Data Center by Name
    Given name "Data Center BDD" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Data Center by Id
    Given name "Data Center BDD" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Data Center Visual Content
    Given name "Data Center BDD" for Resource
    When OneView gets Resource by Name
      And OneView gets Data Center Visual Content
    Then Resource is found

  @update
  Scenario: Update a Data Center
    Given name "Data Center BDD" for Resource
      And Resource values will be updated as follows:
      | currency | BRL |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Data Center
    Given name "Data Center BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @removeByFilter
  Scenario: Remove a Data Center by Filter
    Given Resource values as follows:
      | name | Data Center BDD |
    When OneView deletes Data Center by Filter
    Then I get a success status
