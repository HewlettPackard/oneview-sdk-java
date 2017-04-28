/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.oneview;

import java.util.Map;

public class Background {

    protected static OneView oneView;
    protected static Credential credential;

    protected static String resourceName;
    protected static String resourceID;
    protected static String resourceStr;
    protected static String status;
    
    protected static String systemUri;
    protected static String poolUri;
    protected static String enclosureUri;

    protected static Map<String, String> inputProperties;
    protected Map<String, String> resourceProperties;

    protected int count;
}
