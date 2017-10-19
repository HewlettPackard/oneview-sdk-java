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
Feature: In order to manage Interconnects

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Interconnect

  @getAll
  Scenario: Get all Interconnects
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Interconnect by Name
    Given name "0000A66101, interconnect 2" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Interconnect by Id
    Given name "0000A66101, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get @C7000
  Scenario: Get Interconnect Statistics
    Given name "0000A66101, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView gets Interconnect Statistics
    Then I get a Resource Name

  @get @C7000
  Scenario: Get Interconnect Port Statistics
    Given Resource values as follows:
      | name    | 0000A66101, interconnect 2 |
      | port    | d1                         |
      | subport |                          1 |
    When OneView gets Resource by Name
      And OneView gets Interconnect Port Statistics
    Then Resource is found

  @get
  Scenario: Get Interconnect Named Servers
    Given name "0000A66101, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView gets Interconnect Named Servers
    Then Resource is found

  @patch
  Scenario: Update Interconnect by Patch
    Given name "0000A66101, interconnect 2" for Resource
      And Resource values will be updated as follows:
      | op    | replace     |
      | path  | /powerState |
      | value | Off         |
    When OneView gets Resource by Name
      And OneView runs Resource patch
    Then Resource is found

  @reset
  Scenario: Reset Interconnect Port Protection
    Given name "0000A66101, interconnect 2" for Resource
    When OneView gets Resource by Name
      And OneView runs Interconnect Port Protection reset
    Then I get a success status

  @update @C7000
  Scenario: Update Interconnect Port
    Given name "0000A66101, interconnect 1" for Resource
      And Resource values will be updated as follows:
      | portEnable | false |
    When OneView gets Resource by Name
      And OneView runs update interconnect port
    Then Resource is found

  @update @C7000
  Scenario: Update Interconnect Ports
    Given name "0000A66101, interconnect 5" for Resource
      And Resource values will be updated as follows:
      | portEnable | false |
    When OneView gets Resource by Name
      And OneView runs update interconnect ports
    Then Resource is found
