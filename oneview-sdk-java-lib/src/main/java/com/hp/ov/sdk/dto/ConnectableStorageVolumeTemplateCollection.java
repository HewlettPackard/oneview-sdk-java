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

import java.util.ArrayList;
import java.util.List;

public class ConnectableStorageVolumeTemplateCollection extends BaseCollectionResource<ConnectableStorageVolumeTemplate> {

    private static final long serialVersionUID = -4708296899403834626L;

    private final List<ConnectableStorageVolumeTemplate> members = new ArrayList<>();

    @Override
    public List<ConnectableStorageVolumeTemplate> getMembers() {
        return new ArrayList<>(this.members);
    }

    @Override
    public void setMembers(final List<ConnectableStorageVolumeTemplate> members) {
        if (members != null) {
            this.members.clear();
            this.members.addAll(members);

            super.setCount(this.members.size());
        }
    }

}