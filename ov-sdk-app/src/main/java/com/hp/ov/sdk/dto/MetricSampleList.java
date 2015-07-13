/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class MetricSampleList
{

    private String[] valueArray;
    private String name;

    public String[] getValueArray()
    {
        return valueArray;
    }

    public void setValueArray(String[] valueArray)
    {
        this.valueArray = valueArray;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
