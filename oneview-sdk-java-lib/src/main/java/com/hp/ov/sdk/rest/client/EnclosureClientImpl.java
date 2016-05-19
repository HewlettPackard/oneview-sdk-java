/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.EnclosureAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class EnclosureClientImpl implements EnclosureClient {

    private static final String ROLE_STANDBY = "?role=Standby";
    private static final String ROLE_ACTIVE = "?role=Active";
    private static final Logger LOGGER = LoggerFactory.getLogger(EnclosureClientImpl.class);
    private static final int TIMEOUT = 1200000; // in milliseconds = 20 mins

    private final ResourceAdaptor resourceAdaptor;
    private final EnclosureAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private EnclosureClientImpl(HttpRestClient httpClient,
            ResourceAdaptor resourceAdaptor, EnclosureAdaptor adaptor,
            TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {

        this.httpClient = httpClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static EnclosureClient getClient() {
        return new EnclosureClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new EnclosureAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public Enclosures getEnclosure(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureClientImpl : getEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureV2Client : getEnclosure : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final Enclosures enclosureDto = adaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureV2Client : getEnclosure : name :" + enclosureDto.getName());
        LOGGER.info("EnclosureClientImpl : getEnclosure : End");

        return enclosureDto;
    }

    @Override
    public ResourceCollection<Enclosures> getAllEnclosures(final RestParams params) {
        LOGGER.info("EnclosureClientImpl : getAllEnclosureV2s : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI));

        // call rest client
        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : getAllEnclosureV2s : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        ResourceCollection<Enclosures> enclosureCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, Enclosures.class);

        LOGGER.debug("EnclosureV2Client : getAllEnclosureV2s : members count :" + enclosureCollectionDto.getCount());
        LOGGER.info("EnclosureClientImpl : getAllEnclosureV2s : End");

        return enclosureCollectionDto;

    }

    @Override
    public Enclosures getEnclosureByName(final RestParams params, final String name) {
        Enclosures enclosureDto = null;
        LOGGER.info("EnclosureClientImpl : getEnclosureByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : getEnclosureByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }

        ResourceCollection<Enclosures> enclosureCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, Enclosures.class);

        if (enclosureCollectionDto.getCount() != 0) {
            enclosureDto = enclosureCollectionDto.getMembers().get(0);
        } else {
            enclosureDto = null;
        }

        if (enclosureDto == null) {
            LOGGER.error("EnclosureClientImpl : getEnclosureByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        LOGGER.info("EnclosureClientImpl : getEnclosureByName : End");

        return enclosureDto;
    }

    @Override
    public TaskResourceV2 createEnclosure(final RestParams params, final AddEnclosureV2 enclosureDto, final boolean aSync,
            final boolean useJsonRequest) {
        LOGGER.info("EnclosureClientImpl : createEnclosure : Start");
        String returnObj = null;

        // validate params
        if (enclosureDto == null || params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(enclosureDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : createEnclosure : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : createEnclosure : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : createEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateEnclosure(final RestParams params, final String resourceId, final Enclosures enclosureDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("EnclosureClientImpl : updateEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (enclosureDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(enclosureDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateEnclosure : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateEnclosure : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : updateEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 patchEnclosure(RestParams params, String resourceId, Patch patchDto, boolean aSync) {
        LOGGER.info("EnclosureClientImpl : patchEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (patchDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PATCH);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        JSONArray jsonArray = adaptor.buildJsonArrayDto(patchDto);
        returnObj = httpClient.sendRequest(params, jsonArray);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : patchEnclosure : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : patchEnclosure : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : patchEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteEnclosure(final RestParams params, final String resourceId, final boolean aSync) {

        LOGGER.info("EnclosureClientImpl : deleteEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureV2Client : deleteEnclosure : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : deleteEnclosure : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : deleteEnclosure : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : deleteEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public SsoUrlData getActiveOaSsoUrl(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureClientImpl : getActiveOaSsoUrl : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);

        String restUrl = SdkConstants.ACTIVE_OA_SSO_URL;
        if (ApiVersion.V_200 == params.getApiVersion()) {
            restUrl = SdkConstants.ACTIVE_OA_SSO_URL_V200 + ROLE_ACTIVE;
        }
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, restUrl));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : getActiveOaSsoUrl : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final SsoUrlData ssoUrlDataDto = adaptor.buildSsoUrlData(returnObj);

        LOGGER.info("EnclosureClientImpl : getActiveOaSsoUrl : End");

        return ssoUrlDataDto;
    }

    @Override
    public TaskResourceV2 updateCompliance(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("EnclosureClientImpl : updateCompliance : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.COMPLIANCE));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : updateCompliance : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateCompliance : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateCompliance : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : updateCompliance : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateConfiguration(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("EnclosureClientImpl : updateConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                SdkConstants.CONFIGURATION));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : updateConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.CONFIGURATION,
                    null);
        }
        // Call adaptor to convert to DTO

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateConfiguration : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateConfiguration : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : updateConfiguration : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateEnclosureFwBaseline(final RestParams params, final String resourceId,
            final FwBaselineConfig fwBaselineConfigDto, final boolean aSync) {
        LOGGER.info("EnclosureClientImpl : updateEnclosureFwBaseline : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (fwBaselineConfigDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.ENCLOSURE_FW_BASELINE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                SdkConstants.ENCLOSURE_FW_BASELINE));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating enclosureFwBaseline dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(fwBaselineConfigDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateEnclosureFwBaselineFwBaseline : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateEnclosureFwBaseline : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : updateEnclosureFwBaseline : End");

        return taskResourceV2;
    }

    @Override
    public EnvironmentalConfiguration getEnvironmentalConfiguration(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureClientImpl : getEnvironmentalConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureV2Client : getEnvironmentalConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final EnvironmentalConfiguration environmentalConfigurationDto = adaptor.buildEnvironmentalConfigurationDto(returnObj);

        LOGGER.info("EnclosureClientImpl : getEnvironmentalConfiguration : End");

        return environmentalConfigurationDto;
    }

    @Override
    public EnvironmentalConfiguration updateEnvironmentalConfiguration(final RestParams params, final String resourceId,
            final EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto) {
        LOGGER.info("EnclosureClientImpl : updateEnvironmentalConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (environmentalConfigurationUpdateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENVIRONMENT_CONFIGURATION_URI));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating enclosureFwBaseline dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(environmentalConfigurationUpdateDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        final EnvironmentalConfiguration environmentalConfigurationDto = adaptor.buildEnvironmentalConfigurationDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateEnvironmentalConfiguration : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateEnvironmentalConfiguration : environmentalConfiguration ="
                + environmentalConfigurationDto);

        LOGGER.info("EnclosureClientImpl : updateEnvironmentalConfiguration : End");

        return environmentalConfigurationDto;
    }

    @Override
    public TaskResourceV2 updateRefreshState(final RestParams params, final String resourceId,
            final RefreshStateConfig refreshStateConfigDto, final boolean aSync) {
        LOGGER.info("EnclosureClientImpl : updateRefreshState : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (refreshStateConfigDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.REFRESH_STATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                SdkConstants.REFRESH_STATE));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in updating refreshStateConfigDto dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(refreshStateConfigDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateRefreshState : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateRefreshState : environmentalConfiguration =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }

        LOGGER.info("EnclosureClientImpl : updateRefreshState : End");

        return taskResourceV2;
    }

    @Override
    public String getScript(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureClientImpl : getScript : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureV2Client : getScript : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SCRIPT, null);
        }

        LOGGER.info("EnclosureClientImpl : getScript : End");

        return returnObj;
    }

    @Override
    public TaskResourceV2 updateScript(final RestParams params, final String resourceId, final String scriptData,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("EnclosureClientImpl : updateScript : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (scriptData == null || scriptData.isEmpty()) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SCRIPT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in updating refreshStateConfigDto dto.

        returnObj = httpClient.sendRequest(params, scriptData);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateScript : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateScript : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }

        LOGGER.info("EnclosureClientImpl : updateScript : End");

        return taskResourceV2;
    }

    @Override
    public SsoUrlData getStandbyOaSsoUrl(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureClientImpl : getStandbyOaSsoUrl : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);

        String restUrl = SdkConstants.STANDBY_OA_SSO_URL;
        if (ApiVersion.V_200 == params.getApiVersion()) {
            restUrl = SdkConstants.STANDBY_OA_SSO_URL_V200 + ROLE_STANDBY;
        }
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, restUrl));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : getStandbyOaSsoUrl : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final SsoUrlData ssoUrlDataDto = adaptor.buildSsoUrlData(returnObj);

        LOGGER.info("EnclosureClientImpl : getStandbyOaSsoUrl : End");

        return ssoUrlDataDto;
    }

    @Override
    public UtilizationData getUtilization(final RestParams params, final String resourceId) {
        LOGGER.info("EnclosureClientImpl : getUtilization : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.UTILIZATION));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureClientImpl : getUtilization : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final UtilizationData utilizationDataDto = adaptor.buildUtilizationData(returnObj);

        LOGGER.info("EnclosureClientImpl : getUtilization : End");

        return utilizationDataDto;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Enclosures enclosures = getEnclosureByName(params, name);

        if (null != enclosures.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(enclosures.getUri());
        }
        return resourceId;
    }

}
