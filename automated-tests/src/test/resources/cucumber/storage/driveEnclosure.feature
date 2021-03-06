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
######## Synergy only ########
Feature: In order to manage Drive Enclosures

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Drive Enclosure

  @getAll
  Scenario: Get all Drive Enclosures
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Drive Enclosure by Name
    Given name "0000A66101, bay 1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Drive Enclosure by Id
    Given name "0000A66101, bay 1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Port Map of Drive Enclosure
    Given name "0000A66101, bay 1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView gets Port Map of Drive Enclosure
    Then I get a Resource Name

  @patch
  Scenario: Update a Drive Enclosure by Patch
    Given name "0000A66101, bay 1" for Resource
      And Resource values will be updated as follows:
      | op    | replace     |
      | path  | /powerState |
      | value | On          |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  @update
  Scenario: Update a Drive Enclosure
    Given Resource values will be updated as follows:
      | name         | "0000A66101, bay 1" |
      | refreshState | RefreshPending      |
    When OneView gets Resource by Name
      And OneView runs Resource update
    Then I get a success status
