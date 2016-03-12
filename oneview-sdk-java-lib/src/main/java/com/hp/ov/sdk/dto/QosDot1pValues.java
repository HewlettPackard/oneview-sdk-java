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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QosDot1pValues {

    DOT1P_BEST_EFFORT (0),
    DOT1P_BACKGROUND (1),
    DOT1P_SPARE (2),
    DOT1P_EXCELLENT_EFFORT (3),
    DOT1P_CONTROLLED_LOAD (4),
    DOT1P_VIDEO (5),
    DOT1P_VOICE (6),
    DOT1P_NETWORK_MGMT (7),
    Unknown (-1),
    NA (-2);

    private final int value;

    private QosDot1pValues(final int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static QosDot1pValues lookup(final int egress) {

        return getAllValuesMap().get(egress);
    }

    /**
     * @return
     */
    private static Map<Integer, QosDot1pValues> getAllValuesMap() {
        final Map<Integer, QosDot1pValues> lookup = new HashMap<Integer, QosDot1pValues>();
        lookup.put(0, DOT1P_BEST_EFFORT);
        lookup.put(1, DOT1P_BACKGROUND);
        lookup.put(2, DOT1P_SPARE);
        lookup.put(3, DOT1P_EXCELLENT_EFFORT);
        lookup.put(4, DOT1P_CONTROLLED_LOAD);
        lookup.put(5, DOT1P_VIDEO);
        lookup.put(6, DOT1P_VOICE);
        lookup.put(7, DOT1P_NETWORK_MGMT);

        return lookup;
    }

    public static Collection<QosDot1pValues> getAllValues() {
        final Set<QosDot1pValues> s = new HashSet<QosDot1pValues>();
        s.addAll(getAllValuesMap().values());
        return s;
    }
}
