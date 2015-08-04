package com.hp.ov.sdk.enclosure.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.FwBaselineOptions;
import com.hp.ov.sdk.dto.LicensingIntent;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.RefreshStateConfig.RefreshForceOptions;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.EnclosureClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.FirmwareDriverClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class EnclosureClientSample {
    private RestParams params;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static EnclosureClient enclosureClient;
    private static FirmwareDriverClient firmwareDriverClient;
    private static EnclosureGroupClient enclosureGroupClient;
    private static TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceName = "Encl1";
    private static final String enclosureGroupName = "Enclosure_Test";
    private static final String hostname = "172.18.1.11";
    private static final String username = "dcs";
    private static final String password = "dcs";
    private static final String firmware = "HP Service Pack For ProLiant OneView 2014 11 13";
    private static final String resourceId = "09SGH102X6J1";

    // ================================

    private static void init() {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
        enclosureClient = HPOneViewSdkBeanFactory.getEnclosureClient();
        firmwareDriverClient = HPOneViewSdkBeanFactory.getFirmwareDriverClient();
        enclosureGroupClient = HPOneViewSdkBeanFactory.getEnclosureGroupClient();
    }

    private void getEnclosureById() throws InstantiationException, IllegalAccessException {
        Enclosures enclosureDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureDto = enclosureClient.getEnclosure(params, resourceId);

            System.out.println("EnclosureClientTest : getEnclosureById :" + " enclosure object returned to client : "
                    + enclosureDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getEnclosureById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getEnclosureById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("EnclosureClientTest : getEnclosureById :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("EnclosureClientTest : getEnclosureById :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getEnclosureById :" + " arguments are null ");
            return;
        }

    }

    private void getAllEnclosure() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        EnclosureCollectionV2 enclosureCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureCollectionDto = enclosureClient.getAllEnclosures(params);

            System.out.println("EnclosureClientTest : getAllEnclosure :" + " enclosure object returned to client : "
                    + enclosureCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " arguments are null ");
            return;
        }

    }

    private void getEnclosureByName() throws InstantiationException, IllegalAccessException {
        Enclosures enclosureDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            System.out.println("EnclosureClientTest : getEnclosureByName :" + " enclosure object returned to client : "
                    + enclosureDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " arguments are null ");
            return;
        }

    }

    private void createEnclosure() throws InstantiationException, IllegalAccessException {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create network request body
            final AddEnclosureV2 addEnclosureDto = buildTestEnclosureDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.createEnclosure(params, addEnclosureDto, false, false);

            System.out.println("EnclosureClientTest : createEnclosure : enclosure object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : createEnclosure : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("EnclosureClientTest : createEnclosure : errors in task, please check task resource for more details ");
            return;
        }

    }

    private void updateEnclosure() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        Enclosures enclosureDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            if (null != enclosureDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(enclosureDto.getUri());
            }
            enclosureDto.setName(resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.updateEnclosure(params, resourceId, enclosureDto, false, false);

            System.out.println("EnclosureClientTest : updateEnclosure : " + "Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateEnclosure : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void deleteEnclosure() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String resourceId = null;
        Enclosures enclosureDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            if (null != enclosureDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(enclosureDto.getUri());
            }

            // make sdk service call to get resource
            taskResourceV2 = enclosureClient.deleteEnclosure(params, resourceId, false);

            System.out.println("EnclosureClientTest : deleteEnclosure : " + "enclosure object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosureSet :" + " resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosureSet :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : deleteEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosure : " + "No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosure :" + " arguments are null ");
            return;
        }
    }

    private void getActiveOaSsoUrl() throws InstantiationException, IllegalAccessException {
        SsoUrlData ssoUrlDataDto = null;
        String resourceId = null;
        Enclosures enclosureDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            if (null != enclosureDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(enclosureDto.getUri());
            }

            // then make sdk service call to get resource
            ssoUrlDataDto = enclosureClient.getActiveOaSsoUrl(params, resourceId);

            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " enclosure sso url data object returned to client : "
                    + ssoUrlDataDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " arguments are null ");
            return;
        }
    }

    // TODO - Move Uri fetch logic to SdkUtils

    private AddEnclosureV2 buildTestEnclosureDto() {

        final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();
        final AddEnclosureV2 dto = new AddEnclosureV2();
        dto.setHostname(hostname);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setLicensingIntent(LicensingIntent.OneView);
        dto.setForce(false);
        dto.setEnclosureGroupUri(enclosureGroupUri);
        dto.setFirmwareBaselineUri(firmwareDriverClient.getFirmwareDriverByName(params, firmware).getUri());
        dto.setUpdateFirmwareOn(FwBaselineOptions.EnclosureOnly);
        dto.setForceInstallFirmware(false);
        return dto;
    }

    private FwBaselineConfig buildFwBaselineConfig() {
        FwBaselineConfig fwBaselineConfigDto = new FwBaselineConfig();

        fwBaselineConfigDto.setFwBaselineUri(firmwareDriverClient.getFirmwareDriverByName(params, firmware).getUri());
        fwBaselineConfigDto.setIsFwManaged(true);
        fwBaselineConfigDto.setFirmwareUpdateOn("EnclosureOnly");
        fwBaselineConfigDto.setForceInstallFirmware(false);

        return fwBaselineConfigDto;
    }

    private EnvironmentalConfigurationUpdate buildEnvironmentalConfigurationUpdateConfig() {
        EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto = new EnvironmentalConfigurationUpdate();
        environmentalConfigurationUpdateDto.setCalibratedMaxPower(2500);

        return environmentalConfigurationUpdateDto;
    }

    private RefreshStateConfig buildRefreshStateConfig() {
        RefreshStateConfig refreshStateConfigDto = new RefreshStateConfig();
        RefreshForceOptions refreshForceOptions = refreshStateConfigDto.getNewRefreshForceOptions();
        refreshForceOptions.setAddress(hostname);
        refreshForceOptions.setUsername(username);
        refreshForceOptions.setPassword(password);
        refreshStateConfigDto.setRefreshForceOptions(refreshForceOptions);
        refreshStateConfigDto.setRefreshState(RefreshState.RefreshPending);

        return refreshStateConfigDto;
    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        EnclosureClientSample client = new EnclosureClientSample();
        client.getEnclosureById();
        client.getAllEnclosure();
        client.getActiveOaSsoUrl();
        client.createEnclosure();
        client.getEnclosureByName();
        client.updateEnclosure();
        // client.deleteEnclosure();
    }
}
