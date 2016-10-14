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
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hpe.i3s.client.deployment.GoldenImageClient;
import com.hpe.i3s.dto.deployment.goldenimage.GoldenImage;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class GoldenImageClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoldenImageClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String GOLDEN_IMAGE_NAME = "Sample-GoldenImage";
    private static final String GOLDEN_IMAGE_NAME_UPDATED =  GOLDEN_IMAGE_NAME + "_Updated";
    // ================================

    private final GoldenImageClient client;

    public GoldenImageClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        ImageStreamerClient i3sClient = new ImageStreamerClient(oneViewClient);

        this.client = i3sClient.goldenImage();
    }

    private void getGoldenImageById() {
        GoldenImage goldenImage = this.client.getByName(GOLDEN_IMAGE_NAME).get(0);

        goldenImage = this.client.getById(goldenImage.getResourceId());

        LOGGER.info("Golden Image returned to client: {}", goldenImage.toJsonString());
    }

    private void getAllGoldenImages() {
        ResourceCollection<GoldenImage> goldenImages = this.client.getAll();

        LOGGER.info("Golden Images returned to client: {}", goldenImages.toJsonString());
    }

    private void createGoldenImage() {
        GoldenImage goldenImage = new GoldenImage();

        goldenImage.setName(GOLDEN_IMAGE_NAME);

        String response = this.client.create(goldenImage);

        LOGGER.info("Response returned to client: {}", response);
    }

    private void updateGoldenImage() {
        GoldenImage goldenImage = this.client.getByName(GOLDEN_IMAGE_NAME).get(0);

        goldenImage.setName(GOLDEN_IMAGE_NAME_UPDATED);

        GoldenImage updatedGoldenImage = this.client.update(goldenImage.getResourceId(), goldenImage);

        LOGGER.info("Golden Image object returned to client: {}", updatedGoldenImage.toJsonString());
    }

    private void deleteGoldenImage() {
        GoldenImage goldenImage = this.client.getByName(GOLDEN_IMAGE_NAME_UPDATED).get(0);

        String response = this.client.delete(goldenImage.getResourceId());

        LOGGER.info("Response returned to client: {}", response);
    }

    private void downloadGoldenImage() {
        GoldenImage goldenImage = this.client.getByName(GOLDEN_IMAGE_NAME_UPDATED).get(0);

        String goldenImageContent = this.client.download(goldenImage.getResourceId());

        LOGGER.info("Golden Image content returned to client: {}", goldenImageContent);
    }

    private void getGoldenImageArchivedLogs() {
        GoldenImage goldenImage = this.client.getByName(GOLDEN_IMAGE_NAME_UPDATED).get(0);

        String archivedLogs = this.client.getArchivedLogs(goldenImage.getResourceId());

        LOGGER.info("Golden Image archived logs returned to client: {}", archivedLogs);
    }

    public static void main(String[] args) {
        GoldenImageClientSample sample = new GoldenImageClientSample();

        sample.createGoldenImage();
        sample.getGoldenImageById();
        sample.getAllGoldenImages();
        sample.downloadGoldenImage();
        sample.getGoldenImageArchivedLogs();
        sample.updateGoldenImage();
        sample.deleteGoldenImage();
    }

}
