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
Feature: In order to manage Server Hardwares

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Server Hardware

  @create
  Scenario: Creation of a new Server Hardware
    Given Resource values as follows:
      | name               | 172.18.6.8 |
      | username           | dcs        |
      | password           | dcs        |
      | licensingIntent    | OneView    |
      | configurationState | Managed    |
      | force              | true       |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Server Hardwares
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Server Hardware by Name
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Server Hardware by Id
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Server Hardware Environment Configuration
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Environment Configuration
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Ilo Sso Url
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Ilo Sso Url
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Java Remote Console Url
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Java Remote Console Url
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Remote Console Url
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Remote Console Url
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Utilization
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets ServerHardware Utilization
    Then Resource is found

  @get @onlyOV3
  Scenario: Get a Server Firmware Inventory By Filter
    Given name "172.18.6.8" for Resource
      And Resource values as follows:
      | name              | 172.18.6.8           |
      | componentName     |                      |
      | componentLocation |                      |
      | componentVersion  |                      |
      | serverName        |                      |
      | serverModel       | ProLiant DL380p Gen8 |
    When OneView gets Resource by Name
      And OneView gets Server Firmware Inventory By Filter
    Then I get a count

  @get @onlyOV3
  Scenario: Get a Server Firmware Inventory
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Firmware Inventory
    Then Resource is found

  @update
  Scenario: Update a Server Hardware Power State
    Given name "172.18.6.8" for Resource
      And Resource values will be updated as follows:
      | powerControl | MomentaryPress |
      | powerState   | On             |
    When OneView gets Resource by Name
      And OneView runs Server Hardware Power State update
    Then I get a success status

  @refresh
  Scenario: Refresh a Server Hardware State
    Given name "172.18.6.8" for Resource
      And Resource values will be updated as follows:
      | hostname | 172.18.6.8 |
      | username | dcs        |
      | password | dcs        |
    When OneView gets Resource by Name
      And OneView runs Server Hardware Refresh State update
    Then I get a success status

  #Cannot be tested with managed servers
  @update @onlyUnmanagedDevices @disabled
  Scenario: Update a Server Hardware Environment Configuration
    Given name "172.18.6.7" for Resource
      And Resource values will be updated as follows:
      | calibratedMaxPower | 2500 |
    When OneView gets Resource by Name
      And OneView runs Environment Configuration update
    Then Resource is found

  @update
  Scenario: Update a Mp Firmware Version
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView runs Mp Firmware Version update
    Then I get a success status

  @patch
  Scenario: Update a Server Hardware by Patch
    Given name "172.18.6.8" for Resource
      And Resource values will be updated as follows:
      | op    | add                                               |
      | path  | /scopeUris/-                                      |
      | value | /rest/scopes/4f383d9a-31cd-4d48-87a3-5d930b5a70d0 |
    When OneView gets Resource by Name
      And OneView runs ServerHardware patch
    Then I get a success status

  @remove
  Scenario: Remove a Server Hardware
    Given name "172.18.6.8" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
