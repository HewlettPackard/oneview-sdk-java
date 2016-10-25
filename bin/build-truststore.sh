# !/bin/bash

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

OV_HOSTNAME=$1
OV_USERNAME=$2
OV_PASSWORD=$3

if [ -z "$OV_HOSTNAME" ]; then
	echo "HPE OneView hostname is missing!"
	echo "Usage: build-truststore.sh <hostname> <username> <password>"
	exit 1
fi

if [ -z "$OV_USERNAME" ]; then
	echo "HPE OneView username is missing!"
	echo "Usage: build-truststore.sh <hostname> <username> <password>"
	exit 1
fi

if [ -z "$OV_PASSWORD" ]; then
	echo "HPE OneView password is missing!"
	echo "Usage: build-truststore.sh <hostname> <username> <password>"
	exit 1
fi

echo "Connecting to $OV_HOSTNAME as '$OV_USERNAME' with password '$OV_PASSWORD'"

openssl s_client -connect $OV_HOSTNAME:443 < /dev/null 2>/dev/null | sed -n -e '/BEGIN\ CERTIFICATE/,/END\ CERTIFICATE/ p' > default-server.crt
keytool -importcert -v -trustcacerts -alias $OV_HOSTNAME -file default-server.crt -keystore TrustStore

rm default-server.crt

exit 0
