/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKInternalServerErrorException extends SDKException

{

    /**
     * <p>
     * Currently handles one-view response codes/exceptions such as conflict due
     * to state, precondition failed, unsupported media type, internal server
     * error, service unavailable. conflict due to state - The request could not
     * be completed due to a conflict with the current state of the resource.
     * precondition failed - invalid API version is sent in the X-API-Version
     * header or User attempted to update a resource but provided an
     * unacceptable ETag(multiple user attempt to update same resource)
     * unsupported media type - The media type of the request body is not
     * supported by the server. internal server error - An unexpected error has
     * occurred that does not fit into a standard error category service
     * unavailable - The server is currently unable to handle the request due to
     * temporary overloading or maintenance of the server.
     * </p>
     * 
     */
    private static final long serialVersionUID = 1L;

    public SDKInternalServerErrorException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
