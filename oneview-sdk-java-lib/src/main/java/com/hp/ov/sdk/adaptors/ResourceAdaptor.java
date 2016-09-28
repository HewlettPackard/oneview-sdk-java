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

import java.lang.reflect.Type;
import java.util.List;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.StringUtil;

public class ResourceAdaptor {

    public <T> List<T> buildListOfResource(String source, Class<T> resourceType) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceReplaced = this.applyReplacementsOnSource(source);

        return converter.jsonToList(sourceReplaced, resourceType);
    }

    public <T> ResourceCollection<T> buildResourceCollection(String source, Class<T> resourceType) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceReplaced = this.applyReplacementsOnSource(source);

        return converter.jsonToResourceCollection(sourceReplaced, resourceType);
    }

    public <T> T buildResource(String source, Class<T> resourceType) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceReplaced = this.applyReplacementsOnSource(source);

        return converter.jsonToResource(sourceReplaced, resourceType);
    }

    public Object buildResource(String source, Type resourceType) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceReplaced = this.applyReplacementsOnSource(source);

        return converter.jsonToResource(sourceReplaced, resourceType);
    }

    private String applyReplacementsOnSource(String source) {
        ObjectToJsonConverter converter = ObjectToJsonConverter.getInstance();

        String sourceAsJson = converter.processJsonFromOneView(source);
        String sourceReplacedQuotesAndBackSlash = StringUtil.replaceQuotesAndBackSlash(sourceAsJson);
        String sourceReplacedQuotesBackSlashWithQuote = StringUtil.replaceQuotesBackSlashWithQuote(
                sourceReplacedQuotesAndBackSlash);

        return sourceReplacedQuotesBackSlashWithQuote;
    }

}
