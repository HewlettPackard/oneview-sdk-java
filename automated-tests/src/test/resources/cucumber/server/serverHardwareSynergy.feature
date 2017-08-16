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

  @getAll
  Scenario: Get all Server Hardwares
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Server Hardware by Name
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Server Hardware by Id
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Server Hardware Environment Configuration
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Environment Configuration
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Ilo Sso Url
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Ilo Sso Url
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Java Remote Console Url
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Java Remote Console Url
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Remote Console Url
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Hardware Remote Console Url
    Then Resource is found

  @get
  Scenario: Get a Server Hardware Utilization
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets ServerHardware Utilization
    Then Resource is found

  @onlyOV3
  Scenario: Get a Server Firmware Inventory By Filter
    Given name "0000A66103, bay 3" for Resource
      And Resource values as follows:
      | name              | 0000A66103, bay 3                   |
      | componentName     | HP ProLiant System ROM              |
      | componentLocation | System Board                        |
      | componentVersion  | I39 v1.30 08/26/2014                |
      | serverName        | 0000A66103, bay 3                   |
      | serverModel       | HPE Synergy 660 Gen9 Compute Module |
    When OneView gets Resource by Name
      And OneView gets Server Firmware Inventory By Filter
    Then I get a count

  @onlyOV3
  Scenario: Get a Server Firmware Inventory
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView gets Server Firmware Inventory
    Then Resource is found

  @update
  Scenario: Update a Server Hardware Power State
    Given name "0000A66101, bay 4" for Resource
      And Resource values will be updated as follows:
      | powerControl | MomentaryPress |
      | powerState   | On             |
    When OneView gets Resource by Name
      And OneView runs Server Hardware Power State update
    Then I get a success status

  @update
  Scenario: Update a Server Hardware Power State
    Given name "0000A66101, bay 4" for Resource
      And Resource values will be updated as follows:
      | hostname | 0000A66101, bay 4 |
      | username | dcs               |
      | password | dcs               |
    When OneView gets Resource by Name
      And OneView runs Server Hardware Refresh State update
    Then I get a success status

  @onlyUnmanagedDevices
  Scenario: Update an Environment Configuration
    Given name "0000A66101, bay 4" for Resource
      And Resource values will be updated as follows:
      | calibratedMaxPower | 2500 |
    When OneView gets Resource by Name
      And OneView runs Environment Configuration update
    Then Resource is found

  @update
  Scenario: Update a Mp Firmware Version
    Given name "0000A66101, bay 4" for Resource
    When OneView gets Resource by Name
      And OneView runs Mp Firmware Version update
    Then I get a success status

  @patch
  Scenario: Update a Server Hardware by Patch
    Given name "0000A66101, bay 4" for Resource
      And Resource values will be updated as follows:
      | op    | replace  |
      | path  | /mpState |
      | value | Reset    |
    When OneView gets Resource by Name
      And OneView runs ServerHardware patch
    Then I get a success status
