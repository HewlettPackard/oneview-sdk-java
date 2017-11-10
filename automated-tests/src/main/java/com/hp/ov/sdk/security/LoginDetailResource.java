/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.security;

import java.util.Map;

import com.hp.ov.sdk.dto.security.login.LoginDetail;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.security.LoginDetailClient;

public class LoginDetailResource extends BasicResource implements Resource {

    private static LoginDetailResource instance;
    private LoginDetailClient client;
   

    public LoginDetailResource(){
        client = oneViewClient.loginDetail();        
    }

    public static LoginDetailResource getInstance() {
        if (instance == null) {
            instance = new LoginDetailResource();
        }
        return instance;
    }
  
    public String getLoginDetail() {
        try {
            LoginDetail loginDetail = client.getLoginDetails();
            return loginDetail != null ? loginDetail.toString() : "not-found";
        }
        catch (Exception e) {
            return "not-found";
        }
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return 0;
    }
   
}
