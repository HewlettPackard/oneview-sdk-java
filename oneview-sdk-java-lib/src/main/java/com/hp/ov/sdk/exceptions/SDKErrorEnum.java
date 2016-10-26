/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public enum SDKErrorEnum implements SDKErrorKey {

    invalidArgument("INVALID_ARGUMENT"),
    internalError("INTERNAL_SDK_EXCEPTION"),
    noResponseFromAppliance("NO_RESPONSE"),
    resourceNotFound("RESOURCE_NOT_FOUND"),
    certificateError("CERTIFICATE_ERROR"),
    badRequestError("BAD_REQUEST"),
    forbiddenRequestError("FORBIDDEN_REQUEST"),
    methodNotFound("METHOD_NOT_FOUND"),
    tasksError("TASKS_ERROR"),
    applianceNotReachable("APPLIANCE_NOT_REACHABLE"),
    apiMismatchError("API_VERSION_MISMATCH"),
    unauthorized("UNAUTHORIZED"),
    internalServerError("INTERNAL_SERVER_ERROR"),
    propertiesFileError("PROPERTIES_FILE_ERROR"),
    messageBusConnectionError("MESSAGE_BUS_CONNECTION_ERROR");

    private final String baseKeyName;

    SDKErrorEnum(final String baseKeyName) {
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
