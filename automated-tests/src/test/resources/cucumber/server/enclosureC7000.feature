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
  In order to manage Enclosure

  Background: 
    Given an instance of OneView
    And OneView credentials located in "src/test/resources/oneView.properties"
    And an instance of Enclosure

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    And Resource values as follows:
      | name  | lig-bdd-enclosure |
      | state | ACTIVE            |
    And interconnection values as follows:
      | entries | type                             |
      |       1 | HP VC FlexFabric-20/40 F8 Module |
      |       2 | HP VC FlexFabric-20/40 F8 Module |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Groups
    Given an instance of Enclosure Groups
    And Resource values as follows:
      | name         | enclosure-group-bdd-enclosure |
      | lig          | lig-bdd-enclosure             |
      | stackingMode | Enclosure                     |
    When Enclosure Group sets Uris
    And OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given Resource values as follows:
      | enclosureGroup       | enclosure-group-bdd-enclosure |
      | licensing            | OneView                       |
      | force                | false                         |
      | firmware             | Service Pack for ProLiant     |
      | updateFirmware       | EnclosureOnly                 |
      | forceInstallFirmware | false                         |
    When OneView runs Resource creation
    And OneView gets Enclosure Name
    And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Enclosure
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Enclosure by Name
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Enclosure by Id
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Enclosure Environmental Configuration
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView gets Resource by ID
    And gets Enclosure Environmental Configuration
    Then Resource is found

  @get
  Scenario: Get Enclosure Script Configuration
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView gets Resource by ID
    And gets Enclosure Script Configuration
    Then Resource is found

  @get
  Scenario: Get Enclosure Active Oa Sso Url
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView gets Resource by ID
    And gets Enclosure Active Oa Sso Url
    Then Resource is found

  @get
  Scenario: Get Enclosure Utilization
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView gets Resource by ID
    And gets Enclosure Utilization
    Then Resource is found

  @edit
  Scenario: Edit an Enclosure
    Given Resource values will be updated as follows:
      | name | enclosure-bdd_updated |
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView runs Resource update
    And OneView gets Resource properties
    Then I get previous values in Resource

  @edit
  Scenario: Edit an Enclosure
    Given Resource values will be updated as follows:
      | name | enclosure-bdd |
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView runs Resource update
    And OneView gets Resource properties
    Then I get previous values in Resource

  @refresh
  Scenario: Refresh an Enclosure
    Given name "enclosure-bdd" for Resource
    And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
    And Oneview runs Enclosure refresh
    Then Resource is found

  @remove
  Scenario: Remove an Enclosure
    When OneView gets Enclosure Name
    And OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
    And name "enclosure-group-bdd-enclosure" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    And name "lig-bdd-enclosure" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found
