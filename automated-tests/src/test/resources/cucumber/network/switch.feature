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
######## C7000 only ########
Feature: In order to manage Switches

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Switch

  @create
  Scenario: Creation of a new Logical Switch Group
    Given an instance of Logical Switch Group
      And Resource values as follows:
      | name          | lsg-bdd-switch   |
      | switchType    | Cisco Nexus 55xx |
      | state         | active           |
      | locationEntry |                1 |
      | locationType  | StackingMemberId |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Logical Switch
    Given an instance of Logical Switch
      And Resource values as follows:
      | name            | ls-bdd-switch  |
      | switchGroup     | lsg-bdd-switch |
      | communityString | public         |
      | snmpPort        |            161 |
      | snmpVersion     | SNMPv1         |
      | managementLevel | BASIC_MANAGED  |
      | managementHost  | 172.18.16.1    |
      | user            | dcs            |
      | password        | dcs            |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create @onlyOV1.2
  Scenario: Creation of a new Switch
    Given Resource values as follows:
      | name     | 172.18.16.1 |
      | user     | dcs         |
      | password | dcs         |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Switches
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Switch by Name
    Given name "172.18.16.1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Switch by Id
    Given name "172.18.16.1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Switch Environmental Configuration
    Given name "172.18.16.1" for Resource
    When OneView gets Resource by Name
      And OneView gets Switch Environmental Configuration
    Then Resource is found

  @get @onlyOV2
  Scenario: Get a Switch Statistics
    Given name "172.18.16.1" for Resource
    When OneView gets Resource by Name
      And OneView gets Switch Statistics
    Then Resource is found

  #Disabled because endpoint returns nothing
  @get @onlyOV2 @disabled
  Scenario: Get a Switch Port Statistics
    Given Resource values as follows:
      | name | 172.18.16.1 |
      | port |         1.1 |
    When OneView gets Resource by Name
      And OneView gets Switch Port Statistics
    Then Resource is found

  @update @onlyOV1.2
  Scenario: Update a Switch
    Given Resource values as follows:
      | name     | 172.18.16.1 |
      | user     | dcs         |
      | password | dcs         |
    When OneView gets Resource by Name
      And OneView runs Resource update
    Then I get a success status

  #Disabled because of error returned by endpoint: "CRM_UNMANAGED_TOR_PORTS_CAN_NOT_BE_EDITED:
  #Enabling or disabling of unmanaged top of rack switch ports 1.1 is not allowed."
  @update @onlyOV3 @disabled
  Scenario: Update Switch Ports
    Given Resource values as follows:
      | name | 172.18.16.1 |
      | port |         1.1 |
    When OneView gets Resource by Name
      And OneView runsSwitch Port update
    Then I get a success status

  @refresh @onlyOV1.2
  Scenario: Refresh a Switch
    Given name "172.18.16.1" for Resource
    When OneView gets Resource by Name
      And OneView runs Switch refresh
    Then I get a success status

  @remove @onlyOV1.2
  Scenario: Remove a Switch
    Given name "172.18.16.1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Switch
    Given an instance of Logical Switch
      And name "ls-bdd-switch" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Switch Group
    Given an instance of Logical Switch Group
      And name "lsg-bdd-switch" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
