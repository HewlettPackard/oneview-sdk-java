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
Feature: In order to manage SAS Interconnects

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Sas Interconnect

  @getAll
  Scenario: Get all SAS Interconnects
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a SAS Interconnect by Name
    Given name "0000A66101, interconnect 1" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a SAS Interconnect by Id
    Given name "0000A66101, interconnect 1" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @patch
  Scenario: Update SAS Interconnect by Patch
    Given name "0000A66101, interconnect 1" for Resource
      And Resource values will be updated as follows:
      | op    | replace     |
      | path  | /powerState |
      | value | On          |
    When OneView gets Resource by Name
      And OneView runs Sas Interconnect patch
    Then I get a success status

  @refresh
  Scenario: Refresh a SAS Interconnect
    Given name "0000A66101, interconnect 1" for Resource
    When OneView gets Resource by Name
      And OneView runs Sas Interconnect refresh
    Then I get a success status
