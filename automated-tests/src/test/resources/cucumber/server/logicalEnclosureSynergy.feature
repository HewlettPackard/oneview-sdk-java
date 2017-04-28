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
  In order to manage Logical Enclosure

  Background: 
    Given an instance of OneView
    And OneView credentials located in "src/test/resources/oneView.properties"
    And an instance of Logical Enclosure

  @create
  Scenario: Creation of a new Logical Enclosure
    Given Resource values as follows:
      | name           | multiple_encl_le_bdd      |
      | enclosureGroup | EnclGroup1                |
      | enclosureURI1  | 0000A66101                |
      | enclosureURI2  | 0000A66102                |
      | enclosureURI3  | 0000A66103                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs Multiple  enclosures creation
    And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Logical Enclosure
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Enclosure by Name
    Given name "multiple_encl_le_bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Logical Enclosure by Id
    Given name "multiple_encl_le_bdd" for Resource
    When OneView gets Resource by Name
    And OneView gets Resource by ID
    Then I get a Resource Name

  @update
  Scenario: Update by Patch
    Given name "multiple_encl_le_bdd" for Resource
    And Resource values will be updated as follows:
      | op    | replace       |
      | path  | /firmware     |
      | value | EnclosureOnly |
    When OneView gets Resource by Name
    And OneView runs Logical Enclosure patch
    Then I get a success status

  @update
  Scenario: Update a Logical Enclosure from Group
    Given name "multiple_encl_le_bdd" for Resource
    When OneView gets Resource by Name
    And OneView updates Logical Enclosure from Group
    Then I get a success status

  @update
  Scenario: Creation a Logical Enclosure Support Dump
    Given Resource values as follows:
      | name        | multiple_encl_le_bdd |
      | supportDump | testDump01           |
    When OneView gets Resource by Name
    And OneView create a Logical Enclosure Support Dump
    Then I get a success status

  @update
  Scenario: Update a Logical Enclosure Configuration
    Given name "multiple_encl_le_bdd" for Resource
    When OneView gets Resource by Name
    And OneView updates Logical Enclosure Configuration
    Then I get a success status

  @update
  Scenario: Update a Logical Enclosure
    Given name "multiple_encl_le_bdd" for Resource
    And Resource values will be updated as follows:
      | name | multiple_encl_le_bdd_Updated |
    When OneView gets Resource by Name
    And OneView runs Resource update
    And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Logical Enclosure
    Given name "multiple_encl_le_bdd_Updated" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found
