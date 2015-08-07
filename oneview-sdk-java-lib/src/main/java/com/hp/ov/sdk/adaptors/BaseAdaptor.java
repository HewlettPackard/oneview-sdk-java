/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

public abstract class BaseAdaptor<T, S> {

    public abstract T buildDto(S source);
}
