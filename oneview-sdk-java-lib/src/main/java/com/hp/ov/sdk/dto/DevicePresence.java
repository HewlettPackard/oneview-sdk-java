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

public enum DevicePresence {
    PresenceNoOp("PresenceNoOp"),
    PresenceUnknown("PresenceUnknown"),
    Absent("Absent"),
    Present("Present"),
    Subsumed("Subsumed");

    private final String value;
    private static Map<String, DevicePresence> constants = new HashMap<String, DevicePresence>();

    static {
        for (final DevicePresence c : values()) {
            constants.put(c.value, c);
        }
    }

    private DevicePresence(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static DevicePresence fromValue(final String value) {
        final DevicePresence constant = constants.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
