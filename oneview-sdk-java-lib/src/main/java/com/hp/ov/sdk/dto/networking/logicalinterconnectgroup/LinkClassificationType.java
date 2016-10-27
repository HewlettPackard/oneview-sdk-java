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
package com.hp.ov.sdk.dto.networking.logicalinterconnectgroup;

import java.util.HashMap;
import java.util.Map;

public enum LinkClassificationType {

    DOT1P("DOT1P"),
    DOT1P_AND_DSCP("DOT1P_AND_DSCP"),
    DSCP("DSCP"),
    NA("NA"),
    Unknown("Unknown");

    private final String value;
    private final static Map<String, LinkClassificationType> CONSTANTS = new HashMap<String, LinkClassificationType>();

    static {
        for (LinkClassificationType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private LinkClassificationType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static LinkClassificationType fromValue(String value) {
        LinkClassificationType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
