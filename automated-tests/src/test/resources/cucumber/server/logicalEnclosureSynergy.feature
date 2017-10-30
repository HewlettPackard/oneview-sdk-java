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
Feature: In order to manage Logical Enclosures

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Enclosure

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    And Resource values as follows:
      | name           | lig-bdd-le |
      | state          | ACTIVE     |
      | baySet         |          2 |
      | redundancyType | Redundant  |
      | enclosureType  | SY12000    |
      | enclosureIndex |         -1 |
      And interconnection values as follows:
      | entries | type                                          |
      |       2 | Virtual Connect SE 16Gb FC Module for Synergy |
      |       5 | Virtual Connect SE 16Gb FC Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name             | enclosure-group-bdd |
      | lig              | lig-bdd-le          |
      | stackingMode     | Enclosure           |
      | enclosureCount   |                   3 |
      | ipAddressingMode | DHCP                |
      | entryBayOne      |                   5 |
      | entryBayTwo      |                   2 |
    When Enclosure Group sets Uris
      And OneView runs Enclosure Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @createMultiple
  Scenario: Creation of a new Logical Enclosure Multiple
    Given an instance of Logical Enclosure Multiple
      And Resource values as follows:
      | name           | logical_enclosure_bdd     |
      | enclosureGroup | enclosure-group-bdd       |
      | enclosureURI1  | 0000A66101                |
      | enclosureURI2  | 0000A66102                |
      | enclosureURI3  | 0000A66103                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs Multiple enclosures creation
      And OneView gets Resource by Name
    Then I get an ID

  @createOne @disabled
  Scenario: Creation of a new Logical Enclosure One
  	Given an instance of Logical Enclosure One
      And Resource values as follows:
      | name           | logical_enclosure_bdd     |
      | enclosureGroup | enclosure-group-bdd       |
      | enclosureURI1  | 0000A66101                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs One enclosures creation
      And OneView gets Resource by Name
    Then I get an ID
  
  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66101" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found

  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66102" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
    
  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66103" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
    
  @getAll
  Scenario: Get all Logical Enclosures
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Enclosure by Name
    Given name "logical_enclosure_bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Logical Enclosure by Id
    Given name "logical_enclosure_bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @patch
  Scenario: Update a Logical Enclosure by Patch
    Given name "logical_enclosure_bdd" for Resource
      And Resource values will be updated as follows:
      | op    | replace       |
      | path  | /firmware     |
      | value | EnclosureOnly |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  @update
  Scenario: Update a Logical Enclosure from Group
    Given name "logical_enclosure_bdd" for Resource
    When OneView gets Resource by Name
      And OneView updates Logical Enclosure from Group
    Then I get a success status

  @create
  Scenario: Creation a Logical Enclosure Support Dump
    Given Resource values as follows:
      | name        | logical_enclosure_bdd |
      | supportDump | testDump01            |
    When OneView gets Resource by Name
      And OneView create a Logical Enclosure Support Dump
    Then I get a success status

  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66101" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found

  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66102" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
    
  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66103" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
    
  @update
  Scenario: Update a Logical Enclosure Configuration
    Given name "logical_enclosure_bdd" for Resource
    When OneView gets Resource by Name
      And OneView updates Logical Enclosure Configuration
    Then I get a success status

  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66101" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found

  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66102" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
    
  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66103" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found
    
  @update
  Scenario: Update a Logical Enclosure
    Given name "logical_enclosure_bdd" for Resource
      And Resource values will be updated as follows:
      | name | logical_enclosure_bdd_Updated |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @refresh
  Scenario: Refresh an Enclosure
    Given an instance of Enclosure
      And name "0000A66101" for Resource
      And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And Oneview runs Enclosure refresh
    Then Resource is found

  @remove
  Scenario: Remove a Logical Enclosure
    Given name "logical_enclosure_bdd_Updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd" for Resource
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
