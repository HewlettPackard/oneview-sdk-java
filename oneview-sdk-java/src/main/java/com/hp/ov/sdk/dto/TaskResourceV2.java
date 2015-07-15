/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TaskResourceV2 extends BaseModelResource
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String taskStatus;
    private String stateReason;
    private TaskType taskType;
    private Boolean hidden;
    private Map<String, Object> data;
    private TaskState taskState;
    private String parentTaskUri;
    private Integer totalSteps;
    private Integer completedSteps;
    private Integer percentComplete;
    private Integer expectedDuration;
    private Integer computedPercentComplete;
    private Boolean userInitiated;
    private String owner;
    private AssociatedResource associatedResource;
    private String associatedTaskUri;
    private List<ErrorMessage> taskErrors = new ArrayList<ErrorMessage>();
    private List<TaskProgress> progressUpdates = new ArrayList<TaskProgress>();
    private List<String> taskOutput = new ArrayList<String>();

    public String getTaskStatus()
    {
        return taskStatus;
    }

    public void setTaskStatus(final String taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    public String getStateReason()
    {
        return stateReason;
    }

    public void setStateReason(final String stateReason)
    {
        this.stateReason = stateReason;
    }

    public TaskType getTaskType()
    {
        return taskType;
    }

    public void setTaskType(final TaskType taskType)
    {
        this.taskType = taskType;
    }

    public boolean isHidden()
    {
        return hidden;
    }

    public void setHidden(final boolean hidden)
    {
        this.hidden = hidden;
    }

    public Map<String, Object> getData()
    {
        return data;
    }

    public void setData(final Map<String, Object> data)
    {
        this.data = data;
    }

    public TaskState getTaskState()
    {
        return taskState;
    }

    public void setTaskState(final TaskState taskState)
    {
        this.taskState = taskState;
    }

    public String getParentTaskUri()
    {
        return parentTaskUri;
    }

    public void setParentTaskUri(final String parentTaskUri)
    {
        this.parentTaskUri = parentTaskUri;
    }

    public Integer getTotalSteps()
    {
        return totalSteps;
    }

    public void setTotalSteps(final Integer totalSteps)
    {
        this.totalSteps = totalSteps;
    }

    public Integer getCompletedSteps()
    {
        return completedSteps;
    }

    public void setCompletedSteps(final Integer completedSteps)
    {
        this.completedSteps = completedSteps;
    }

    public Integer getPercentComplete()
    {
        return percentComplete;
    }

    public void setPercentComplete(final Integer percentComplete)
    {
        this.percentComplete = percentComplete;
    }

    public Integer getExpectedDuration()
    {
        return expectedDuration;
    }

    public void setExpectedDuration(final Integer expectedDuration)
    {
        this.expectedDuration = expectedDuration;
    }

    public Integer getComputedPercentComplete()
    {
        return computedPercentComplete;
    }

    public void setComputedPercentComplete(final Integer computedPercentComplete)
    {
        this.computedPercentComplete = computedPercentComplete;
    }

    public boolean isUserInitiated()
    {
        return userInitiated;
    }

    public void setUserInitiated(final boolean userInitiated)
    {
        this.userInitiated = userInitiated;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(final String owner)
    {
        this.owner = owner;
    }

    public AssociatedResource getAssociatedResource()
    {
        return associatedResource;
    }

    public void setAssociatedResource(final AssociatedResource associatedResource)
    {
        this.associatedResource = associatedResource;
    }

    public String getAssociatedTaskUri()
    {
        return associatedTaskUri;
    }

    public void setAssociatedTaskUri(final String associatedTaskUri)
    {
        this.associatedTaskUri = associatedTaskUri;
    }

    public List<ErrorMessage> getTaskErrors()
    {
        return taskErrors;
    }

    public void setTaskErrors(final List<ErrorMessage> taskErrors)
    {
        this.taskErrors = taskErrors;
    }

    public List<TaskProgress> getProgressUpdates()
    {
        return progressUpdates;
    }

    public void setProgressUpdates(final List<TaskProgress> progressUpdates)
    {
        this.progressUpdates = progressUpdates;
    }

    public List<String> getTaskOutput()
    {
        return taskOutput;
    }

    public void setTaskOutput(final List<String> taskOutput)
    {
        this.taskOutput = taskOutput;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
