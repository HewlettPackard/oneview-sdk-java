/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component
public class MsmbConnection
{
    private Connection conn = null;
    private Channel channel = null;
    private MsmbState state;

    public MsmbState getState()
    {
        return state;
    }

    public void setState(MsmbState state)
    {
        this.state = state;
    }

    public Connection getConn()
    {
        return conn;
    }

    public void setConn(Connection conn)
    {
        this.conn = conn;
    }

    public Channel getChannel()
    {
        return channel;
    }

    public void setChannel(Channel channel)
    {
        this.channel = channel;
    }

}
