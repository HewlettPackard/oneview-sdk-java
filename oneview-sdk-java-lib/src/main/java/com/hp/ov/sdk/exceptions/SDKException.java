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

public class SDKException extends SdkRuntimeException {

    /**
     * <p>
     * The generic exception class from which all one-view sdk exception extend
     * from.
     * </p>
     */
    private static final long serialVersionUID = 1L;
    private SDKErrorKey errorKey;

    public SDKException(final SDKErrorKey sdkErrorKey,
                        final Object[] messageParameters,
                        final Object[] detailsParameters,
                        final Object[] recommendedActionsParameters,
                        final String errorSource, final Throwable cause) {
        super(sdkErrorKey.getErrorCode(), sdkErrorKey.getMessageKey(),
              messageParameters, sdkErrorKey.getDetailsKey(),
              detailsParameters, sdkErrorKey.getRecommendedActionsKey(),
              recommendedActionsParameters, errorSource, cause);
        setErrorKey(sdkErrorKey);
    }

    public SDKErrorKey getErrorKey() {
        return errorKey;
    }

    protected void setErrorKey(final SDKErrorKey key) {
       // We could simulate final by only allowing it to be set if null;
       this.errorKey = key;
    }
}
