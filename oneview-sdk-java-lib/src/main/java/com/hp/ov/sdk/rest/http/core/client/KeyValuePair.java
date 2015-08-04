/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import java.util.Objects;

public class KeyValuePair {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public KeyValuePair(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof KeyValuePair)) {
            return false;
        }

        final KeyValuePair other = (KeyValuePair) obj;

        return Objects.equals(this.getKey(), other.getKey()) && Objects.equals(this.getValue(), other.getValue());

    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
