/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.adaptors;


import com.hp.ov.sdk.dto.FcIssueResponse;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class FcIssueResponseAdaptor extends BaseAdaptor<FcIssueResponse, Object> {

    private static class FcIssueResponseAdaptorHolder {
        private static final FcIssueResponseAdaptor INSTANCE = new FcIssueResponseAdaptor();
    }

    public static FcIssueResponseAdaptor getInstance() {
        return FcIssueResponseAdaptorHolder.INSTANCE;
    }

    protected FcIssueResponseAdaptor() { }

    @Override
    public FcIssueResponse buildDto(Object source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();
        // TODO - exceptions
        // convert json Object to DTO, replace quotes and back slash in the file
        final FcIssueResponse fcIssueResponse = converter.convertJsonToObject(
                StringUtil.replaceQuotesAndBackSlash(converter.convertObjectToJsonString(source)),
                FcIssueResponse.class);

        return fcIssueResponse;
    }

}
