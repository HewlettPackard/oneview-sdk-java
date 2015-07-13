package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.SwitchCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class SwitchClientImpl implements SwitchClient{

	//TODO
	@Override
	public SwitchCollection getAllSwitches(RestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public Switch getSwitchById(RestParams params, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 createSwitch(RestParams params, Switch switchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 refreshSwitch(RestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 updateSwitch(RestParams params, String resourceId,
			Switch switchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 deleteSwitch(RestParams params, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public EnvironmentalConfiguration getSwitchEnvironmentConfiguration(
			RestParams params, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
