/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rabbitmq.certificates;

import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.certs.MessagingCertificateManagerImpl;
import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClientTest;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.util.SdkUtils;

public class SCMBCertificatesImplTest
{

    private static final Logger logger = LoggerFactory.getLogger(SCMBCertificatesImplTest.class);

    private MessagingCertificateManagerImpl scmbCertClient;

    @Autowired
    private HttpRestClientTest httpRestClientTest;
    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private LoginSessions loginSessions;

    private RabbitMqClientCert rabbitMqClientCertDto;

    private CaCert caCertDto;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private SampleRestParams sampleRestParams;

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        httpRestClientTest = (HttpRestClientTest) ctx.getBean("httpRestClientTest");
        scmbCertClient = (MessagingCertificateManagerImpl) ctx.getBean("messagingCertificateManagerImpl");
        loginSessions = (LoginSessions) ctx.getBean("loginSessionsImpl");
        caCertDto = (CaCert) ctx.getBean("caCert");
        loginSessionAdaptor = (LoginSessionAdaptor) ctx.getBean("loginSessionAdaptor");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetRabbitMqClientCertificateKeyPair()
            throws InstantiationException, IllegalAccessException
    {
        //first get the session Id
        try
        {
            //Get the basic REST parameters like hostname, username and password
            params = sampleRestParams.getBasicRestParams();

            //update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            //then make rest call to get resource
            rabbitMqClientCertDto = scmbCertClient.getRabbitMqClientCertificateKeyPair(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : resource not found : " + params.getHostname());
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("SCMBCertificatesImplTest : testGetRabbitMqClientCertificateKeyPair : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("SCMBCertificatesImplTest : testGetRabbitMqClientCertificateKeyPair : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("SCMBCertificatesImplTest : testGetRabbitMqClientCertificateKeyPair : arguments are null ");
        }

        logger.info("SCMBCertificatesImplTest : testGetRabbitMqClientCertificateKeyPair : rabbitmq client certificate object returned to client : "
                + rabbitMqClientCertDto.toString());
    }

    @Test
    public void testGetCaCert()
            throws InstantiationException, IllegalAccessException
    {
        try
        {
            //Get the basic REST parameters like hostname, username and password
            params = sampleRestParams.getBasicRestParams();

            //update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            //then make rest call to get resource
            caCertDto = scmbCertClient.getCACertificate(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : resource not found : " + params.getHostname());
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("SCMBCertificatesImplTest : testGetCaCert : Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("SCMBCertificatesImplTest : testGetCaCert : No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("SCMBCertificatesImplTest : testGetCaCert : arguments are null ");
        }

        logger.info("SCMBCertificatesImplTest : testGetCaCert : ca certificate object returned to client : "
                + rabbitMqClientCertDto.toString());
    }

    @Test
    public void testGenerateRabbitMqClientCert()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
        String createCert = null;
        try
        {
            //Get the basic REST parameters like hostname, username and password
            params = sampleRestParams.getBasicRestParams();

            //update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create rabbitMqClientCert request body
            final RabbitMqClientCert rabbitMqClientCertDto = buildTestRabbitMqClientCertDto();
            /**
             * then make sdk service call to get resource
             * aSync parameter indicates sync vs async
             * useJsonRequest parameter indicates whether json input request
             * present or not
             */
            createCert = scmbCertClient.generateRabbitMqClientCert(params, rabbitMqClientCertDto,
                    false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("RabbitMqClientCertClientTest : testGenerateRabbitMqClientCert : resource not found : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("RabbitMqClientCertClientTest : testGenerateRabbitMqClientCert : bad request, try again : ");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("RabbitMqClientCertClientTest : testGenerateRabbitMqClientCert : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("RabbitMqClientCertClientTest : testGenerateRabbitMqClientCert : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGenerateRabbitMqClientCert : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testGenerateRabbitMqClientCert : "
                    + "errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("RabbitMqClientCertClientTest : testGenerateRabbitMqClientCert : rabbitMqClientCert object returned to client : "
                + createCert);
    }

    public RabbitMqClientCert buildTestRabbitMqClientCertDto()
    {
        final RabbitMqClientCert rabbitMqClientCertDto = new RabbitMqClientCert();
        rabbitMqClientCertDto.setCommonName("any");
        rabbitMqClientCertDto.setSignedCert(false);
        rabbitMqClientCertDto.setType("RabbitMqClientCertV2");
        return rabbitMqClientCertDto;
    }
}
