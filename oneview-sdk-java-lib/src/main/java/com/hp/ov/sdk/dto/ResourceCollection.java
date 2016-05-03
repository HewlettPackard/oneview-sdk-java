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

package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class ResourceCollection<T> extends BaseModelResource {

    private int start;
    private int count;
    private int total;
    private String prevPageUri;
    private String nextPageUri;
    private List<T> members = new ArrayList<>();

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPrevPageUri() {
        return prevPageUri;
    }

    public void setPrevPageUri(String prevPageUri) {
        this.prevPageUri = prevPageUri;
    }

    public String getNextPageUri() {
        return nextPageUri;
    }

    public void setNextPageUri(String nextPageUri) {
        this.nextPageUri = nextPageUri;
    }

    public List<T> getMembers() {
        return new ArrayList<>(this.members);
    }

    public boolean isEmpty() {
        return this.members.isEmpty();
    }

    public void setMembers(List<T> members) {
        this.members.clear();

        if (members != null) {
            this.members.addAll(members);
        }
        this.setCount(this.members.size());
    }
}
