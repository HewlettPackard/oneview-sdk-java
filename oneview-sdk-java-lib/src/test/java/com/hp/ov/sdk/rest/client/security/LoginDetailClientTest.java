/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
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

package com.hp.ov.sdk.rest.client.security;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.security.login.LoginDetail;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LoginDetailClientTest {

    private BaseClient baseClient = mock(BaseClient.class);
    private LoginDetailClient client = Reflection.newProxy(LoginDetailClient.class,
            new ClientRequestHandler<>(baseClient, LoginDetailClient.class));

    @Test
    public void shouldGetLoginDetails() {
        client.getLoginDetails();
        
        String expectedUri = LoginDetailClient.LOGIN_DETAILS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);
        
        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LoginDetail.class).getType());
    }
}
