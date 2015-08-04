/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface MsmbConnectionManager {

    public void startMsmb(final RestParams params);

    public void removeMsmbConnection(final RestParams params);

    public void processConsumer(final RestParams params, final String routingKey, final MsmbMessageExecutionQueue messageQueue);

    public void stopMsmb(final RestParams params);
}
