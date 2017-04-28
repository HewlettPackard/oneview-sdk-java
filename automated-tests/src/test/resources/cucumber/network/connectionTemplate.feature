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
  In order to manage Connection Template

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Connection Template

  @getAll
  Scenario: Get all Connection Template
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Connection Template by Name
    When OneView gets Name of First Connection Template
     And OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a Connection Template by Id
    When OneView gets Name of First Connection Template
     And OneView gets Resource by Name
     And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get a Default Connection Template
    When OneView gets Default Connection Template
    Then Resource is found

  @edit
  Scenario: Edit a Connection Template
    Given Resource values will be updated as follows:
      | maxBandwidth     | 7000 |
      | typicalBandwidth | 2000 |
     When OneView gets Name of First Connection Template
      And OneView gets Resource by Name
      And OneView runs Resource update
     Then I get a success status
