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
  In order to manage Interconnect Link Topology

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Interconnect Link Topology

  @getAll
  Scenario: Get all Interconnect Link Topology
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get an Interconnect Link Topology by Name
    Given name "name1958952731-1484765491815" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get an Interconnect Link Topology by Id
    Given name "name1958952731-1484765491815" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name
