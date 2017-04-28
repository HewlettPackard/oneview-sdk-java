/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbodAttachment;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.storage.SasLogicalJbodAttachmentClient;

public class SasLogicalJbodAttachmentResource extends BasicResource implements Resource {

    private static SasLogicalJbodAttachmentResource instance;
    private SasLogicalJbodAttachmentClient client;

    public SasLogicalJbodAttachmentResource() {
        client = oneViewClient.sasLogicalJbodAttachment();
    }

    public static SasLogicalJbodAttachmentResource getInstance() {
        if (instance == null) {
            instance = new SasLogicalJbodAttachmentResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        SasLogicalJbodAttachment sasLogicalJbodAttachment = (SasLogicalJbodAttachment) getResource(
                client.getByName(name));
        return sasLogicalJbodAttachment == null ? "" : sasLogicalJbodAttachment.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

}
