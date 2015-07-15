/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProfileConnectionV3 implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer allocatedMbps;
    private ConnectionBoot boot;
    private ConnectionStatus deploymentStatus;
    private FunctionType functionType;
    private Integer id;
    private String interconnectUri;
    private String mac;
    private AssignmentType macType;
    private Integer maximumMbps;
    private String name;
    private String networkUri;
    private String portId;
    private String requestedMbps;
    private String wwnn;
    private String wwpn;
    private AssignmentType wwpnType;

    /**
     * 
     * @return
     *         The allocatedMbps
     */
    public Integer getAllocatedMbps()
    {
        return allocatedMbps;
    }

    /**
     * 
     * @param allocatedMbps
     *        The allocatedMbps
     */
    public void setAllocatedMbps(final Integer allocatedMbps)
    {
        this.allocatedMbps = allocatedMbps;
    }

    /**
     * 
     * @return
     *         The boot
     */
    public ConnectionBoot getBoot()
    {
        return boot;
    }

    /**
     * 
     * @param boot
     *        The boot
     */
    public void setBoot(final ConnectionBoot boot)
    {
        this.boot = boot;
    }

    /**
     * 
     * @return
     *         The deploymentStatus
     */
    public ConnectionStatus getDeploymentStatus()
    {
        return deploymentStatus;
    }

    /**
     * 
     * @param deploymentStatus
     *        The deploymentStatus
     */
    public void setDeploymentStatus(final ConnectionStatus deploymentStatus)
    {
        this.deploymentStatus = deploymentStatus;
    }

    /**
     * 
     * @return
     *         The functionType
     */
    public FunctionType getFunctionType()
    {
        return functionType;
    }

    /**
     * 
     * @param functionType
     *        The functionType
     */
    public void setFunctionType(final FunctionType functionType)
    {
        this.functionType = functionType;
    }

    /**
     * 
     * @return
     *         The id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 
     * @param id
     *        The id
     */
    public void setId(final Integer id)
    {
        this.id = id;
    }

    /**
     * 
     * @return
     *         The interconnectUri
     */
    public String getInterconnectUri()
    {
        return interconnectUri;
    }

    /**
     * 
     * @param interconnectUri
     *        The interconnectUri
     */
    public void setInterconnectUri(final String interconnectUri)
    {
        this.interconnectUri = interconnectUri;
    }

    /**
     * 
     * @return
     *         The mac
     */
    public String getMac()
    {
        return mac;
    }

    /**
     * 
     * @param mac
     *        The mac
     */
    public void setMac(final String mac)
    {
        this.mac = mac;
    }

    /**
     * 
     * @return
     *         The macType
     */
    public AssignmentType getMacType()
    {
        return macType;
    }

    /**
     * 
     * @param macType
     *        The macType
     */
    public void setMacType(final AssignmentType macType)
    {
        this.macType = macType;
    }

    /**
     * 
     * @return
     *         The maximumMbps
     */
    public Integer getMaximumMbps()
    {
        return maximumMbps;
    }

    /**
     * 
     * @param maximumMbps
     *        The maximumMbps
     */
    public void setMaximumMbps(final Integer maximumMbps)
    {
        this.maximumMbps = maximumMbps;
    }

    /**
     * 
     * @return
     *         The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @param name
     *        The name
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return
     *         The networkUri
     */
    public String getNetworkUri()
    {
        return networkUri;
    }

    /**
     * 
     * @param networkUri
     *        The networkUri
     */
    public void setNetworkUri(final String networkUri)
    {
        this.networkUri = networkUri;
    }

    /**
     * 
     * @return
     *         The portId
     */
    public String getPortId()
    {
        return portId;
    }

    /**
     * 
     * @param portId
     *        The portId
     */
    public void setPortId(final String portId)
    {
        this.portId = portId;
    }

    /**
     * 
     * @return
     *         The requestedMbps
     */
    public String getRequestedMbps()
    {
        return requestedMbps;
    }

    /**
     * 
     * @param requestedMbps
     *        The requestedMbps
     */
    public void setRequestedMbps(final String requestedMbps)
    {
        this.requestedMbps = requestedMbps;
    }

    /**
     * 
     * @return
     *         The wwnn
     */
    public String getWwnn()
    {
        return wwnn;
    }

    /**
     * 
     * @param wwnn
     *        The wwnn
     */
    public void setWwnn(final String wwnn)
    {
        this.wwnn = wwnn;
    }

    /**
     * 
     * @return
     *         The wwpn
     */
    public String getWwpn()
    {
        return wwpn;
    }

    /**
     * 
     * @param wwpn
     *        The wwpn
     */
    public void setWwpn(final String wwpn)
    {
        this.wwpn = wwpn;
    }

    /**
     * 
     * @return
     *         The wwpnType
     */
    public AssignmentType getWwpnType()
    {
        return wwpnType;
    }

    /**
     * 
     * @param wwpnType
     *        The wwpnType
     */
    public void setWwpnType(final AssignmentType wwpnType)
    {
        this.wwpnType = wwpnType;
    }

    public static enum AssignmentType
    {

        Physical ("Physical"),
        UserDefined ("UserDefined"),
        Virtual ("Virtual");
        private final String value;
        private static Map<String, ProfileConnectionV3.AssignmentType> constants = new HashMap<String, ProfileConnectionV3.AssignmentType>();

        static
        {
            for (final ProfileConnectionV3.AssignmentType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private AssignmentType(final String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return this.value;
        }

        public static ProfileConnectionV3.AssignmentType fromValue(final String value)
        {
            final ProfileConnectionV3.AssignmentType constant = constants.get(value);
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

    public static enum FunctionType
    {

        Ethernet ("Ethernet"),
        FibreChannel ("FibreChannel");
        private final String value;
        private static Map<String, ProfileConnectionV3.FunctionType> constants = new HashMap<String, ProfileConnectionV3.FunctionType>();

        static
        {
            for (final ProfileConnectionV3.FunctionType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private FunctionType(final String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return this.value;
        }

        public static ProfileConnectionV3.FunctionType fromValue(final String value)
        {
            final ProfileConnectionV3.FunctionType constant = constants.get(value);
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

    public static enum ConnectionStatus
    {

        Deployed ("Deployed"),
        Reserved ("Reserved"),
        Undefined ("Undefined");
        private final String value;
        private static Map<String, ProfileConnectionV3.ConnectionStatus> constants = new HashMap<String, ProfileConnectionV3.ConnectionStatus>();

        static
        {
            for (final ProfileConnectionV3.ConnectionStatus c : values())
            {
                constants.put(c.value, c);
            }
        }

        private ConnectionStatus(final String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return this.value;
        }

        public static ProfileConnectionV3.ConnectionStatus fromValue(final String value)
        {
            final ProfileConnectionV3.ConnectionStatus constant = constants.get(value);
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
