/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

/*
 * <p>
 * The SDKErrorKey fetches the error detail and recommended action
 * </p>
 */
public interface SDKErrorKey extends ErrorEnum {

    String getDetailsKey();

    String getRecommendedActionsKey();
}
