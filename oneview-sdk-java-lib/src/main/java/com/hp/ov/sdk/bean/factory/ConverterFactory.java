/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.bean.factory;

import com.hp.ov.sdk.util.ObjectToJsonConverter;

public class ConverterFactory {

    private static volatile ObjectToJsonConverter INSTANCE = null;

    public static ObjectToJsonConverter getConverter() {
        if (INSTANCE == null) {
            synchronized (ConverterFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ObjectToJsonConverter();
                }
            }
        }
        return INSTANCE;
    }

}
