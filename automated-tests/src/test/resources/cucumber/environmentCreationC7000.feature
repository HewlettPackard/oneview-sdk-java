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
Feature: In order to create an environment

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
      And Resource values as follows:
      | name   | all-fcoe-bdd |
      | vlanID |          400 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Network Set
    Given an instance of Network Set
      And Resource values as follows:
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
  Scenario: Creation of a new Logical Switch Group
    Given an instance of Logical Switch Group
      And Resource values as follows:
      | name          | all-lsg-bdd      |
      | switchType    | Cisco Nexus 55xx |
      | state         | active           |
      | locationEntry |                1 |
      | locationType  | StackingMemberId |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Logical Switch
    Given an instance of Logical Switch
      And Resource values as follows:
      | name            | all-switch-bdd |
      | switchGroup     | all-lsg-bdd    |
      | communityString | public         |
      | snmpPort        |            161 |
      | snmpVersion     | SNMPv1         |
      | managementLevel | BASIC_MANAGED  |
      | managementHost  | 172.18.16.1    |
      | user            | dcs            |
      | password        | dcs            |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

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
      And Resource values as follows:
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
  Scenario: Creation of a new Server Hardware
    Given an instance of Server Hardware
      And Resource values as follows:
      | name               | 172.18.6.8 |
      | username           | dcs        |
      | password           | dcs        |
      | licensingIntent    | OneView    |
      | configurationState | Managed    |
      | force              | true       |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Data Center
    Given an instance of Data Center
      And Resource values as follows:
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
  Scenario: Creation of a new Power Delivery Device
    Given an instance of Power Delivery Device
      And Resource values as follows:
      | name          | Power Delivery Device BDD |
      | model         | Model BDD                 |
      | ratedCapacity |                        40 |
      | lineVoltage   |                        40 |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Power Delivery Device by Discover
    Given an instance of Power Delivery Device
      And Resource values as follows:
      | hostname | 172.18.8.11 |
      | username | dcs         |
      | password | dcs         |
      | force    | true        |
    When OneView runs creation of Power Delivery Device by Discover
    Then I get a success status

  @create
  Scenario: Creation of a new Rack
    Given an instance of Rack
      And Resource values as follows:
      | name | Rack BDD |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Unmanaged Device
    Given an instance of Unmanaged Device
      And Resource values as follows:
      | name        | Unmanaged Device BDD |
      | model       | Procurve 4200VL      |
      | deviceType  | Server               |
      | ipv4Address | 192.168.0.2          |
      | mac         | 68:a5:99:az:71:wc    |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Fc Provider
    Given an instance of Fc San Provider
      And Resource values as follows:
      | name          | 172.18.15.1             |
      | hostname      | Host                    |
      | port          | Port                    |
      | provider      | Brocade Network Advisor |
      | use_ssl       | UseSsl                  |
      | use_ssl_value | true                    |
      | user          | dcs                     |
      | password      | dcs                     |
    When OneView runs Resource creation
      And OneView lists all
    Then I get a count

  @create
  Scenario: Creation of a new Fc Sans Managed San Endpoints Csv
    Given an instance of Fc Sans Managed San
      And Resource values as follows:
      | name | SAN1_0 |
    When OneView gets Resource by Name
      And OneView runs Fc Sans Managed San Endpoints Csv creation
    Then Resource is found

  @create
  Scenario: Creation of a new Fc Sans Managed San Issues Report
    Given an instance of Fc Sans Managed San
      And Resource values as follows:
      | name | SAN1_0 |
    When OneView gets Resource by Name
      And OneView runs Fc Sans Managed San Issues Report creation
    Then Resource is found

  @create
  Scenario: Creation of a new Logical Interconnect Group
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name  | lig-bdd-4-sp |
      | state | ACTIVE       |
      And interconnection values as follows:
      | entries | type                             |
      |       1 | HP VC FlexFabric-20/40 F8 Module |
      |       2 | HP VC FlexFabric-20/40 F8 Module |
    When OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @update
  Scenario: Update a Logical Interconnect Group Adding Uplink Sets
    Given an instance of Logical Interconnect Group
      And Resource values as follows:
      | name   | lig-bdd-4-sp |
      | baySet |            1 |
      And Uplink values as follows:
      | name              | type         | networks                     | bayPort    |
      | EthernetUplinkSet | Ethernet     | all-net-bdd-1, all-net-bdd-2 | Q1.1, Q1.2 |
      | FCUplinkSet       | FibreChannel | all-fc-bdd-1                 | X2         |
    When OneView gets Resource by Name
      And OneView runs Resource update
      And OneView gets Resource by ID
    Then I get an ID

  @create
  Scenario: Creation of a new Enclosure Group
    Given an instance of Enclosure Groups
      And Resource values as follows:
      | name         | enclosure-group-bdd-4-sp |
      | lig          | lig-bdd-4-sp             |
      | stackingMode | Enclosure                |
    When Enclosure Group sets Uris
      And OneView runs Resource creation
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Addition of a new Enclosure
    Given an instance of Enclosure
      And Resource values as follows:
      | enclosureGroup       | enclosure-group-bdd-4-sp  |
      | licensing            | OneView                   |
      | force                | false                     |
      | firmware             | Service Pack for ProLiant |
      | updateFirmware       | EnclosureOnly             |
      | forceInstallFirmware | false                     |
    When OneView runs Resource creation
      And OneView gets Enclosure Name
      And OneView gets Resource by Name
    Then I get an ID

  @create
  Scenario: Creation of a new Server Profile Template
    Given an instance of Server Profile Template
      And Resource values as follows:
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
      And Resource values as follows:
      | name             | sp-bdd                    |
      | description      | sp-bdd                    |
      | affinity         | Bay                       |
      | macType          | Virtual                   |
      | wwnType          | Virtual                   |
      | serialNumberType | Physical                  |
      | enclosureGroup   | enclosure-group-bdd-4-sp  |
      | serverHardware   | Encl1, bay 2              |
      | fcNetworks       | all-fc-bdd-1              |
      | hostOSType       | Windows 2012 / WS2012 R2  |
      | volume           | volume-bdd-storage-volume |
      | requestBandwidth |                     10000 |
      And an Enclosure Group Uri
    When Server Profile sets Uris
      And OneView runs Resource creation
      And OneView lists all
    Then I get a count
