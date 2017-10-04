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

package com.hp.ov.sdk.resources.network;

import com.hp.ov.sdk.network.FcNetworkResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;
import com.hp.ov.sdk.storage.FcSansManagedSanResource;

import cucumber.api.java.en.Given;

public class FcNetworkBDDStep extends Background {

    @Given("^an instance of FC Network$")
    public void an_instance_of_FC_Network() throws Throwable {
        OneView.setResource(FcNetworkResource.getInstance());
    }

    @Given("^OneView sets FC Sans Managed San uri to FC Network$")
    public void oneview_sets_FC_Sans_Managed_San_uri_to_FC_Network() throws Throwable {
        String sanName = inputProperties.get("fc_sans_managed");
        String sanUri = FcSansManagedSanResource.getInstance().getUri(sanName);
        FcNetworkResource.getInstance().setSanUri(sanUri);
    }
}
