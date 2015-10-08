/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "order", "manageBoot" })
public class Boot implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @JsonProperty("order")
    private List<String> order = new ArrayList<String>();
    @JsonProperty("manageBoot")
    private Boolean manageBoot;

    /**
     * 
     * @return The order
     */
    @JsonProperty("order")
    public List<String> getOrder() {
        return order;
    }

    /**
     * 
     * @param order
     *            The order
     */
    @JsonProperty("order")
    public void setOrder(final List<String> order) {
        this.order = order;
    }

    /**
     * 
     * @return The manageBoot
     */
    @JsonProperty("manageBoot")
    public Boolean getManageBoot() {
        return manageBoot;
    }

    /**
     * 
     * @param manageBoot
     *            The manageBoot
     */
    @JsonProperty("manageBoot")
    public void setManageBoot(final Boolean manageBoot) {
        this.manageBoot = manageBoot;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(order).append(manageBoot).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Boot) == false) {
            return false;
        }
        final Boot rhs = ((Boot) other);
        return new EqualsBuilder().append(order, rhs.order).append(manageBoot, rhs.manageBoot).isEquals();
    }

}
