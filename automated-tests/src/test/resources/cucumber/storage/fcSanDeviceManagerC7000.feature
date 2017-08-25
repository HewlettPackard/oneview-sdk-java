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
Feature: In order to manage FC SAN Device Managers

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Fc San Device Manager

  @create
  Scenario: Creation of a new FC SAN Provider
    Given an instance of Fc San Provider
      And Resource values as follows:
      | name          | 172.18.15.1             |
      | hostname      | Host                    |
      | port          | Port                    |
      | provider      | Brocade Network Advisor |
      | use_ssl       | UseSsl                  |
      | use_ssl_value | true                    |
      | user          | dcs                     |
      | password      | dcs                     |
    When OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @getAll
  Scenario: Get all FC SAN Device Managers
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a FC SAN Device Manager by Name
    Given name "172.18.15.1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a FC SAN Device Manager by Id
    Given name "172.18.15.1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @update
  Scenario: Update a FC SAN Device Manager
    Given Resource values as follows:
      | name         |    172.18.15.1 |
      | password     | dcs            |
      | refreshState | RefreshPending |
    When OneView gets Resource by Name
      And OneView runs Resource update
    Then I get a success status

  @remove
  Scenario: Remove a FC SAN Device Manager
    Given name "172.18.15.1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
