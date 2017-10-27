***Legend***

| Item | Meaning |
| ---------------------- | -------------------------------------------------------------------- |
|  :white_check_mark:    | Endpoint implemented in the Java SDK and tested for this API version |
|  :black_square_button: | Endpoint to be implemented                                           |
|  :black_circle:        | Endpoint considered as 'out-of-scope' for the Java SDK               |
|  :heavy_minus_sign:    | Endpoint not available for this API Version                          |



<br />

***Notes***


<br />

## HPE OneView

| Endpoints                                                                               | Verb     | V200                 | V300                 | V500                 |
| --------------------------------------------------------------------------------------- | -------- | :------------------: | :------------------: | :------------------: |
|     **Alerts**                                                                                                                                   |
|<sub>/rest/alerts	</sub>                                                                |GET       | :white_check_mark:  | :white_check_mark: | 
|<sub>/rest/alerts	</sub>                                                                |DELETE    | :white_check_mark: | :white_check_mark: |
|<sub>/rest/alerts/{id}	</sub>                                                            |GET       | :white_check_mark: | :white_check_mark: |
|<sub>/rest/alerts/{id}	</sub>                                                            |PUT       | :white_check_mark: | :white_check_mark: |
|<sub>/rest/alerts/{id}	</sub>                                                            |DELETE    | :white_check_mark: | :white_check_mark: |
|<sub>/rest/alerts/AlertChangeLog/{id}</sub>                                              |DELETE    | :white_check_mark: | :white_check_mark: |
|     **Appliance Time and Locale Configuration**                                                                                                                         |
|<sub>/rest/appliance/configuration/time-locale</sub>                                     |GET       | :black_circle: | :black_circle: |    |
|<sub>/rest/appliance/configuration/time-locale</sub>                                     |POST      | :black_circle: | :black_circle: |    |
|     **Backups**                                                                                                                                  |
|<sub>/rest/backups</sub>                                                                 |GET       | :black_square_button: | :black_square_button: |
|<sub>/rest/backups</sub>                                                                 |POST      | :black_square_button: | :black_square_button: |
|<sub>/rest/backups/archive</sub>                                                         |POST      | :black_square_button: | :black_square_button: |
|<sub>/rest/backups/archive/{id}</sub>                                                    |GET       | :black_square_button: | :black_square_button: |
|<sub>/rest/backups/config</sub>                                                          |GET       | :heavy_minus_sign: | :black_square_button: |
|<sub>/rest/backups/config</sub>                                                          |PUT       | :heavy_minus_sign: | :black_square_button: |
|<sub>/rest/backups/remotearchive/{id}</sub>                                              |PUT       | :heavy_minus_sign: | :black_square_button: |
|<sub>/rest/backups/remotearchive/{id}</sub>                                              |DELETE    | :heavy_minus_sign: | :black_square_button: |
|<sub>/rest/backups/{id}</sub>                                                            |GET       | :black_square_button: | :black_square_button: |
|<sub>/rest/backups/{id}</sub>                                                            |DELETE    | :black_square_button: | :black_square_button: |
|     **Certificate Authority**                                                                                                                    |
|<sub>/rest/certificates/ca</sub>                                                         |GET       | :black_circle: | :black_circle: |
|<sub>/rest/certificates/ca/crl</sub>                                                     |GET       | :black_circle: | :black_circle: |
|<sub>/rest/certificates/ca/{aliasName}</sub>                                             |DELETE    | :black_circle: | :black_circle: |
|     **Certificates Client RabbitMq**                                                                                                             |
|<sub>/rest/certificates/client/rabbitmq</sub>                                            |POST      | :black_circle: | :black_circle: |
|<sub>/rest/certificates/client/rabbitmq/keypair/{aliasName}</sub>                        |GET       | :black_circle: | :black_circle: |
|<sub>/rest/certificates/client/rabbitmq/keys/{aliasName}</sub>                           |GET       | :black_circle: | :black_circle: |
|<sub>/rest/certificates/client/rabbitmq/{aliasName}</sub>                                |GET       | :black_circle: | :black_circle: |
|     **Connection Templates**                                                                                                                      |
|<sub>/rest/connection-templates</sub>                                                    |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/connection-templates/defaultConnectionTemplate</sub>                          |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/connection-templates/{id}</sub>                                               |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/connection-templates/{id}</sub>                                               |PUT       | :white_check_mark: | :white_check_mark: |    |
|     **Datacenter**                                                                                                                               |
|<sub>/rest/datacenters</sub>                                                             | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/datacenters</sub>                                                             | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/datacenters</sub>                                                             | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/datacenters/{id}</sub>                                                        | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/datacenters/{id}</sub>                                                        | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/datacenters/{id}</sub>                                                        | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/datacenters/{id}/visualContent</sub>                                          | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Drive Enclosures**                                                                                                                         |
|<sub>/rest/drive-enclosures</sub>                                                        | GET      | :heavy_minus_sign:  | :white_check_mark: |    |
|<sub>/rest/drive-enclosures/{id}</sub>                                                   | GET      | :heavy_minus_sign:  | :white_check_mark: |    |
|<sub>/rest/drive-enclosures/{id}</sub>                                                   | PATCH    | :heavy_minus_sign:  | :white_check_mark: |    |
|<sub>/rest/drive-enclosures/{id}/port-map</sub>                                          | GET      | :heavy_minus_sign:  | :white_check_mark: |    |
|<sub>/rest/drive-enclosures/{id}/refreshState</sub>                                      | PUT      | :heavy_minus_sign:  | :white_check_mark: |    |
|     **Enclosures**                                                                                                                                |
|<sub>/rest/enclosures</sub>                                                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures</sub>                                                              | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}</sub>                                                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}</sub>                                                         | PATCH    | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}</sub>                                                         | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/configuration</sub>                                           | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/environmentalConfiguration</sub>                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/environmentalConfiguration</sub>                              | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/refreshState</sub>                                            | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/script</sub>                                                  | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/sso</sub>                                                     | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosures/{id}/utilization</sub>                                             | GET      | :white_check_mark: | :white_check_mark:  |    |
|     **Enclosure Groups**                                                                                                                          |
|<sub>/rest/enclosure-groups</sub>                                                        | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosure-groups</sub>                                                        | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosure-groups/{id}</sub>                                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosure-groups/{id}</sub>                                                   | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosure-groups/{id}</sub>                                                   | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosure-groups/script</sub>                                                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/enclosure-groups/script</sub>                                                 | PUT      | :white_check_mark: | :white_check_mark: |    |
|     **Endpoints**                                                                                                                                |
|<sub>/rest/fc-sans/endpoints</sub>                                                       | GET      | :black_circle: | :black_circle: |
|     **Ethernet Networks**                                                                                                                         |
|<sub>/rest/ethernet-networks</sub>                                                       | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks</sub>                                                       | POST     | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/bulk</sub>                                                  | POST     | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | PUT      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | PATCH    | :heavy_minus_sign: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/{id}</sub>                                                  | DELETE   | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/{id}/associatedProfiles</sub>                               | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/ethernet-networks/{id}/associatedUplinkGroups</sub>                           | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|     **Events**                                                                                                                         |
|<sub>/rest/events</sub>                                                                  | GET      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/events</sub>                                                                  | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/events/{id}</sub>                                                             | GET      | :black_square_button: | :black_square_button: |    |
|     **Fabric**                                                                                                                                   |
|<sub>/rest/fabrics</sub>                                                                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fabrics/{id}</sub>                                                            | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fabrics/{id}/reserved-vlan-range</sub>                                        | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/fabrics/{id}/reserved-vlan-range</sub>                                        | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|     **FC Networks**                                                                                                                               |
|<sub>/rest/fc-networks</sub>                                                             | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fc-networks</sub>                                                             | POST     | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fc-networks/{id}</sub>                                                        | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fc-networks/{id}</sub>                                                        | PATCH    | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fc-networks/{id}</sub>                                                        | PUT      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fc-networks/{id}</sub>                                                        | DELETE   | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|     **FCoE Networks**                                                                                                                             |
|<sub>/rest/fcoe-networks</sub>                                                           | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fcoe-networks</sub>                                                           | POST     | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | PATCH    | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | PUT      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/fcoe-networks/{id}</sub>                                                      | DELETE   | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|     **Firmware Bundles**                                                                                                                          |
|<sub>/rest/firmware-bundles/{id}/script</sub>                                            | POST     | :white_check_mark: | :white_check_mark: |    |
|     **Firmware Drivers**                                                                                                                          |
|<sub>/rest/firmware-drivers/</sub>                                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/firmware-drivers/</sub>                                                       | POST     | :black_circle: | :black_circle: |    |
|<sub>/rest/firmware-drivers/{id}</sub>                                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/firmware-drivers/{id}</sub>                                                   | DELETE   | :white_check_mark: | :white_check_mark: |    |
|     **Id Pools IPv4 Ranges**                                                                                                                      |
|<sub>/rest/id-pools/ipv4/ranges</sub>                                                    | POST     | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/ranges/{id}</sub>                                               | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/ranges/{id}</sub>                                               | PUT      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/ranges/{id}</sub>                                               | DELETE   | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/ranges/{id}/allocated-fragments</sub>                           | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/ranges/{id}/free-fragments</sub>                                | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/subnets</sub>                                                   | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/subnets</sub>                                                   | POST     | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/subnets/{id}</sub>                                              | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/subnets/{id}</sub>                                              | PUT      | :black_square_button: | :black_square_button: |
|<sub>/rest/id-pools/ipv4/subnets/{id}</sub>                                              | DELETE   | :black_square_button: | :black_square_button: |
|     **Id Pools vMAC Ranges**                                                                                                                      |
|<sub>/rest/id-pools/vmac/ranges</sub>                                                    | POST     | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}</sub>                                               | GET      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}</sub>                                               | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}</sub>                                               | DELETE   | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}/allocated-fragments</sub>                           | GET      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}/allocator</sub>                                     |PUT       | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}/collector</sub>                                     | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vmac/ranges/{id}/free-fragments</sub>                                | GET      | :black_circle: | :black_circle: |
|     **Id Pools vSN Ranges**                                                                                                                       |
|<sub>/rest/id-pools/vsn/ranges</sub>                                                     | POST     | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}</sub>                                                | GET      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}</sub>                                                | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}</sub>                                                | DELETE   | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}/allocated-fragments</sub>                            | GET      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}/allocator</sub>                                      | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}/collector</sub>                                      | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vsn/ranges/{id}/free-fragments</sub>                                 | GET      | :black_circle: | :black_circle: |
|     **Id Pools vWWN Ranges**                                                                                                                      |
|<sub>/rest/id-pools/vwwn/ranges</sub>                                                    | POST     | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}</sub>                                               | GET      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}</sub>                                               | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}</sub>                                               | DELETE   | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}/allocated-fragments</sub>                           | GET      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}/allocator</sub>                                     | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}/collector</sub>                                     | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/id-pools/vwwn/ranges/{id}/free-fragments</sub>                                | GET      | :black_circle: | :black_circle: |
|     **Internal Link Sets**                                                                                                                       |
|<sub>/rest/internal-link-sets</sub>                                                      | GET      | :heavy_minus_sign: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/internal-link-sets/{id}</sub>                                                 | GET      | :heavy_minus_sign: | :white_check_mark: | :white_check_mark: |
|     **Interconnect Link Topologies**                                                                                                                       |
|<sub>/rest/interconnect-link-topologies</sub>                                            | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/interconnect-link-topologies{id}</sub>                                        | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **Interconnect Types**                                                                                                                        |
|<sub>/rest/interconnect-types</sub>                                                      | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/interconnect-types/{id}</sub>                                                 | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|     **Interconnects**                                                                                                                            |
|<sub>/rest/interconnects</sub>                                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}</sub>                                                      | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}</sub>                                                      | PATCH    | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/configuration</sub>                                        | PUT      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/interconnects/{id}/pluggableModuleInformation</sub>                           | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/interconnects/{id}/ports</sub>                                                | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/ports</sub>                                                | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/ports/{portId:.+}</sub>                                    | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/resetportprotection</sub>                                  | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/statistics</sub>                                           | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/statistics/{portName:.+}</sub>                             | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/statistics/{portName:.+}/subport/{subportNum}</sub>        | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/update-ports</sub>                                         | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/interconnects/{id}/nameServers</sub>                                          | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Labels**                                                                                                                                   |
|<sub>/rest/labels</sub>                                                                  | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/labels/resources</sub>                                                        | POST     | :black_square_button: | :black_square_button: |
|<sub>/rest/labels/resources/**</sub>                                                     | GET      | :black_square_button: | :black_square_button: |
|<sub>/rest/labels/resources/**</sub>                                                     | PUT      | :black_square_button: | :black_square_button: |
|<sub>/rest/labels/resources/**</sub>                                                     | DELETE   | :black_square_button: | :black_square_button: |
|<sub>/rest/labels/resources/{id}</sub>                                                   | GET      | :black_square_button: | :black_square_button: |
|     **Licenses**                                                                                                                                 |
|<sub>/rest/licenses</sub>                                                                |GET       |    | :white_check_mark: | :white_check_mark: |
|<sub>/rest/licenses</sub>                                                                |POST      |    | :white_check_mark: | :white_check_mark: |
|<sub>/rest/licenses/{id}</sub>                                                           |GET       |    | :white_check_mark: | :white_check_mark: |
|<sub>/rest/licenses/{licenseId}</sub>                                                    |GET       |    | :white_check_mark: | :white_check_mark: |
|     **Logical Downlinks**                                                                                                                         |
|<sub>/rest/logical-downlinks</sub>                                                       |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-downlinks/withoutEthernet</sub>                                       |GET       | :white_check_mark: | :heavy_minus_sign: |    |
|<sub>/rest/logical-downlinks/{id}</sub>                                                  |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-downlinks/{id}/withoutEthernet</sub>                                  |GET       | :white_check_mark: | :heavy_minus_sign: |    |
|     **Logical Enclosures**                                                                                                                        |
|<sub>/rest/logical-enclosures</sub>                                                      | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures</sub>                                                      | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | PATCH    | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}</sub>                                                 | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}/configuration</sub>                                   | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}/script</sub>                                          | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}/script</sub>                                          | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}/support-dumps</sub>                                   | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-enclosures/{id}/updateFromGroup</sub>                                 | PUT      | :white_check_mark: | :white_check_mark: |    |
|     **Logical Interconnects**                                                                                                                     |
|<sub>/rest/logical-interconnects</sub>                                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/locations/interconnects</sub>                           | POST     | :white_check_mark: | |    |
|<sub>/rest/logical-interconnects/locations/interconnects</sub>                           | DELETE   | :white_check_mark: | |    |
|<sub>/rest/logical-interconnects/{id}</sub>                                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/compliance</sub>                                   | PUT      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/logical-interconnects/{id}/ethernetSettings</sub>                             | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/ethernetSettings</sub>                             | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/firmware</sub>                                     | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/firmware</sub>                                     | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/forwarding-information-base</sub>                  | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/forwarding-information-base</sub>                  | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/forwarding-information-base/{dumpFileName}.{suffix}</sub>                  | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/logical-interconnects/{id}/internalNetworks</sub>                             | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/internalVlans</sub>                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/qos-aggregated-configuration</sub>                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/qos-aggregated-configuration</sub>                 | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/settings</sub>                                     | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/snmp-configuration</sub>                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/snmp-configuration</sub>                           | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/support-dumps</sub>                                | POST     | :heavy_minus_sign: |  :heavy_minus_sign: |                      |
|<sub>/rest/logical-interconnects/{id}/unassignedUplinkPortsForPortMonitor</sub>          | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/configuration</sub>                                | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/port-monitor</sub>                                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/port-monitor</sub>                                 | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-interconnects/{id}/telemetry-configurations/{tcId}</sub>              | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Logical Interconnect Groups**                                                                                                               |
|<sub>/rest/logical-interconnect-groups</sub>                                             | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups</sub>                                             | POST     | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/defaultSettings</sub>                             | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | PUT      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | PATCH    | | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}</sub>                                        | DELETE   | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|<sub>/rest/logical-interconnect-groups/{id}/settings</sub>                               | GET      | :white_check_mark: | :white_check_mark: | :white_check_mark: |
|     **Logical Switch Groups**                                                                                                                     |
|<sub>/rest/logical-switch-groups</sub>                                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switch-groups</sub>                                                   |POST      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |PATCH     | | :black_square_button: | :black_square_button:  |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |PUT       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switch-groups/{id}</sub>                                              |DELETE    | :white_check_mark: | :white_check_mark: |    |
|     **Logical Switches**                                                                                                                         |
|<sub>/rest/logical-switches</sub>                                                        | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switches</sub>                                                        |POST      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switches/{id}</sub>                                                   |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switches/{id}</sub>                                                   |PATCH     | | :black_square_button: | :black_square_button:   |
|<sub>/rest/logical-switches/{id}</sub>                                                   |PUT       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switches/{id}</sub>                                                   |DELETE    | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/logical-switches/{id}/refresh</sub>                                           |PUT       | :white_check_mark: | :white_check_mark: |    |
|     **Login Details**                                                                                                                                                   |
|<sub>/rest/logindetails</sub>                                                            | GET      | :black_square_button: | :black_square_button: |   |
|     **Managed SANs**                                                                                                                             |
|<sub>/rest/fc-sans/managed-sans</sub>                                                    | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/managed-sans/{id}</sub>                                               | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/managed-sans/{id}</sub>                                               | PUT      | :white_check_mark: | :heavy_minus_sign: |    |
|<sub>/rest/fc-sans/managed-sans/{id}/endpoints</sub>                                     | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/managed-sans/{id}/endpoints</sub>                                     | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/managed-sans/{id}/issues</sub>                                        | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/managed-sans/WWN+</sub>                                               | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **Metric Streaming**                                                                                                                         |
|<sub>/rest/metrics/capability</sub>                                                      | GET      | :black_circle:  | :black_circle: |    |
|<sub>/rest/metrics/configuration</sub>                                                   | GET      | :black_circle: | :black_circle: |    |
|<sub>/rest/metrics/configuration</sub>                                                   | PUT      | :black_circle: | :black_circle: |    |
|     **Migratable VC Domains**                                                                                                                    |
|<sub>/rest/migratable-vc-domains</sub>                                                   | GET      | :heavy_minus_sign: | :black_circle: |
|<sub>/rest/migratable-vc-domains</sub>                                                   | POST     | :black_circle: | :black_circle: |
|<sub>/rest/migratable-vc-domains/{id}</sub>                                               | PUT      | :black_circle: | :black_circle: |
|<sub>/rest/migratable-vc-domains/{id}</sub>                                               | GET      | :black_circle: | :black_circle: |
|<sub>/rest/migratable-vc-domains/{id}</sub>                                               | DELETE   | :black_circle: | :black_circle: |
|     **Network Sets**                                                                                                                              |
|<sub>/rest/network-sets</sub>                                                            |GET       | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/network-sets</sub>                                                            | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/network-sets/withoutEthernet</sub>                                            | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/network-sets/{id}</sub>                                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/network-sets/{id}</sub>                                                       | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/network-sets/{id}</sub>                                                       | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/network-sets/{id}/withoutEthernet</sub>                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|     **OS Deployment Plans**                                                                                                                      |
|<sub>/rest/os-deployment-plans/</sub>                                                    | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/os-deployment-plans/{id}</sub>                                                | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **OS Deployment Servers**                                                                                                                      |
|<sub>/rest/os-deployment-servers/</sub>                                                  | GET      | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/os-deployment-servers/</sub>                                                  | POST     | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/os-deployment-servers/image-streamer-appliances/</sub>                        | GET      | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/os-deployment-servers/image-streamer-appliances/{id}</sub>                    | GET      | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/os-deployment-servers/network</sub>                                           | GET      | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/deployment-servers/security-mode/security-compatibility-checker</sub>         | POST     | :heavy_minus_sign: | :black_square_button: |                       
|<sub>/rest/os-deployment-servers/{id}/</sub>                                             | GET      | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/os-deployment-servers/{id}/</sub>                                             | PUT      | :heavy_minus_sign: | :black_square_button: |                      |
|<sub>/rest/os-deployment-servers/{id}/</sub>                                             | DELETE   | :heavy_minus_sign: | :black_square_button: |                      |
|     **Power Devices**                                                                                                                             |
|<sub>/rest/power-devices</sub>                                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices</sub>                                                           | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices</sub>                                                           | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/discover</sub>                                                  | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}</sub>                                                      | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}</sub>                                                      | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}</sub>                                                      | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/powerState</sub>                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/powerState</sub>                                           | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/refreshState</sub>                                         | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/synchronous</sub>                                          | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/uidState</sub>                                             | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/uidState</sub>                                             | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/power-devices/{id}/utilization</sub>                                          | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Racks**                                                                                                                                     |
|<sub>/rest/racks</sub>                                                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/racks</sub>                                                                   | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/racks</sub>                                                                   | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/racks/{id}</sub>                                                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/racks/{id}</sub>                                                              | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/racks/{id}</sub>                                                              | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/racks/{id}/deviceTopology</sub>                                               | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Restores**                                                                                                                                     |
|<sub>/rest/restores</sub>                                                                | GET     | :black_square_button: | :black_square_button: |                      |
|<sub>/rest/restores</sub>                                                                | POST    | :black_square_button: | :black_square_button: |                      |
|<sub>/rest/restores/failures</sub>                                                       | GET     | :black_square_button: | :black_square_button: |                      |
|<sub>/rest/restores/{id}</sub>                                                           | GET     | :black_square_button: | :black_square_button: |                      |
|     **Roles**                                                                                                                                     |
|<sub>/rest/roles</sub>                                                                   | GET      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/roles/{roleName}</sub>                                                        | GET      | :black_square_button: | :black_square_button: |    |
|     **SAN Managers**                                                                                                                             |
|<sub>/rest/fc-sans/device-managers</sub>                                                 | GET      | :white_check_mark: | :heavy_minus_sign: |    |
|<sub>/rest/fc-sans/device-managers/{id}</sub>                                            | GET      | :white_check_mark: | :heavy_minus_sign: |    |
|<sub>/rest/fc-sans/device-managers/{id}</sub>                                            | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/device-managers/{id}</sub>                                            | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/fc-sans/providers/{id}/device-managers</sub>                                  | POST     | :white_check_mark: | :heavy_minus_sign: |    |
|     **SAS Interconnect Types**                                                                                                                   |
|<sub>/rest/sas-interconnect-types</sub>                                                  | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-interconnect-types/{id}</sub>                                             | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **SAS Interconnects**                                                                                                                        |
|<sub>/rest/sas-interconnects/{id}</sub>                                                  | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-interconnects/{id}</sub>                                                  | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-interconnects/{id}</sub>                                                  | PATCH    | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-interconnects/{id}/refreshState</sub>                                     | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|     **SAS Logical Interconnect Groups**                                                                                                          |
|<sub>/rest/sas-logical-interconnect-groups</sub>                                         | POST     | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnect-groups</sub>                                         | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnect-groups/{id}</sub>                                    | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnect-groups/{id}</sub>                                    | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnect-groups/{id}</sub>                                    | DELETE   | :heavy_minus_sign: | :white_check_mark: |    |
|     **SAS Logical Interconnects**                                                                                                                |
|<sub>/rest/sas-logical-interconnects</sub>                                               | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/{id}</sub>                                          | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/{id}/firmware</sub>                                 | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/{id}/firmware</sub>                                 | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/compliance</sub>                                    | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/{id}/compliance</sub>                               | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/{lsId}/configuration</sub>                          | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-interconnects/{id}/replaceDriveEnclosure</sub>                    | POST     | :heavy_minus_sign: | :white_check_mark: |    |
|     **SAS Logical JBOD Attachments**                                                                                                             |
|<sub>/rest/sas-logical-jbod-attachments</sub>                                            | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-jbod-attachments/{id}</sub>                                       | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **SAS Logical JBODs**                                                                                                                        |
|<sub>/rest/sas-logical-jbods</sub>                                                       | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-jbods/{id}</sub>                                                  | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/sas-logical-jbods/{id}/drives</sub>                                           | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **Scopes**                                                                                                                                   |
|<sub>/rest/scopes/ </sub>                                                                | POST     | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/scopes/</sub>                                                                 | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/scopes/{id}</sub>                                                             | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/scopes/{id}</sub>                                                             | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/scopes/{id}</sub>                                                             | PATCH    | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/scopes/{id}</sub>                                                             | DELETE   | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/scopes/{id}/resource-assignments</sub>                                        | PATCH    | :heavy_minus_sign: | :white_check_mark: |    |
|     **Server Hardware**                                                                                                                          |
|<sub>/rest/server-hardware</sub>                                                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware</sub>                                                         | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}</sub>                                                    | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}</sub>                                                    | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/bios</sub>                                               | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/environmentalConfiguration</sub>                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/environmentalConfiguration</sub>                         | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/iloSsoUrl</sub>                                          | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/javaRemoteConsole</sub>                                  | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/mpFirmwareVersion</sub>                                  | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/physicalServerHardware</sub>                             | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/server-hardware/{id}/powerState</sub>                                         | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/refreshState</sub>                                       | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/remoteConsoleUrl</sub>                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/utilization</sub>                                        | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}                                                          | PATCH    | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/server-hardware/*/firmware                                                    | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|<sub>/rest/server-hardware/{id}/firmware                                                 | GET      | :heavy_minus_sign: | :white_check_mark: |    |
| **Server Hardware Types**                                                                                           |
|<sub>/rest/server-hardware-types</sub>                                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware-types/{id}</sub>                                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware-types/{id}</sub>                                              | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-hardware-types/{id}</sub>                                              | DELETE   | :white_check_mark: | :white_check_mark: |    |
|     **Server Profiles**                                                                                                                           |
|<sub>/rest/server-profiles</sub>                                                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles</sub>                                                         | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles</sub>                                                         | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/available-networks</sub>                                      | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/available-servers</sub>                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/available-storage-system</sub>                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/available-storage-systems</sub>                               | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/available-targets</sub>                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/profile-ports</sub>                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}</sub>                                                    | PATCH    | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}/compliance-preview</sub>                                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}/new-profile-template</sub>                               | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/server-profiles/{id}/messages</sub>                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profiles/{id}/transformation</sub>                                     | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Server Profile Templates**                                                                                                                  |
|<sub>/rest/server-profile-templates</sub>                                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profile-templates</sub>                                                | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profile-templates/{id}</sub>                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profile-templates/{id}</sub>                                           | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profile-templates/{id}</sub>                                           | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profile-templates/{id}/new-profile</sub>                               | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/server-profile-templates/{id}/transformation</sub>                            | GET      | :heavy_minus_sign: | :white_check_mark: |    |
|     **Storage Pools**                                                                                                                            |
|<sub>/rest/storage-pools</sub>                                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-pools</sub>                                                           | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-pools/reachable-storage-pools</sub>                                   | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/storage-pools/{id}</sub>                                                      | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-pools/{id}</sub>                                                      | PUT      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/storage-pools/{id}</sub>                                                      | DELETE   | :white_check_mark: | :white_check_mark: |    |
|     **Storage Systems**                                                                                                                           |
|<sub>/rest/storage-systems</sub>                                                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems</sub>                                                         | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/host-types</sub>                                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{arrayId}/storage-pools</sub>                                 | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{id}</sub>                                                    | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{id}</sub>                                                    | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{id}</sub>                                                    | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{id}/managedPorts</sub>                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{id}/managedPorts/{portId}</sub>                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-systems/{id}/reachable-ports</sub>                                    | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/storage-systems/{id}/templates</sub>                                          | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|     **Storage Volume Attachments**                                                                                                                |
|<sub>/rest/storage-volume-attachments</sub>                                              | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-attachments/{id}</sub>                                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-attachments/repair</sub>                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-attachments/repair</sub>                                       | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-attachments/{attachmentId}/paths</sub>                                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-attachments/{attachmentId)/paths/{id}</sub>                    | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Storage Volume Templates**                                                                                                                  |
|<sub>/rest/storage-volume-templates</sub>                                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-templates</sub>                                                | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-templates/connectable-volume-templates</sub>                   | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-templates/reachable-volume-templates</sub>                     | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/storage-volume-templates/{id}</sub>                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-templates/{id}</sub>                                           | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-templates/{id}</sub>                                           | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volume-templates/{id}/compatible-systems</sub>                        | GET      | :heavy_minus_sign: | :heavy_minus_sign: |    |
|     **Users**                                                                                                                                   |
|<sub>/rest/users</sub>                                                                   | GET      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users</sub>                                                                   | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users</sub>                                                                   | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users</sub>                                                                   | PUT      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users</sub>                                                                   | DELETE   | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/administrator/resetPassword</sub>                                       | PUT      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/changePassword</sub>                                                    | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/role/{userName}</sub>                                                   | GET      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/roles</sub>                                                             | DELETE   | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/roles/users/{role}</sub>                                                | GET      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/validateLoginName/{userName}</sub>                                      | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/validateUserName/{fullName}</sub>                                       | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/{userName}</sub>                                                        | GET      | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/{userName}</sub>                                                        | DELETE   | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/{userName}/roles</sub>                                                  | POST     | :black_square_button: | :black_square_button: |    |
|<sub>/rest/users/{userName}/roles</sub>                                                  | PUT      | :black_square_button: | :black_square_button: |    |
|     **Volumes**                                                                                                                                   |
|<sub>/rest/storage-volumes</sub>                                                         | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes</sub>                                                         | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/attachable-volumes</sub>                                      | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/from-existing</sub>                                           | POST     | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/storage-volumes/from-snapshot</sub>                                           | POST     | :heavy_minus_sign: | :heavy_minus_sign: |    |
|<sub>/rest/storage-volumes/repair</sub>                                                  | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/repair</sub>                                                  | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}</sub>                                                    | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}</sub>                                                    | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}</sub>                                                    | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}/snapshots</sub>                                          | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}/snapshots</sub>                                          | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}/snapshots/{snapshotId}</sub>                             | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/storage-volumes/{id}/snapshots/{snapshotId}</sub>                             | DELETE   | :white_check_mark: | :white_check_mark: |    |
|     **Switches**                                                                                                                                   |
|<sub>/rest/switches</sub>                                                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switches/{id}</sub>                                                           | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switches/{id}</sub>                                                           | PATCH    | :black_square_button: | :black_square_button: |    |
|<sub>/rest/switches/{id}</sub>                                                           | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switches/{id}/environmentalConfiguration</sub>                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switches/{id}/statistics</sub>                                                | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switches/{id}/statistics/{portName:.+}</sub>                                  | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switches/{id}/update-ports</sub>                                              | PUT      | :heavy_minus_sign: | :white_check_mark: |    |
|     **Switch Types**                                                                                                                              |
|<sub>/rest/switch-types</sub>                                                            | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/switch-types/{id}</sub>                                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Tasks**                                                                                                                                     |
|<sub>/rest/tasks</sub>                                                                   | GET      | :white_check_mark: | :white_check_mark: |
|<sub>/rest/tasks/{id}</sub>                                                              | GET      | :white_check_mark: | :white_check_mark: |
|     **Unmanaged Devices**                                                                                                                         |
|<sub>/rest/unmanaged-devices</sub>                                                       | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/unmanaged-devices</sub>                                                       | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/unmanaged-devices</sub>                                                       | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/unmanaged-devices/{id}</sub>                                                  | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/unmanaged-devices/{id}</sub>                                                  | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/unmanaged-devices/{id}</sub>                                                  | DELETE   | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/unmanaged-devices/{id}/environmentalConfiguration</sub>                       | GET      | :white_check_mark: | :white_check_mark: |    |
|     **Uplink Sets**                                                                                                                              |
|<sub>/rest/uplink-sets</sub>                                                             | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/uplink-sets</sub>                                                             | POST     | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/uplink-sets/{id}</sub>                                                        | GET      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/uplink-sets/{id}</sub>                                                        | PUT      | :white_check_mark: | :white_check_mark: |    |
|<sub>/rest/uplink-sets/{id}</sub>                                                        | DELETE   | :white_check_mark: | :white_check_mark: |    |
|     **Version**                                                                                                                         |
|<sub>/rest/version</sub>                                                                 | GET      | :black_square_button: | :black_square_button: |                      |


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

