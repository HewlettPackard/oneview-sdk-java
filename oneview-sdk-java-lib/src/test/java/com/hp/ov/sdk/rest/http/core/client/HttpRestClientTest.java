package com.hp.ov.sdk.rest.http.core.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKForbiddenException;
import com.hp.ov.sdk.exceptions.SDKInternalServerErrorException;
import com.hp.ov.sdk.exceptions.SDKMethodNotAllowed;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKUnauthorizedException;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClients.class, SSLContext.class, SSLConnectionSocketFactory.class})
public class HttpRestClientTest {

    @Mock
    private TrustManager trustMgr;
    @Mock
    private SSLContext sslContext;
    @Mock
    private SSLConnectionSocketFactory sslsf;
    @Mock
    private SSLSocketFactory socketFactory;
    @Mock
    private HostnameVerifier hostnameVerifier;
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
        params = new RestParams();
        params.setSessionId("sessionID");
        params.setTrustManager(trustMgr);
        params.setHostnameVerifier(hostnameVerifier);

        // Apache HTTP client mock
        PowerMockito.mockStatic(SSLContext.class);
        Mockito.when(SSLContext.getInstance(Mockito.anyString())).thenReturn(sslContext);
        Mockito.when(sslContext.getSocketFactory()).thenReturn(socketFactory);

        PowerMockito.mockStatic(SSLConnectionSocketFactory.class);
        Mockito.when(SSLConnectionSocketFactory.getDefaultHostnameVerifier()).thenReturn(hostnameVerifier);
        PowerMockito.whenNew(SSLConnectionSocketFactory.class).withAnyArguments().thenReturn(sslsf);

        PowerMockito.mockStatic(HttpClients.class);
        Mockito.when(HttpClients.custom()).thenReturn(clientBuilder);
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
        restClient = HttpRestClient.getClient();
        assertNotNull(restClient);
    }

    @Test
    public void testSendRequestPost() {
        restClient = HttpRestClient.getClient();
        Mockito.when(responseStatus.getStatusCode()).thenReturn(201);
        String result = restClient.sendRequest(params, new Request(HttpMethod.POST, ""));
        assertNotNull(result);
    }

    public void testSendRequestPostNoBody() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.POST, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestGet() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPatch() {
        restClient = HttpRestClient.getClient();
        Mockito.when(responseStatus.getStatusCode()).thenReturn(202);
        String result = restClient.sendRequest(params, new Request(HttpMethod.PATCH, ""));
        assertNotNull(result);
    }

    public void testSendRequestPatchNoBody() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.PATCH, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPut() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.PUT, ""));
        assertNotNull(result);
    }

    public void testSendRequestPutNoBody() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.PUT, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestDelete() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.DELETE, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestNotAuthoritativeResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(203);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestNoContentResponse() throws Exception {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseEntity.getContent()).thenReturn(new ByteArrayInputStream("".getBytes()));
        Mockito.when(responseStatus.getStatusCode()).thenReturn(204);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
        assertEquals("Return must be \"{}\"", "{}", result);
    }

    @Test
    public void testSendRequestResetResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(205);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPartialResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(206);

        String result = restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMultiChoiceResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(300);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMovedPermResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(301);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMovedTempResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(302);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestSeeOtherResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(303);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotModifiedResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(304);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestUseProxyResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(305);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestBadRequestResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(400);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKUnauthorizedException.class)
    public void testSendRequestUnauthorizedResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(401);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestPaymentRequiredResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(402);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKForbiddenException.class)
    public void testSendRequestForbiddenResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(403);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testSendRequestNotFoundResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(404);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKMethodNotAllowed.class)
    public void testSendRequestBadMethodResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(405);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotAcceptableResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(406);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestProxyAuthResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(407);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestClientTimeoutResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(408);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestConflictResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(409);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestGoneResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(410);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestLengthRequiredResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(411);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestPreconFailedResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(412);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestEntityTooLargeResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(413);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestUriTooLongResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(414);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestUnsupportedTypeResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(415);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestInternalErrorResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(500);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotImplementedResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(501);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestBadGatewayResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(502);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestUnavailableResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(503);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestGatewayTimoutResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(504);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestHttpVersionNotSupportedResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(505);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotReachableResponse() {
        restClient = HttpRestClient.getClient();

        Mockito.when(responseStatus.getStatusCode()).thenReturn(999);

        restClient.sendRequest(params, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestInvalidRequestType() {
        restClient = HttpRestClient.getClient();
        restClient.sendRequest(params, new Request(null, ""));
        fail("Exception should have been raised");
    }

    @Test
    public void testSendRequestForceReturnTask() {
        restClient = HttpRestClient.getClient();
        Request request = new Request(HttpMethod.POST, "");

        request.setForceReturnTask(true);

        String result = restClient.sendRequest(params, request);
        assertNotNull(result);
    }

    @Test
    public void testSendRequestStringBody() {
        restClient = HttpRestClient.getClient();
        String result = restClient.sendRequest(params, new Request(HttpMethod.POST, "", ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestWithUriParameters() {
        restClient = HttpRestClient.getClient();

        Request request = new Request(HttpMethod.GET, "");

        request.addQuery(new UrlParameter("filter", "name='anyName"));

        String result = restClient.sendRequest(params, request);
        assertNotNull(result);
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestWithBadUriParameters() {
        restClient = HttpRestClient.getClient();
        Request request = new Request(HttpMethod.GET, "http :");

        request.addQuery(new UrlParameter("filter", "name='anyName"));

        String result = restClient.sendRequest(params, request);
        assertNotNull(result);
    }
}
