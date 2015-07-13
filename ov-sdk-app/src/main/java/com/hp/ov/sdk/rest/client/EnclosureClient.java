/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface EnclosureClient
{

    /**
     * The module aids in fetching the enclosure details for the specified
     * enclosure resourceId
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure as seen in HP OneView.
     * @return enclosuresDto, which is a object containing the enclosure
     *         details.
     */
    public Enclosures getEnclosure(final RestParams params,
            final String resourceId);

    /**
     * The module aids in fetching the Enclosure details for all Enclosure
     * registered under current HP OneView
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return enclosureCollectionDto which is a object containing the
     *         collection of enclosure.
     */
    public EnclosureCollectionV2 getAllEnclosures(final RestParams params);

    /**
     * The module aids in fetching the enclosure details for the specified
     * enclosure name
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param name
     *        The resourceName is the enclosure name as seen in HP
     * @return enclosuresDto, which is a object containing the enclosure
     *         details.
     */
    public Enclosures getEnclosureByName(final RestParams params,
            final String name);

    /**
     * The module aids in creation of enclosure when provided with the enclosure
     * details as AddEnclosure object or JsonRequest. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param addEnclosureDto
     *        The connection details to fetch enclosure.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of AddEnclosure Object which
     *        takes in a String containing the new Enclosure details, which
     *        is converted to AddEnclosure Object using adaptor and
     *        processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createEnclosure(final RestParams params,
            final AddEnclosureV2 addEnclosureDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an Enclosure object or JsonRequest and updates the
     * existing enclosure based on resource Id. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure as seen in HP OneView.
     * @param enclosureDto
     *        This is a object containing the update to be made to existing
     *        Enclosure pointed to by the above mentioned resourceId
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of Enclosure Object which takes
     *        in a String containing the update to be made, which is
     *        converted to Enclosure Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateEnclosure(final RestParams params,
            final String resourceId, final Enclosures enclosureDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a enclosure for the specified enclosure
     * resourceId. It can process the request asynchronously or synchronously
     * based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure as seen in HP OneView.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteEnclosure(final RestParams params,
            final String resourceId, final boolean aSync);
    
    /**
     * This method aids in fetching data that can be used to 
     * construct a single sign-on URL for an Onboard Administrator.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @return SsoUrlData, gets the data used for single sign URL for onboard administrator
     */
    public  SsoUrlData getActiveOaSsoUrl(final RestParams param, final String resourceId);
    
    /**
     * This method aids in updating the enclosure configuration with that of enclosure group script
     * not applicable if enclosure is monitored.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateCompliance(final RestParams param, final String resourceId);
    
    /**
     * This method aids in reapplying the enclosure configuration.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateConfiguration(final RestParams param, final String resourceId);
    
    /**
     * This method aids in applying the firmware baseline to the enclosure.
     * This can be used to update the OA firmware or the OA, logical interconnect,
     *  and server profiles in the enclosure.
     *  Not applicable if enclosure is monitored.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @param fwBaselineConfigDto
     * 			fwBaselineConfig is the firmware baseline to be applied to the enclosure
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateEnclosureFwBaseline(final RestParams param, final String resourceId, final FwBaselineConfig fwBaselineConfigDto);
    
    /**
     * This methods aids in fetching the environmental configuration of the enclosure resourceId.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @return environmentalConfiguration, fetches the environmental configuration of enclosure resource
     */
    public EnvironmentalConfiguration getEnvironmentalConfiguration(final RestParams param, final String resourceId);
    
    /**
     * This methods aids in updating the environmental configuration of the enclosure resourceId.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @param environmentalConfigurationUpdateDto
     * 			environmentalConfiguration, environmental configuration of enclosure resource specifying the cpu, power value
     * @return environmentalConfiguration, fetches the environmental configuration of enclosure resource
     */
    public EnvironmentalConfiguration updateEnvironmentalConfiguration(final RestParams param, final String resourceId, final EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto);

    /**
     * This method aids in refreshing the enclosure to fix configuration issues.
     * @param param
     * 			The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 			The resourceId for enclosure as seen in HP OneView.
     * @param refreshStateConfigDto
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateRefreshState(final RestParams param, final String resourceId, final RefreshStateConfig refreshStateConfigDto);
    
    /**
     * The module aids in fetching the configuration script for the specified enclosure
     *  resourceId.
     * @param params
     * 		  	The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 		  The resourceId for enclosure as seen in HP OneView.
     * @return String, the configuration script for the specified enclosure 
     */
    public String getScript(final RestParams param, final String resourceId);
    
    /**
     * The module aids in updating the configuration script for the specified enclosure
     * resourceId.
     * @param params
     * 		  The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 		  The resourceId for enclosure as seen in HP OneView.
     * @param scriptData
     * 		  The script data to be updated for enclosure 
     * @return taskResource which returns the task status for the process 
     */
    public TaskResourceV2 updateScript(final RestParams param, final String resourceId, final String scriptData);
    
    /**
     * This method aids in fetching data that can be used to 
     * construct a single sign-on URL for an Onboard Administrator.
     * @param param
     * 		The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 		The resourceId for enclosure as seen in HP OneView.
     * @return SsoUrlData, gets the data used for single sign URL for onboard administrator
     */
    public SsoUrlData getStandbyOaSsoUrl(final RestParams param, final String resourceId);
    
    /**
     * This method aids in retrieving historical utilization data for the specified enclosure.
     * @param param
     * 		The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     * 		The resourceId for enclosure as seen in HP OneView.
     * @return UtilizationData, specifies the resource data utlization such as power, cpu 
     */
    public UtilizationData getUtilization(final RestParams param, final String resourceId);
}
