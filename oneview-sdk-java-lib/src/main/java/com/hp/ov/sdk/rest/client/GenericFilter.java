package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.UrlQuery;

public class GenericFilter implements UrlQuery {

    private List<UrlParameter> filters = new ArrayList<>();

    public GenericFilter setFilter(String filterValue) {
        this.filters.add(new UrlParameter("filter", filterValue));
        return this;
    }

    @Override
    public List<UrlParameter> parameters() {
        return ImmutableList.copyOf(this.filters);
    }

}
