/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.SwitchCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface SwitchClient {

    /**
     * This method aids in fetching the top of rack switches.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return switchCollectionDto, which is a object containing the collection
     *         of switch details.
     */
    public SwitchCollection getAllSwitches(final RestParams params);

    /**
     * This method aids in fetching the switch for the resourceId specified
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @return switchDto, which is a object containing the switch details.
     */
    public Switch getSwitchById(final RestParams params, final String resourceId);

    /**
     * This method aids in creation of switch.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param switchDto
     *            , which is a object containing the switch details.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createSwitch(final RestParams params, final Switch switchDto);

    /**
     * This method aids in refreshing the switch.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 refreshSwitch(final RestParams params);

    /**
     * This method aids in updating the switch.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @param switchDto
     *            , which is a object containing the switch details.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateSwitch(final RestParams params, final String resourceId, final Switch switchDto);

    /**
     * This method aids in deletion of switch
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteSwitch(final RestParams params, final String resourceId);

    /**
     * This method aids in fetching the environmental configuration for the
     * switch.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @return environmentConfiguration, contains the environment configuration
     *         for the switch.
     */
    public EnvironmentalConfiguration getSwitchEnvironmentConfiguration(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Switch details for the Switch name as
     * specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The name is the Switch name as seen in HP OneView.
     * @return switchDto, which is a object containing the Switch details.
     */
    public Switch getSwitchByName(final RestParams params, final String name);

    /**
     * The module aids in fetching the Switch details for the Switch name as
     * specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Switch name as seen in HP OneView.
     * @return String, which is a resource Id for the Switch name as seen in
     *         HPOneView.
     */
    public String getId(final RestParams creds, final String name);

}
