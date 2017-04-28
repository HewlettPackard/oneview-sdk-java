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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import com.hp.ov.sdk.rest.http.core.client.ApiVersion;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OneViewCredentialsBDDStep extends Background {

    @Given("^an instance of Credentials$")
    public void an_instance_of_Credentials() {
        credential = Credential.getInstance();
    }

    @Given("^a DOMAIN \"(.*?)\"$")
    public void a_DOMAIN(String domain) {
        credential.setDomain(domain);
    }

    @Given("^a VERSION \"(.*?)\"$")
    public void a_VERSION(String version) {
        credential.setVersion(ApiVersion.valueOf(version));
    }

    @Given("^a FILE_SDK_CONFIG \"(.*?)\"$")
    public void a_FILE_SDK_CONFIG(String file_sdk_config) throws Throwable {
        credential.setFile_sdk_config(file_sdk_config);
    }

    @Given("^OneView credentials as follows:$")
    public void oneview_credentials_as_follows(Map<String, String> values) throws Throwable {
        credential.setHostname(values.get("hostname"));
        credential.setUsername(values.get("username"));
        credential.setPassword(values.get("password"));
    }

    @Given("^Enclosure credentials as follows:$")
    public void enclosure_credentials_as_follows(Map<String, String> values) throws Throwable {
        credential.setEnclosureHostname(values.get("hostname"));
        credential.setEnclosureUsername(values.get("username"));
        credential.setEnclosurePassword(values.get("password"));
    }

    @Given("^Storage System credentials as follows:$")
    public void storage_System_credentials_as_follows(Map<String, String> values) throws Throwable {
        credential.setStorageSystemHostname(values.get("hostname"));
        credential.setStorageSystemUsername(values.get("username"));
        credential.setStorageSystemPassword(values.get("password"));
    }

    @When("^credentials are written at \"(.*?)\"$")
    public void credentials_are_written_at(String targetFile) throws Throwable {
        credential.writeProperties(targetFile);
    }

    @Then("^there is a file \"(.*?)\"$")
    public void there_is_a_file(String targetFile) throws Throwable {
        assertTrue(new File(targetFile).exists());
    }

}
