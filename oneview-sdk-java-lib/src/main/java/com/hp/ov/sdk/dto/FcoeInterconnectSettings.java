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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class FcoeInterconnectSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    private FcoeMode fcoeMode = FcoeMode.NotApplicable;

    /**
     * @return the fcoeMode
     */
    public FcoeMode getFcoeMode() {
        return fcoeMode;
    }

    /**
     * @param fcoeMode the fcoeMode to set
     */
    public void setFcoeMode(FcoeMode fcoeMode) {
        this.fcoeMode = fcoeMode;
    }

    public static enum FcoeMode {

        FcfNpv("FcfNpv"),
        NotApplicable("NotApplicable"),
        Transit("Transit"),
        Unknown("Unknown");

        private final String value;
        private static Map<String, FcoeInterconnectSettings.FcoeMode> constants = new HashMap<String, FcoeInterconnectSettings.FcoeMode>();

        static {
            for (final FcoeInterconnectSettings.FcoeMode c : values()) {
                constants.put(c.value, c);
            }
        }

        private FcoeMode(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static FcoeInterconnectSettings.FcoeMode fromValue(final String value) {
            final FcoeInterconnectSettings.FcoeMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }
}
