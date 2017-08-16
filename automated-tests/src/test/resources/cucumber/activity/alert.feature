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
Feature: In order to manage Alerts

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Alert

  @getAll
  Scenario: Get all Alerts
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Alert by Id
    When OneView gets Id of First Alert
      And OneView gets Resource by ID
    Then I get a Resource Name

  @update
  Scenario: Update an Alert
    Given Resource values will be updated as follows:
      | notes | Sample notes. |
    When OneView gets Id of First Alert
      And OneView runs Resource update
    Then I get a success status

  @remove
  Scenario: Remove an Alert Change Log
    When OneView deletes Alert Change Log
    Then I get a success status

  @remove
  Scenario: Remove an Alert By Filter
    Given Resource values as follows:
      | alertUrgency | Medium |
    When OneView deletes Alert by filter
    Then I get a success status

  @remove
  Scenario: Remove an Alert
    When OneView gets Id of First Alert
      And OneView deletes the Resource
    Then I get a success status
