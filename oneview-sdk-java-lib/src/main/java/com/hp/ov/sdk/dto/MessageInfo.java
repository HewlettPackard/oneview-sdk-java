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

import java.io.Serializable;

import com.hp.ov.sdk.dto.servers.serverprofile.ProfileConnectionStatus.ServerProfileStatus;

public class MessageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private ServerProfileStatus status;
    private String messageId;
    private String errorSource;
    private String sourceName;
    private String activityUri;
    private String message;
    private String recommendedActions;
    private String createdTime;

    /**
     * @return the status
     */
    public ServerProfileStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ServerProfileStatus status) {
        this.status = status;
    }

    /**
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the errorSource
     */
    public String getErrorSource() {
        return errorSource;
    }

    /**
     * @param errorSource the errorSource to set
     */
    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    /**
     * @return the sourceName
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * @param sourceName the sourceName to set
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * @return the activityUri
     */
    public String getActivityUri() {
        return activityUri;
    }

    /**
     * @param activityUri the activityUri to set
     */
    public void setActivityUri(String activityUri) {
        this.activityUri = activityUri;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the recommendedActions
     */
    public String getRecommendedActions() {
        return recommendedActions;
    }

    /**
     * @param recommendedActions the recommendedActions to set
     */
    public void setRecommendedActions(String recommendedActions) {
        this.recommendedActions = recommendedActions;
    }

    /**
     * @return the createdTime
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
