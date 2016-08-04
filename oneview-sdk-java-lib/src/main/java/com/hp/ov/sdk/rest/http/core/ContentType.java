/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.rest.http.core;

public enum ContentType {

    TEXT_PLAIN("text/plain", "UTF-8"),
    APPLICATION_JSON("application/json", "UTF-8"),
    APPLICATION_JSON_PATCH("application/json-patch+json", "UTF-8"),
    MULTIPART_FORM_DATA("multipart/form-data", "ISO-8859-1");

    private final String mimeType;
    private final String charset;

    ContentType(String mimeType, String charset) {
        this.mimeType = mimeType;
        this.charset = charset;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }
}
