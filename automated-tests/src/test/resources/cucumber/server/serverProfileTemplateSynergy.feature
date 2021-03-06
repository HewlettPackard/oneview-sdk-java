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
Feature: In order to manage Server Profile Templates

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Server Profile Template

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name           | lig-bdd-spt |
      | state          | ACTIVE      |
      | baySet         |           1 |
      | redundancyType | Redundant   |
      | enclosureType  | SY12000     |
      | enclosureIndex |           1 |
      And interconnection values as follows:
      | entries | type                                          |
      |       1 | Virtual Connect SE 40Gb F8 Module for Synergy |
      |       4 | Virtual Connect SE 40Gb F8 Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
  	Given an instance of Enclosure Groups
      And Resource values as follows:
      | name             | enclosure-group-bdd-spt |
      | lig              | lig-bdd-spt             |
      | stackingMode     | Enclosure               |
      | enclosureCount   |                       1 |
      | ipAddressingMode | DHCP                    |
      | entryBayOne      |                       1 |
      | entryBayTwo      |                       4 |
    When Enclosure Group sets Uris
      And OneView runs Enclosure Synergy creation
      And OneView gets Resource by Name
    Then I get an ID
    
  @create
  Scenario: Creation of a new Server Profile Template
    Given Resource values as follows:
      | name             | spt-bdd                 |
      | enclosureGroup   | enclosure-group-bdd-spt |
      | serialNumberType | Virtual                 |
      | macType          | Virtual                 |
      | wwnType          | Virtual                 |
      | affinity         | Bay                     |
      And an Enclosure Group Uri
    When Server Profile Template sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @getAll
  Scenario: Get all Server Profile Templates
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Server Profile Template by Name
    Given name "spt-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Server Profile Template by Id
    Given name "spt-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Server Profile from Template
    Given name "spt-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And gets a Server Profile from Template
    Then I get a Resource Name

  @update
  Scenario: Update a Server Profile Template
    Given name "spt-bdd" for Resource
      And Resource values will be updated as follows:
      | name | spt-bdd_updated |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Server Profile Template
    Given name "spt-bdd_updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
    
  @remove
  Scenario: Remove an Enclosure Group
  	Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-spt" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-spt" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
