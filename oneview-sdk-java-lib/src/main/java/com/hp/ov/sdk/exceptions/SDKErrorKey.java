/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public interface SDKErrorKey extends ErrorEnum {

    String getDetailsKey();

    String getRecommendedActionsKey();
}
