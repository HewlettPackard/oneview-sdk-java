## v4.0.0 (Unreleased)

Enhancements:


Bug fixes:


## v3.2.1
This release sets API 300 as default.

## v3.2.0

#### New Resources:
  - OS Deployment Plan
  
#### New Features:
This release adds support to new endpoints to OneView Rest API version 300 to the already existing features:
  - Logical Interconnect
    - _GET_ ethernetSettings
  - Interconnect
    - _GET_ ports
    - _GET_ ports/{portId:.+}
  - Network Sets
    - _GET_ withoutEthernet
    - _GET_ {id}/withoutEthernet

This release adds support to OneView Rest API version 500 to the already existing features:
  - Ethernet Network
  - FC Network
  - FCoE Network
  - Interconnect Type
  - Internal Link Sets
  
