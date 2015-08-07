/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public enum SDKErrorEnum implements SDKErrorKey {

    fail("FAIL"), invalidArgument("INVALID_ARGUMENT"), invalidUrl("INVALID_URL"), fileNotFound("FILE_NOTFOUND"), internalError(
            "INTERNAL_SDK_EXCEPTION"), noResponseFromAppliance("NO_RESPONSE"), scmbConnectionNotFound("SCMB_CONNECTION_NOT_FOUND"), resourceNotFound(
            "RESOURCE_NOT_FOUND"), certificateError("CERTIFICATE_ERROR"), rabbitMqCertificateError("RABBIT_MQ_CERTIFICATE_ERROR"), badRequestError(
            "BAD_REQUEST"), forbiddenRequestError("FORBIDDEN_REQUEST"), methodNotFound("METHOD_NOT_FOUND"), tasksError(
            "TASKS_ERROR"), applicanceNotReachable("APPLIANCE_NOT_REACHABLE"), apiMismatchError("API_VERSION_MISMATCH"), invalidOperationException(
            "INVALID_OPERATION_EXCEPTION");

    private final String baseKeyName;

    private SDKErrorEnum(final String baseKeyName) {
        this.baseKeyName = baseKeyName;
    }

    @Override
    public String getErrorCode() {
        return "SDK_" + baseKeyName;
    }

    @Override
    public String getMessageKey() {
        return baseKeyName + "_MESSAGE";
    }

    @Override
    public String getResolutionKey() {
        return baseKeyName + "_RESOLUTION";
    }

    @Override
    public String getDetailsKey() {
        return baseKeyName + "_DETAILS";
    }

    @Override
    public String getRecommendedActionsKey() {
        return baseKeyName + "_RECOMMENDED_ACTIONS";
    }

}
