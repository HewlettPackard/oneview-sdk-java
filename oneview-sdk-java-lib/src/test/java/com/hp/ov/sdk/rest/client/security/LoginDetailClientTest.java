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

        String expectedUri = LoginDetailClient.LOGIN_DETAILSS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LoginDetail.class).getType());
    }
}
