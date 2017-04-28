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
  In order to manage FC Networks

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of FC Network

  @create
  Scenario: Creation of a new FC Network
    Given Resource values as follows:
      | name                    | fc-network-bdd |
      | fabricType              | FabricAttach   |
      | linkStabilityTime       |             30 |
      | autoLoginRedistribution | true           |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @getAll
  Scenario: Get all FC Networks
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a FC Network by Name
    Given name "fc-network-bdd" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a FC Network by Id
    Given name "fc-network-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @edit
  Scenario: Edit a FC Network
    Given name "fc-network-bdd" for Resource
      And Resource values will be updated as follows:
      | name                    | fc-network-bdd_updated |
      | linkStabilityTime       |                     50 |
      | autoLoginRedistribution | true                   |
     When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource properties
     Then I get previous values in Resource

  @remove
  Scenario: Remove a FC Network
    Given name "fc-network-bdd_updated" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
