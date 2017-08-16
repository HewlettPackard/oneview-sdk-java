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
Feature: In order to manage Logical Switches

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Switch

  @create
  Scenario: Creation of a new Logical Switch Group
    Given an instance of Logical Switch Group
      And Resource values as follows:
      | name          | lsg-bdd          |
      | switchType    | Cisco Nexus 55xx |
      | state         | active           |
      | locationEntry |                1 |
      | locationType  | StackingMemberId |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @create
  Scenario: Creation of a new Logical Switch
    Given Resource values as follows:
      | name            | switch-bdd    |
      | switchGroup     | lsg-bdd       |
      | communityString | public        |
      | snmpPort        |           161 |
      | snmpVersion     | SNMPv1        |
      | managementLevel | BASIC_MANAGED |
      | managementHost  |   172.18.16.1 |
      | user            | dcs           |
      | password        | dcs           |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @getAll
  Scenario: Get all Logical Switches
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Switch by Name
    Given name "switch-bdd" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a Logical Switch by Id
    Given name "switch-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @update
  Scenario: Update a Logical Switch
    Given Resource values as follows:
      | name     | switch-bdd |
      | user     | dcs        |
      | password | dcs        |
     When OneView gets Resource by Name
      And OneView runs Resource update
     Then I get a success status

  @refresh
  Scenario: Refresh a Logical Switch
    Given name "switch-bdd" for Resource
     When OneView gets Resource by Name
      And OneView runs Logical Switch refresh
     Then I get a success status

  @remove
  Scenario: Remove a Logical Switch
    Given name "switch-bdd" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  @remove
  Scenario: Remove a Logical Switch Group
    Given an instance of Logical Switch Group
      And name "lsg-bdd" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
