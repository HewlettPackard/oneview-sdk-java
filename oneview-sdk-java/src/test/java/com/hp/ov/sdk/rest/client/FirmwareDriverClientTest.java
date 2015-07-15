/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.util.SdkUtils;

public class FirmwareDriverClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(FirmwareDriverClientTest.class);

    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private SampleRestParams sampleRestParams;

    @Autowired
    private FirmwareDriverClient firmwareDriverClient;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "HP Service Pack For ProLiant OneView 2014 11 13";
    private static final String resourceId = "bp-hp-service-pack-for-proliant-oneview-2014-11-30-05";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        firmwareDriverClient = (FirmwareDriverClient) ctx
                .getBean("firmwareDriverClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetFirmwareDriverById()
            throws InstantiationException,
            IllegalAccessException
    {
        FwBaseline fwBaselineDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fwBaselineDto = firmwareDriverClient
                    .getFirmwareDriver(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverById :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverById :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverById :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverById :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetFirmwareDriverById :"
                    + " arguments are null ");
        }

        logger.info("FirmwareDriverClientTest : testGetFirmwareDriverById :"
                + " fwBaseline group object returned to client : "
                + fwBaselineDto.toString());
    }

    @Test
    public void testGetAllFirmwareDriver()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        FwBaselineCollection fwBaselineCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fwBaselineCollectionDto = firmwareDriverClient
                    .getAllFirmwareDrivers(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetAllFirmwareDriver "
                    + ": resource you are looking is not found");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetAllFirmwareDriver :"
                    + " no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FirmwareDriverClientTest : testGetAllFirmwareDriver :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetAllFirmwareDriver :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllFirmwareDriver :"
                    + " arguments are null ");
        }
        logger.info("FirmwareDriverClientTest : testGetAllFirmwareDriver :"
                + " fwBaseline groups object returned to client : "
                + fwBaselineCollectionDto.toString());
    }

    @Test
    public void testGetFirmwareDriverByName()
            throws InstantiationException,
            IllegalAccessException
    {
        FwBaseline fwBaselineDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fwBaselineDto = firmwareDriverClient.getFirmwareDriverByName(
                    params, resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverByName :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverByName :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverByName :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FirmwareDriverClientTest : testGetFirmwareDriverByName :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetFirmwareDriverByName :"
                    + " arguments are null ");
        }

        logger.info("FirmwareDriverClientTest : testGetFirmwareDriverByName :"
                + " fwBaseline group object returned to client : "
                + fwBaselineDto.toString());
    }

}
