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
Feature: In order to manage Interconnects

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Interconnect

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-interconnect |
      | state | ACTIVE               |
      And interconnection values as follows:
      | entries | type                             |
      |       1 | HP VC FlexFabric-20/40 F8 Module |
      |       2 | HP VC FlexFabric-20/40 F8 Module |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | eg-bdd-interconnect  |
      | lig          | lig-bdd-interconnect |
      | stackingMode | Enclosure            |
    When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | eg-bdd-interconnect       |
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
  Scenario: Get all Interconnects
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Interconnect by Name
    Given name "Encl1, interconnect 2" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Interconnect by Id
    Given name "Encl1, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Interconnect Statistics
    Given name "Encl1, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView gets Interconnect Statistics
    Then I get a Resource Name

  @get
  Scenario: Get Interconnect Port Statistics
    Given Resource values as follows:
      | name    | Encl1, interconnect 2 |
      | port    | d1                    |
      | subport |                     1 |
    When OneView gets Resource by Name
      And OneView gets Interconnect Port Statistics
    Then Resource is found

  @get
  Scenario: Get Interconnect Sub Port Statistics
    Given Resource values as follows:
      | name    | Encl1, interconnect 2 |
      | port    | d1                    |
      | subport |                     1 |
    When OneView gets Resource by Name
      And OneView gets Interconnect Sub Port Statistics
    Then Resource is found

  @get
  Scenario: Get Interconnect Named Servers
    Given name "Encl1, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView gets Interconnect Named Servers
    Then Resource is found

  @patch
  Scenario: Update an Interconnect by Patch
    Given name "Encl1, interconnect 2" for Resource
      And Resource values will be updated as follows:
      | op    | replace     |
      | path  | /powerState |
      | value | Off         |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  @reset
  Scenario: Reset Interconnect Port Protection
    Given name "Encl1, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView runs Interconnect Port Protection reset
    Then I get a success status

  @update
  Scenario: Update an Interconnect Port
    Given name "Encl1, interconnect 2" for Resource
      And Resource values will be updated as follows:
      | portEnable | false |
    When OneView gets Resource by Name
      And OneView runs update interconnect port
    Then Resource is found

  @update
  Scenario: Update Interconnect Ports
    Given name "Encl1, interconnect 2" for Resource
      And Resource values will be updated as follows:
      | portEnable | false |
    When OneView gets Resource by Name
      And OneView runs update interconnect ports
    Then Resource is found

  @remove
  Scenario: Remove an Enclosure
    Given an instance of Enclosure
      And name "Encl1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "eg-bdd-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-interconnect" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
