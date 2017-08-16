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
Feature: In order to set OneView credentials in "oneview_java_sdk_config.properties" file

  Scenario: Creation of OneView properties
    Given an instance of Credentials
      And a DOMAIN "LOCAL"
      And a VERSION "V_300"
      And a FILE_SDK_CONFIG "src/test/resources/oneview_java_sdk_config.properties"
      And OneView credentials as follows:
      | hostname | 10.10.10.10   |
      | username | administrator |
      | password | serveradmin   |
      And Enclosure credentials as follows:
      | hostname | 10.10.10.10 |
      | username | dcs         |
      | password | dcs         |
      And Storage System credentials as follows:
      | hostname | 10.10.10.10 |
      | username | dcs         |
      | password | dcs         |
     When credentials are written at "src/test/resources/oneView.properties"
     Then there is a file "src/test/resources/oneView.properties"
     