/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;

public class ScmbProcessor extends Thread
{

    private static final Logger logger = LoggerFactory.getLogger(ScmbProcessor.class);

    private RestParams params;
    private Connection conn;
    private Channel channel;
    private String routingKey;
    private ScmbMessageExecutionQueue messageQueue;

    public ScmbProcessor(
            final RestParams params,
            final Connection conn,
            final Channel channel,
            final String routingKey,
            final ScmbMessageExecutionQueue queue)
    {
        this.params = params;
        this.conn = conn;
        this.channel = channel;
        this.routingKey = routingKey;
        this.messageQueue = queue;
    }

    @Override
    public void run()
    {

        try
        {
            //do not specify queue name. AMQP will create a queue with random name starting with amq.gen* e.g. amq.gen-32sfQz9
            final DeclareOk queue = channel.queueDeclare("", true, false, true, null);

            //Now get the queue name from above call and bind it to required Exchange with required routing key.
            channel.queueBind(queue.getQueue(), SdkConstants.SCMB_EXCHANGE_NAME, routingKey);
            //Now you should be able to receive messages from queue
            while (true)
            {
                final GetResponse chResponse = channel.basicGet(queue.getQueue(), false);
                if (chResponse == null)
                {
                    //logger.debug("ScmbProcessor : run : No Message Received: " );
                }
                else
                {
                    final byte[] body = chResponse.getBody();
                    String responseBody = new String(body);
                    //add to queue
                    messageQueue.add(responseBody);
                }
                //TODO - get feedback, is it good idea to sleep in while loop?            	                
            }
        }
        catch (IOException e)
        {
            logger.error("ScmbProcessor : run : error in scmb processor : thread might have been interrupted by Stop user");
        }
    }

    public void releaseScmbThread()
    {
        this.interrupt();
        //also stop Message Queue thread
        try
        {
            messageQueue.shutDown();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
