/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionBoot implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private BootControl priority;
    private List<BootTarget> targets = new ArrayList<BootTarget>();

    /**
     * 
     * @return
     *         The priority
     */
    public BootControl getPriority()
    {
        return priority;
    }

    /**
     * 
     * @param priority
     *        The priority
     */
    public void setPriority(BootControl priority)
    {
        this.priority = priority;
    }

    /**
     * 
     * @return
     *         The targets
     */
    public List<BootTarget> getTargets()
    {
        return targets;
    }

    /**
     * 
     * @param targets
     *        The targets
     */
    public void setTargets(List<BootTarget> targets)
    {
        this.targets = targets;
    }

    public static enum BootControl
    {

        NotBootable ("NotBootable"),
        Primary ("Primary"),
        Secondary ("Secondary");
        private final String value;
        private static Map<String, ConnectionBoot.BootControl> constants = new HashMap<String, ConnectionBoot.BootControl>();

        static
        {
            for (ConnectionBoot.BootControl c : values())
            {
                constants.put(c.value, c);
            }
        }

        private BootControl(String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return this.value;
        }

        public static ConnectionBoot.BootControl fromValue(String value)
        {
            ConnectionBoot.BootControl constant = constants.get(value);
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
