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
Feature: In order to manage Fc San Providers

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Fc San Provider
    
  @create @onlyOV310
  Scenario: Creation of a new Fc San Manager Under the Specified Provider
    Given Resource values as follows:
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

  @getAll
  Scenario: Get all Fc San Providers
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Fc San Provider by Name
    Given name "172.18.20.1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Fc San Provider by Id
    Given name "172.18.20.1" for Resource
    When OneView gets Resource by Name
    And OneView gets Resource by ID
    Then I get a Resource Name

  @remove
  Scenario: Remove a Fc San Device Manager
    Given an instance of Fc San Device Manager
      And name "172.18.20.1" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
    