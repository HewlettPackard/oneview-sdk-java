/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "valueFormat", "valueType", "propertyName", "value"
})
public class ConnectionProperty implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("valueFormat")
    private ConnectionProperty.ValueFormat valueFormat;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("valueType")
    private ConnectionProperty.ValueType valueType;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("propertyName")
    private String propertyName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("value")
    private String value;

    /**
     * 
     * (Required)
     * 
     * @return The valueFormat
     */
    @JsonProperty("valueFormat")
    public ConnectionProperty.ValueFormat getValueFormat()
    {
        return valueFormat;
    }

    /**
     * 
     * (Required)
     * 
     * @param valueFormat
     *        The valueFormat
     */
    @JsonProperty("valueFormat")
    public void setValueFormat(final ConnectionProperty.ValueFormat valueFormat)
    {
        this.valueFormat = valueFormat;
    }

    /**
     * 
     * (Required)
     * 
     * @return The valueType
     */
    @JsonProperty("valueType")
    public ConnectionProperty.ValueType getValueType()
    {
        return valueType;
    }

    /**
     * 
     * (Required)
     * 
     * @param valueType
     *        The valueType
     */
    @JsonProperty("valueType")
    public void setValueType(final ConnectionProperty.ValueType valueType)
    {
        this.valueType = valueType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The propertyName
     */
    @JsonProperty("propertyName")
    public String getPropertyName()
    {
        return propertyName;
    }

    /**
     * 
     * (Required)
     * 
     * @param propertyName
     *        The propertyName
     */
    @JsonProperty("propertyName")
    public void setPropertyName(final String propertyName)
    {
        this.propertyName = propertyName;
    }

    /**
     * 
     * (Required)
     * 
     * @return The value
     */
    @JsonProperty("value")
    public String getValue()
    {
        return value;
    }

    /**
     * 
     * (Required)
     * 
     * @param value
     *        The value
     */
    @JsonProperty("value")
    public void setValue(final String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(valueFormat).append(valueType)
                .append(propertyName).append(value).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof ConnectionProperty) == false)
        {
            return false;
        }
        final ConnectionProperty rhs = ((ConnectionProperty) other);
        return new EqualsBuilder().append(valueFormat, rhs.valueFormat)
                .append(valueType, rhs.valueType)
                .append(propertyName, rhs.propertyName)
                .append(value, rhs.value).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum ValueFormat
    {

        SecuritySensitive ("SecuritySensitive"),
        Unknown ("Unknown");
        private final String value;
        private static Map<String, ConnectionProperty.ValueFormat> constants = new HashMap<String, ConnectionProperty.ValueFormat>();

        static
        {
            for (final ConnectionProperty.ValueFormat c : values())
            {
                constants.put(c.value, c);
            }
        }

        private ValueFormat(final String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static ConnectionProperty.ValueFormat fromValue(final String value)
        {
            final ConnectionProperty.ValueFormat constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum ValueType
    {

        Integer ("Integer"),
        String ("String"),
        Float ("Float"),
        Double ("Double"),
        Boolean (
                "Boolean"),
        Unknown ("Unknown");
        private final String value;
        private static Map<String, ConnectionProperty.ValueType> constants = new HashMap<String, ConnectionProperty.ValueType>();

        static
        {
            for (final ConnectionProperty.ValueType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private ValueType(final String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static ConnectionProperty.ValueType fromValue(final String value)
        {
            final ConnectionProperty.ValueType constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

}
