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
  In order to manage Power Delivery Device

  Background: 
    Given an instance of OneView
    And OneView credentials located in "src/test/resources/oneView.properties"
    And an instance of Power Delivery Device

  @create
  Scenario: Creation of a new Power Delivery Device
    Given Resource values as follows:
      | name          | Power Delivery Device BDD |
      | model         | Model BDD                 |
      | ratedCapacity |                        40 |
      | lineVoltage   |                        40 |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @createByDiscover
  Scenario: Creation of a new Power Delivery Device by Discover
    Given Resource values as follows:
      | hostname | 172.18.8.11 |
      | username | dcs         |
      | password | dcs         |
      | force    | true        |
    When OneView runs creation of Power Delivery Device by Discover
    Then I get a success status

  @getAll
  Scenario: Get all Power Delivery Device
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Power Delivery Device by Name
    Given name "Power Delivery Device BDD" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Power Delivery Device by Id
    Given name "Power Delivery Device BDD" for Resource
    When OneView gets Resource by Name
    And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Power State of Power Delivery Device
    Given name "172.18.8.11, PDU 1, L6,Outlet1" for Resource
    When OneView gets Resource by Name
    And OneView gets a Power State of Power Delivery Device
    Then Resource is found

  @get
  Scenario: Get an Uid State of Power Delivery Device
    Given name "172.18.8.11, PDU 1, L6,Outlet1" for Resource
    When OneView gets Resource by Name
    And OneView gets an Uid State of Power Delivery Device
    Then Resource is found

  @get
  Scenario: Get Power Delivery Device Utilization
    Given name "172.18.8.11, PDU 1, L6,Outlet1" for Resource
    When OneView gets Resource by Name
    And OneView gets Power Delivery Device Utilization
    Then Resource is found

  @updatePowerState
  Scenario: Update Power State of a Power Delivery Device
    Given name "172.18.8.11, PDU 1, L6,Outlet1" for Resource
    And Resource values will be updated as follows:
      | powerState | On |
    When OneView gets Resource by Name
    And OneView runs update Power State
    Then I get a success status

  @updateRefresh
  Scenario: Update Refresh State of a Power Delivery Device
    Given name "172.18.8.11, PDU 1, L6,Outlet1" for Resource
    And Resource values will be updated as follows:
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
    And OneView runs update Refresh State
    Then I get a success status

  @updateUid
  Scenario: Update Uid State of a Power Delivery Device
    Given name "172.18.8.11, PDU 1, L6,Outlet1" for Resource
    And Resource values will be updated as follows:
      | uidState | On |
    When OneView gets Resource by Name
    And OneView runs update Uid State
    Then I get a success status

  @update
  Scenario: Update a Power Delivery Device
    Given name "Power Delivery Device BDD" for Resource
    And Resource values will be updated as follows:
      | name  | Power Delivery Device BDD Update |
      | model | Model BDD Update                 |
    When OneView gets Resource by Name
    And OneView runs Resource update
    And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove a Power Delivery Device
    Given name "Power Delivery Device BDD Update" for Resource
    When OneView gets Resource by Name
    And OneView deletes the Resource
    And OneView gets Resource by ID
    Then Resource is not found

  @removeByFilter
  Scenario: Remove a Power Delivery Device by Filter
    Given Resource values as follows:
      | name | Power Delivery Device BDD |
    When OneView deletes Power Delivery Device by Filter
    Then I get a success status

  @removeSynchronously
  Scenario: Remove a Power Delivery Device Synchronously
    Given name "Power Delivery Device BDD" for Resource
    When OneView gets Resource by Name
    And OneView deletes Power Delivery Device Synchronously
    Then I get a success status
