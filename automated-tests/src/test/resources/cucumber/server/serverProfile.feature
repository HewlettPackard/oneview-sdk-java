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
  In order to manage Server Profile

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Server Profile

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-4-sp |
      | state | ACTIVE       |
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
      | name         | enclosure-group-bdd-4-sp |
      | lig          | lig-bdd-4-sp             |
      | stackingMode | Enclosure                |
     When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | enclosure-group-bdd-4-sp  |
      | licensing            | OneView                   |
      | force                | false                     |
      | firmware             | Service Pack for ProLiant |
      | updateFirmware       | EnclosureOnly             |
      | forceInstallFirmware | false                     |
     When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Creation of a new Server Profile
    Given Resource values as follows:
      | name             | sp-bdd                    |
      | description      | sp-bdd                    |
      | firmware         | Service Pack for ProLiant |
      | affinity         | Bay                       |
      | macType          | UserDefined               |
      | wwnType          | UserDefined               |
      | serialNumberType | Physical                  |
      | enclosureGroup   | enclosure-group-bdd-4-sp  |
      And an Enclosure Group Uri
     When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
     Then I get a count

  @getAll
  Scenario: Get all Server Profile
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Server Profile by Name
    Given name "sp-bdd" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a Server Profile by Id
    Given name "sp-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @get
  Scenario: Get Available Servers For ServerProfile
    Given name "sp-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Available Servers For ServerProfile
     Then I get a count

  @get
  Scenario: Get Available Servers For ServerProfile using Profile
    Given name "sp-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets Available Servers For ServerProfile using Profile
     Then I get a count

  @get
  Scenario: Get ServerProfile Compliance Preview
    Given name "sp-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets ServerProfile Compliance Preview
     Then Resource is found

  @get
  Scenario: Get ServerProfile Message
    Given name "sp-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets ServerProfile Message
     Then Resource is found

  @edit
  Scenario: Edit a Server Profile
    Given name "sp-bdd" for Resource
      And Resource values will be updated as follows:
      | name | sp-bdd_updated |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
     Then I get previous values in Resource

  @remove
  Scenario: Remove a Server Profile
    Given name "sp-bdd_updated" for Resource
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
      And name "enclosure-group-bdd-4-sp" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-4-sp" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
