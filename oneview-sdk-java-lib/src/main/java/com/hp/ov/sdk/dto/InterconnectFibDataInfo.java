/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

public class InterconnectFibDataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private long fileSize = 0;
    private TaskState state;

    private String uri;
    private String category;
    private String type;
    private String eTag;
    private String created;
    private String modified;
    private String status;

    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }
    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    /**
     * @return the state
     */
    public TaskState getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(TaskState state) {
        this.state = state;
    }
    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }
    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }
    /**
     * @param eTag the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }
    /**
     * @return the created
     */
    public String getCreated() {
        return created;
    }
    /**
     * @param created the created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }
    /**
     * @return the modified
     */
    public String getModified() {
        return modified;
    }
    /**
     * @param modified the modified to set
     */
    public void setModified(String modified) {
        this.modified = modified;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }


}
