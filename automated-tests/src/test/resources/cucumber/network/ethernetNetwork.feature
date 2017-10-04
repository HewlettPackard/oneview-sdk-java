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
Feature: In order to manage Ethernet Networks

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Ethernet Network

  @create
  Scenario: Creation of a new Ethernet Network
    Given Resource values as follows:
      | name         | network-bdd |
      | ethernetType | Tagged      |
      | vlanId       |         300 |
      | purpose      | General     |
      | private      | false       |
      | smartLink    | true        |
      And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Ethernet Networks
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Ethernet Network by Name
    Given name "network-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Ethernet Network by Id
    Given name "network-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @list
  Scenario: List Associated Profile for Network
    Given name "network-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView gets the Associated Profile List
    Then I get a count

  @list
  Scenario: List Associated Profile Uplink Groups for Network
    Given name "network-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
      And OneView gets the Associated Uplink Groups
    Then I get a count

  @create
  Scenario: Creation of a new Scope
    Given an instance of Scope
      And Resource values as follows:
      | name        | BDD Scope             |
      | description | BDD Scope description |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @patch
  Scenario: Update an Ethernet Network by Patch
    Given name "network-bdd" for Resource
      And Resource values will be updated as follows:
      | op     | replace      |
      | path   | /scopeUris   |
      | value  | BDD Scope    |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  @update
  Scenario: Update an Ethernet Network
    Given name "network-bdd" for Resource
      And Resource values will be updated as follows:
      | name                | network-bdd_updated |
      | ethernetNetworkType | Tagged              |
      | purpose             | VMMigration         |
      | privateNetwork      | false               |
      | smartLink           | true                |
    When OneView gets Resource by Name
      And OneView runs Resource update
    Then I get a success status

  @remove
  Scenario: Remove an Ethernet Network
    Given name "network-bdd_updated" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Scope
    Given an instance of Scope
      And name "BDD Scope" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
