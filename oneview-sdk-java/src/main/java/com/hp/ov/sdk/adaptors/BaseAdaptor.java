/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;

public abstract class BaseAdaptor<T, S>
{

    public abstract T buildDto(S source);
}
