package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerProfileTemplateClient {

    /**
     * The module aids in fetching the server profile Template details for the specified
     * server profile Template resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile Template as seen in HPE OneView.
     * @return {@link ServerProfileTemplate} containing the server profile Template details.
     */
     ServerProfileTemplate getServerProfileTemplateById(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the server profile Template details for all
     * the server profiles Templates found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link ServerProfileTemplate}&gt; containing
     * the details for all found server profiles Templates.
     */
     ResourceCollection<ServerProfileTemplate> getAllServerProfileTemplates(final RestParams params);

     /**
      * A server profile object will be returned with the configuration based on this template.
      * This profile object can subsequently be used to create a new server profile.
      *
      * @param params
      *            The {@link RestParams} is a structure containing the connection details.
      * @param resourceId
      *            The resource identifier for server profile Template as seen in HPE OneView.
      * @return {@link ServerProfile} containing the server profile details.
      */
      ServerProfile getNewServerProfile(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the server profile Template details for the
     * server profile Template name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the server profile Template name as seen in HPE OneView.
     * @return {@link ServerProfileTemplate} containing the server profile Template details.
     */
     ServerProfileTemplate getServerProfileTemplateByName(final RestParams params, final String name);

     /**
      * The module aids in the creation of a server profile Template when provided with the
      * server profile Template details as a {@link ServerProfileTemplate} object. It can
      * process the request asynchronously or synchronously, based on the flag input.
      *
      * @param params
      *            The {@link RestParams} is a structure containing the connection details.
      * @param serverProfileTemplateDto
      *            Object containing the server profile template details, used to create a
      *            server profile template.
      * @param aSync
      *            Flag input to process request asynchronously or synchronously.
      *
      * @return {@link TaskResourceV2} containing the task status for the process.
      */
     TaskResourceV2 createServerProfileTemplate(final RestParams params, final ServerProfileTemplate serverProfileTemplateDto, final boolean aSync);

     /**
      * The module takes in a ServerProfileTemplate object and updates
      * the existing server profile Template based on the resource identifier. It can process the
      * request asynchronously or synchronously, based on the flag input.
      *
      * @param params
      *            The {@link RestParams} is a structure containing the connection details.
      * @param resourceId
      *            The resource identifier for server profile Template as seen in HPE OneView.
      * @param serverProfileTemplateDto
      *            Object containing the server profile Template details, used to update a
      *            server profile Template.
      * @param aSync
      *            Flag input to process request asynchronously or synchronously.
      *
      * @return {@link TaskResourceV2} containing the task status for the process.
      */
     TaskResourceV2 updateServerProfileTemplate(final RestParams params, final String resourceId,
             final ServerProfileTemplate serverProfileTemplateDto, final boolean aSync);

     /**
      * This method aids in deletion of a server profile template.
      *
      * @param params
      *            The {@link RestParams} is a structure containing the connection details.
      * @param resourceId
      *            The resource identifier for server profile template as seen in HPE OneView.
      * @param aSync flag to indicate whether the request should be processed
      *              asynchronously or synchronously.
      * @return {@link TaskResourceV2} containing the task status for the process.
      */
     TaskResourceV2 deleteServerProfileTemplate(RestParams params, String resourceId, boolean aSync);

}
