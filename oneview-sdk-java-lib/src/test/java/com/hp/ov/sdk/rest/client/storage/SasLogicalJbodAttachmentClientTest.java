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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbodAttachment;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class SasLogicalJbodAttachmentClientTest {

    private static final String ANY_SAS_LOGICAL_ATTACHMENT_RESOURCE_ID = "random-UUID";
    private static final String ANY_SAS_LOGICAL_ATTACHMENT_TYPE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SasLogicalJbodAttachmentClient sasLogicalJbodAttachmentClient;

    @Test
    public void shouldGetSasLogicalJbodAttachmentById() {
        sasLogicalJbodAttachmentClient.getById(ANY_SAS_LOGICAL_ATTACHMENT_RESOURCE_ID);

        String expectedUri = SasLogicalJbodAttachmentClient.SAS_LOGICAL_JBOD_ATTACHMENT_URI
                + "/" + ANY_SAS_LOGICAL_ATTACHMENT_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, SasLogicalJbodAttachment.class);
    }

    @Test
    public void shouldGetAllSasLogicalJbodAttachments() {
        sasLogicalJbodAttachmentClient.getAll();

        then(baseClient).should().getResourceCollection(
                SasLogicalJbodAttachmentClient.SAS_LOGICAL_JBOD_ATTACHMENT_URI,
                SasLogicalJbodAttachment.class);
    }

    @Test
    public void shouldGetSasLogicalJbodAttachmentByName() {
        sasLogicalJbodAttachmentClient.getByName(ANY_SAS_LOGICAL_ATTACHMENT_TYPE_NAME);

        then(baseClient).should().getResourceCollection(
                SasLogicalJbodAttachmentClient.SAS_LOGICAL_JBOD_ATTACHMENT_URI,
                SasLogicalJbodAttachment.class,
                UrlParameter.getFilterByNameParameter(ANY_SAS_LOGICAL_ATTACHMENT_TYPE_NAME));
    }

}
