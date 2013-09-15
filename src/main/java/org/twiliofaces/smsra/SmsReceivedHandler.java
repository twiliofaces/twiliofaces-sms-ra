/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.smsra;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.resource.spi.UnavailableException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.WorkException;
import javax.resource.spi.work.WorkManager;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.twiliofaces.smsra.util.SmsUtils;

public class SmsReceivedHandler extends SimpleChannelUpstreamHandler
{

   private static final Logger logger = Logger.getLogger(
            SmsReceivedHandler.class.getName());

   private final AtomicLong transferredBytes = new AtomicLong();

   private MessageEndpointFactory endpointFactory;
   private WorkManager workManager;

   public SmsReceivedHandler()
   {
   }

   public SmsReceivedHandler(MessageEndpointFactory endpointFactory, WorkManager workManager)
   {
      super();
      this.endpointFactory = endpointFactory;
      this.workManager = workManager;
   }

   public long getTransferredBytes()
   {
      return transferredBytes.get();
   }

   @Override
   public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e)
   {
      // Send back the received message to the remote peer.
      ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
      transferredBytes.addAndGet(buffer.readableBytes());

      // e.getChannel().write(e.getMessage());
      StringBuilder strBuffer = new StringBuilder();

      while (buffer.readable())
      {
         strBuffer.append((char) buffer.readByte());
      }
      ChannelBuffer channelNew = ChannelBuffers.copiedBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  " +
               "<Response></Response>" + "\n", Charset.defaultCharset());
      try
      {
         MapMessage smsMessage = SmsUtils.fromStringParams(strBuffer.toString());

         if (smsMessage != null)
         {
            workManager.scheduleWork(new SMSMessageWorker(smsMessage,
                     endpointFactory.createEndpoint(null)));
         }
      }
      catch (UnavailableException ue)
      {
         ue.printStackTrace();
      }
      catch (WorkException e1)
      {
         e1.printStackTrace();
      }
      catch (JMSException e1)
      {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      catch (UnsupportedEncodingException e1)
      {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      e.getChannel().write(channelNew);
   }

   @Override
   public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e)
   {
      // Close the connection when an exception is raised.
      logger.log(
               Level.WARNING,
               "Unexpected exception from downstream.",
               e.getCause());
      e.getChannel().close();
   }
}
