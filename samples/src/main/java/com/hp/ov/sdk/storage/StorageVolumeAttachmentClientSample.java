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
package com.hp.ov.sdk.storage;

import java.util.List;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ExtraStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.StorageVolumeAttachmentsClient;
import com.hp.ov.sdk.rest.client.StorageVolumeAttachmentsClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class StorageVolumeAttachmentClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String ATTACHMENT_ID = "B854DCBA-260C-43FC-A697-B68268D6C3AB";
    private static final String PATH_ID = "0B0C5738-D078-4A27-85DC-1F0E5BBFCE7D";
    private static final String SERVER_PROFILE_ID = "ac0aa4e4-05be-47b8-9ac7-347deb18bb5f";
    // ================================

    private final StorageVolumeAttachmentsClient storageVolumeAttachmentsClient;

    public StorageVolumeAttachmentClientSample() {
        this.storageVolumeAttachmentsClient = StorageVolumeAttachmentsClientImpl.getClient();
    }

    private void getStorageVolumeAttachment() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            StorageVolumeAttachment attachment = this.storageVolumeAttachmentsClient.getStorageVolumeAttachment(
                    params, ATTACHMENT_ID);

            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachment : " +
                    "StorageVolumeAttachment object returned to client : " + attachment);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachment : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachment : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachment : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachment : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachment : " +
                    "arguments are null ");
        }
    }

    private void getAllStorageVolumeAttachments() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            StorageVolumeAttachmentCollection collection
                    = this.storageVolumeAttachmentsClient.getAllStorageVolumeAttachments(params);

            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachments : " +
                    "StorageVolumeAttachmentCollection object returned to client : " + collection);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachments : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachments : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachments : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachments : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachments : " +
                    "arguments are null ");
        }
    }

    private void getStorageVolumeAttachmentPath() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            StorageVolumeAttachmentPath path = this.storageVolumeAttachmentsClient.getStorageVolumeAttachmentPath(
                    params, ATTACHMENT_ID, PATH_ID);

            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachmentPath : " +
                    "StorageVolumeAttachmentPath object returned to client : " + path);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachmentPath : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachmentPath : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachmentPath : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachmentPath : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getStorageVolumeAttachmentPath : " +
                    "arguments are null ");
        }
    }

    private void getAllStorageVolumeAttachmentPaths() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            List<StorageVolumeAttachmentPath> collection
                    = this.storageVolumeAttachmentsClient.getAllStorageVolumeAttachmentPaths(params, ATTACHMENT_ID);

            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachmentPaths : " +
                    "StorageVolumeAttachmentPathCollection object returned to client : " + collection);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachmentPaths : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachmentPaths : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachmentPaths : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachmentPaths : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getAllStorageVolumeAttachmentPaths : " +
                    "arguments are null ");
        }
    }

    private void getExtraUnmanagedStorageVolumeAttachaments() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            ExtraStorageVolumeCollection collection
                    = this.storageVolumeAttachmentsClient.getExtraUnmanagedStorageVolumeAttachments(params);

            System.out.println("StorageVolumeAttachmentClientSample : getExtraUnmanagedStorageVolumeAttachments : " +
                    "ExtraStorageVolumeCollection object returned to client : " + collection);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getExtraUnmanagedStorageVolumeAttachments : " +
                    "resource you are looking is not found");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getExtraUnmanagedStorageVolumeAttachments : " +
                    "no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeAttachmentClientSample : getExtraUnmanagedStorageVolumeAttachments : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getExtraUnmanagedStorageVolumeAttachments : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : getExtraUnmanagedStorageVolumeAttachments : " +
                    "arguments are null ");
        }
    }

    private void repairExtraUnmanagedStorageVolumeAttachment() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

            repair.setType("ExtraUnmanagedStorageVolumes");
            repair.setResourceUri(String.format("%s/%s", ResourceUris.SERVER_PROFILE_URI, SERVER_PROFILE_ID));

            TaskResourceV2 task = this.storageVolumeAttachmentsClient.repairExtraUnmanagedStorageVolumeAttachment(
                    params, repair, false);

            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment :" +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment : " +
                    "resource you are looking is not found");
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment : " +
                    "bad request, may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment : " +
                    "no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment :" +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeAttachmentClientSample : repairExtraUnmanagedStorageVolumeAttachment : "
                    + "errors in task, please check task resource for more details ");
        }
    }

    public static void main(String[] args) {
        StorageVolumeAttachmentClientSample client = new StorageVolumeAttachmentClientSample();

        client.getStorageVolumeAttachment();
        client.getAllStorageVolumeAttachments();
        client.getStorageVolumeAttachmentPath();
        client.getAllStorageVolumeAttachmentPaths();
        client.getExtraUnmanagedStorageVolumeAttachaments();
        client.repairExtraUnmanagedStorageVolumeAttachment();
    }

}
