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

import java.util.HashMap;
import java.util.Map;

public enum ReconfigurationState {

    NotReapplyingConfiguration("NotReapplyingConfiguration"),
    ReapplyingConfiguration("ReapplyingConfiguration"),
    ReapplyingConfigurationFailed("ReapplyingConfigurationFailed"),
    PENDING("Pending");

    private final String value;
    private static Map<String, ReconfigurationState> constants = new HashMap<String, ReconfigurationState>();

    static {
        for (final ReconfigurationState c : values()) {
            constants.put(c.value, c);
        }
    }

    private ReconfigurationState(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ReconfigurationState fromValue(final String value) {
        final ReconfigurationState constant = constants.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
