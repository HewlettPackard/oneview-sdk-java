/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.util.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;

public class HPOneViewCredential {

    private static SdkUtils sdkUtils;

    public static RestParams createCredentials() {
        // Get the basic REST parameters like hostname, username and
        // password
        RestParams params = SampleRestParams.getInstance().getBasicRestParams();

        // update the parameters with version and sessionId
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        params = sdkUtils.createRestParams(params);

        return params;
    }

}
