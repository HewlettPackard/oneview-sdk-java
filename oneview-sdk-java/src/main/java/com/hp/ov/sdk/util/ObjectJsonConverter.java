/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.util;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKFileNotFoundException;
import com.hp.ov.sdk.exceptions.SDKInternalException;

@Component
public class ObjectJsonConverter
{

    private static final Logger logger = LoggerFactory.getLogger(ObjectJsonConverter.class);

    private Gson gson;

    @Autowired
    private StringUtil util;

    public void convertObjectToJson(final Object inObj, final String outputFilename)
    {
        gson = new Gson();
        final String json = gson.toJson(inObj);

        try
        {
            final FileWriter writer = new FileWriter(outputFilename);
            writer.write(json);
            writer.close();
        }
        catch (final IOException e)
        {
            //TODO - exception
            throw new SDKFileNotFoundException(
                    SDKErrorEnum.fileNotFound,
                    null,
                    null,
                    null,
                    SdkConstants.OBJECT_TO_JOSON_CONVERSION,
                    null);
        }

        logger.info("ObjectJsonConverter : convertObjectToJson: json =" + json);
    }

    public String convertObjectToJsonString(final Object inObj)
    {
        gson = new Gson();
        return (gson.toJson(inObj).toString());
    }

    public <T> T convertJsonToObject(final String inStr, final Class<T> target)
    {
        gson = new Gson();
        T retObj = null;
        try
        {
            retObj = target.newInstance();
            retObj = gson.fromJson(inStr, target);
        }
        catch (final InstantiationException e)
        {
            throw new SDKInternalException(
                    SDKErrorEnum.internalError,
                    null,
                    null,
                    null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION,
                    null);
        }
        catch (final IllegalAccessException e)
        {
            throw new SDKInternalException(
                    SDKErrorEnum.internalError,
                    null,
                    null,
                    null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION,
                    null);
        }
        logger.info("ObjectJsonConverter : convertObjectToJson: json to object =" + retObj.toString());
        return retObj;
    }

    public List<?> convertJsonToListObject(final String inStr, final Type mtype)
    {
        gson = new Gson();
        List<?> retObj = null;
        try
        {
            retObj = gson.fromJson(inStr, mtype);

        }
        catch (final Exception e)
        {
            throw new SDKInternalException(
                    SDKErrorEnum.internalError,
                    null,
                    null,
                    null,
                    SdkConstants.JSON_TO_OBJECT_CONVERSION,
                    null);
        }
        logger.info("ObjectJsonConverter : convertObjectToJson: json to object =" + retObj.toString());
        return retObj;
    }

    public StringUtil getUtil()
    {
        return util;
    }

    public void setUtil(final StringUtil util)
    {
        this.util = util;
    }

}
