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
Feature: In order to manage Fc Sans Managed Sans

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Fc Sans Managed San

  @create @onlyOV310
  Scenario: Creation of a new Fc San Manager Under the Specified Provider
  	Given an instance of Fc San Provider
     And Resource values as follows:
      | type          | Cisco San Plugin |
      | name          | 172.18.20.1      |
      | snmpPort      |              161 |
      | snmpUserName  | dcs-SHA          |
      | securityLevel | AUTHNOPRIV       |
      | authProtocol  | SHA              |
      | password      | dcsdcsdcs        | 
    When OneView runs Fc San Provider Synergy creation
      And OneView lists all
    Then I get a count 

  @create @onlyOV3 @disabled
  Scenario: Creation of a new Fc San Manager Under the Specified Provider
    Given Resource values as follows:
      | type          | Cisco San Plugin |
      | name          | 172.18.20.1      |
      | hostname      | Host             |
      | snmpPort      |              161 |
      | snmpUserName  | dcs-SHA          |
      | securityLevel | AUTHNOPRIV       |
      | authProtocol  | SHA              |
      | password      | hpinvent!        | 
    When OneView runs Fc San Provider Synergy creation
      And OneView lists all
    Then I get a count
       
  @create
  Scenario: Creation of a new Fc Sans Managed San Endpoints Csv
    Given Resource values as follows:
      | name | VSAN1 |
    When OneView gets Resource by Name
      And OneView runs Fc Sans Managed San Endpoints Csv creation
    Then Resource is found

  @create
  Scenario: Creation of a new Fc Sans Managed San Issues Report
    Given Resource values as follows:
      | name | VSAN1 |
    When OneView gets Resource by Name
      And OneView runs Fc Sans Managed San Issues Report creation
    Then Resource is found

  @getAll
  Scenario: Get all Fc Sans Managed Sans
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Fc Sans Managed San by Name
    Given name "VSAN1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Fc Sans Managed San by Id
    Given name "VSAN1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Fc Sans Managed San Endpoints
    Given name "VSAN1" for Resource
    When OneView gets Fc Sans Managed San Endpoints
    Then Resource is found

  @get
  Scenario: Get a Fc Sans Managed San Wwn Associations
    Given name "VSAN1" for Resource
    When OneView gets Fc Sans Managed San Wwn Associations
    Then I get a count

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
      And Resource values as follows:
      | name                    | fc-san1-bdd  |
      | fabricType              | FabricAttach |
      | linkStabilityTime       |           30 |
      | autoLoginRedistribution | true         |
      | fc_sans_managed         | "VSAN1"      |
      And OneView sets FC Sans Managed San uri to FC Network
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @update
  Scenario: Update a Fc Sans Managed San
    Given Resource values as follows:
      | name           | SAN1_0                    |
      | enableAliasing | true                      |
      | zoningPolicy   | SingleInitiatorAllTargets |
    When OneView gets Resource by Name
      And OneView runs Resource update
    Then I get a success status

  @remove
  Scenario: Remove a FC Network
    Given an instance of FC Network
      And name "fc-san1-bdd" for Resource
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
