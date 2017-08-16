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
Feature: In order to manage Logical Downlinks

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Downlink

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name           | lig-bdd-2 |
      | state          | ACTIVE    |
      | baySet         |         1 |
      | redundancyType | Redundant |
      | enclosureType  | SY12000   |
      | enclosureIndex |         1 |
      And interconnection values as follows:
      | entries | type                                          |
      |       1 | Virtual Connect SE 40Gb F8 Module for Synergy |
      |       4 | Virtual Connect SE 40Gb F8 Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
      And OneView gets Resource by Name
    Then I get an ID
    
  @getAll
  Scenario: Get all Logical Downlinks
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Downlink by Name
    When OneView gets Name of First Logical Downlink
      And OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Logical Downlink by Id
    When OneView gets Name of First Logical Downlink
      And OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get all Logical Downlink Without Ethernet
    When OneView lists all Logical Downlink Without Ethernet
    Then I get a count

  @get
  Scenario: Get a Logical Downlink by Id Without Ethernet
    When OneView gets Name of First Logical Downlink
      And OneView gets Resource by Name
      And OneView gets Logical Downlink by ID Without Ethernet
    Then I get a Resource Name

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-2" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
    