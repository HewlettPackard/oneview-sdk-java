/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.List;

public abstract class BaseCollectionResource<T> extends BaseModelResource {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int start;
    private int count;
    private int total;
    private String prevPageUri;
    private String nextPageUri;

    public BaseCollectionResource() {

    }

    public BaseCollectionResource(final int start, final int count, final int total) {
        this.start = start;
        this.count = count;
        this.total = total;
    }

    // TODO
    // public BaseCollectionResource(final BaseCollectionResource<?> toCopy) {
    // super(toCopy);
    //
    //
    // }

    public int getStart() {
        return start;
    }

    public void setStart(final int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(final int total) {
        this.total = total;
    }

    public String getPrevPageUri() {
        return prevPageUri;
    }

    public void setPrevPageUri(final String prevPageUri) {
        this.prevPageUri = prevPageUri;
    }

    public String getNextPageUri() {
        return nextPageUri;
    }

    public void setNextPageUri(final String nextPageUri) {
        this.nextPageUri = nextPageUri;
    }

    public abstract List<T> getMembers();

    public abstract void setMembers(List<T> members);

}
