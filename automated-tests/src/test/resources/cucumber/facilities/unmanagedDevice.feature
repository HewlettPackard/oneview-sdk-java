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
Feature: In order to manage Unmanaged Devices

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Unmanaged Device

  @create
  Scenario: Creation of a new Unmanaged Device
    Given Resource values as follows:
      | name        | Unmanaged Device BDD |
      | model       | Procurve 4200VL      |
      | deviceType  | Server               |
      | ipv4Address | 192.168.0.2          |
      | mac         | 68:a5:99:az:71:wc    |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Unmanaged Devices
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Unmanaged Device by Name
    Given name "Unmanaged Device BDD" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Unmanaged Device by Id
    Given name "Unmanaged Device BDD" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get an Environmental Configuration
    Given name "Unmanaged Device BDD" for Resource
    When OneView gets Resource by Name
      And OneView gets an Environmental Configuration
    Then Resource is found

  @update
  Scenario: Update an Unmanaged Device
    Given name "Unmanaged Device BDD" for Resource
      And Resource values will be updated as follows:
      | deviceType | Blade System |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
    Then I get previous values in Resource

  @remove
  Scenario: Remove an Unmanaged Device
    Given name "Unmanaged Device BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @create
  Scenario: Creation of a new Unmanaged Device
    Given Resource values as follows:
      | name        | Unmanaged Device BDD 2 |
      | model       | Procurve 4200VL        |
      | deviceType  | Server                 |
      | ipv4Address | 192.168.0.2            |
      | mac         | 68:a5:99:az:71:wc      |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @removeByFilter
  Scenario: Remove an Unmanaged Device by Filter
    Given Resource values as follows:
      | name | Unmanaged Device BDD 2 |
    When OneView deletes Unmanaged Device by Filter
    Then I get a success status
