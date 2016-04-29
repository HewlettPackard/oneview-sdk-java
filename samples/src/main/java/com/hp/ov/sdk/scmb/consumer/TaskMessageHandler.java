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

package com.hp.ov.sdk.scmb.consumer;

import com.hp.ov.sdk.dto.AssociatedResource;
import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.dto.ScmbAlertsResource;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener;

public class TaskMessageHandler implements ScmbListener {

    @Override
    public void handleScmbMessage(ScmbAlertsMessageDto alertsDto) {
        ScmbAlertsResource resource = alertsDto.getResource();

        TaskResourceV2 task = new TaskResourceV2();
        AssociatedResource associatedResource = new AssociatedResource();

        associatedResource.setResourceUri(resource.getAssociatedResource().getResourceUri());
        associatedResource.setAssociationType(resource.getAssociatedResource().getAssociationType());
        associatedResource.setResourceCategory(resource.getAssociatedResource().getResourceCategory());
        associatedResource.setResourceName(resource.getAssociatedResource().getResourceName());

        task.setUri(resource.getUri());
        task.setAssociatedResource(associatedResource);
        task.setModified(resource.getModified());
        task.setCreated(resource.getCreated());
        task.setName(resource.getName());

        System.out.println("Task built according to SCMB message: " + task);
    }
}
