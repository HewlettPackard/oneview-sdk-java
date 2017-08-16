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
Feature: In order to manage SAS Logical JBODs

  Background: 
    Given an instance of OneView
      And OneView credentials located in "src/test/resources/oneView.properties"
      And an instance of Sas Logical Jbod

  @create
  Scenario: Creation of a new SAS Logical Interconnect Group
    Given an instance of Sas Logical Interconnect Group
      And Resource values as follows:
      | name | SAS-LIG-JBOD-BDD |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name             | EG-bdd-JBOD      |
      | sasLig           | SAS-LIG-JBOD-BDD |
      | stackingMode     | Enclosure        |
      | enclosureCount   |                3 |
      | ipAddressingMode | DHCP             |
      | entryBayOne      |                1 |
      | entryBayTwo      |                4 |
    When Enclosure Group sets Uris
      And OneView runs Enclosure Synergy creation
      And OneView gets Resource by Name
    Then I get an ID   

  @createMultiple
  Scenario: Creation of a new Logical Enclosure Multiple
    Given an instance of Logical Enclosure Multiple
      And Resource values as follows:
      | name           | LE-bdd-JBOD               |
      | enclosureGroup | EG-bdd-JBOD               |
      | enclosureURI1  | 0000A66101                |
      | enclosureURI2  | 0000A66102                |
      | enclosureURI3  | 0000A66103                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs Multiple enclosures creation
      And OneView gets Resource by Name
    Then I get an ID

  @createOne @disabled
  Scenario: Creation of a new Logical Enclosure One
    Given an instance of Logical Enclosure One
      And Resource values as follows:
      | name           | LE-bdd-JBOD               |
      | enclosureGroup | EG-bdd-JBOD               |
      | enclosureURI1  | 0000A66101                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs One enclosures creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new SAS Logical JBOD
    Given an instance of Server Profile
      And Resource values as follows:
      | name               | Sas-Logical-JBOD-bdd                        |
      | description        | Sas L JBOD                                  |
      | firmware           | Service Pack for ProLiant version 2017.07.0 |
      | affinity           | Bay                                         |
      | macType            | UserDefined                                 |
      | wwnType            | UserDefined                                 |
      | serialNumberType   | Physical                                    |
      | enclosureGroup     | EG-bdd-JBOD                                 |
      | serverHardware     | 0000A66101, bay 5                           |
      | serverHardwareType | SY 480 Gen9 3                               |
      | sasLogicalJbodName | sasLogicalJbod-bdd                          |
      | deviceSlot         | Mezz 1                                      |
      | numPhysicalDrives  |                                           1 |
      | driveMinSizeGB     |                                           1 |
      | driveMaxSizeGB     |                                        1000 |
      | driveTechnology    | SasHdd                                      |     
      | id                 |                                           1 |
      And an Enclosure Group Uri
    When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count   
             
  @getAll
  Scenario: Get all SAS Logical JBODs
    When OneView lists all
    Then I get a count

  @get
  Scenario: Get a SAS Logical JBOD by Name
    Given name "sasLogicalJbod-bdd" for Resource
    When OneView gets Resource by Name
    Then I get an ID

  @get
  Scenario: Get a SAS Logical JBOD by Id
    Given name "sasLogicalJbod-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Resource by ID
    Then I get a Resource Name

  @get
  Scenario: Get SAS Logical JBOD Drives
    Given name "sasLogicalJbod-bdd" for Resource
    When OneView gets Resource by Name
      And OneView gets Logical Jbod Drives
      And OneView gets Resource by ID
    Then I get a count

  @remove
  Scenario: Remove a SAS Logical JBOD
    Given an instance of Server Profile
      And name "Sas-Logical-JBOD-bdd" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
  
  @remove
  Scenario: Remove a Logical Enclosure
  	Given an instance of Logical Enclosure
      And name "LE-bdd-JBOD" for Resource
   When OneView gets Resource by Name
     And OneView deletes the Resource
     And OneView gets Resource by ID
   Then Resource is not found
   
  @remove
  Scenario: Remove an Enclosure Group
    Given an instance of Enclosure Groups
      And name "EG-bdd-JBOD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
    
  @remove
  Scenario: Remove a SAS Logical Interconnect Group
    Given an instance of Sas Logical Interconnect Group
      And name "SAS-LIG-JBOD-BDD" for Resource
    When OneView gets Resource by Name
      And OneView deletes the Resource
      And OneView gets Resource by ID
    Then Resource is not found
    