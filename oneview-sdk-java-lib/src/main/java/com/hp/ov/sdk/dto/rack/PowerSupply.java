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

package com.hp.ov.sdk.dto.rack;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PowerSupply {

    private Integer id;
    private PowerSupplySide side;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PowerSupplySide getSide() {
        return side;
    }

    public void setSide(PowerSupplySide side) {
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof PowerSupply) {
            PowerSupply that = (PowerSupply) o;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .append(side, that.side)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(side)
                .toHashCode();
    }
}
