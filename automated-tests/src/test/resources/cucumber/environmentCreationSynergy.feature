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
  In order to create an environment

  Background: 
    Given an instance of OneView
    And OneView credentials located in "src/test/resources/oneView.properties"

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
    And Resource values as follows:
      | name         | all-net-bdd-1 |
      | ethernetType | Tagged        |
      | vlanId       |           301 |
      | purpose      | General       |
      | private      | false         |
      | smartLink    | true          |
    And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Ethernet Network
    Given an instance of Ethernet Network
    And Resource values as follows:
      | name         | all-net-bdd-2 |
      | ethernetType | Tagged        |
      | vlanId       |           302 |
      | purpose      | General       |
      | private      | false         |
      | smartLink    | true          |
    And bandwidth values as follows:
      | maxBandwidth     | 8000 |
      | typicalBandwidth | 3000 |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
    And Resource values as follows:
      | name                    | all-fc-bdd-1 |
      | fabricType              | FabricAttach |
      | linkStabilityTime       |           30 |
      | autoLoginRedistribution | true         |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new FC Network
    Given an instance of FC Network
    And Resource values as follows:
      | name                    | all-fc-bdd-2 |
      | fabricType              | FabricAttach |
      | linkStabilityTime       |           30 |
      | autoLoginRedistribution | true         |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new FCoE Network
    Given an instance of FCoE Network
    Given Resource values as follows:
      | name   | all-fcoe-bdd |
      | vlanID |          400 |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Network Set
    Given an instance of Network Set
    Given Resource values as follows:
      | name | all-network-set-bdd |
    And Ethernet Network names as follows:
      | all-net-bdd-1 |
      | all-net-bdd-2 |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Storage System
    Given an instance of Storage System
    When OneView runs Resource creation
    And OneView lists all
    Then I get a count

  @create
  Scenario: Update Storage System
    Given an instance of Storage System
    And Resource values as follows:
      | fc-network | all-fc-bdd-1, all-fc-bdd-2 |
      | port       | 0:1:1, 0:1:2               |
      | domain     | TestDomain                 |
    When OneView gets Storage Name
    And OneView gets Resource by Name
    And OneView gets Resource by ID
    And OneView runs Resource update
    And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Storage Pool
    Given an instance of Storage Pool
    And Resource values as follows:
      | name | FST_CPG1 |
    And a Storage System Uri
    When StoragePool sets Uris
    And OneView runs Resource creation
    And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Storage Volume
    Given an instance of Storage volume
    And Resource values as follows:
      | name          | volume-bdd-storage-volume |
      | description   | Storage Volume BDD        |
      | provisionType | Full                      |
      | shareable     | true                      |
      | capacity      |               20480000000 |
    And a Storage System Uri
    And a Storage Pool Uri
    When StorageVolume sets Uris
    And OneView runs Resource creation
    And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Storage Volume Template
    Given an instance of Storage Volume Template
    Given Resource values as follows:
      | name          | all-svt-bdd      |
      | state         | Normal           |
      | description   | Example Template |
      | stateReason   | None             |
      | provisionType | Thin             |
      | shareable     | true             |
      | capacity      |     235834383322 |
    And a Storage System Uri
    And a Storage Pool Uri
    When Storage Volume Template sets Uris
    And OneView runs Resource creation
    And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Data Center
    Given an instance of Data Center
    Given Resource values as follows:
      | name                    | Data Center BDD |
      | coolingCapacity         |               5 |
      | costPerKilowattHour     |             0.5 |
      | currency                | USD             |
      | deratingType            | NaJp            |
      | deratingPercentage      |            20.0 |
      | defaultPowerLineVoltage |             220 |
      | coolingMultiplier       |             1.5 |
      | width                   |            4000 |
      | depth                   |            5000 |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Power Delivery Device Synergy
    Given an instance of Power Delivery Device
    Given Resource values as follows:
      | type          | PowerStrip                |
      | name          | Power Delivery Device BDD |
      | model         | Model BDD                 |
      | ratedCapacity |                       266 |
      | lineVoltage   |                       110 |
      | volts         | SinglePhase               |
      | feed          | A                         |
      | partNumber    |                         1 |
      | serialNumber  | SERIE1                    |
    When OneView runs Power Delivery Synergy creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Rack
    Given an instance of Rack
    Given Resource values as follows:
      | name | Rack BDD |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Unmanaged Device
    Given an instance of Unmanaged Device
    Given Resource values as follows:
      | name        | Unmanaged Device BDD |
      | model       | Procurve 4200VL      |
      | deviceType  | Server               |
      | ipv4Address | 192.168.0.2          |
      | mac         | 68:a5:99:az:71:wc    |
    When OneView runs Resource creation
    And OneView gets Resource by Name
    Then I get an ID

  @Not_yet_for_resolved
  Scenario: Creation of a new Fc Provider
    Given an instance of Fc San Provider
    And Resource values as follows:
      | provider          | Cisco          |
      | name              | 172.18.20.1    |
      | hostname          | Host           |
      | snmp_port         | SnmpPort       |
      | snmp_username     | dcs-SHA        |
      | security_level    | Authentication |
      | security_lv_value | true           |
      | auth_protocol     | SHA            |
      | auth_password     | hpinvent!      |
    When OneView runs Resource creation
    And OneView lists all
    Then I get a count

  @Not_yet_for_resolved
  Scenario: Creation of a new Fc Sans Managed San Endpoints Csv
    Given an instance of Fc Sans Managed San
    Given Resource values as follows:
      | name | VSAN1 |
    When OneView gets Resource by Name
    And OneView runs Fc Sans Managed San Endpoints Csv creation
    Then Resource is found

  @Not_yet_for_resolved
  Scenario: Creation of a new Fc Sans Managed San Issues Report
    Given an instance of Fc Sans Managed San
    Given Resource values as follows:
      | name | VSAN1 |
    When OneView gets Resource by Name
    And OneView runs Fc Sans Managed San Issues Report creation
    Then Resource is found

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    And Resource values as follows:
      | name           | lig-bdd-4-sp |
      | state          | ACTIVE       |
      | baySet         |            3 |
      | redundancyType | Redundant    |
      | enclosureType  | SY12000      |
    And interconnection values as follows:
      | entries | type                                          |
      |       3 | Virtual Connect SE 40Gb F8 Module for Synergy |
      |       6 | Virtual Connect SE 40Gb F8 Module for Synergy |
    When OneView runs Logical Interconnect Group Synergy creation
    And OneView gets Resource by Name
    Then I get an ID

  @Not_yet_for_resolved
  Scenario: Edit a Logical Interconnect Group
    Given an instance of Logical Interconnect Group
    Given name "lig-bdd-4-sp" for Resource
    And Uplink values as follows:
      | name              | type         | networks      | bayPort |
      | FCUplinkSet       | FibreChannel | all-fc-bdd-1  | Q2:3    |
    When OneView gets Resource by Name
    And OneView runs Resource update
    And OneView gets Resource by ID
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Groups
    Given an instance of Enclosure Groups
    And Resource values as follows:
      | name             | enclosure-group-bdd-4-sp |
      | lig              | lig-bdd-4-sp             |
      | stackingMode     | Enclosure                |
      | enclosureCount   |                        1 |
      | ipAddressingMode | DHCP                     |
    When Enclosure Group sets Uris
    And OneView runs Enclosure Synergy creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Logical Enclosure
    Given an instance of Logical Enclosure
    Given Resource values as follows:
      | name           | logical_enclosure_bdd     |
      | enclosureGroup | enclosure-group-bdd-4-sp  |
      | enclosureURI1  | 0000A66101                |
      | firmware       | Service Pack for ProLiant |
    When OneView runs One enclosures creation
    And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Server Profile Template
    Given an instance of Server Profile Template
    Given Resource values as follows:
      | name             | spt-bdd                  |
      | enclosureGroup   | enclosure-group-bdd-4-sp |
      | serialNumberType | Virtual                  |
      | macType          | Virtual                  |
      | wwnType          | Virtual                  |
      | affinity         | Bay                      |
    And an Enclosure Group Uri
    When Server Profile Template sets Uris
    And OneView runs Resource creation
    And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Server Profile
    Given an instance of Server Profile
    Given Resource values as follows:
      | name               | sp-bdd                    |
      | description        | sp-bdd                    |
      | firmware           | Service Pack for ProLiant |
      | affinity           | Bay                       |
      | macType            | UserDefined               |
      | wwnType            | UserDefined               |
      | serialNumberType   | Physical                  |
      | enclosureGroup     | enclosure-group-bdd-4-sp  |
      | serverHardware     | 0000A66101, bay 4         |
      | serverHardwareType | SY 660 Gen9 1             |
      | volume             | volume-bdd-storage-volume |
      | hostOSType         | Windows 2012 / WS2012 R2  |
      | requestBandwidth   |                     16000 |
    And an Enclosure Group Uri
    When Server Profile sets Uris
    And OneView runs Resource creation
    And OneView lists all
    Then I get a count
