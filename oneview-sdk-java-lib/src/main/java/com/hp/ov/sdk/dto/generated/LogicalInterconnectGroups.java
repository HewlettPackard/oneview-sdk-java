/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "stackingMode", "stackingHealth", "interconnectMapTemplate", "uplinkSets", "snmpConfiguration",
        "telemetryConfiguration", "ethernetSettings", "description", "status", "name", "state", "eTag", "created", "modified",
        "category", "uri", "type" })
public class LogicalInterconnectGroups implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stackingMode")
    private LogicalInterconnectGroups.StackingMode stackingMode;
    @JsonProperty("stackingHealth")
    private LogicalInterconnectGroups.StackingHealth stackingHealth;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("interconnectMapTemplate")
    private InterconnectMapTemplate interconnectMapTemplate;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uplinkSets")
    private List<UplinkSet> uplinkSets = new ArrayList<UplinkSet>();
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

    /**
     * 
     * (Required)
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     * @param type
     *            The type
     */
    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * 
     * (Required)
     * 
     * @return The stackingMode
     */
    @JsonProperty("stackingMode")
    public LogicalInterconnectGroups.StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     * 
     * (Required)
     * 
     * @param stackingMode
     *            The stackingMode
     */
    @JsonProperty("stackingMode")
    public void setStackingMode(final LogicalInterconnectGroups.StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

    /**
     * 
     * @return The stackingHealth
     */
    @JsonProperty("stackingHealth")
    public LogicalInterconnectGroups.StackingHealth getStackingHealth() {
        return stackingHealth;
    }

    /**
     * 
     * @param stackingHealth
     *            The stackingHealth
     */
    @JsonProperty("stackingHealth")
    public void setStackingHealth(final LogicalInterconnectGroups.StackingHealth stackingHealth) {
        this.stackingHealth = stackingHealth;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectMapTemplate
     */
    @JsonProperty("interconnectMapTemplate")
    public InterconnectMapTemplate getInterconnectMapTemplate() {
        return interconnectMapTemplate;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnectMapTemplate
     *            The interconnectMapTemplate
     */
    @JsonProperty("interconnectMapTemplate")
    public void setInterconnectMapTemplate(final InterconnectMapTemplate interconnectMapTemplate) {
        this.interconnectMapTemplate = interconnectMapTemplate;
    }

    /**
     * 
     * (Required)
     * 
     * @return The uplinkSets
     */
    @JsonProperty("uplinkSets")
    public List<UplinkSet> getUplinkSets() {
        return uplinkSets;
    }

    /**
     * 
     * (Required)
     * 
     * @param uplinkSets
     *            The uplinkSets
     */
    @JsonProperty("uplinkSets")
    public void setUplinkSets(final List<UplinkSet> uplinkSets) {
        this.uplinkSets = uplinkSets;
    }

    /**
     * 
     * @return The snmpConfiguration
     */
    @JsonProperty("snmpConfiguration")
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     * 
     * @param snmpConfiguration
     *            The snmpConfiguration
     */
    @JsonProperty("snmpConfiguration")
    public void setSnmpConfiguration(final SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     * 
     * @return The telemetryConfiguration
     */
    @JsonProperty("telemetryConfiguration")
    public TelemetryConfiguration getTelemetryConfiguration() {
        return telemetryConfiguration;
    }

    /**
     * 
     * @param telemetryConfiguration
     *            The telemetryConfiguration
     */
    @JsonProperty("telemetryConfiguration")
    public void setTelemetryConfiguration(final TelemetryConfiguration telemetryConfiguration) {
        this.telemetryConfiguration = telemetryConfiguration;
    }

    /**
     * 
     * @return The ethernetSettings
     */
    @JsonProperty("ethernetSettings")
    public EthernetSettings getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     * 
     * @param ethernetSettings
     *            The ethernetSettings
     */
    @JsonProperty("ethernetSettings")
    public void setEthernetSettings(final EthernetSettings ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *            The description
     */
    @JsonProperty("description")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    @JsonProperty("name")
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            The state
     */
    @JsonProperty("state")
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag() {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *            The eTag
     */
    @JsonProperty("eTag")
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *            The created
     */
    @JsonProperty("created")
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    /**
     * 
     * @param modified
     *            The modified
     */
    @JsonProperty("modified")
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *            The category
     */
    @JsonProperty("category")
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *            The uri
     */
    @JsonProperty("uri")
    public void setUri(final String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(stackingMode).append(stackingHealth).append(interconnectMapTemplate)
                .append(uplinkSets).append(snmpConfiguration).append(telemetryConfiguration).append(ethernetSettings)
                .append(description).append(status).append(name).append(state).append(eTag).append(created).append(modified)
                .append(category).append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalInterconnectGroups) == false) {
            return false;
        }
        final LogicalInterconnectGroups rhs = ((LogicalInterconnectGroups) other);
        return new EqualsBuilder().append(type, rhs.type).append(stackingMode, rhs.stackingMode)
                .append(stackingHealth, rhs.stackingHealth).append(interconnectMapTemplate, rhs.interconnectMapTemplate)
                .append(uplinkSets, rhs.uplinkSets).append(snmpConfiguration, rhs.snmpConfiguration)
                .append(telemetryConfiguration, rhs.telemetryConfiguration).append(ethernetSettings, rhs.ethernetSettings)
                .append(description, rhs.description).append(status, rhs.status).append(name, rhs.name).append(state, rhs.state)
                .append(eTag, rhs.eTag).append(created, rhs.created).append(modified, rhs.modified).append(category, rhs.category)
                .append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum StackingHealth {

        Unknown("Unknown"), Connected("Connected"), BiConnected("BiConnected"), Disconnected("Disconnected");
        private final String value;
        private static Map<String, LogicalInterconnectGroups.StackingHealth> constants = new HashMap<String, LogicalInterconnectGroups.StackingHealth>();

        static {
            for (final LogicalInterconnectGroups.StackingHealth c : values()) {
                constants.put(c.value, c);
            }
        }

        private StackingHealth(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static LogicalInterconnectGroups.StackingHealth fromValue(final String value) {
            final LogicalInterconnectGroups.StackingHealth constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum StackingMode {

        None("None"), Enclosure("Enclosure"), SwitchPairs("SwitchPairs"), MultiEnclosure("MultiEnclosure");
        private final String value;
        private static Map<String, LogicalInterconnectGroups.StackingMode> constants = new HashMap<String, LogicalInterconnectGroups.StackingMode>();

        static {
            for (final LogicalInterconnectGroups.StackingMode c : values()) {
                constants.put(c.value, c);
            }
        }

        private StackingMode(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static LogicalInterconnectGroups.StackingMode fromValue(final String value) {
            final LogicalInterconnectGroups.StackingMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
