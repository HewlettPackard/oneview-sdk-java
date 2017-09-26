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

public class SDKUnsupportedMediaTypeException extends SDKException {

    /**
     * <p>
     * The media type of the request body (as specified in the Content-Type
     * header) is not supported by the server.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKUnsupportedMediaTypeException(SDKErrorKey sdkErrorKey,
                                            Object[] messageParameters,
                                            Object[] detailsParameters,
                                            Object[] recommendedActionsParameters,
                                            String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters,
              recommendedActionsParameters, errorSource, cause);

    }

}