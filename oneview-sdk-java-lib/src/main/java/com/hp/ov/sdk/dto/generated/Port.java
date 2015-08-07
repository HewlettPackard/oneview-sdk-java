/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
@JsonPropertyOrder({ "portName", "portStatus", "portHealthStatus", "portStatusReason", "enabled", "interconnectName",
        "fcPortProperties", "bayNumber", "portType", "lagId", "associatedUplinkSetUri", "portId", "portMonitorConfigInfo",
        "capability", "pairedPortName", "subports", "connectorType", "neighbor", "configPortTypes", "portTypeExtended",
        "operationalSpeed", "description", "status", "name", "state", "eTag", "created", "modified", "category", "uri" })
public class Port implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("portName")
    private String portName;
    @JsonProperty("portStatus")
    private Port.PortStatus portStatus;
    @JsonProperty("portHealthStatus")
    private Port.PortHealthStatus portHealthStatus;
    @JsonProperty("portStatusReason")
    private Port.PortStatusReason portStatusReason;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("interconnectName")
    private String interconnectName;
    @JsonProperty("fcPortProperties")
    private FcPortProperties fcPortProperties;
    @JsonProperty("bayNumber")
    private Integer bayNumber;
    @JsonProperty("portType")
    private Port.PortType portType;
    @JsonProperty("lagId")
    private Integer lagId;
    @JsonProperty("associatedUplinkSetUri")
    private String associatedUplinkSetUri;
    @JsonProperty("portId")
    private String portId;
    @JsonProperty("portMonitorConfigInfo")
    private Port.PortMonitorConfigInfo portMonitorConfigInfo;
    @JsonProperty("capability")
    private List<String> capability = new ArrayList<String>();
    @JsonProperty("pairedPortName")
    private String pairedPortName;
    @JsonProperty("subports")
    private List<Subport> subports = new ArrayList<Subport>();
    @JsonProperty("connectorType")
    private String connectorType;
    @JsonProperty("neighbor")
    private Neighbor neighbor;
    @JsonProperty("configPortTypes")
    private List<String> configPortTypes = new ArrayList<String>();
    @JsonProperty("portTypeExtended")
    private Port.PortTypeExtended portTypeExtended;
    @JsonProperty("operationalSpeed")
    private String operationalSpeed;
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

    /**
     * 
     * @return The portName
     */
    @JsonProperty("portName")
    public String getPortName() {
        return portName;
    }

    /**
     * 
     * @param portName
     *            The portName
     */
    @JsonProperty("portName")
    public void setPortName(final String portName) {
        this.portName = portName;
    }

    /**
     * 
     * @return The portStatus
     */
    @JsonProperty("portStatus")
    public Port.PortStatus getPortStatus() {
        return portStatus;
    }

    /**
     * 
     * @param portStatus
     *            The portStatus
     */
    @JsonProperty("portStatus")
    public void setPortStatus(final Port.PortStatus portStatus) {
        this.portStatus = portStatus;
    }

    /**
     * 
     * @return The portHealthStatus
     */
    @JsonProperty("portHealthStatus")
    public Port.PortHealthStatus getPortHealthStatus() {
        return portHealthStatus;
    }

    /**
     * 
     * @param portHealthStatus
     *            The portHealthStatus
     */
    @JsonProperty("portHealthStatus")
    public void setPortHealthStatus(final Port.PortHealthStatus portHealthStatus) {
        this.portHealthStatus = portHealthStatus;
    }

    /**
     * 
     * @return The portStatusReason
     */
    @JsonProperty("portStatusReason")
    public Port.PortStatusReason getPortStatusReason() {
        return portStatusReason;
    }

    /**
     * 
     * @param portStatusReason
     *            The portStatusReason
     */
    @JsonProperty("portStatusReason")
    public void setPortStatusReason(final Port.PortStatusReason portStatusReason) {
        this.portStatusReason = portStatusReason;
    }

    /**
     * 
     * @return The enabled
     */
    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 
     * @param enabled
     *            The enabled
     */
    @JsonProperty("enabled")
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 
     * @return The interconnectName
     */
    @JsonProperty("interconnectName")
    public String getInterconnectName() {
        return interconnectName;
    }

    /**
     * 
     * @param interconnectName
     *            The interconnectName
     */
    @JsonProperty("interconnectName")
    public void setInterconnectName(final String interconnectName) {
        this.interconnectName = interconnectName;
    }

    /**
     * 
     * @return The fcPortProperties
     */
    @JsonProperty("fcPortProperties")
    public FcPortProperties getFcPortProperties() {
        return fcPortProperties;
    }

    /**
     * 
     * @param fcPortProperties
     *            The fcPortProperties
     */
    @JsonProperty("fcPortProperties")
    public void setFcPortProperties(final FcPortProperties fcPortProperties) {
        this.fcPortProperties = fcPortProperties;
    }

    /**
     * 
     * @return The bayNumber
     */
    @JsonProperty("bayNumber")
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * 
     * @param bayNumber
     *            The bayNumber
     */
    @JsonProperty("bayNumber")
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * 
     * @return The portType
     */
    @JsonProperty("portType")
    public Port.PortType getPortType() {
        return portType;
    }

    /**
     * 
     * @param portType
     *            The portType
     */
    @JsonProperty("portType")
    public void setPortType(final Port.PortType portType) {
        this.portType = portType;
    }

    /**
     * 
     * @return The lagId
     */
    @JsonProperty("lagId")
    public Integer getLagId() {
        return lagId;
    }

    /**
     * 
     * @param lagId
     *            The lagId
     */
    @JsonProperty("lagId")
    public void setLagId(final Integer lagId) {
        this.lagId = lagId;
    }

    /**
     * 
     * @return The associatedUplinkSetUri
     */
    @JsonProperty("associatedUplinkSetUri")
    public String getAssociatedUplinkSetUri() {
        return associatedUplinkSetUri;
    }

    /**
     * 
     * @param associatedUplinkSetUri
     *            The associatedUplinkSetUri
     */
    @JsonProperty("associatedUplinkSetUri")
    public void setAssociatedUplinkSetUri(final String associatedUplinkSetUri) {
        this.associatedUplinkSetUri = associatedUplinkSetUri;
    }

    /**
     * 
     * @return The portId
     */
    @JsonProperty("portId")
    public String getPortId() {
        return portId;
    }

    /**
     * 
     * @param portId
     *            The portId
     */
    @JsonProperty("portId")
    public void setPortId(final String portId) {
        this.portId = portId;
    }

    /**
     * 
     * @return The portMonitorConfigInfo
     */
    @JsonProperty("portMonitorConfigInfo")
    public Port.PortMonitorConfigInfo getPortMonitorConfigInfo() {
        return portMonitorConfigInfo;
    }

    /**
     * 
     * @param portMonitorConfigInfo
     *            The portMonitorConfigInfo
     */
    @JsonProperty("portMonitorConfigInfo")
    public void setPortMonitorConfigInfo(final Port.PortMonitorConfigInfo portMonitorConfigInfo) {
        this.portMonitorConfigInfo = portMonitorConfigInfo;
    }

    /**
     * 
     * @return The capability
     */
    @JsonProperty("capability")
    public List<String> getCapability() {
        return capability;
    }

    /**
     * 
     * @param capability
     *            The capability
     */
    @JsonProperty("capability")
    public void setCapability(final List<String> capability) {
        this.capability = capability;
    }

    /**
     * 
     * @return The pairedPortName
     */
    @JsonProperty("pairedPortName")
    public String getPairedPortName() {
        return pairedPortName;
    }

    /**
     * 
     * @param pairedPortName
     *            The pairedPortName
     */
    @JsonProperty("pairedPortName")
    public void setPairedPortName(final String pairedPortName) {
        this.pairedPortName = pairedPortName;
    }

    /**
     * 
     * @return The subports
     */
    @JsonProperty("subports")
    public List<Subport> getSubports() {
        return subports;
    }

    /**
     * 
     * @param subports
     *            The subports
     */
    @JsonProperty("subports")
    public void setSubports(final List<Subport> subports) {
        this.subports = subports;
    }

    /**
     * 
     * @return The connectorType
     */
    @JsonProperty("connectorType")
    public String getConnectorType() {
        return connectorType;
    }

    /**
     * 
     * @param connectorType
     *            The connectorType
     */
    @JsonProperty("connectorType")
    public void setConnectorType(final String connectorType) {
        this.connectorType = connectorType;
    }

    /**
     * 
     * @return The neighbor
     */
    @JsonProperty("neighbor")
    public Neighbor getNeighbor() {
        return neighbor;
    }

    /**
     * 
     * @param neighbor
     *            The neighbor
     */
    @JsonProperty("neighbor")
    public void setNeighbor(final Neighbor neighbor) {
        this.neighbor = neighbor;
    }

    /**
     * 
     * @return The configPortTypes
     */
    @JsonProperty("configPortTypes")
    public List<String> getConfigPortTypes() {
        return configPortTypes;
    }

    /**
     * 
     * @param configPortTypes
     *            The configPortTypes
     */
    @JsonProperty("configPortTypes")
    public void setConfigPortTypes(final List<String> configPortTypes) {
        this.configPortTypes = configPortTypes;
    }

    /**
     * 
     * @return The portTypeExtended
     */
    @JsonProperty("portTypeExtended")
    public Port.PortTypeExtended getPortTypeExtended() {
        return portTypeExtended;
    }

    /**
     * 
     * @param portTypeExtended
     *            The portTypeExtended
     */
    @JsonProperty("portTypeExtended")
    public void setPortTypeExtended(final Port.PortTypeExtended portTypeExtended) {
        this.portTypeExtended = portTypeExtended;
    }

    /**
     * 
     * @return The operationalSpeed
     */
    @JsonProperty("operationalSpeed")
    public String getOperationalSpeed() {
        return operationalSpeed;
    }

    /**
     * 
     * @param operationalSpeed
     *            The operationalSpeed
     */
    @JsonProperty("operationalSpeed")
    public void setOperationalSpeed(final String operationalSpeed) {
        this.operationalSpeed = operationalSpeed;
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
        return new HashCodeBuilder().append(portName).append(portStatus).append(portHealthStatus).append(portStatusReason)
                .append(enabled).append(interconnectName).append(fcPortProperties).append(bayNumber).append(portType).append(lagId)
                .append(associatedUplinkSetUri).append(portId).append(portMonitorConfigInfo).append(capability)
                .append(pairedPortName).append(subports).append(connectorType).append(neighbor).append(configPortTypes)
                .append(portTypeExtended).append(operationalSpeed).append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category).append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Port) == false) {
            return false;
        }
        final Port rhs = ((Port) other);
        return new EqualsBuilder().append(portName, rhs.portName).append(portStatus, rhs.portStatus)
                .append(portHealthStatus, rhs.portHealthStatus).append(portStatusReason, rhs.portStatusReason)
                .append(enabled, rhs.enabled).append(interconnectName, rhs.interconnectName)
                .append(fcPortProperties, rhs.fcPortProperties).append(bayNumber, rhs.bayNumber).append(portType, rhs.portType)
                .append(lagId, rhs.lagId).append(associatedUplinkSetUri, rhs.associatedUplinkSetUri).append(portId, rhs.portId)
                .append(portMonitorConfigInfo, rhs.portMonitorConfigInfo).append(capability, rhs.capability)
                .append(pairedPortName, rhs.pairedPortName).append(subports, rhs.subports).append(connectorType, rhs.connectorType)
                .append(neighbor, rhs.neighbor).append(configPortTypes, rhs.configPortTypes)
                .append(portTypeExtended, rhs.portTypeExtended).append(operationalSpeed, rhs.operationalSpeed)
                .append(description, rhs.description).append(status, rhs.status).append(name, rhs.name).append(state, rhs.state)
                .append(eTag, rhs.eTag).append(created, rhs.created).append(modified, rhs.modified).append(category, rhs.category)
                .append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum PortHealthStatus {

        Normal("Normal"), Warning("Warning"), Error("Error"), Disabled("Disabled");
        private final String value;
        private static Map<String, Port.PortHealthStatus> constants = new HashMap<String, Port.PortHealthStatus>();

        static {
            for (final Port.PortHealthStatus c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortHealthStatus(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Port.PortHealthStatus fromValue(final String value) {
            final Port.PortHealthStatus constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PortMonitorConfigInfo {

        NotMonitored("NotMonitored"), MonitoredToServer("MonitoredToServer"), MonitoredFromServer("MonitoredFromServer"), MonitoredBoth(
                "MonitoredBoth"), AnalyzerPort("AnalyzerPort");
        private final String value;
        private static Map<String, Port.PortMonitorConfigInfo> constants = new HashMap<String, Port.PortMonitorConfigInfo>();

        static {
            for (final Port.PortMonitorConfigInfo c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortMonitorConfigInfo(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Port.PortMonitorConfigInfo fromValue(final String value) {
            final Port.PortMonitorConfigInfo constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PortStatus {

        Linked("Linked"), Unlinked("Unlinked"), Unknown("Unknown");
        private final String value;
        private static Map<String, Port.PortStatus> constants = new HashMap<String, Port.PortStatus>();

        static {
            for (final Port.PortStatus c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortStatus(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Port.PortStatus fromValue(final String value) {
            final Port.PortStatus constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PortStatusReason {

        None("None"), Active("Active"), StandBy("StandBy"), LoggedIn("LoggedIn"), NotLoggedIn("NotLoggedIn"), Unknown("Unknown"), AdminHidden(
                "AdminHidden"), LoopDetected("LoopDetected"), PauseFloodDetected("PauseFloodDetected"), AdminDisabled(
                "AdminDisabled"), EkeyMismatch("EkeyMismatch"), Unpopulated("Unpopulated"), ModuleUnrecognized("ModuleUnrecognized"), FailedValidation(
                "FailedValidation"), ModuleUnsupported("ModuleUnsupported"), ModuleIncompatible("ModuleIncompatible"), SmartLink(
                "SmartLink"), SmartLinkButNoDCC("SmartLinkButNoDCC"), OkUncertified("OkUncertified"), OkNonHP("OkNonHP"), UnsupportedStorage(
                "UnsupportedStorage"), FabricTypeMismatch("FabricTypeMismatch"), Ok("Ok"), Unavailable("Unavailable"), PortPairMisMatchSfpType(
                "PortPairMisMatchSfpType"), PortPairMismatchEnetSpeed("PortPairMismatchEnetSpeed");
        private final String value;
        private static Map<String, Port.PortStatusReason> constants = new HashMap<String, Port.PortStatusReason>();

        static {
            for (final Port.PortStatusReason c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortStatusReason(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Port.PortStatusReason fromValue(final String value) {
            final Port.PortStatusReason constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PortType {

        Uplink("Uplink"), Downlink("Downlink"), Stacking("Stacking"), Auto("Auto");
        private final String value;
        private static Map<String, Port.PortType> constants = new HashMap<String, Port.PortType>();

        static {
            for (final Port.PortType c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Port.PortType fromValue(final String value) {
            final Port.PortType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PortTypeExtended {

        Internal("Internal"), External("External"), Unknown("Unknown");
        private final String value;
        private static Map<String, Port.PortTypeExtended> constants = new HashMap<String, Port.PortTypeExtended>();

        static {
            for (final Port.PortTypeExtended c : values()) {
                constants.put(c.value, c);
            }
        }

        private PortTypeExtended(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Port.PortTypeExtended fromValue(final String value) {
            final Port.PortTypeExtended constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
