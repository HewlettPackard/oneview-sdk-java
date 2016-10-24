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

package com.hp.ov.sdk.messaging.msmb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetricSample implements Serializable {

    private static final long serialVersionUID = -7090704811685853929L;

    private List<String> valueArray = new ArrayList<>();
    private String name;

    /**
     * @return the valueArray
     */
    public List<String> getValueArray() {
        return valueArray;
    }

    /**
     * @param valueArray the valueArray to set
     */
    public void setValueArray(List<String> valueArray) {
        this.valueArray = valueArray;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
