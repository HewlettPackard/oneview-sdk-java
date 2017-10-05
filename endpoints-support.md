***Legend***

| Item | Meaning |
| ------------------ | --------------------------------------------- |
|  :white_check_mark: | Endpoint implemented in the Java SDK and tested for this API version :tada: |
|  :heavy_minus_sign: | Endpoint not available for this API Version |

<br />

***Notes***


<br />

## HPE OneView

| Endpoints                                                                               | Verb     | V200                 | V300                 | V500                 |
| --------------------------------------------------------------------------------------- | -------- | :------------------: | :------------------: | :------------------: |
|     **Alerts**                                                                                                                                   |
|<sub>/rest/alerts	</sub>                                                                |GET       |    |    |
|<sub>/rest/alerts	</sub>                                                                |DELETE    |    |    |
|<sub>/rest/alerts/{id}	</sub>                                                            |GET       |    |    |
|<sub>/rest/alerts/{id}	</sub>                                                            |PUT       |    |    |
|<sub>/rest/alerts/{id}	</sub>                                                            |DELETE    |    |    |
|<sub>/rest/alerts/AlertChangeLog/{id}</sub>                                              |DELETE    |    |    |
|     **Appliance Time and Locale Configuration**                                                                                                                         |
|<sub>/rest/appliance/configuration/time-locale</sub>                                     |GET       |    |    |    |
|<sub>/rest/appliance/configuration/time-locale</sub>                                     |POST      |    |    |    |
|     **Backups**                                                                                                                                  |
|<sub>/rest/backups</sub>                                                                 |GET       |    |    |
|<sub>/rest/backups</sub>                                                                 |POST      |    |    |
|<sub>/rest/backups/archive</sub>                                                         |POST      |    |    |
|<sub>/rest/backups/archive/{id}</sub>                                                    |GET       |    |    |
|<sub>/rest/backups/config</sub>                                                          |GET       |    |    |
|<sub>/rest/backups/config</sub>                                                          |PUT       |    |    |
|<sub>/rest/backups/remotearchive/{id}</sub>                                              |PUT       |    |    |
|<sub>/rest/backups/remotearchive/{id}</sub>                                              |DELETE    |    |    |
|<sub>/rest/backups/{id}</sub>                                                            |GET       |    |    |
|<sub>/rest/backups/{id}</sub>                                                            |DELETE    |    |    |
|     **Certificate Authority**                                                                                                                    |
|<sub>/rest/certificates/ca</sub>                                                         |GET       |    |    |
|<sub>/rest/certificates/ca/crl</sub>                                                     |GET       |    |    |
|<sub>/rest/certificates/ca/{aliasName}</sub>                                             |DELETE    |    |    |
|     **Certificates Client RabbitMq**                                                                                                             |
|<sub>/rest/certificates/client/rabbitmq</sub>                                            |POST      |    |    |
|<sub>/rest/certificates/client/rabbitmq/keypair/{aliasName}</sub>                        |GET       |    |    |
|<sub>/rest/certificates/client/rabbitmq/keys/{aliasName}</sub>                           |GET       |    |    |
|<sub>/rest/certificates/client/rabbitmq/{aliasName}</sub>                                |GET       |    |    |
|     **Connection Template**                                                                                                                      |
|<sub>/rest/connection-templates</sub>                                                    |GET       |    |    |    |
|<sub>/rest/connection-templates/defaultConnectionTemplate</sub>                          |GET       |    |    |    |
|<sub>/rest/connection-templates/{id}</sub>                                               |GET       |    |    |    |
|<sub>/rest/connection-templates/{id}</sub>                                               |PUT       |    |    |    |
|     **Datacenter**                                                                                                                               |
|<sub>/rest/datacenters</sub>                                                             | GET      |    |    |    |
|<sub>/rest/datacenters</sub>                                                             | POST     |    |    |    |
|<sub>/rest/datacenters</sub>                                                             | DELETE   |    |    |    |
|<sub>/rest/datacenters/{id}</sub>                                                        | GET      |    |    |    |
|<sub>/rest/datacenters/{id}</sub>                                                        | PUT      |    |    |    |
|<sub>/rest/datacenters/{id}</sub>                                                        | DELETE   |    |    |    |
|<sub>/rest/datacenters/{id}/visualContent</sub>                                          | GET      |    |    |    |
|     **Drive Enclosures**                                                                                                                         |
|<sub>/rest/drive-enclosures</sub>                                                        | GET      |    |    |    |
|<sub>/rest/drive-enclosures/{id}</sub>                                                   | GET      |    |    |    |
|<sub>/rest/drive-enclosures/{id}</sub>                                                   | PATCH    |    |    |    |
|<sub>/rest/drive-enclosures/{id}/port-map</sub>                                          | GET      |    |    |    |
|<sub>/rest/drive-enclosures/{id}/refreshState</sub>                                      | PUT      |    |    |    |
|     **Enclosure**                                                                                                                                |
|<sub>/rest/enclosures</sub>                                                              | GET      |    |    |    |
|<sub>/rest/enclosures</sub>                                                              | POST     |    |    |    |
|<sub>/rest/enclosures/{id}</sub>                                                         | DELETE   |    |    |    |
|<sub>/rest/enclosures/{id}</sub>                                                         | PATCH    |    |    |    |
|<sub>/rest/enclosures/{id}</sub>                                                         | DELETE   |    |    |    |
|<sub>/rest/enclosures/{id}/configuration</sub>                                           | PUT      |    |    |    |
|<sub>/rest/enclosures/{id}/environmentalConfiguration</sub>                              | GET      |    |    |    |
|<sub>/rest/enclosures/{id}/environmentalConfiguration</sub>                              | PUT      |    |    |    |
|<sub>/rest/enclosures/{id}/refreshState</sub>                                            | PUT      |    |    |    |
|<sub>/rest/enclosures/{id}/script</sub>                                                  | GET      |    |    |    |
|<sub>/rest/enclosures/{id}/sso</sub>                                                     | GET      |    |    |    |
|<sub>/rest/enclosures/{id}/utilization</sub>                                             | GET      |    |    |    |
|     **Enclosure Group**                                                                                                                          |
|<sub>/rest/enclosure-groups</sub>                                                        | GET      |    |    |    |
|<sub>/rest/enclosure-groups</sub>                                                        | POST     |    |    |    |
|<sub>/rest/enclosure-groups/{id}</sub>                                                   | GET      |    |    |    |
|<sub>/rest/enclosure-groups/{id}</sub>                                                   | PUT      |    |    |    |
|<sub>/rest/enclosure-groups/{id}</sub>                                                   | DELETE   |    |    |    |
|<sub>/rest/enclosure-groups/script</sub>                                                 | GET      |    |    |    |
|<sub>/rest/enclosure-groups/script</sub>                                                 | PUT      |    |    |    |
|     **Endpoints**                                                                                                                                |
|<sub>/rest/fc-sans/endpoints</sub>                                                       | GET      |    |    |
|     **Ethernet Network**                                                                                                                         |
|<sub>/rest/ethernet-networks</sub>                                                       | GET      |    |    |    |
|<sub>/rest/ethernet-networks</sub>                                                       | POST     |    |    |    |
|<sub>/rest/ethernet-networks/bulk</sub>                                                  | POST     |    |    |    |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | GET      |    |    |    |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | PUT      |    |    |    |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | DELETE   |    |    |    |
|<sub>/rest/ethernet-networks/{id}/associatedProfiles</sub>                               | GET      |    |    |    |
|<sub>/rest/ethernet-networks/{id}/associatedUplinkGroups</sub>                           | GET      |    |    |    |
|     **Events**                                                                                                                         |
|<sub>/rest/events</sub>                                                                  | GET      |    |    |    |
|<sub>/rest/events</sub>                                                                  | POST     |    |    |    |
|<sub>/rest/events/{id}</sub>                                                             | GET      |    |    |    |
|     **Fabric**                                                                                                                                   |
|<sub>/rest/fabrics</sub>                                                                 | GET      |    |    |    |
|<sub>/rest/fabrics/{id}</sub>                                                            | GET      |    |    |    |
|<sub>/rest/fabrics/{id}/reserved-vlan-range</sub>                                        | GET      |    |    |    |
|<sub>/rest/fabrics/{id}/reserved-vlan-range</sub>                                        | GET      |    |    |    |
|     **FC Network**                                                                                                                               |
|<sub>/rest/fc-networks</sub>                                                             | GET      |    |    |    |
|<sub>/rest/fc-networks</sub>                                                             | POST     |    |    |    |
|<sub>/rest/fc-networks/{id}</sub>                                                        | GET      |    |    |    |
|<sub>/rest/fc-networks/{id}</sub>                                                        | PUT      |    |    |    |
|<sub>/rest/fc-networks/{id}</sub>                                                        | DELETE   |    |    |    |
|     **FCoE Network**                                                                                                                             |
|<sub>/rest/fcoe-networks</sub>                                                           | GET      |    |    |    |
|<sub>/rest/fcoe-networks</sub>                                                           | POST     |    |    |    |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | GET      |    |    |    |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | PUT      |    |    |    |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | DELETE   |    |    |    |
|     **Firmware Bundle**                                                                                                                          |
|<sub>/rest/firmware-bundles/{id}/script</sub>                                            | POST     |    |    |    |
|     **Firmware Driver**                                                                                                                          |
|<sub>/rest/firmware-drivers/</sub>                                                       | GET      |    |    |    |
|<sub>/rest/firmware-drivers/</sub>                                                       | POST     |    |    |    |
|<sub>/rest/firmware-drivers/{id}</sub>                                                   | GET      |    |    |    |
|<sub>/rest/firmware-drivers/{id}</sub>                                                   | DELETE   |    |    |    |
|     **Id Pools vMAC Range**                                                                                                                      |
|<sub>/rest/id-pools/vmac/ranges</sub>                                                    | POST     |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}</sub>                                               | GET      |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}</sub>                                               | PUT      |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}</sub>                                               | DELETE   |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}/allocated-fragments</sub>                           | GET      |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}/allocator</sub>                                     |PUT       |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}/collector</sub>                                     | PUT      |    |    |
|<sub>/rest/id-pools/vmac/ranges/{id}/free-fragments</sub>                                | GET      |    |    |
|     **Id Pools vSN Range**                                                                                                                       |
|<sub>/rest/id-pools/vsn/ranges</sub>                                                     | POST     |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}</sub>                                                | GET      |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}</sub>                                                | PUT      |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}</sub>                                                | DELETE   |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}/allocated-fragments</sub>                            | GET      |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}/allocator</sub>                                      |PUT       |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}/collector</sub>                                      | PUT      |    |    |
|<sub>/rest/id-pools/vsn/ranges/{id}/free-fragments</sub>                                 | GET      |    |    |
|     **Id Pools vWWN Range**                                                                                                                      |
|<sub>/rest/id-pools/vwwn/ranges</sub>                                                    | POST     |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}</sub>                                               | GET      |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}</sub>                                               | PUT      |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}</sub>                                               | DELETE   |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}/allocated-fragments</sub>                           | GET      |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}/allocator</sub>                                     |PUT       |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}/collector</sub>                                     | PUT      |    |    |
|<sub>/rest/id-pools/vwwn/ranges/{id}/free-fragments</sub>                                | GET      |    |    |
|     **Internal Link Sets**                                                                                                                       |
|<sub>/rest/internal-link-sets</sub>                                                      | GET      |    |    |    |
|<sub>/rest/internal-link-sets/{id}</sub>                                                 | PUT      |    |    |    |
|     **Interconnect Link Topologies**                                                                                                                       |
|<sub>/rest/interconnect-link-topologies</sub>                                            | GET      |    |    |    |
|<sub>/rest/interconnect-link-topologies{id}</sub>                                        | GET      |    |    |    |
|     **Interconnect Type**                                                                                                                        |
|<sub>/rest/interconnect-types</sub>                                                      | GET      |    |    |    |
|<sub>/rest/interconnect-types/{id}</sub>                                                 | GET      |    |    |    |
|     **Interconnects**                                                                                                                            |
|<sub>/rest/interconnects</sub>                                                           | GET      |    |    |    |
|<sub>/rest/interconnects/{id}</sub>                                                      | GET      |    |    |    |
|<sub>/rest/interconnects/{id}</sub>                                                      | PATCH    |    |    |    |
|<sub>/rest/interconnects/{id}/configuration</sub>                                        | PUT      |    |    |    |
|<sub>/rest/interconnects/{id}/pluggableModuleInformation</sub>                           | GET      |    |    |    |
|<sub>/rest/interconnects/{id}/ports</sub>                                                | GET      |    |    |    |
|<sub>/rest/interconnects/{id}/ports</sub>                                                | PUT      |    |    |    |
|<sub>/rest/interconnects/{id}/ports/{portId:.+}</sub>                                    | GET      |    |    |    |
|<sub>/rest/interconnects/{id}/resetportprotection</sub>                                  | PUT      |    |    |    |
|<sub>/rest/interconnects/{id}/statistics</sub>                                           | PUT      |    |    |    |
|<sub>/rest/interconnects/{id}/statistics/{portName:.+}</sub>                             | GET      |    |    |    |
|<sub>/rest/interconnects/{id}/statistics/{portName:.+}/subport/{subportNum}</sub>        | GET      |    |    |    |
|<sub>/rest/interconnects/{id}/update-ports</sub>                                         | PUT      |    |    |    |
|<sub>/rest/interconnects/{id}/nameServers</sub>                                          | GET      |    |    |    |
|     **Labels**                                                                                                                                   |
|<sub>/rest/labels</sub>                                                                  | GET      |    |    |
|<sub>/rest/labels/resources</sub>                                                        | POST     |    |    |
|<sub>/rest/labels/resources/**</sub>                                                     | GET      |    |    |
|<sub>/rest/labels/resources/**</sub>                                                     | PUT      |    |    |
|<sub>/rest/labels/resources/**</sub>                                                     | DELETE   |    |    |
|<sub>/rest/labels/resources/{id}</sub>                                                   | GET      |    |    |
|     **License**                                                                                                                        |
|<sub>/rest/licenses</sub>                                                                |GET       |    |    | :white_check_mark: |
|<sub>/rest/licenses</sub>                                                                |POST      |    |    | :white_check_mark: |
|<sub>/rest/licenses/{id}</sub>                                                           |GET       |    |    | :white_check_mark: |
|<sub>/rest/licenses/{licenseId}</sub>                                                    |GET       |    |    | :white_check_mark: |
|     **Logical Downlink**                                                                                                                         |
|<sub>/rest/logical-downlinks</sub>                                                       |GET       |    |    |    |
|<sub>/rest/logical-downlinks/withoutEthernet</sub>                                       |GET       |    |    |    |
|<sub>/rest/logical-downlinks/{id}</sub>                                                  |GET       |    |    |    |
|<sub>/rest/logical-downlinks/{id}/withoutEthernet</sub>                                  |GET       |    |    |    |
|     **Logical Enclosures**                                                                                                                        |
|<sub>/rest/logical-enclosures</sub>                                                      | GET      |    |    |    |
|<sub>/rest/logical-enclosures</sub>                                                      | POST     |    |    |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | GET      |    |    |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | PUT      |    |    |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | PATCH    |    |    |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | DELETE   |    |    |    |
|<sub>/rest/logical-enclosures/{id}/configuration</sub>                                   | PUT      |    |    |    |
|<sub>/rest/logical-enclosures/{id}/script</sub>                                          | GET      |    |    |    |
|<sub>/rest/logical-enclosures/{id}/script</sub>                                          | PUT      |    |    |    |
|<sub>/rest/logical-enclosures/{id}/support-dumps</sub>                                   | POST     |    |    |    |
|<sub>/rest/logical-enclosures/{id}/updateFromGroup</sub>                                 | PUT      |    |    |    |
|     **Logical Interconnects**                                                                                                                     |
|<sub>/rest/logical-interconnects</sub>                                                   | GET      |    |    |    |
|<sub>/rest/logical-interconnects/locations/interconnects</sub>                           | POST     |    |    |    |
|<sub>/rest/logical-interconnects/locations/interconnects</sub>                           | DELETE   |    |    |    |
|<sub>/rest/logical-interconnects/{id}</sub>                                              | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/compliance</sub>                                   | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/ethernetSettings</sub>                             | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/ethernetSettings</sub>                             | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/firmware</sub>                                     | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/firmware</sub>                                     | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/forwarding-information-base</sub>                  | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/forwarding-information-base</sub>                  | POST     |    |    |    |
|<sub>/rest/logical-interconnects/{id}/forwarding-information-base/{dumpFileName}.{suffix}</sub>                  | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/internalNetworks</sub>                             | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/internalVlans</sub>                                | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/qos-aggregated-configuration</sub>                 | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/qos-aggregated-configuration</sub>                 | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/settings</sub>                                     | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/snmp-configuration</sub>                           | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/snmp-configuration</sub>                           | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/support-dumps</sub>                                | POST     |                      |                      |                      |
|<sub>/rest/logical-interconnects/{id}/unassignedUplinkPortsForPortMonitor</sub>          | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/configuration</sub>                                | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/port-monitor</sub>                                 | GET      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/port-monitor</sub>                                 | PUT      |    |    |    |
|<sub>/rest/logical-interconnects/{id}/telemetry-configurations/{tcId}</sub>              | GET      |    |    |    |
|     **Logical Interconnect Group**                                                                                                               |
|<sub>/rest/logical-interconnect-groups</sub>                                             | GET      |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups</sub>                                             | POST     |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/defaultSettings</sub>                             | GET      |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | GET      |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | PUT      |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | PATCH    |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | DELETE   |    |    | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}/settings</sub>                               | GET      |    |    | :white_check_mark: |
|     **Logical Switch Group**                                                                                                                     |
|<sub>/rest/logical-switch-groups</sub>                                                   | GET      |    |    |    |
|<sub>/rest/logical-switch-groups</sub>                                                   |POST      |    |    |    |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |GET       |    |    |    |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |PUT       |    |    |    |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |DELETE    |    |    |    |
|     **Logical Switches**                                                                                                                         |
|<sub>/rest/logical-switches</sub>                                                        | GET      |    |    |    |
|<sub>/rest/logical-switches</sub>                                                        |POST      |    |    |    |
|<sub>/rest/logical-switches/{id}</sub>                                                   |GET       |    |    |    |
|<sub>/rest/logical-switches/{id}</sub>                                                   |PUT       |    |    |    |
|<sub>/rest/logical-switches/{id}</sub>                                                   |DELETE    |    |    |    |
|<sub>/rest/logical-switches/{id}/refresh</sub>                                           |PUT       |    |    |    |
|     **Login Details**                                                                                                                                                   |
|<sub>/rest/logindetails</sub>                                                            | GET      |   |   |   |
|     **Managed SANs**                                                                                                                             |
|<sub>/rest/fc-sans/managed-sans</sub>                                                    | GET      |    |    |    |
|<sub>/rest/fc-sans/managed-sans/{id}</sub>                                               | GET      |    |    |    |
|<sub>/rest/fc-sans/managed-sans/{id}</sub>                                               | PUT      |    |    |    |
|<sub>/rest/fc-sans/managed-sans/{id}/endpoints</sub>                                     | GET      |    |    |    |
|<sub>/rest/fc-sans/managed-sans/{id}/endpoints</sub>                                     | POST     |    |    |    |
|<sub>/rest/fc-sans/managed-sans/{id}/issues</sub>                                        | POST     |    |    |    |
|<sub>/rest/fc-sans/managed-sans/WWN+</sub>                                               | GET      |    |    |    |
|     **Metric Streaming**                                                                                                                         |
|<sub>/rest/metrics/capability</sub>                                                      | GET      |    |    |    |
|<sub>/rest/metrics/configuration</sub>                                                   | GET      |    |    |    |
|<sub>/rest/metrics/configuration</sub>                                                   | PUT      |    |    |    |
|     **Migratable VC Domains**                                                                                                                    |
|<sub>/rest/migratable-vc-domains</sub>                                                   | GET      |    |                      |
|<sub>/rest/migratable-vc-domains</sub>                                                   | POST     |    |                      |
|<sub>/rest/migratable-vc-domains{id}</sub>                                               | PUT      |    |                      |
|<sub>/rest/migratable-vc-domains{id}</sub>                                               | GET      |    |    |
|<sub>/rest/migratable-vc-domains{id}</sub>                                               | DELETE   |    |    |
|     **Network Set**                                                                                                                              |
|<sub>/rest/network-sets</sub>                                                            |GET       |    |    |    |
|<sub>/rest/network-sets</sub>                                                            | POST     |    |    |    |
|<sub>/rest/network-sets/withoutEthernet</sub>                                            | GET      |    |    |    |
|<sub>/rest/network-sets/{id}</sub>                                                       | GET      |    |    |    |
|<sub>/rest/network-sets/{id}</sub>                                                       | PUT      |    |    |    |
|<sub>/rest/network-sets/{id}</sub>                                                       | DELETE   |    |    |    |
|<sub>/rest/network-sets/{id}/withoutEthernet</sub>                                       | GET      |    |    |    |
|     **OS Deployment Plans**                                                                                                                      |
|<sub>/rest/os-deployment-plans/</sub>                                                    | GET      |    |    |    |
|<sub>/rest/os-deployment-plans/{id}</sub>                                                | GET      |    |    |    |
|     **OS Deployment Servers**                                                                                                                      |
|<sub>/rest/os-deployment-servers/</sub>                                                  | GET      |    |    |                      |
|<sub>/rest/os-deployment-servers/</sub>                                                  | POST     |    |    |                      |
|<sub>/rest/os-deployment-servers/image-streamer-appliances/</sub>                        | GET      |    |    |                      |
|<sub>/rest/os-deployment-servers/image-streamer-appliances/{id}</sub>                    | GET      |    |    |                      |
|<sub>/rest/os-deployment-servers/network</sub>                                           | GET      |    |    |                      |
|<sub>/rest/deployment-servers/security-mode/security-compatibility-checker</sub>         | POST     |                      |                      |                       
|<sub>/rest/os-deployment-servers/{id}/</sub>                                             | GET      |    |    |                      |
|<sub>/rest/os-deployment-servers/{id}/</sub>                                             | PUT      |    |    |                      |
|<sub>/rest/os-deployment-servers/{id}/</sub>                                             | DELETE   |    |    |                      |
|     **Power Device**                                                                                                                             |
|<sub>/rest/power-devices</sub>                                                           | GET      |    |    |    |
|<sub>/rest/power-devices</sub>                                                           | POST     |    |    |    |
|<sub>/rest/power-devices</sub>                                                           | DELETE   |    |    |    |
|<sub>/rest/power-devices/discover</sub>                                                  | POST     |    |    |    |
|<sub>/rest/power-devices/{id}</sub>                                                      | GET      |    |    |    |
|<sub>/rest/power-devices/{id}</sub>                                                      | PUT      |    |    |    |
|<sub>/rest/power-devices/{id}</sub>                                                      | DELETE   |    |    |    |
|<sub>/rest/power-devices/{id}/powerState</sub>                                           | GET      |    |    |    |
|<sub>/rest/power-devices/{id}/powerState</sub>                                           | PUT      |    |    |    |
|<sub>/rest/power-devices/{id}/refreshState</sub>                                         | PUT      |    |    |    |
|<sub>/rest/power-devices/{id}/synchronous</sub>                                          | DELETE   |    |    |    |
|<sub>/rest/power-devices/{id}/uidState</sub>                                             | GET      |    |    |    |
|<sub>/rest/power-devices/{id}/uidState</sub>                                             | PUT      |    |    |    |
|<sub>/rest/power-devices/{id}/utilization</sub>                                          | GET      |    |    |    |
|     **Rack**                                                                                                                                     |
|<sub>/rest/racks</sub>                                                                   | GET      |    |    |    |
|<sub>/rest/racks</sub>                                                                   | POST     |    |    |    |
|<sub>/rest/racks</sub>                                                                   | DELETE   |    |    |    |
|<sub>/rest/racks/{id}</sub>                                                              | GET      |    |    |    |
|<sub>/rest/racks/{id}</sub>                                                              | PUT      |    |    |    |
|<sub>/rest/racks/{id}</sub>                                                              | DELETE   |    |    |    |
|<sub>/rest/racks/{id}/deviceTopology</sub>                                               | GET      |    |    |    |
|     **Restores**                                                                                                                                     |
|<sub>/rest/restores</sub>                                                                | POST     |    |    |                      |
|<sub>/rest/restores</sub>                                                                | POST     |    |    |                      |
|<sub>/rest/restores/failures</sub>                                                       | POST     |    |    |                      |
|<sub>/rest/restores/{id}</sub>                                                           | POST     |    |    |                      |
|     **Roles**                                                                                                                                     |
|<sub>/rest/roles</sub>                                                                   | GET      |    |    |    |
|<sub>/rest/roles/{roleName}</sub>                                                        | GET      |    |    |    |
|     **SAN Managers**                                                                                                                             |
|<sub>/rest/fc-sans/device-managers</sub>                                                 | GET      |    |    |    |
|<sub>/rest/fc-sans/device-managers/{id}</sub>                                            | GET      |    |    |    |
|<sub>/rest/fc-sans/device-managers/{id}</sub>                                            | PUT      |    |    |    |
|<sub>/rest/fc-sans/device-managers/{id}</sub>                                            | DELETE   |    |    |    |
|<sub>/rest/fc-sans/providers/{id}/device-managers</sub>                                  | POST     |    |    |    |
|     **SAS Interconnect Types**                                                                                                                   |
|<sub>/rest/sas-interconnect-types</sub>                                                  | GET      |    |    |    |
|<sub>/rest/sas-interconnect-types/{id}</sub>                                             | GET      |    |    |    |
|     **SAS Interconnects**                                                                                                                        |
|<sub>/rest/sas-interconnects</sub>                                                       | GET      |    |    |    |
|<sub>/rest/sas-interconnects/{id}</sub>                                                  | GET      |    |    |    |
|<sub>/rest/sas-interconnects/{id}</sub>                                                  | PATCH    |    |    |    |
|<sub>/rest/sas-interconnects/{id}/refreshState</sub>                                     | PUT      |    |    |    |
|     **SAS Logical Interconnect Groups**                                                                                                          |
|<sub>/rest/sas-logical-interconnect-groups</sub>                                         | POST     |    |    |    |
|<sub>/rest/sas-logical-interconnect-groups</sub>                                         | GET      |    |    |    |
|<sub>/rest/sas-logical-interconnect-groups/{id}</sub>                                    | GET      |    |    |    |
|<sub>/rest/sas-logical-interconnect-groups/{id}</sub>                                    | PUT      |    |    |    |
|<sub>/rest/sas-logical-interconnect-groups/{id}</sub>                                    | DELETE   |    |    |    |
|     **SAS Logical Interconnects**                                                                                                                |
|<sub>/rest/sas-logical-interconnects</sub>                                               | GET      |    |    |    |
|<sub>/rest/sas-logical-interconnects/{id}</sub>                                          | GET      |    |    |    |
|<sub>/rest/sas-logical-interconnects/{id}/firmware</sub>                                 | GET      |    |    |    |
|<sub>/rest/sas-logical-interconnects/{id}/firmware</sub>                                 | PUT      |    |    |    |
|<sub>/rest/sas-logical-interconnects/compliance</sub>                                    | PUT      |    |    |    |
|<sub>/rest/sas-logical-interconnects/{id}/compliance</sub>                               | PUT      |    |    |    |
|<sub>/rest/sas-logical-interconnects/{lsId}/configuration</sub>                          | PUT      |    |    |    |
|<sub>/rest/sas-logical-interconnects/{id}/replaceDriveEnclosure</sub>                    | POST     |    |    |    |
|     **SAS Logical JBOD Attachments**                                                                                                             |
|<sub>/rest/sas-logical-jbod-attachments</sub>                                            | GET      |    |    |    |
|<sub>/rest/sas-logical-jbod-attachments/{id}</sub>                                       | GET      |    |    |    |
|     **SAS Logical JBODs**                                                                                                                        |
|<sub>/rest/sas-logical-jbods</sub>                                                       | GET      |    |    |    |
|<sub>/rest/sas-logical-jbods/{id}</sub>                                                  | GET      |    |    |    |
|<sub>/rest/sas-logical-jbods/{id}/drives</sub>                                           | GET      |    |    |    |
|     **Scopes**                                                                                                                                   |
|<sub>/rest/scopes/ </sub>                                                                | POST     |    |    |    |
|<sub>/rest/scopes/</sub>                                                                 | GET      |    |    |    |
|<sub>/rest/scopes/{id}</sub>                                                             | GET      |    |    |    |
|<sub>/rest/scopes/{id}</sub>                                                             | PUT      |    |    |    |
|<sub>/rest/scopes/{id}</sub>                                                             | PATCH    |    |    |    |
|<sub>/rest/scopes/{id}</sub>                                                             | DELETE   |    |    |    |
|<sub>/rest/scopes/{id}/resource-assignments</sub>                                        | PATCH    |    |    |    |
|     **Server Hardware**                                                                                                                          |
|<sub>/rest/server-hardware</sub>                                                         | GET      |    |    |    |
|<sub>/rest/server-hardware</sub>                                                         | POST     |    |    |    |
|<sub>/rest/server-hardware/{id}</sub>                                                    | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}</sub>                                                    | DELETE   |    |    |    |
|<sub>/rest/server-hardware/{id}/bios</sub>                                               | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/environmentalConfiguration</sub>                         | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/environmentalConfiguration</sub>                         | PUT      |    |    |    |
|<sub>/rest/server-hardware/{id}/iloSsoUrl</sub>                                          | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/javaRemoteConsole</sub>                                  | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/mpFirmwareVersion</sub>                                  | PUT      |    |    |    |
|<sub>/rest/server-hardware/{id}/physicalServerHardware</sub>                             | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/powerState</sub>                                         | PUT      |    |    |    |
|<sub>/rest/server-hardware/{id}/refreshState</sub>                                       | PUT      |    |    |    |
|<sub>/rest/server-hardware/{id}/remoteConsoleUrl</sub>                                   | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/utilization</sub>                                        | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}                                                          | PATCH    |    |    |    |
|<sub>/rest/server-hardware/*/firmware                                                    | GET      |    |    |    |
|<sub>/rest/server-hardware/{id}/firmware                                                 | GET      |    |    |    |
|     **Server Hardware Type**                                                                                                                     |
|<sub>/rest/server-hardware-types</sub>                                                   | GET      |    |    |    |
|<sub>/rest/server-hardware-types/{id}</sub>                                              | GET      |    |    |    |
|<sub>/rest/server-hardware-types/{id}</sub>                                              | PUT      |    |    |    |
|<sub>/rest/server-hardware-types/{id}</sub>                                              | DELETE   |    |    |    |
|     **Server Profile**                                                                                                                           |
|<sub>/rest/server-profiles</sub>                                                         | GET      |    |    |    |
|<sub>/rest/server-profiles</sub>                                                         | POST     |    |    |    |
|<sub>/rest/server-profiles</sub>                                                         | DELETE   |    |    |    |
|<sub>/rest/server-profiles/available-networks</sub>                                      | GET      |    |    |    |
|<sub>/rest/server-profiles/available-servers</sub>                                       | GET      |    |    |    |
|<sub>/rest/server-profiles/available-storage-systems</sub>                               | GET      |    |    |    |
|<sub>/rest/server-profiles/available-targets</sub>                                       | GET      |    |    |    |
|<sub>/rest/server-profiles/profile-ports</sub>                                           | GET      |    |    |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | GET      |    |    |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | PUT      |    |    |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | DELETE   |    |    |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | PATCH    |    |    |    |
|<sub>/rest/server-profiles/{id}/compliance-preview</sub>                                 | GET      |    |    |    |
|<sub>/rest/server-profiles/{id}/new-profile-template</sub>                               | GET      |    |    |    |
|<sub>/rest/server-profiles/{id}/messages</sub>                                           | GET      |    |    |    |
|<sub>/rest/server-profiles/{id}/transformation</sub>                                     | GET      |    |    |    |
|     **Server Profile Template**                                                                                                                  |
|<sub>/rest/server-profile-templates</sub>                                                | GET      |    |    |    |
|<sub>/rest/server-profile-templates</sub>                                                | POST     |    |    |    |
|<sub>/rest/server-profile-templates/{id}</sub>                                           | GET      |    |    |    |
|<sub>/rest/server-profile-templates/{id}</sub>                                           | PUT      |    |    |    |
|<sub>/rest/server-profile-templates/{id}</sub>                                           | DELETE   |    |    |    |
|<sub>/rest/server-profile-templates/{id}/new-profile</sub>                               | GET      |    |    |    |
|<sub>/rest/server-profile-templates/{id}/transformation</sub>                            | GET      |    |    |    |
|     **Storage Pools**                                                                                                                            |
|<sub>/rest/storage-pools</sub>                                                           | GET      |    |    |    |
|<sub>/rest/storage-pools</sub>                                                           | POST     |    |    |    |
|<sub>/rest/storage-pools/reachable-storage-pools</sub>                                   | GET      |    |    |    |
|<sub>/rest/storage-pools/{id}</sub>                                                      | GET      |    |    |    |
|<sub>/rest/storage-pools/{id}</sub>                                                      | PUT      |    |    |    |
|<sub>/rest/storage-pools/{id}</sub>                                                      | DELETE   |    |    |    |
|     **Storage System**                                                                                                                           |
|<sub>/rest/storage-systems</sub>                                                         | GET      |    |    |    |
|<sub>/rest/storage-systems</sub>                                                         | POST     |    |    |    |
|<sub>/rest/storage-systems/host-types</sub>                                              | GET      |    |    |    |
|<sub>/rest/storage-systems/{arrayId}/storage-pools</sub>                                 | GET      |    |    |    |
|<sub>/rest/storage-systems/{id}</sub>                                                    | GET      |    |    |    |
|<sub>/rest/storage-systems/{id}</sub>                                                    | PUT      |    |    |    |
|<sub>/rest/storage-systems/{id}</sub>                                                    | DELETE   |    |    |    |
|<sub>/rest/storage-systems/{id}/managedPorts</sub>                                       | GET      |    |    |    |
|<sub>/rest/storage-systems/{id}/managedPorts/{portId}</sub>                              | GET      |    |    |    |
|<sub>/rest/storage-systems/{id}/reachable-ports</sub>                                    | GET      |    |    |    |
|<sub>/rest/storage-systems/{id}/templates</sub>                                          | GET      |    |    |    |
|     **Storage Volume Attachment**                                                                                                                |
|<sub>/rest/storage-volume-attachments</sub>                                              | GET      |    |    |    |
|<sub>/rest/storage-volume-attachments/{id}</sub>                                         | GET      |    |    |    |
|<sub>/rest/storage-volume-attachments/repair</sub>                                       | GET      |    |    |    |
|<sub>/rest/storage-volume-attachments/repair</sub>                                       | POST     |    |    |    |
|<sub>/rest/storage-volume-attachments/{id}/paths</sub>                                   | GET      |    |    |    |
|<sub>/rest/storage-volume-attachments/{attachmentId)/paths/{id}</sub>                    | GET      |    |    |    |
|     **Storage Volume Template**                                                                                                                  |
|<sub>/rest/storage-volume-templates</sub>                                                | GET      |    |    |    |
|<sub>/rest/storage-volume-templates</sub>                                                | POST     |    |    |    |
|<sub>/rest/storage-volume-templates/connectable-volume-templates</sub>                   | GET      |    |    |    |
|<sub>/rest/storage-volume-templates/reachable-volume-templates</sub>                     | GET      |    |    |    |
|<sub>/rest/storage-volume-templates/{id}</sub>                                           | GET      |    |    |    |
|<sub>/rest/storage-volume-templates/{id}</sub>                                           | PUT      |    |    |    |
|<sub>/rest/storage-volume-templates/{id}</sub>                                           | DELETE   |    |    |    |
|<sub>/rest/storage-volume-templates/{id}/compatible-systems</sub>                        | GET      |    |    |    |
|     **Users**                                                                                                                                   |
|<sub>/rest/users</sub>                                                                   | GET      |    |    |    |
|<sub>/rest/users</sub>                                                                   | POST     |    |    |    |
|<sub>/rest/users</sub>                                                                   | PUT      |    |    |    |
|<sub>/rest/users</sub>                                                                   | DELETE   |    |    |    |
|<sub>/rest/users/administrator/resetPassword</sub>                                       | PUT      |    |    |    |
|<sub>/rest/users/changePassword</sub>                                                    | POST     |    |    |    |
|<sub>/rest/users/role/{userName}</sub>                                                   | GET      |    |    |    |
|<sub>/rest/users/roles</sub>                                                             | DELETE   |    |    |    |
|<sub>/rest/users/roles/users/{role}</sub>                                                | GET      |    |    |    |
|<sub>/rest/users/validateLoginName/{userName}</sub>                                      | POST     |    |    |    |
|<sub>/rest/users/validateUserName/{fullName}</sub>                                       | POST     |    |    |    |
|<sub>/rest/users/{userName}</sub>                                                        | GET      |    |    |    |
|<sub>/rest/users/{userName}</sub>                                                        | DELETE   |    |    |    |
|<sub>/rest/users/{userName}/roles</sub>                                                  | POST     |    |    |    |
|<sub>/rest/users/{userName}/roles</sub>                                                  | PUT      |    |    |    |
|     **Volume**                                                                                                                                   |
|<sub>/rest/storage-volumes</sub>                                                         | GET      |    |    |    |
|<sub>/rest/storage-volumes</sub>                                                         | POST     |    |    |    |
|<sub>/rest/storage-volumes/attachable-volumes</sub>                                      | GET      |    |    |    |
|<sub>/rest/storage-volumes/from-existing</sub>                                           | POST     |    |    |    |
|<sub>/rest/storage-volumes/from-snapshot</sub>                                           | POST     |    |    |    |
|<sub>/rest/storage-volumes/repair</sub>                                                  | GET      |    |    |    |
|<sub>/rest/storage-volumes/repair</sub>                                                  | POST     |    |    |    |
|<sub>/rest/storage-volumes/{id}</sub>                                                    | GET      |    |    |    |
|<sub>/rest/storage-volumes/{id}</sub>                                                    | PUT      |    |    |    |
|<sub>/rest/storage-volumes/{id}</sub>                                                    | DELETE   |    |    |    |
|<sub>/rest/storage-volumes/{id}/snapshots</sub>                                          | GET      |    |    |    |
|<sub>/rest/storage-volumes/{id}/snapshots</sub>                                          | POST     |    |    |    |
|<sub>/rest/storage-volumes/{id}/snapshots/{snapshotId}</sub>                             | GET      |    |    |    |
|<sub>/rest/storage-volumes/{id}/snapshots/{snapshotId}</sub>                             | DELETE   |    |    |    |
|     **Switch**                                                                                                                                   |
|<sub>/rest/switches</sub>                                                                | GET      |    |    |    |
|<sub>/rest/switches/{id}</sub>                                                           | GET      |    |    |    |
|<sub>/rest/switches/{id}</sub>                                                           | PATCH    |    |    |    |
|<sub>/rest/switches/{id}</sub>                                                           | DELETE   |    |    |    |
|<sub>/rest/switches/{id}/environmentalConfiguration</sub>                                | GET      |    |    |    |
|<sub>/rest/switches/{id}/statistics</sub>                                                | GET      |    |    |    |
|<sub>/rest/switches/{id}/statistics/{portName:.+}</sub>                                  | GET      |    |    |    |
|<sub>/rest/switches/{id}/update-ports</sub>                                              | PUT      |    |    |    |
|     **Switch Type**                                                                                                                              |
|<sub>/rest/switch-types</sub>                                                            | GET      |    |    |    |
|<sub>/rest/switch-types/{id}</sub>                                                       | GET      |    |    |    |
|     **Task**                                                                                                                                     |
|<sub>/rest/tasks</sub>                                                                   | GET      |    |    |
|<sub>/rest/tasks/{id}</sub>                                                              | GET      |    |    |
|     **Unmanaged Device**                                                                                                                         |
|<sub>/rest/unmanaged-devices</sub>                                                       | GET      |    |    |    |
|<sub>/rest/unmanaged-devices</sub>                                                       | POST     |    |    |    |
|<sub>/rest/unmanaged-devices</sub>                                                       | DELETE   |    |    |    |
|<sub>/rest/unmanaged-devices/{id}</sub>                                                  | GET      |    |    |    |
|<sub>/rest/unmanaged-devices/{id}</sub>                                                  | PUT      |    |    |    |
|<sub>/rest/unmanaged-devices/{id}</sub>                                                  | DELETE   |    |    |    |
|<sub>/rest/unmanaged-devices/{id}/environmentalConfiguration</sub>                       | GET      |    |    |    |
|     **Uplink Sets**                                                                                                                              |
|<sub>/rest/uplink-sets</sub>                                                             | GET      |    |    |    |
|<sub>/rest/uplink-sets</sub>                                                             | POST     |    |    |    |
|<sub>/rest/uplink-sets/{id}</sub>                                                        | GET      |    |    |    |
|<sub>/rest/uplink-sets/{id}</sub>                                                        | PUT      |    |    |    |
|<sub>/rest/uplink-sets/{id}</sub>                                                        | DELETE   |    |    |    |
|     **Version**                                                                                                                         |
|<sub>/rest/version</sub>                                                                 | GET      |                      |                      |                      |


