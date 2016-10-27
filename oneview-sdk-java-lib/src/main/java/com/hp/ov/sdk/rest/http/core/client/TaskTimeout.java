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

package com.hp.ov.sdk.rest.http.core.client;

/**
 * Some operations can be time consuming and will return a {@link com.hp.ov.sdk.dto.TaskResource}
 * as a response so the client can monitor the progress of the request. In this cases, it is
 * possible to specify a timeout using an implementation of {@link RequestOption}
 * called {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}.
 *
 * <p>Once a positive value for timeout has been configured, the returned task will be monitored
 * until the timeout is reached. If the task does not finish during the specified time interval,
 * an {@link com.hp.ov.sdk.exceptions.SDKTasksException} will be thrown. Otherwise, the resulting
 * task is returned.
 *
 * <p>If no timeout is specified, the default behavior is to wait until the operation completes.
 * Below is an example that illustrates how the timeout can be specified:
 *
 * <pre>{@code
 *     SomeClient client = oneViewClient.someClient();
 *     SomeResource resource = new SomeResource();
 *     TaskResource task = client.create(resource, TaskTimeout.of(5000)); //5 secs
 * }</pre>
 */
public class TaskTimeout implements RequestOption {

    private final int taskTimeoutMillis;

    public TaskTimeout(int taskTimeoutMillis) {
        this.taskTimeoutMillis = taskTimeoutMillis;
    }

    public static TaskTimeout of(int taskTimeoutMillis) {
        return new TaskTimeout(taskTimeoutMillis);
    }

    @Override
    public void apply(Request request) {
        request.setTimeout(this.taskTimeoutMillis);
    }
}
