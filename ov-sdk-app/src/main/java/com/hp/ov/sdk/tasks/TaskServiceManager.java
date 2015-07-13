/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.tasks;

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface TaskServiceManager
{

    public TaskResourceV2 getTaskResource(final RestParams params, final String taskUri);
}
