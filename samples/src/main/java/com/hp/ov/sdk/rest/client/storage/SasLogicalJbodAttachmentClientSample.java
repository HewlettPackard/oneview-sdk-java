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

package com.hp.ov.sdk.rest.client.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbodAttachment;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class SasLogicalJbodAttachmentClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalJbodAttachmentClientSample.class);

    private final SasLogicalJbodAttachmentClient sasLogicalJbodAttachmentClient;

    // These are variables to be defined by user
    // ================================
    private static final String SAS_LOGICAL_JBOD_ATTACHMENT_RESOURCE_ID = "99469cbb-f5e8-4dc8-812e-632fb700ba62";
    private static final String SAS_LOGICAL_JBOD_ATTACHMENT_NAME = "logical-enclosure-logical-interconnect-group-1_4-1-SLJA-1";
    // ================================

    private SasLogicalJbodAttachmentClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.sasLogicalJbodAttachmentClient = oneViewClient.sasLogicalJbodAttachment();
    }

    private void getSasLogicalJbodAttachmentById() {
        SasLogicalJbodAttachment attachment = this.sasLogicalJbodAttachmentClient.getById(
                SAS_LOGICAL_JBOD_ATTACHMENT_RESOURCE_ID);

        LOGGER.info("SAS logical JBOD attachment returned to client: {}", attachment.toJsonString());
    }

    private void getAllSasLogicalJbodAttachments() {
        ResourceCollection<SasLogicalJbodAttachment> attachments = this.sasLogicalJbodAttachmentClient.getAll();

        LOGGER.info("SAS logical JBOD attachments returned to client: {}", attachments.toJsonString());
    }

    private void getSasLogicalJbodAttachmentByName() {
        SasLogicalJbodAttachment attachment = this.sasLogicalJbodAttachmentClient.getByName(
                SAS_LOGICAL_JBOD_ATTACHMENT_NAME).get(0);

        LOGGER.info("SAS logical JBOD attachment returned to client: {}", attachment.toJsonString());
    }

    public static void main(String[] args) {
        SasLogicalJbodAttachmentClientSample sample = new SasLogicalJbodAttachmentClientSample();

        sample.getAllSasLogicalJbodAttachments();
        sample.getSasLogicalJbodAttachmentById();
        sample.getSasLogicalJbodAttachmentByName();
    }

}
