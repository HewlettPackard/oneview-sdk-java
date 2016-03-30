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

import java.util.List;

public class AvailableTargets extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private List<Target> targets;

    /**
     * @return the targets
     */
    public List<Target> getTargets() {
        return targets;
    }

    /**
     * @param targets the targets to set
     */
    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }


}
