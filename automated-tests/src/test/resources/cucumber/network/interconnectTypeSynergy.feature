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
  In order to manage Interconnect Type

  Background: 
    Given an instance of OneView
    And OneView credentials located in "src/test/resources/oneView.properties"
    And an instance of Interconnect Type

  @getAll
  Scenario: Get all Interconnect Type
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Interconnect Type by Name
    Given name "Virtual_Connect_SE_40Gb_F8_Module_for_Synergy" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get an Interconnect Type by Id
    Given name "Virtual_Connect_SE_40Gb_F8_Module_for_Synergy" for Resource
    When OneView gets Resource by Name
    And OneView gets Resource by ID
    Then I get a Resource Name
