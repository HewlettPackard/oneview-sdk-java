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

/**
 * Invalid request URI, invalid header, or invalid request parameter sent in
 * request. GET, DELETE- The syntax of a request parameter (filter, query,
 * start, count, sort) is invalid or refers to invalid resource attribute
 * names POST, PUT- Field validation failed: One or more field values sent
 * in an add/update request are not acceptable, because the format or
 * content of the field is invalid.
 */
public class SDKBadRequestException extends SDKException {

    private static final long serialVersionUID = 1L;

    public SDKBadRequestException(SDKErrorKey sdkErrorKey, String errorSource) {
        this(sdkErrorKey, errorSource, null);
    }

    public SDKBadRequestException(SDKErrorKey sdkErrorKey, String errorSource, Throwable cause) {
        super(sdkErrorKey, errorSource, cause);
    }

}
