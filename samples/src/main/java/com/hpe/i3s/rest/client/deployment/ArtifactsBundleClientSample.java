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
package com.hpe.i3s.rest.client.deployment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hpe.i3s.client.deployment.ArtifactsBundleClient;
import com.hpe.i3s.dto.deployment.artifactsbundle.ArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.UserBackupParams;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class ArtifactsBundleClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactsBundleClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String ARTIFACTS_BUNDLE_RESOURCE_ID = "abc123";
    private static final String ARTIFACTS_BUNDLE_NAME = "Sample Artifact Bundle";
    private static final String ARTIFACTS_BUNDLE_NAME_UPDATED =  ARTIFACTS_BUNDLE_NAME + " Updated";
    private static final String ARTIFACTS_BUNDLE_FILE_PATH =  "src/main/resources/bundle.ab";
    // ================================

    private final ArtifactsBundleClient artifactsBundleClient;

    public ArtifactsBundleClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        ImageStreamerClient i3sClient = new ImageStreamerClient(oneViewClient);

        this.artifactsBundleClient = i3sClient.artifactsBundle();
    }

    private void getArtifactsBundleById() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getByName(ARTIFACTS_BUNDLE_NAME).get(0);

        artifactsBundle = this.artifactsBundleClient.getById(artifactsBundle.getResourceId());

        LOGGER.info("Artifacts Bundle returned to client: {}", artifactsBundle.toJsonString());
    }

    private void getArtifactsBundleByName() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getByName(ARTIFACTS_BUNDLE_NAME).get(0);

        LOGGER.info("Artifacts Bundle returned to client: {}", artifactsBundle.toJsonString());
    }

    private void getAllArtifactsBundles() {
        ResourceCollection<ArtifactsBundle> artifactsBundles = this.artifactsBundleClient.getAll();

        LOGGER.info("Artifacts Bundles returned to client: {}", artifactsBundles.toJsonString());
    }

    private void getBackupArchiveBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getBackupArchiveBundle(ARTIFACTS_BUNDLE_RESOURCE_ID);

        artifactsBundle = this.artifactsBundleClient.getBackupArchiveBundle(artifactsBundle.getResourceId());

        LOGGER.info("Artifacts Bundle returned to client: {}", artifactsBundle.toJsonString());
    }

    private void getBackupBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getBackupBundle(ARTIFACTS_BUNDLE_RESOURCE_ID);

        artifactsBundle = this.artifactsBundleClient.getBackupBundle(artifactsBundle.getResourceId());

        LOGGER.info("Artifacts Bundle returned to client: {}", artifactsBundle.toJsonString());
    }

    private void getBackupBundles() {
        ResourceCollection<ArtifactsBundle> artifactsBundle = this.artifactsBundleClient.getBackupBundles();

        LOGGER.info("Artifacts Bundles returned to client: {}", artifactsBundle.toJsonString());
    }

    private void createArtifactsBundle() {
        ArtifactsBundle artifactsBundle = new ArtifactsBundle();

        artifactsBundle.setName(ARTIFACTS_BUNDLE_NAME);

        TaskResource task = this.artifactsBundleClient.create(artifactsBundle);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void createArtifactsBundleWithFile() {
        TaskResource task = this.artifactsBundleClient.create(ARTIFACTS_BUNDLE_FILE_PATH);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void createBackupBundle() {
        UserBackupParams backupParams = new UserBackupParams();

        TaskResource task = this.artifactsBundleClient.createBackupBundle(backupParams);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void extractBackupBundle() {
        UserBackupParams backupParams = new UserBackupParams();

        TaskResource task = this.artifactsBundleClient.extractBackupBundle(ARTIFACTS_BUNDLE_RESOURCE_ID, backupParams);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void extractBundle() {
        TaskResource task = this.artifactsBundleClient.extractBundle(ARTIFACTS_BUNDLE_RESOURCE_ID);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void downloadBundle() {
        String response = this.artifactsBundleClient.downloadBundle(ARTIFACTS_BUNDLE_RESOURCE_ID);

        LOGGER.info("Task object returned to client: {}", response);
    }

    private void stopBundleCreation() {
        ArtifactsBundle artifactsBundle = new ArtifactsBundle();

        artifactsBundle.setName(ARTIFACTS_BUNDLE_NAME);

        TaskResource task = this.artifactsBundleClient.create(artifactsBundle);
        String response = this.artifactsBundleClient.stopBundleCreation(ARTIFACTS_BUNDLE_RESOURCE_ID, task.getUri());

        LOGGER.info("Response object returned to client: {}", response);
    }

    private void createBackupArchiveBundle() {
        ArtifactsBundle artifactsBundle = new ArtifactsBundle();

        artifactsBundle.setName(ARTIFACTS_BUNDLE_NAME);

        TaskResource task = this.artifactsBundleClient.createBackupArchiveBundle(ARTIFACTS_BUNDLE_FILE_PATH);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void updateArtifactsBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getByName(ARTIFACTS_BUNDLE_NAME).get(0);

        artifactsBundle.setName(ARTIFACTS_BUNDLE_NAME_UPDATED);

        TaskResource task = this.artifactsBundleClient.update(
                artifactsBundle.getResourceId(), artifactsBundle);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void deleteArtifactsBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getByName(ARTIFACTS_BUNDLE_NAME_UPDATED).get(0);

        TaskResource task = this.artifactsBundleClient.delete(artifactsBundle.getResourceId());

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    public static void main(String[] args) {
        ArtifactsBundleClientSample sample = new ArtifactsBundleClientSample();

        sample.createArtifactsBundle();
        sample.createArtifactsBundleWithFile();
        sample.createBackupBundle();
        sample.createBackupArchiveBundle();
        sample.getBackupBundles();
        sample.getAllArtifactsBundles();
        sample.getArtifactsBundleById();
        sample.getArtifactsBundleByName();
        sample.getBackupArchiveBundle();
        sample.getBackupBundle();
        sample.updateArtifactsBundle();
        sample.downloadBundle();
        sample.extractBackupBundle();
        sample.extractBundle();
        sample.deleteArtifactsBundle();
        sample.stopBundleCreation();
    }

}
