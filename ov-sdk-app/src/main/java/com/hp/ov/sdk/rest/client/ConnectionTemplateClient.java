/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ConnectionTemplateCollection;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ConnectionTemplateClient
{

    /**
     * The module aids in fetching the connection template when provided with
     * the ConnectionTemplate resourceId
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param id
     *        The resourceId for connection template as seen in HP OneView.
     * @return connectionTemplateDto, which is a object containing the
     *         connection template details.
     * 
     */
    public ConnectionTemplate getConnectionTemplate(final RestParams params,
            final String id);

    /**
     * The module aids in fetching the connection template when provided with
     * the connection template name
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceName
     *        The resourceName is the connection template name as seen in HP
     *        OneView.
     * @return connectionTemplateDto, which is a object containing the
     *         connection template details.
     */

    public ConnectionTemplate getConnectionTemplateByName(
            final RestParams params, final String resourceName);

    /**
     * The module takes in an ConnectionTemplate object or JsonRequest and
     * updates the existing connection template based on resource Id. It can
     * process the request asynchronously or synchronously based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for connection template as seen in HP OneView.
     * @param connectionTemplateDto
     *        This is a object containing the update to be made to existing
     *        ConnectionTempalte pointed to by the above mentioned
     *        resourceId
     * @param useJsonRequest
     *        The JsonRequest body is part of ConnectionTemplate Object
     *        which takes in a String containing the update to be made,
     *        which is converted to ConnectionTemplate Object using adaptor
     *        and processed.
     * @return connectionTemplateDto, which is a object containing the updated
     *         connection template details.
     */
    public ConnectionTemplate updateConnectionTemplate(final RestParams params,
            final String resourceId,
            final ConnectionTemplate connectionTemplateDto,
            final boolean useJsonRequest);
    
    /**
     * The module aids in fetching the Connection template details for all the Network
     * found under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return connectionTemplateCollectionDto, which is a object containing the collection
     *         of Connection Template details.
     */   
    public ConnectionTemplateCollection getAllConnectionTemplates(final RestParams params);
    
    /**
     * The module aids in fetching the default Connection template details for the Network
     *  found under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return connectionTemplateDto, which is a object containing the 
     *          Connection Template details.
     */ 
    public ConnectionTemplate getDefaultConnectionTemplateForConnectionTemplate(final RestParams params);
}
