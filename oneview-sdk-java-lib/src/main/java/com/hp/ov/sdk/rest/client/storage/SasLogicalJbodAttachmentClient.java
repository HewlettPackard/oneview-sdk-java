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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbodAttachment;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class SasLogicalJbodAttachmentClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalJbodAttachmentClient.class);

    protected static final String SAS_LOGICAL_JBOD_ATTACHMENT_URI = "/rest/sas-logical-jbod-attachments";

    private final BaseClient baseClient;

    public SasLogicalJbodAttachmentClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link SasLogicalJbodAttachment} details for the specified
     * SAS logical JBOD attachment.
     *
     * @param resourceId SAS logical JBOD attachment resource identifier as seen in HPE OneView.
     *
     * @return {@link SasLogicalJbodAttachment} object containing the details.
     */
    public SasLogicalJbodAttachment getById(String resourceId) {
        LOGGER.info("SasLogicalJbodAttachmentClient : getById : Start");

        SasLogicalJbodAttachment attachment = baseClient.getResource(
                UrlUtils.createUrl(SAS_LOGICAL_JBOD_ATTACHMENT_URI, resourceId), SasLogicalJbodAttachment.class);

        LOGGER.info("SasLogicalJbodAttachmentClient : getById : End");

        return attachment;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalJbodAttachment}&gt; containing details
     * for all the available SAS logical JBOD attachments found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalJbodAttachment}&gt; containing
     * the details for all found SAS logical JBOD attachments.
     */
    public ResourceCollection<SasLogicalJbodAttachment> getAll() {
        LOGGER.info("SasLogicalJbodAttachmentClient : getAll : Start");

        ResourceCollection<SasLogicalJbodAttachment> attachments = baseClient.getResourceCollection(
                SAS_LOGICAL_JBOD_ATTACHMENT_URI, SasLogicalJbodAttachment.class);

        LOGGER.info("SasLogicalJbodAttachmentClient : getAll : End");

        return attachments;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalJbodAttachment}&gt; containing details
     * for the available SAS logical JBOD attachments found under the current HPE OneView that match the name.
     *
     * @param name SAS logical JBOD attachment name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalJbodAttachment}&gt; containing
     * the details for the found SAS logical JBOD attachments.
     */
    public ResourceCollection<SasLogicalJbodAttachment> getByName(String name) {
        LOGGER.info("SasLogicalJbodAttachmentClient : getByName : Start");

        ResourceCollection<SasLogicalJbodAttachment> attachments = baseClient.getResourceCollection(
                SAS_LOGICAL_JBOD_ATTACHMENT_URI, SasLogicalJbodAttachment.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("SasLogicalJbodAttachmentClient : getByName : End");

        return attachments;
    }

}
