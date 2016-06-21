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

package com.hp.ov.sdk.dto.servers.serverhardwaretype;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BiosSettings {

    private String category;
    private String defaultValue;
    private List<SettingDependency> dependencyList;
    private String helpText;
    private String id;
    private Integer lowerBound;
    private String name;
    private List<SettingOption> options;
    private Integer scalarIncrement;
    private Integer stringMaxLength;
    private Integer stringMinLength;
    private BiosSettingType type;
    private Integer upperBound;
    private String valueExpression;
    private String warningText;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<SettingDependency> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<SettingDependency> dependencyList) {
        this.dependencyList = dependencyList;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Integer lowerBound) {
        this.lowerBound = lowerBound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SettingOption> getOptions() {
        return options;
    }

    public void setOptions(List<SettingOption> options) {
        this.options = options;
    }

    public Integer getScalarIncrement() {
        return scalarIncrement;
    }

    public void setScalarIncrement(Integer scalarIncrement) {
        this.scalarIncrement = scalarIncrement;
    }

    public Integer getStringMaxLength() {
        return stringMaxLength;
    }

    public void setStringMaxLength(Integer stringMaxLength) {
        this.stringMaxLength = stringMaxLength;
    }

    public Integer getStringMinLength() {
        return stringMinLength;
    }

    public void setStringMinLength(Integer stringMinLength) {
        this.stringMinLength = stringMinLength;
    }

    public BiosSettingType getType() {
        return type;
    }

    public void setType(BiosSettingType type) {
        this.type = type;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Integer upperBound) {
        this.upperBound = upperBound;
    }

    public String getValueExpression() {
        return valueExpression;
    }

    public void setValueExpression(String valueExpression) {
        this.valueExpression = valueExpression;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof BiosSettings) {
            BiosSettings that = (BiosSettings) o;

            return new EqualsBuilder()
                    .append(category, that.category)
                    .append(defaultValue, that.defaultValue)
                    .append(dependencyList, that.dependencyList)
                    .append(helpText, that.helpText)
                    .append(id, that.id)
                    .append(lowerBound, that.lowerBound)
                    .append(name, that.name)
                    .append(options, that.options)
                    .append(scalarIncrement, that.scalarIncrement)
                    .append(stringMaxLength, that.stringMaxLength)
                    .append(stringMinLength, that.stringMinLength)
                    .append(type, that.type)
                    .append(upperBound, that.upperBound)
                    .append(valueExpression, that.valueExpression)
                    .append(warningText, that.warningText)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(category)
                .append(defaultValue)
                .append(dependencyList)
                .append(helpText)
                .append(id)
                .append(lowerBound)
                .append(name)
                .append(options)
                .append(scalarIncrement)
                .append(stringMaxLength)
                .append(stringMinLength)
                .append(type)
                .append(upperBound)
                .append(valueExpression)
                .append(warningText)
                .toHashCode();
    }
}
