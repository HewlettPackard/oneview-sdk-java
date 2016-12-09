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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.http.core.client.DownloadPath;
import com.hpe.i3s.client.deployment.ArtifactsBundleClient;
import com.hpe.i3s.client.deployment.DeploymentGroupClient;
import com.hpe.i3s.dto.deployment.artifactsbundle.ArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.CreateArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.InputArtifacts;
import com.hpe.i3s.dto.deployment.artifactsbundle.TaskUri;
import com.hpe.i3s.dto.deployment.artifactsbundle.UserBackupParams;
import com.hpe.i3s.dto.deployment.deploymentgroup.DeploymentGroup;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class ArtifactsBundleClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactsBundleClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String ARTIFACTS_BUNDLE_RESOURCE_ID = "92710244-aa44-4ee5-b96e-4d60d67f49d0";
    private static final String ARTIFACTS_BUNDLE_BACKUP_RESOURCE_ID = "8063162e-b99c-4cca-9487-37c7f992724e";
    private static final String ARTIFACTS_BUNDLE_NAME = "bundle";
    private static final String ARTIFACTS_BUNDLE_NAME_UPDATED =  ARTIFACTS_BUNDLE_NAME + " Updated";
    private static final String ARTIFACTS_BUNDLE_FILE_PATH =  "/home/user/Downloads/bundle-file.zip";
    private static final String ARTIFACTS_BUNDLE_BACKUP_FILE_PATH =  "/home/user/Downloads/bundle-file-backup.zip";
    private static final String ARTIFACTS_BUNDLE_BACKUP_DOWNLOAD_PATH =  "/home/user/Downloads";
    // ================================


    private final ArtifactsBundleClient artifactsBundleClient;
    private final ImageStreamerClient i3sClient;
    
    public ArtifactsBundleClientSample() {
        this.i3sClient = new ImageStreamerClientSample().getImageStreamerClient();

        this.artifactsBundleClient = this.i3sClient.artifactsBundle();
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

    private void downloadBackupArchiveBundle() {
        String filePath = this.artifactsBundleClient.downloadBackupArchiveBundle(ARTIFACTS_BUNDLE_BACKUP_RESOURCE_ID,
                DownloadPath.at(ARTIFACTS_BUNDLE_BACKUP_DOWNLOAD_PATH));

        LOGGER.info("File returned to client: {}", filePath);
    }

    private void getBackupBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getBackupBundle(ARTIFACTS_BUNDLE_BACKUP_RESOURCE_ID);

        artifactsBundle = this.artifactsBundleClient.getBackupBundle(artifactsBundle.getResourceId());

        LOGGER.info("Artifacts Bundle returned to client: {}", artifactsBundle.toJsonString());
    }

    private void getBackupBundles() {
        ResourceCollection<ArtifactsBundle> artifactsBundle = this.artifactsBundleClient.getBackupBundles();

        LOGGER.info("Artifacts Bundles returned to client: {}", artifactsBundle.toJsonString());
    }

    private void createArtifactsBundle() {
        CreateArtifactsBundle artifactsBundle = this.buildAddArtifactBundle();

        TaskResource task = this.artifactsBundleClient.create(artifactsBundle);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void createArtifactsBundleWithFile() {
        File file = new File(ARTIFACTS_BUNDLE_FILE_PATH);
        TaskResource task = this.artifactsBundleClient.create(file);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void createBackupBundle() {
        DeploymentGroupClient deploymentGroupClient = this.i3sClient.deploymentGroup();
        ResourceCollection<DeploymentGroup> deploymentGroups = deploymentGroupClient.getAll();

        UserBackupParams backupParams = new UserBackupParams();
        backupParams.setDeploymentGroupURI(deploymentGroups.get(0).getUri());

        TaskResource task = this.artifactsBundleClient.createBackupBundle(backupParams);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void extractBackupBundle() {
        DeploymentGroupClient deploymentGroupClient = this.i3sClient.deploymentGroup();
        ResourceCollection<DeploymentGroup> deploymentGroups = deploymentGroupClient.getAll();

        UserBackupParams backupParams = new UserBackupParams();
        backupParams.setDeploymentGroupURI(deploymentGroups.get(0).getUri());

        TaskResource task = this.artifactsBundleClient.extractBackupBundle(ARTIFACTS_BUNDLE_BACKUP_RESOURCE_ID,
                backupParams);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void extractBundle() {
        TaskResource task = this.artifactsBundleClient.extractBundle(ARTIFACTS_BUNDLE_RESOURCE_ID);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void downloadBundle() {
        String filePath = this.artifactsBundleClient.downloadBundle(ARTIFACTS_BUNDLE_RESOURCE_ID,
                DownloadPath.at(ARTIFACTS_BUNDLE_BACKUP_DOWNLOAD_PATH));

        LOGGER.info("File returned to client: {}", filePath);
    }

    private void stopBundleCreation() {
        CreateArtifactsBundle artifactsBundle = this.buildAddArtifactBundle();

        TaskResource task = this.artifactsBundleClient.create(artifactsBundle);

        TaskUri taskUri = new TaskUri();

        taskUri.setTaskUri(task.getUri());

        String response = this.artifactsBundleClient.stopBundleCreation(ARTIFACTS_BUNDLE_RESOURCE_ID, taskUri);

        LOGGER.info("Response returned to client: {}", response);
    }

    private void createBackupArchiveBundle() {
        File file = new File(ARTIFACTS_BUNDLE_BACKUP_FILE_PATH);

        DeploymentGroupClient deploymentGroupClient = this.i3sClient.deploymentGroup();
        ResourceCollection<DeploymentGroup> deploymentGroups = deploymentGroupClient.getAll();

        String deploymentGrpUri = deploymentGroups.get(0).getUri();

        TaskResource task = this.artifactsBundleClient.createBackupArchiveBundle(file, deploymentGrpUri);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void updateArtifactsBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getByName(ARTIFACTS_BUNDLE_NAME).get(0);

        artifactsBundle.setName(ARTIFACTS_BUNDLE_NAME_UPDATED);

        artifactsBundle = this.artifactsBundleClient.update(
                artifactsBundle.getResourceId(), artifactsBundle);

        LOGGER.info("Artifacts Bundle object returned to client: {}", artifactsBundle.toJsonString());
    }

    private void deleteArtifactsBundle() {
        ArtifactsBundle artifactsBundle = this.artifactsBundleClient.getByName(ARTIFACTS_BUNDLE_NAME_UPDATED).get(0);

        TaskResource task = this.artifactsBundleClient.delete(artifactsBundle.getResourceId());

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private CreateArtifactsBundle buildAddArtifactBundle() {
        CreateArtifactsBundle artifactsBundle = new CreateArtifactsBundle();
        artifactsBundle.setName(ARTIFACTS_BUNDLE_NAME);
        artifactsBundle.setDescription("");

        List<InputArtifacts> buildPlans = new ArrayList<>();
        InputArtifacts buildPlan = new InputArtifacts();
        buildPlan.setResourceUri("/rest/build-plans/" + OsBuildPlanClientSample.OS_BUILD_PLAN_RESOURCE_ID);
        buildPlans.add(buildPlan);
        artifactsBundle.setBuildPlans(buildPlans);

        List<InputArtifacts> deploymentPlans = new ArrayList<>();
        InputArtifacts deploymentPlan = new InputArtifacts();
        deploymentPlan.setResourceUri("/rest/deployment-plans/" + DeploymentPlanClientSample.DEPLOYMENT_PLAN_RESOURCE_ID);
        deploymentPlans.add(deploymentPlan);
        artifactsBundle.setDeploymentPlans(deploymentPlans);

        List<InputArtifacts> goldenImages = new ArrayList<>();
        InputArtifacts goldenImage = new InputArtifacts();
        goldenImage.setResourceUri("/rest/golden-images/" + GoldenImageClientSample.GOLDEN_IMAGE_RESOURCE_ID);
        goldenImages.add(goldenImage);
        artifactsBundle.setGoldenImages(goldenImages);

        return artifactsBundle;
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
        sample.getBackupBundle();
        sample.downloadBackupArchiveBundle();
        sample.updateArtifactsBundle();
        sample.downloadBundle();
        sample.extractBackupBundle();
        sample.extractBundle();
        sample.deleteArtifactsBundle();
        sample.stopBundleCreation();
    }

}
