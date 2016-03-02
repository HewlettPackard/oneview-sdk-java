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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "fcoeMode"
})
public class FcoeSettings implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("fcoeMode")
    private FcoeSettings.FcoeMode fcoeMode;

    /**
     *
     * @return
     *     The fcoeMode
     */
    @JsonProperty("fcoeMode")
    public FcoeSettings.FcoeMode getFcoeMode() {
        return fcoeMode;
    }

    /**
     *
     * @param fcoeMode
     *     The fcoeMode
     */
    @JsonProperty("fcoeMode")
    public void setFcoeMode(FcoeSettings.FcoeMode fcoeMode) {
        this.fcoeMode = fcoeMode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(fcoeMode)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FcoeSettings) == false) {
            return false;
        }
        FcoeSettings rhs = ((FcoeSettings) other);
        return new EqualsBuilder()
                .append(fcoeMode, rhs.fcoeMode)
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum FcoeMode {

        TRANSIT("Transit"),
        FCF_NPV("FcfNpv"),
        UNKNOWN("Unknown"),
        NOT_APPLICABLE("NotApplicable");
        private final String value;
        private final static Map<String, FcoeSettings.FcoeMode> CONSTANTS = new HashMap<String, FcoeSettings.FcoeMode>();

        static {
            for (FcoeSettings.FcoeMode c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private FcoeMode(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static FcoeSettings.FcoeMode fromValue(String value) {
            FcoeSettings.FcoeMode constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
