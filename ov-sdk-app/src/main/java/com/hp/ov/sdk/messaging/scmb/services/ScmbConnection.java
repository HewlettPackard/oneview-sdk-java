/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.services;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component
public class ScmbConnection
{
    private Connection conn = null;
    private Channel channel = null;
    private ScmbState state;

    public ScmbState getState()
    {
        return state;
    }

    public void setState(ScmbState state)
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
