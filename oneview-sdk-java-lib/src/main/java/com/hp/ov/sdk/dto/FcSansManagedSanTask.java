/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto;


import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;

public class FcSansManagedSanTask {

    private final TaskResourceV2 task;
    private final ResourceAdaptor adaptor;

    public FcSansManagedSanTask(TaskResourceV2 task,
            ResourceAdaptor adaptor) {

        this.task = task;
        this.adaptor = adaptor;

    }

    public TaskResourceV2 getTask() {
        return task;
    }

    public List<FcIssueResponse> getIssues() {
        List<FcIssueResponse> issues = new ArrayList<>();

        for (String taskOutput : task.getTaskOutput()) {
            issues.add(adaptor.buildResourceObject(taskOutput, FcIssueResponse.class));
        }
        return issues;
    }

}
