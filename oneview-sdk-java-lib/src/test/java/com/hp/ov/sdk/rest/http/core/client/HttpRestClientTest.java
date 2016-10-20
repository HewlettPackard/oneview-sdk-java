package com.hp.ov.sdk.rest.http.core.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKForbiddenException;
import com.hp.ov.sdk.exceptions.SDKInternalServerErrorException;
import com.hp.ov.sdk.exceptions.SDKMethodNotAllowed;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKUnauthorizedException;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.SSLContextFactory;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.JsonSerializer;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClientBuilder.class, SSLContext.class, SSLConnectionSocketFactory.class})
public class HttpRestClientTest {

    @Mock
    private SSLContext sslContext;
    @Mock
    private SSLConnectionSocketFactory sslsf;
    @Mock
    private SSLSocketFactory socketFactory;
    @Mock
    private CloseableHttpClient httpClient;
    @Mock
    private HttpClientBuilder clientBuilder;
    @Mock
    private CloseableHttpResponse response;
    @Mock
    private StatusLine responseStatus;
    @Mock
    private HttpEntity responseEntity;
    @Mock
    private Header locationHeader;

    private RestParams params;
    private HttpRestClient restClient;

    private String responseContent = "{data:\"data\"}";

    @Before
    public void setUp() throws Exception {
        SDKConfiguration config = SDKConfiguration.create()
                .withOneViewApiVersion(ApiVersion.V_300)
                .withOneViewHostname("1.1.1.1")
                .withOneViewUser("user", "pass")
                .build();

        params = new RestParams(config);
        params.setSessionId("sessionID");

        // Apache HTTP client mock
        PowerMockito.mockStatic(SSLContext.class);
        Mockito.when(SSLContext.getInstance(Mockito.anyString())).thenReturn(sslContext);
        Mockito.when(sslContext.getSocketFactory()).thenReturn(socketFactory);

        PowerMockito.mockStatic(SSLConnectionSocketFactory.class);
        PowerMockito.whenNew(SSLConnectionSocketFactory.class).withAnyArguments().thenReturn(sslsf);

        PowerMockito.mockStatic(HttpClientBuilder.class);
        Mockito.when(HttpClientBuilder.create()).thenReturn(clientBuilder);
        Mockito.when(clientBuilder.setDefaultRequestConfig(Mockito.any(RequestConfig.class))).thenReturn(clientBuilder);
        Mockito.when(clientBuilder.setConnectionManager(Mockito.any(HttpClientConnectionManager.class))).thenReturn(clientBuilder);
        Mockito.when(clientBuilder.build()).thenReturn(httpClient);
        Mockito.when(httpClient.execute(Mockito.any(HttpUriRequest.class))).thenReturn(response);
        Mockito.when(response.getStatusLine()).thenReturn(responseStatus);
        Mockito.when(locationHeader.getValue()).thenReturn("/rest/tasks/task-id");
        Mockito.when(response.getFirstHeader("Location")).thenReturn(locationHeader);
        Mockito.when(responseStatus.getStatusCode()).thenReturn(200);
        Mockito.when(response.getEntity()).thenReturn(responseEntity);
        Mockito.when(responseEntity.getContent()).thenReturn(new ByteArrayInputStream(responseContent.getBytes()));
    }

    @Test
    public void testGetClient() {
        assertNull(restClient);
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        assertNotNull(restClient);
    }

    @Test
    public void testSendRequestPost() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        Mockito.when(responseStatus.getStatusCode()).thenReturn(201);
        String result = restClient.sendRequest(params, new Request(HttpMethod.POST, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPostNoBody() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.POST, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestGet() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPatch() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        Mockito.when(responseStatus.getStatusCode()).thenReturn(202);
        String result = restClient.sendRequest(params, new Request(HttpMethod.PATCH, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPatchNoBody() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.PATCH, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPut() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.PUT, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPutNoBody() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.PUT, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestDelete() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.DELETE, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestNotAuthoritativeResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(203);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestNoContentResponse() throws Exception {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseEntity.getContent()).thenReturn(new ByteArrayInputStream("".getBytes()));
        Mockito.when(responseStatus.getStatusCode()).thenReturn(204);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
        assertEquals("Return must be \"{}\"", "{}", result);
    }

    @Test
    public void testSendRequestResetResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(205);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPartialResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(206);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMultiChoiceResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(300);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMovedPermResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(301);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMovedTempResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(302);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestSeeOtherResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(303);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotModifiedResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(304);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestUseProxyResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(305);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestBadRequestResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(400);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKUnauthorizedException.class)
    public void testSendRequestUnauthorizedResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(401);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestPaymentRequiredResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(402);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKForbiddenException.class)
    public void testSendRequestForbiddenResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(403);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testSendRequestNotFoundResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(404);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKMethodNotAllowed.class)
    public void testSendRequestBadMethodResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(405);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotAcceptableResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(406);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestProxyAuthResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(407);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestClientTimeoutResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(408);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestConflictResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(409);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestGoneResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(410);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestLengthRequiredResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(411);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestPreconFailedResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(412);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestEntityTooLargeResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(413);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestUriTooLongResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(414);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestUnsupportedTypeResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(415);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestInternalErrorResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(500);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotImplementedResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(501);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestBadGatewayResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(502);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestUnavailableResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(503);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestGatewayTimoutResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(504);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestHttpVersionNotSupportedResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(505);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotReachableResponse() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Mockito.when(responseStatus.getStatusCode()).thenReturn(999);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestInvalidRequestType() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        restClient.sendRequest(params, new Request(null, ""));
        fail("Exception should have been raised");
    }

    @Test
    public void testSendRequestForceReturnTask() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        Request request = new Request(HttpMethod.POST, "");

        request.setForceReturnTask(true);

        String result = restClient.sendRequest(params, request);
        assertNotNull(result);
    }

    @Test
    public void testSendRequestStringBody() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        String result = restClient.sendRequest(params, new Request(HttpMethod.POST, "", ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestWithUriParameters() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());

        Request request = new Request(HttpMethod.GET, "");

        request.addQuery(new UrlParameter("filter", "name='anyName"));

        String result = restClient.sendRequest(params, request);
        assertNotNull(result);
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestWithBadUriParameters() {
        restClient = new HttpRestClient(new JsonSerializer(), SSLContextFactory.getAvailableContext());
        Request request = new Request(HttpMethod.GET, "http :");

        request.addQuery(new UrlParameter("filter", "name='anyName"));

        String result = restClient.sendRequest(params, request);
        assertNotNull(result);
    }
}
