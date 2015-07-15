/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.hp.ov.sdk.dto.InterconnectMap;
import com.hp.ov.sdk.dto.PortMonitor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "stackingHealth", "snmpConfiguration",
        "telemetryConfiguration", "ethernetSettings", "description", "status",
        "name", "state", "eTag", "created", "modified", "category", "uri",
        "type", "domainUri", "consistencyStatus", "enclosureUris",
        "interconnectMap", "interconnects", "logicalInterconnectGroupUri",
        "portMonitor"
})
public class LogicalInterconnects implements Serializable
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
    @JsonProperty("stackingHealth")
    private LogicalInterconnects.StackingHealth stackingHealth;
    @JsonProperty("snmpConfiguration")
    private SnmpConfiguration snmpConfiguration;
    @JsonProperty("telemetryConfiguration")
    private TelemetryConfiguration telemetryConfiguration;
    @JsonProperty("ethernetSettings")
    private EthernetSettings ethernetSettings;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("category")
    private String category;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("type")
    private String type;
    @JsonProperty("consistencyStatus")
    private Compliance consistencyStatus;
    @JsonProperty("domainUri")
    private String domainUri;
    @JsonProperty("enclosureUris")
    private List<String> enclosureUris = new ArrayList<String>();
    @JsonProperty("interconnectMap")
    private InterconnectMap interconnectMap;
    @JsonProperty("interconnects")
    private List<String> interconnects = new ArrayList<String>();
    @JsonProperty("logicalInterconnectGroupUri")
    private String logicalInterconnectGroupUri;
    @JsonProperty("portMonitor")
    private PortMonitor portMonitor;

    /**
     * 
     * @return The stackingHealth
     */
    @JsonProperty("stackingHealth")
    public LogicalInterconnects.StackingHealth getStackingHealth()
    {
        return stackingHealth;
    }

    /**
     * 
     * @param stackingHealth
     *        The stackingHealth
     */
    @JsonProperty("stackingHealth")
    public void setStackingHealth(
            final LogicalInterconnects.StackingHealth stackingHealth)
    {
        this.stackingHealth = stackingHealth;
    }

    /**
     * 
     * @return The snmpConfiguration
     */
    @JsonProperty("snmpConfiguration")
    public SnmpConfiguration getSnmpConfiguration()
    {
        return snmpConfiguration;
    }

    /**
     * 
     * @param snmpConfiguration
     *        The snmpConfiguration
     */
    @JsonProperty("snmpConfiguration")
    public void setSnmpConfiguration(final SnmpConfiguration snmpConfiguration)
    {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     * 
     * @return The telemetryConfiguration
     */
    @JsonProperty("telemetryConfiguration")
    public TelemetryConfiguration getTelemetryConfiguration()
    {
        return telemetryConfiguration;
    }

    /**
     * 
     * @param telemetryConfiguration
     *        The telemetryConfiguration
     */
    @JsonProperty("telemetryConfiguration")
    public void setTelemetryConfiguration(
            final TelemetryConfiguration telemetryConfiguration)
    {
        this.telemetryConfiguration = telemetryConfiguration;
    }

    /**
     * 
     * @return The ethernetSettings
     */
    @JsonProperty("ethernetSettings")
    public EthernetSettings getEthernetSettings()
    {
        return ethernetSettings;
    }

    /**
     * 
     * @param ethernetSettings
     *        The ethernetSettings
     */
    @JsonProperty("ethernetSettings")
    public void setEthernetSettings(final EthernetSettings ethernetSettings)
    {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     * @param description
     *        The description
     */
    @JsonProperty("description")
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    /**
     * 
     * @param status
     *        The status
     */
    @JsonProperty("status")
    public void setStatus(final String status)
    {
        this.status = status;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(final String state)
    {
        this.state = state;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag()
    {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *        The eTag
     */
    @JsonProperty("eTag")
    public void setETag(final String eTag)
    {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated()
    {
        return created;
    }

    /**
     * 
     * @param created
     *        The created
     */
    @JsonProperty("created")
    public void setCreated(final String created)
    {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified()
    {
        return modified;
    }

    /**
     * 
     * @param modified
     *        The modified
     */
    @JsonProperty("modified")
    public void setModified(final String modified)
    {
        this.modified = modified;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory()
    {
        return category;
    }

    /**
     * 
     * @param category
     *        The category
     */
    @JsonProperty("category")
    public void setCategory(final String category)
    {
        this.category = category;
    }

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri()
    {
        return uri;
    }

    /**
     * 
     * @param uri
     *        The uri
     */
    @JsonProperty("uri")
    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     * @param type
     *        The type
     */
    @JsonProperty("type")
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * 
     * (Required)
     * 
     * @return The domainUri
     */
    @JsonProperty("domain")
    public String getDomainUri()
    {
        return domainUri;
    }

    /**
     * 
     * (Required)
     * 
     * @param domainUri
     *        The domainUri
     */
    @JsonProperty("domain")
    public void setDomainUri(final String domainUri)
    {
        this.domainUri = domainUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The enclosureUris
     */
    @JsonProperty("enclosureUris")
    public List<String> getEnclosureUris()
    {
        return enclosureUris;
    }

    /**
     * 
     * (Required)
     * 
     * @param enclosureUris
     *        The enclosureUris
     */
    @JsonProperty("enclosureUris")
    public void setEnclosureUris(final List<String> enclosureUris)
    {
        this.enclosureUris = enclosureUris;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectMap
     */
    @JsonProperty("interconnectMap")
    public InterconnectMap getInterconnectMap()
    {
        return interconnectMap;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnectMap
     *        The interconnectMap
     */
    @JsonProperty("interconnectMap")
    public void setInterconnectMap(final InterconnectMap interconnectMap)
    {
        this.interconnectMap = interconnectMap;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnects
     */
    @JsonProperty("interconnects")
    public List<String> getInterconnects()
    {
        return interconnects;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnects
     *        The interconnects
     */
    @JsonProperty("interconnects")
    public void setInterconnects(final List<String> interconnects)
    {
        this.interconnects = interconnects;
    }

    /**
     * 
     * (Required)
     * 
     * @return The logicalInterconnectGroupUri
     */
    @JsonProperty("logicalInterconnectGroupUri")
    public String getLogicalInterconnectGroupUri()
    {
        return logicalInterconnectGroupUri;
    }

    /**
     * 
     * (Required)
     * 
     * @param logicalInterconnectGroupUri
     *        The logicalInterconnectGroupUri
     */
    @JsonProperty("logicalInterconnectGroupUri")
    public void setLogicalInterconnectGroupUri(
            final String logicalInterconnectGroupUri)
    {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The portMonitor
     */
    @JsonProperty("portMonitor")
    public PortMonitor getPortMonitor()
    {
        return portMonitor;
    }

    /**
     * 
     * (Required)
     * 
     * @param portMonitor
     *        The portMonitor
     */
    @JsonProperty("portMonitor")
    public void setPortMonitor(final PortMonitor portMonitor)
    {
        this.portMonitor = portMonitor;
    }

    /**
     * 
     * (Required)
     * 
     * @return The consistencyStatus
     */
    @JsonProperty("consistencyStatus")
    public Compliance getConsistencyStatus()
    {
        return consistencyStatus;
    }

    /**
     * 
     * (Required)
     * 
     * @param consistencyStatus
     *        The consistencyStatus
     */
    @JsonProperty("consistencyStatus")
    public void setConsistencyStatus(final Compliance consistencyStatus)
    {
        this.consistencyStatus = consistencyStatus;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(consistencyStatus)
                .append(domainUri).append(enclosureUris)
                .append(interconnectMap).append(interconnects)
                .append(logicalInterconnectGroupUri).append(portMonitor)
                .append(type).append(stackingHealth).append(snmpConfiguration)
                .append(telemetryConfiguration).append(ethernetSettings)
                .append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category)
                .append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof LogicalInterconnects) == false)
        {
            return false;
        }
        final LogicalInterconnects rhs = ((LogicalInterconnects) other);
        return new EqualsBuilder()
                .append(consistencyStatus, rhs.consistencyStatus)
                .append(domainUri, rhs.domainUri)
                .append(enclosureUris, rhs.enclosureUris)
                .append(interconnectMap, rhs.interconnectMap)
                .append(interconnects, rhs.interconnects)
                .append(logicalInterconnectGroupUri,
                        rhs.logicalInterconnectGroupUri)
                .append(portMonitor, rhs.portMonitor).append(type, rhs.type)
                .append(stackingHealth, rhs.stackingHealth)
                .append(snmpConfiguration, rhs.snmpConfiguration)
                .append(telemetryConfiguration, rhs.telemetryConfiguration)
                .append(ethernetSettings, rhs.ethernetSettings)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum StackingHealth
    {

        Unknown ("Unknown"),
        Connected ("Connected"),
        BiConnected ("BiConnected"),
        Disconnected (
                "Disconnected");
        private final String value;
        private static Map<String, LogicalInterconnects.StackingHealth> constants = new HashMap<String, LogicalInterconnects.StackingHealth>();

        static
        {
            for (final LogicalInterconnects.StackingHealth c : values())
            {
                constants.put(c.value, c);
            }
        }

        private StackingHealth(final String value)
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
        public static LogicalInterconnects.StackingHealth fromValue(final String value)
        {
            final LogicalInterconnects.StackingHealth constant = constants.get(value);
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
    public static enum Compliance
    {

        CONSISTENT ("CONSISTENT"),
        NOT_CONSISTENT ("NOT_CONSISTENT");
        private final String value;
        private static Map<String, LogicalInterconnects.Compliance> constants = new HashMap<String, LogicalInterconnects.Compliance>();

        static
        {
            for (final LogicalInterconnects.Compliance c : values())
            {
                constants.put(c.value, c);
            }
        }

        private Compliance(final String value)
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
        public static LogicalInterconnects.Compliance fromValue(final String value)
        {
            final LogicalInterconnects.Compliance constant = constants.get(value);
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
    public static enum StackingMode
    {

        None ("None"),
        Enclosure ("Enclosure"),
        SwitchPairs ("SwitchPairs"),
        MultiEnclosure (
                "MultiEnclosure");
        private final String value;
        private static Map<String, LogicalInterconnects.StackingMode> constants = new HashMap<String, LogicalInterconnects.StackingMode>();

        static
        {
            for (final LogicalInterconnects.StackingMode c : values())
            {
                constants.put(c.value, c);
            }
        }

        private StackingMode(final String value)
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
        public static LogicalInterconnects.StackingMode fromValue(final String value)
        {
            final LogicalInterconnects.StackingMode constant = constants.get(value);
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
