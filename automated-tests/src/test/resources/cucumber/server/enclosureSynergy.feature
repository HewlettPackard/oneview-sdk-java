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
Feature: In order to manage Enclosures

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Enclosure

  @getAll
  Scenario: Get all Enclosures
    When OneView lists all
    Then I get a count

  @create
  Scenario: Add a Remote Enclosure Synergy
    Given Resource values as follows:
      | hostname | fe80::2:0:9:1%eth2 |
    When OneView runs Remote Enclosure creation

  @get
  Scenario: Get an Enclosure by Name
    Given name "0000A66101" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Enclosure by Id
    Given name "0000A66101" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Enclosure Environmental Configuration
    Given name "0000A66101" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Enclosure Environmental Configuration
    Then Resource is found

  @get
  Scenario: Get Enclosure Utilization
    Given name "0000A66101" for Resource
      And OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Enclosure Utilization
    Then Resource is found

  @patch
  Scenario: Update an Enclosure by Patch
    Given name "0000A66101" for Resource
      And Resource values will be updated as follows:
      | op    | replace              |
      | path  | /name                |
      | value | update-enclosure-bdd |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  #Returns to default name
  @update
  Scenario: Update an Enclosure
    Given Resource values will be updated as follows:
      | name | 0000A66101 |
    When name "update-enclosure-bdd" for Resource
      And OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @refresh
  Scenario: Refresh an Enclosure
    Given name "0000A66101" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
