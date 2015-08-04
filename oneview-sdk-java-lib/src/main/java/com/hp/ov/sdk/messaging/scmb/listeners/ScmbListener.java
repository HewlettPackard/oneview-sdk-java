package com.hp.ov.sdk.messaging.scmb.listeners;

import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;

public interface ScmbListener
{
    public void handleScmbMessage(final ScmbAlertsMessageDto alertsDto);

}
