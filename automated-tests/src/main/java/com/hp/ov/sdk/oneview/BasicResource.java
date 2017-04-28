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

package com.hp.ov.sdk.oneview;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.client.OneViewClient;

public abstract class BasicResource {

    protected Map<String, String> resourceProperties;
    protected OneViewClient oneViewClient;

    protected TreeMap<String, String> category;

    public BasicResource() {
        oneViewClient = Credential.getInstance().getOneViewClient();
        category = new TreeMap<String, String>();
    }

    public void setResourceProperties(Map<String, String> properties) {
        this.resourceProperties = properties;
    }

    protected Map<String, String> getResourceProperties(Object obj) {
        if (obj != null) {
            try {
                return BeanUtils.describe(obj);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getCount(ResourceCollection<?> collection) {
        return collection != null ? collection.getCount() : -1;
    }

    public Object getResource(ResourceCollection<?> collection) {
        return !collection.getMembers().isEmpty() ? collection.getMembers().get(0) : null;
    }

    public String objToString(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public String emptyField(String str) {
        return str.isEmpty() ? null : str;
    }

    public String taskToString(TaskResource task) {
        return task.getName();
    }

    public String getCategory() {
        return category.get(Credential.getInstance().getVersion());
    }

}
