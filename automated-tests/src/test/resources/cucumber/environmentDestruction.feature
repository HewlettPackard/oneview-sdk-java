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
  In order to create an environment

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"

  Scenario: Remove a Server Profile
    Given an instance of Server Profile
      And name "sp-bdd" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove an Enclosure
    Given an instance of Enclosure
     When OneView gets Enclosure Name
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove an Enclosure Groups
    Given an instance of Enclosure Groups
      And name "enclosure-group-bdd-4-sp" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And name "lig-bdd-4-sp" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove a Storage Volume
    Given an instance of Storage volume
      And name "volume-bdd-storage-volume" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

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

  Scenario: Remove a Storage System
    Given an instance of Storage System
     When OneView gets Storage Name
      And OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "all-net-bdd-1" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "all-net-bdd-2" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "all-fc-bdd-1" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found

  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "all-fc-bdd-2" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
