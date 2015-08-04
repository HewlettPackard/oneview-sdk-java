/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class MetricSampleList {

    private String[] valueArray;
    private String name;

    public String[] getValueArray() {
        return valueArray;
    }

    public void setValueArray(final String[] valueArray) {
        this.valueArray = valueArray;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
