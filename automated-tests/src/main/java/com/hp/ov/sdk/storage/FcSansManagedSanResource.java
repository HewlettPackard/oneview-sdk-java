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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.fcsans.SanPolicy;
import com.hp.ov.sdk.dto.fcsans.SanRequest;
import com.hp.ov.sdk.dto.fcsans.SanResponse;
import com.hp.ov.sdk.dto.fcsans.ZoningPolicy;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.storage.FcSanManagedSanClient;

public class FcSansManagedSanResource extends BasicResource implements Resource, UpdateResource {

	private static FcSansManagedSanResource instance;

	private FcSanManagedSanClient client;

	public FcSansManagedSanResource() {
		client = oneViewClient.fcSanManagedSan();
	}

	public static FcSansManagedSanResource getInstance() {
		if (instance == null) {
			instance = new FcSansManagedSanResource();
		}
		return instance;
	}

	@Override
	public Map<String, String> getResourceProperties(String id) {
		return getResourceProperties(client.getById(id));
	}

	@Override
	public String findByName(String name) {
		SanResponse san = (SanResponse) getResource(client.getByName(name));
		return san == null ? "" : san.getResourceId();
	}

	@Override
	public String findById(String id) {
		try {
			return client.getById(id).getName();
		} catch (final SDKResourceNotFoundException ex) {
			return "";
		}
	}

	@Override
	public int count() {
		return getCount(client.getAll());
	}

	public String createEndpointsCsv(String id) {
		return objToString(client.createEndpointsCsv(id));
	}

	public String createIssuesReport(String id) {
		return objToString(client.createIssuesReport(id));
	}

	public String getEndpoints(String id) {
		return objToString(client.getEndpoints(id));
	}

	public int getWwnAssociations(String id) {
		SanResponse san = client.getById(id);
		String wwn = san.getPrincipalSwitch();
		return client.getWwnAssociations(wwn).size();
	}

	public String getUri(String name) {
		SanResponse san = (SanResponse) getResource(client.getByName(name));
		return san == null ? "" : san.getUri();
	}

	@Override
	public String update(String id) {
		return objToString(client.update(id, builderUpdate()));
	}

	public SanRequest builderUpdate() {
		SanRequest sanRequest = new SanRequest();
		SanPolicy sanPolicy = new SanPolicy();

		sanPolicy.setEnableAliasing(Boolean.valueOf(resourceProperties.get("enableAliasing")));
		sanPolicy.setZoningPolicy(ZoningPolicy.valueOf(resourceProperties.get("zoningPolicy")));

		sanPolicy.setInitiatorNameFormat("sample_initiator_name_format");
		sanPolicy.setTargetGroupNameFormat("sample_target_group_name_format");
		sanPolicy.setTargetNameFormat("sample_target_name_format");
		sanPolicy.setZoneNameFormat("sample_zone_name_format");

		sanRequest.setSanPolicy(sanPolicy);

		if (resourceProperties.containsKey(sanRequest)) {
			sanRequest.setRefreshState(RefreshState.valueOf(resourceProperties.get("refreshState")));
		}
		return sanRequest;
	}

}
