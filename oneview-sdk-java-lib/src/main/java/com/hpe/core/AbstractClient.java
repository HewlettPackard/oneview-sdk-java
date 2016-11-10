/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hpe.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.reflect.Reflection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

public abstract class AbstractClient {

    private final Map<Class<?>, Object> clients = new ConcurrentHashMap<>();

    protected AbstractClient() { }

    protected abstract BaseClient baseClient();

    protected <T> T getProxy(Class<T> clientClass) {
        T instance = (T) this.clients.get(clientClass);

        if (instance == null) {
            instance = Reflection.newProxy(clientClass,
                    new ClientRequestHandler<>(this.baseClient(), clientClass));

            this.clients.put(clientClass, instance);
        }
        return instance;
    }

}
