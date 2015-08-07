/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Component
public class MsmbConnection {
    private Connection conn = null;
    private Channel channel = null;
    private MsmbState state;

    public MsmbState getState() {
        return state;
    }

    public void setState(final MsmbState state) {
        this.state = state;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(final Connection conn) {
        this.conn = conn;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(final Channel channel) {
        this.channel = channel;
    }

}
