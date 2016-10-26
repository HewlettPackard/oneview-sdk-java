package com.hp.ov.sdk.rest.http.core.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKForbiddenException;
import com.hp.ov.sdk.exceptions.SDKInternalServerErrorException;
import com.hp.ov.sdk.exceptions.SDKMethodNotAllowed;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKUnauthorizedException;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.JsonSerializer;

@RunWith(MockitoJUnitRunner.class)
public class HttpRestClientTest {

    private static final String SESSION_ID = "random-Auth";

    @Mock
    private SDKConfiguration sdkConfiguration;
    @Mock
    private CloseableHttpClient httpClient;
    @Mock
    private CloseableHttpResponse response;
    @Mock
    private StatusLine responseStatus;
    @Mock
    private HttpEntity responseEntity;
    @Mock
    private Header locationHeader;

    private HttpRestClient restClient;

    private String responseContent = "{data:\"data\"}";

    @Before
    public void setUp() throws Exception {
        this.restClient = new HttpRestClient(sdkConfiguration, Mockito.mock(JsonSerializer.class), httpClient);

        Mockito.when(sdkConfiguration.getClientMaxNumberOfConnections()).thenReturn(20);
        Mockito.when(sdkConfiguration.getClientSocketTimeout()).thenReturn(60);
        Mockito.when(sdkConfiguration.getOneViewHostname()).thenReturn("1.1.1.1");
        Mockito.when(sdkConfiguration.getOneViewApiVersion()).thenReturn(ApiVersion.V_300);

        Mockito.when(httpClient.execute(Mockito.any(HttpUriRequest.class))).thenReturn(response);
        Mockito.when(response.getStatusLine()).thenReturn(responseStatus);
        Mockito.when(locationHeader.getValue()).thenReturn("/rest/tasks/task-id");
        Mockito.when(response.getFirstHeader("Location")).thenReturn(locationHeader);
        Mockito.when(responseStatus.getStatusCode()).thenReturn(200);
        Mockito.when(response.getEntity()).thenReturn(responseEntity);
        Mockito.when(responseEntity.getContent()).thenReturn(new ByteArrayInputStream(responseContent.getBytes()));
    }

    @Test
    public void testSendRequestPost() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(201);

        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.POST, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPostNoBody() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.POST, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestGet() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPatch() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(202);
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.PATCH, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPatchNoBody() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.PATCH, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPut() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.PUT, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPutNoBody() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.PUT, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestDelete() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.DELETE, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestNotAuthoritativeResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(203);

        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestNoContentResponse() throws Exception {
        Mockito.when(responseEntity.getContent()).thenReturn(new ByteArrayInputStream("".getBytes()));
        Mockito.when(responseStatus.getStatusCode()).thenReturn(204);

        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
        assertEquals("Return must be \"{}\"", "{}", result);
    }

    @Test
    public void testSendRequestResetResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(205);

        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test
    public void testSendRequestPartialResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(206);

        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        assertNotNull(result);
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMultiChoiceResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(300);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMovedPermResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(301);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestMovedTempResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(302);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestSeeOtherResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(303);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotModifiedResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(304);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestUseProxyResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(305);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestBadRequestResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(400);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKUnauthorizedException.class)
    public void testSendRequestUnauthorizedResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(401);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestPaymentRequiredResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(402);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKForbiddenException.class)
    public void testSendRequestForbiddenResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(403);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testSendRequestNotFoundResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(404);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKMethodNotAllowed.class)
    public void testSendRequestBadMethodResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(405);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotAcceptableResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(406);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestProxyAuthResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(407);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestClientTimeoutResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(408);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestConflictResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(409);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestGoneResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(410);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestLengthRequiredResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(411);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestPreconFailedResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(412);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestEntityTooLargeResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(413);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestUriTooLongResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(414);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestUnsupportedTypeResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(415);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestInternalErrorResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(500);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotImplementedResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(501);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestBadGatewayResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(502);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKInternalServerErrorException.class)
    public void testSendRequestUnavailableResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(503);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestGatewayTimeoutResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(504);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestHttpVersionNotSupportedResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(505);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKApplianceNotReachableException.class)
    public void testSendRequestNotReachableResponse() {
        Mockito.when(responseStatus.getStatusCode()).thenReturn(999);

        restClient.sendRequest(SESSION_ID, new Request(HttpMethod.GET, ""));
        fail("Exception should have been raised");
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestInvalidRequestType() {
        restClient.sendRequest(SESSION_ID, new Request(null, ""));
        fail("Exception should have been raised");
    }

    @Test
    public void testSendRequestForceReturnTask() {
        Request request = new Request(HttpMethod.POST, "");

        request.setForceReturnTask(true);

        String result = restClient.sendRequest(SESSION_ID, request);
        assertNotNull(result);
    }

    @Test
    public void testSendRequestStringBody() {
        String result = restClient.sendRequest(SESSION_ID, new Request(HttpMethod.POST, "", ""));

        assertNotNull(result);
    }

    @Test
    public void testSendRequestWithUriParameters() {
        Request request = new Request(HttpMethod.GET, "");

        request.addQuery(new UrlParameter("filter", "name='anyName"));

        String result = restClient.sendRequest(SESSION_ID, request);
        assertNotNull(result);
    }

    @Test (expected = SDKBadRequestException.class)
    public void testSendRequestWithBadUriParameters() {
        Request request = new Request(HttpMethod.GET, "http :");

        request.addQuery(new UrlParameter("filter", "name='anyName"));

        String result = restClient.sendRequest(SESSION_ID, request);
        assertNotNull(result);
    }
}
