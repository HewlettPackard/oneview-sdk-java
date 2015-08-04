/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public interface ErrorEnum {
    String getErrorCode();

    String getMessageKey();

    String getResolutionKey();

    // String getResoruceBundle();
}
