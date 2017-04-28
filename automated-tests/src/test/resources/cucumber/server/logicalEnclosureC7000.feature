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
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-le |
      | state | ACTIVE     |
      And interconnection values as follows:
      | entries | type                                 |
      |       1 | HP VC FlexFabric 10Gb/24-Port Module |
      |       2 | HP VC FlexFabric 10Gb/24-Port Module |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Groups
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | enclosure-group-bdd-le |
      | lig          | lig-bdd-le             |
      | stackingMode | Enclosure              |
     When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | enclosure-group-bdd-le    |
      | licensing            | OneView                   |
      | force                | false                     |
      | firmware             | Service Pack for ProLiant |
      | updateFirmware       | EnclosureOnly             |
      | forceInstallFirmware | false                     |
     When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
     Then I get an ID

  @getAll
  Scenario: Get all Logical Enclosure
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Enclosure by Name
    Given name "Encl1" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a Logical Enclosure by Id
    Given name "Encl1" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @get
  Scenario: Get a Logical Enclosure Configuration Script
    Given name "Encl1" for Resource
     When OneView gets Resource by Name
      And OneView gets Logical Enclosure Configuration Script
     Then Resource is found

  @update
  Scenario: Update a Logical Enclosure from Group
    Given name "Encl1" for Resource
     When OneView gets Resource by Name
      And OneView updates Logical Enclosure from Group
     Then I get a success status

  @update
  Scenario: Update a Logical Enclosure Configuration
    Given name "Encl1" for Resource
     When OneView gets Resource by Name
      And OneView updates Logical Enclosure Configuration
     Then I get a success status

  @update
  Scenario: Update a Logical Enclosure Configuration Script
    Given Resource values will be updated as follows:
      | name   | Encl1                            |
      | script | \\"name=Enclosure_test_script\\" |
     When OneView gets Resource by Name
      And OneView updates Logical Enclosure Configuration Script
     Then I get a success status

  @update
  Scenario: Creation a Logical Enclosure Support Dump
    Given Resource values as follows:
      | name        | Encl1      |
      | supportDump | testDump01 |
     When OneView gets Resource by Name
      And OneView create a Logical Enclosure Support Dump
     Then I get a success status

  @update
  Scenario: Update by Patch
    Given name "Encl1" for Resource
      And Resource values will be updated as follows:
      | op    | replace       |
      | path  | /firmware     |
      | value | EnclosureOnly |
     When OneView gets Resource by Name
      And OneView runs Logical Enclosure patch
     Then I get a success status

  @update
  Scenario: Update a Logical Enclosure
    Given name "Encl1" for Resource
      And Resource values will be updated as follows:
      | name | Encl1_Updated |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
     Then I get previous values in Resource

  @oneView3 @synergy
  Scenario: Creation of a new Logical Enclosure
    Given Resource values as follows:
      | name           | Logical_Encl1                 |
      | enclosureGroup | enclosure-group-bdd-enclosure |
      | enclosure      | Encl1                         |
      | firmware       | Service Pack for ProLiant     |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @oneView3 @synergy
  Scenario: Remove a Logical Enclosure
    Given name "Logical_Encl1" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove an Enclosure
    Given an instance of Enclosure
     When OneView gets Enclosure Name
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Groups
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-le" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-le" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
