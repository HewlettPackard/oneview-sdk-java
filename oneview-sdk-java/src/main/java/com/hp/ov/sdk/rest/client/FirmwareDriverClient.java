/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FirmwareDriverClient
{
    /**
     * The module aids in fetching the FirmwareDriver details for the specified
     * FirmwareDriver resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for FirmwareDriver as seen in HP OneView.
     * @return FwBaselineDto, which is a object containing the
     *         FirmwareDriver details.
     */
    public FwBaseline getFirmwareDriver(final RestParams params,
            final String resourceId);

    /**
     * The module aids in fetching the FirmwareDriver details for all the
     * FirmwareDriver found under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return FwBaselineCollectionDto, which is a object containing a
     *         collection of FirmwareDriver details.
     */
    public FwBaselineCollection getAllFirmwareDrivers(
            final RestParams params);

    /**
     * The module aids in fetching the FirmwareDriver details for the
     * FirmwareDriver name as specified in HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param firmwareName
     *        The firmwareName is the FirmwareDriver name as seen in HP OneView.
     * @return FwBaselineDto, which is a object containing the
     *         FirmwareDriver details.
     */
    public FwBaseline getFirmwareDriverByName(final RestParams params,
            final String firmwareName);

    // TODO - implement the remaining update methods and GetByName method

}
