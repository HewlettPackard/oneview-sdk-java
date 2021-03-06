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
Feature: In order to destroy an environment

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"

  @remove
  Scenario: Remove a Server Profile
    Given an instance of Server Profile
      And name "sp-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Server Profile Template
    Given an instance of Server Profile Template
      And name "spt-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Enclosure
    Given an instance of Logical Enclosure
      And name "logical_enclosure_bdd-all" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-all" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-all" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Fc San Device Manager
    Given an instance of Fc San Device Manager
      And name "172.18.20.1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Unmanaged Device
    Given an instance of Unmanaged Device
      And name "Unmanaged Device BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Rack
    Given an instance of Rack
      And name "Rack BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Power Delivery Device
    Given an instance of Power Delivery Device
      And name "Power Delivery Device BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Data Center
    Given an instance of Data Center
      And name "Data Center BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Volume Template
    Given an instance of Storage Volume Template
      And name "all-svt-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Volume
    Given an instance of Storage volume
      And name "volume-bdd-storage-volume" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage Pool
    Given an instance of Storage System
      And a Storage System Uri
    Given an instance of Storage Pool
      And name "FST_CPG1" for Resource
    When StoragePool sets Uris
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Storage System
    Given an instance of Storage System
    When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a Network Set
    Given an instance of Network Set
      And name "all-network-set-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FCoE Network
    Given an instance of FCoE Network
      And name "all-fcoe-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "all-net-bdd-1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "all-net-bdd-2" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "all-fc-bdd-1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "all-fc-bdd-2" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Alert Change Log
    Given an instance of Alert
    When OneView deletes Alert Change Log
    Then I get a success status

  @remove
  Scenario: Remove an Alert By Filter
    Given an instance of Alert
      And Resource values as follows:
      | alertUrgency | Medium |
    When OneView deletes Alert by filter
    Then I get a success status

  @remove
  Scenario: Remove an Alert
    Given an instance of Alert
    When OneView gets Id of First Alert
      And OneView deletes the Resource
    Then I get a success status
