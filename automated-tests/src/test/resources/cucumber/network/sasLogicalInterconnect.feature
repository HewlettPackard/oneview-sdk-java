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
  In order to manage Sas Logical Interconnect

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Sas Logical Interconnect

  @getAll
  Scenario: Get all Sas Logical Interconnect
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a Sas Logical Interconnect by Name
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView gets Resource by Name
     Then I get an ID

  @get
  Scenario: Get a Sas Logical Interconnect by Id
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView gets Resource by Name
      And OneView gets Resource by ID
     Then I get a Resource Name

  @get
  Scenario: Get a Sas Logical Interconnect Firmware
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView gets Resource by Name
      And OneView gets Sas Logical Interconnect firmware
     Then Resource is found

  @update
  Scenario: Replace Sas Logical Interconnect Drive Enclosure
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView gets Resource by Name
      And OneView runs replace Sas Logical Interconnect replace Drive Enclosure
     Then I get a success status

  @update
  Scenario: Apply Sas Logical Interconnect Configuration
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView gets Resource by Name
      And OneView runs Sas Logical Interconnect apply configuration
     Then I get a success status

  @update
  Scenario: Update Sas Logical Interconnect Compliance
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView gets Resource by Name
      And OneView runs Sas Logical Interconnect update compliance
     Then I get a success status

  @update
  Scenario: Update Multiple Sas Logical Interconnect Compliance
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
     When OneView runs Multiple Sas Logical Interconnect update compliance
     Then I get a success status

  @update
  Scenario: Update Sas Logical Interconnect Firmware
    Given name "LogicalEncl1-logicalEnclosureSas-SAS-Logical-Interconnect-Group-BDD-1-1" for Resource
      And Resource values will be updated as follows:
      | fwName | Service Pack for ProLiant |
     When OneView gets Resource by Name
      And OneView runs Sas Logical Interconnect update firmware
     Then I get a success status
