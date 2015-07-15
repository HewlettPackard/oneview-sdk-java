/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.tasks;

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface TaskMonitorManager
{

    /**
     * The module is used to check the progress status obtained via getTask
     */
    public TaskResourceV2 checkStatus(RestParams params, final String taskUri, final int timeout);

    /**
     * The module is used for receiving updates about the progress
     * of the request.
     */
    public TaskResourceV2 getTask(RestParams params, final String taskUri);
}
