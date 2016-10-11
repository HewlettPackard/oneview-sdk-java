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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public enum QosDscpValues {

    DSCP10_AF11("DSCP 10, AF11"),
    DSCP12_AF12("DSCP 12, AF12"),
    DSCP14_AF13("DSCP 14, AF13"),
    DSCP18_AF21("DSCP 18, AF21"),
    DSCP20_AF22("DSCP 20, AF22"),
    DSCP22_AF23("DSCP 22, AF23"),
    DSCP26_AF31("DSCP 26, AF31"),
    DSCP28_AF32("DSCP 28, AF32"),
    DSCP30_AF33("DSCP 30, AF33"),
    DSCP34_AF41("DSCP 34, AF41"),
    DSCP36_AF42("DSCP 36, AF42"),
    DSCP38_AF43("DSCP 38, AF43"),
    DSCP46_EF("DSCP 46, EF"),
    DSCP0_CS0("DSCP 0, CS0"),
    DSCP8_CS1("DSCP 8, CS1"),
    DSCP16_CS2("DSCP 16, CS2"),
    DSCP24_CS3("DSCP 24, CS3"),
    DSCP32_CS4("DSCP 32, CS4"),
    DSCP40_CS5("DSCP 40, CS5"),
    DSCP48_CS6("DSCP 48, CS6"),
    DSCP56_CS7("DSCP 56, CS7"),
    Unknown("Unknown"),
    NA("NA");

    private final String value;

    private QosDscpValues(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static QosDscpValues lookup(final String dscpVal) {
        return getAllValuesMap().get(dscpVal);
    }

    /**
     * @return
     */
    private static Map<String, QosDscpValues> getAllValuesMap() {
        final Map<String, QosDscpValues> lookup = new HashMap<String, QosDscpValues>();

        lookup.put("DSCP 10, AF11", DSCP10_AF11);
        lookup.put("DSCP 12, AF12", DSCP12_AF12);
        lookup.put("DSCP 14, AF13", DSCP14_AF13);
        lookup.put("DSCP 18, AF21", DSCP18_AF21);
        lookup.put("DSCP 20, AF22", DSCP20_AF22);
        lookup.put("DSCP 22, AF23", DSCP22_AF23);
        lookup.put("DSCP 26, AF31", DSCP26_AF31);
        lookup.put("DSCP 28, AF32", DSCP28_AF32);
        lookup.put("DSCP 30, AF33", DSCP30_AF33);
        lookup.put("DSCP 34, AF41", DSCP34_AF41);
        lookup.put("DSCP 36, AF42", DSCP36_AF42);
        lookup.put("DSCP 38, AF43", DSCP38_AF43);
        lookup.put("DSCP 46, EF", DSCP46_EF);
        lookup.put("DSCP 0, CS0", DSCP0_CS0);
        lookup.put("DSCP 8, CS1", DSCP8_CS1);
        lookup.put("DSCP 16, CS2", DSCP16_CS2);
        lookup.put("DSCP 24, CS3", DSCP24_CS3);
        lookup.put("DSCP 32, CS4", DSCP32_CS4);
        lookup.put("DSCP 40, CS5", DSCP40_CS5);
        lookup.put("DSCP 48, CS6", DSCP48_CS6);
        lookup.put("DSCP 56, CS7", DSCP56_CS7);

        return lookup;
    }

    public static Collection<QosDscpValues> getAllValues() {
        final Set<QosDscpValues> s = new HashSet<QosDscpValues>();
        s.addAll(getAllValuesMap().values());
        return s;
    }
}
