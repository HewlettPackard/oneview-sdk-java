/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hp.ov.sdk.oneview;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.hp.ov.sdk.resources.activity.AlertBDDTest;
import com.hp.ov.sdk.resources.facilities.AllFacilitiesTestsSynergy;
import com.hp.ov.sdk.resources.network.AllNetworkTestsSynergy;
import com.hp.ov.sdk.resources.security.AllSecurityTestsSynergy;
import com.hp.ov.sdk.resources.server.AllServerTestsSynergy;
import com.hp.ov.sdk.resources.settings.FirmwareDriverBDDTest;
import com.hp.ov.sdk.resources.storage.AllStorageTestsSynergy;


@RunWith(Suite.class)
@SuiteClasses({ 
    /* OneViewCredentialsBDDTest.class,
     * Enable this one if you want to have oneview_java_sdk_config.properties file configured using the .feature file */
    AllFacilitiesTestsSynergy.class,
    FirmwareDriverBDDTest.class,
    AllNetworkTestsSynergy.class,
    AllSecurityTestsSynergy.class,
    AllServerTestsSynergy.class,
    AllStorageTestsSynergy.class,
    AlertBDDTest.class
    })

/* 
 * To run this test suite use: 
 *   DCS appliance: Synergy
 *   Schematic: synergy_3encl_demo
 *   Manually add a firmware bundle before
 * Recommended VM configuration:
 *   Memory: 12GB
 *   CPUs: 8 cores
 */
public class AllTestsSynergy {

}
