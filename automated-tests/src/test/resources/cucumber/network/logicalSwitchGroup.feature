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
  In order to manage Logical Switch Group

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Logical Switch Group

  @create
  Scenario: Creation of a new Logical Switch Group
    Given Resource values as follows:
      | name          | lsg-bdd          |
      | switchType    | Cisco Nexus 55xx |
      | state         | active           |
      | locationEntry |                1 |
      | locationType  | StackingMemberId |
     When OneView runs Resource creation
      And OneView gets Resource by Name
     Then I get an ID

  @getAll
  Scenario: Get all Logical Switch Group
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Logical Switch Group by Name
    Given name "lsg-bdd" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a Logical Switch Group by Id
    Given name "lsg-bdd" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @edit
  Scenario: Edit a Logical Switch Group
    Given Resource values as follows:
      | name          | lsg-bdd          |
      | switchType    | Cisco Nexus 55xx |
      | locationEntry |                2 |
      | locationType  | StackingMemberId |
     When OneView gets Resource by Name
      And OneView runs Resource update
     Then I get a success status

  @remove
  Scenario: Remove a Logical Switch Group
    Given name "lsg-bdd" for Resource
     When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
     Then Resource is not found
     
     
