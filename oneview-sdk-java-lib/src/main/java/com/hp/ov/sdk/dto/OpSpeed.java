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

public enum OpSpeed {

    Speed0M("Speed0M"),
    Speed1M("Speed1M"),
    Speed10M("Speed10M"),
    Speed100M("Speed100M"),
    Speed1G("Speed1G"),
    Speed2G("Speed2G"),
    Speed2_5G("Speed2_5G"),
    Speed4G("Speed4G"),
    Speed8G("Speed8G"),
    Speed10G("Speed10G"),
    Auto("Auto"),
    Speed20G("Speed20G"),
    Speed40G("Speed40G"),
    Unknown("Unknown");

    private final String value;
    private static Map<String, OpSpeed> constants = new HashMap<String, OpSpeed>();

    static {
        for (final OpSpeed c : values()) {
            constants.put(c.value, c);
        }
    }

    private OpSpeed(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static OpSpeed fromValue(final String value) {
        final OpSpeed constant = constants.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
