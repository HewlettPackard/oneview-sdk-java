/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class MsmbConnection {

    private final Connection conn;
    private final Channel channel;
    private final MsmbState state;

    public MsmbConnection(Connection conn, Channel channel, MsmbState state) {
        this.conn = conn;
        this.channel = channel;
        this.state = state;
    }

    public MsmbState getState() {
        return state;
    }

    public Connection getConn() {
        return conn;
    }

    public Channel getChannel() {
        return channel;
    }

}