## HPE Synergy Image Streamer

| Endpoints                                               | Verb    | V300               |
| --------------------------------------------------------------------------------- | ------- | :----------------: |
|     **Artifacts Bundle**                                                                                         |
|<sub>	/rest/artifact-bundles	</sub>                                                  | GET | :white_check_mark: |
|<sub>	/rest/artifact-bundles	</sub>                                       |  POST(create)  | :white_check_mark: |
|<sub>	/rest/artifact-bundles	</sub>                                       |  POST(upload)  | :white_check_mark: |
|<sub>	/rest/artifact-bundles/backups	</sub>                                          | GET | :white_check_mark: |
|<sub>	/rest/artifact-bundles/backups	</sub>                                 | POST(create) | :white_check_mark: |
|<sub>	/rest/artifact-bundles/backups/archive	</sub>                         | POST(upload) | :white_check_mark: |
|<sub>	/rest/artifact-bundles/backups/archive/{id}	</sub>                          | GET | :white_check_mark: |
|<sub>	/rest/artifact-bundles/backups/{id}	</sub>                                  | GET | :white_check_mark: |
|<sub>	/rest/artifact-bundles/backups/{id}	</sub>                                  | PUT | :white_check_mark: |
|<sub>	/rest/artifact-bundles/download/{id}	</sub>                                  | GET | :white_check_mark: |
|<sub>	/rest/artifact-bundles/{id}	</sub>                                          | GET | :white_check_mark: |
|<sub>	/rest/artifact-bundles/{id}	</sub>                             | PUT(extract)     | :white_check_mark: |
|<sub>	/rest/artifact-bundles/{id}	</sub>                             | PUT(update attr) | :white_check_mark: |
|<sub>	/rest/artifact-bundles/{id}	</sub>                             | DELETE           | :white_check_mark: |
|<sub>	/rest/artifact-bundles/{id}/stopArtifactCreate	</sub>             | PUT              | :white_check_mark: |
|     **Deployment Plans**                                                                                         |
|<sub> /rest/deployment-plans </sub>                                       | POST             | :white_check_mark: |
|<sub> /rest/deployment-plans </sub>                                       | GET              | :white_check_mark: |
|<sub> /rest/deployment-plans/{id} </sub>                                  | GET              | :white_check_mark: |
|<sub> /rest/deployment-plans/{id} </sub>                                  | PUT              | :white_check_mark: |
|<sub> /rest/deployment-plans/{id} </sub>                                  | DELETE           | :white_check_mark: |
|     **Deployment Groups**                                                                                        |
|<sub> /rest/deployment-groups</sub>                                       | GET              | :white_check_mark: |
|<sub> /rest/deployment-groups/{id}</sub>                                  | GET              | :white_check_mark: |
|     **Golden Images**                                                                                            |
|<sub> /rest/golden-images</sub>                                           | POST(create)     | :white_check_mark: |
|<sub> /rest/golden-images</sub>                                           | POST(upload)     | :white_check_mark: |
|<sub> /rest/golden-images</sub>                                           | GET              | :white_check_mark: |
|<sub> /rest/golden-images/{id}</sub>                                      | GET              | :white_check_mark: |
|<sub> /rest/golden-images/archive/{id}</sub>                              | GET              | :white_check_mark: |
|<sub> /rest/golden-images/download/{id}</sub>                             | GET              | :white_check_mark: |
|<sub> /rest/golden-images/{id}</sub>                                      | PUT              | :white_check_mark: |
|<sub> /rest/golden-images/{id}</sub>                                      | DELETE           | :white_check_mark: |
|     **OS Build Plan**                                                                                            |
|<sub> /rest/build-plans</sub>                                             | POST             | :white_check_mark: |
|<sub> /rest/build-plans</sub>                                             | GET              | :white_check_mark: |
|<sub> /rest/build-plans/{id}</sub>                                        | GET              | :white_check_mark: |
|<sub> /rest/build-plans/{id}</sub>                                        | PUT              | :white_check_mark: |
|<sub> /rest/build-plans/{id}</sub>                                        | DELETE           | :white_check_mark: |
|     **OS Volumes**                                                                                               |
|<sub> /rest/os-volumes</sub>                                              | GET              | :white_check_mark: |
|<sub> /rest/os-volumes/{id}</sub>                                         | GET              | :white_check_mark: |
|<sub> /rest/os-volumes/archive/{id}</sub>                                 | GET              | :white_check_mark: |
|     **Plan Scripts**                                                                                             |
|<sub> /rest/plan-scripts</sub>                                            | POST             | :white_check_mark: |
|<sub> /rest/plan-scripts/differences/{id}</sub>                           | POST             | :white_check_mark: |
|<sub> /rest/plan-scripts</sub>                                            | GET              | :white_check_mark: |
|<sub> /rest/plan-scripts/{id}</sub>                                       | GET              | :white_check_mark: |
|<sub> /rest/plan-scripts/{id}</sub>                                       | PUT              | :white_check_mark: |
|<sub> /rest/plan-scripts/{id}</sub>                                       | DELETE           | :white_check_mark: |

