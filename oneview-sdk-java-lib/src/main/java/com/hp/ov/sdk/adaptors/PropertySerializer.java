package com.hp.ov.sdk.adaptors;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hp.ov.sdk.dto.Property;

public class PropertySerializer implements JsonDeserializer<Property> {

    @Override
    public Property deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.serializeNulls().create();
        
        Property property = new Property();
        property = gson.fromJson(json, Property.class);
        
        if (!json.getAsJsonObject().get("value").isJsonNull()) {
            
            JsonObject jsonObject = json.getAsJsonObject();
            String valueType = jsonObject.get("valueType").getAsString();

            switch (valueType) {
                case "Integer":
                    property.setValue(jsonObject.get("value").getAsInt());
                    break;
                case "String":
                    property.setValue(jsonObject.get("value").getAsString());
                    break;
                case "Boolean":
                    property.setValue(jsonObject.get("value").getAsBoolean());
                    break;
                case "Float":
                    property.setValue(jsonObject.get("value").getAsFloat());
                    break;
                case "Double":
                    property.setValue(jsonObject.get("value").getAsDouble());
                    break;
                case "Unknown":
                    property.setValue(jsonObject.get("value").getAsString());
                    break;
            }
        }
        return property;
    }
}
