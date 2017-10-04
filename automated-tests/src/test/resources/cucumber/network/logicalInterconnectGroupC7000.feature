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
Feature: In order to manage Logical Interconnect Groups

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Interconnect Group

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given Resource values as follows:
      | name  | logical-interconnect-group-bdd |
      | state | ACTIVE                         |
      And interconnection values as follows:
      | entries | type                                 |
      |       1 | HP VC FlexFabric 10Gb/24-Port Module |
      |       2 | HP VC FlexFabric 10Gb/24-Port Module |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @getAll
  Scenario: Get all Logical Interconnect Groups
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Interconnect Group by Name
    Given name "logical-interconnect-group-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Logical Interconnect Group by Id
    Given name "logical-interconnect-group-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get Id of Default Interconnect Settings
    When OneView gets Default Interconnect Settings
    Then I get an ID

  @get
  Scenario: Get Id of an Interconnect Settings
    Given name "logical-interconnect-group-bdd" for Resource
    When OneView gets Default Interconnect Settings
      And OneView gets Resource by Name
      And OneView gets Interconnect Settings
    Then I get a Resource Name

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | network1-bdd-logical-interconnect-group |
      | ethernetType | Tagged                                  |
      | vlanId       |                                     301 |
      | purpose      | General                                 |
      | private      | false                                   |
      | smartLink    | true                                    |
      And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
      And Resource values as follows:
      | name         | network2-bdd-logical-interconnect-group |
      | ethernetType | Tagged                                  |
      | vlanId       |                                     302 |
      | purpose      | General                                 |
      | private      | false                                   |
      | smartLink    | true                                    |
      And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-network-bdd-logical-interconnect-group |
      | fabricType              | FabricAttach                              |
      | linkStabilityTime       |                                        30 |
      | autoLoginRedistribution | true                                      |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

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
  Scenario: Update Logical Interconnect Group by Patch
    Given name "logical-interconnect-group-bdd" for Resource
      And Resource values will be updated as follows:
      | op    | replace    |
      | path  | /scopeUris |
      | value | BDD Scope  |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then I get a success status

  @update
  Scenario: Update a Logical Interconnect Group Adding an Uplink Set
    Given Resource values as follows:
      | name   | logical-interconnect-group-bdd |
      | baySet |                              1 |
      And Uplink values as follows:
      | name              | type         | networks                                                                        | bayPort |
      | EthernetUplinkSet | Ethernet     | network1-bdd-logical-interconnect-group,network2-bdd-logical-interconnect-group | X5, X6  |
      | FCUplinkSet       | FibreChannel | fc-network-bdd-logical-interconnect-group                                       | X1      |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
    Then I get an ID

  @remove
  Scenario: Remove a Logical Interconnect Group
    Given name "logical-interconnect-group-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "network1-bdd-logical-interconnect-group" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove an Ethernet Network
    Given an instance of Ethernet Network
      And name "network2-bdd-logical-interconnect-group" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-network-bdd-logical-interconnect-group" for Resource
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
