/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public interface ErrorEnum
{
    String getErrorCode();

    String getMessageKey();

    String getResolutionKey();

    //String getResoruceBundle();
}
