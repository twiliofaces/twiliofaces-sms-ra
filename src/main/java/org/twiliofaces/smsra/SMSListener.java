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

import java.util.logging.Logger;

import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkManager;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.local.DefaultLocalServerChannelFactory;
import org.jboss.netty.channel.local.LocalAddress;

public class SMSListener implements Work
{

   private static final Logger logger = Logger.getLogger(SMSListener.class
            .getName());
   private boolean released;
   private MessageEndpointFactory messageEndpointFactory;
   private WorkManager workManager;
   private ChannelFactory factory = null;
   private volatile Channel serverChannel;
   private SMSActivationSpec activationSpec;

   public SMSListener(SMSActivationSpec smsActivationSpec,
            MessageEndpointFactory messageEndpointFactory, WorkManager workManager)
   {
      logger.info("starting twiliofaces sms worker");
      this.messageEndpointFactory = messageEndpointFactory;
      this.workManager = workManager;
      this.released = false;
      this.factory = new DefaultLocalServerChannelFactory();
      this.activationSpec = smsActivationSpec;
   }

   public void release()
   {
      logger.info("releasing listener");
      this.released = true;
      try
      {
         if (this.serverChannel != null)
         {
            logger.info("closing serverChannel");
            this.serverChannel.close();
         }
      }
      catch (Throwable e)
      {
         e.printStackTrace();
      }

      logger.info("listener closed");
   }

   public boolean isReleased()
   {
      return released;
   }

   public void run()
   {
      try
      {
         ServerBootstrap serverBootstrap = new ServerBootstrap(factory);
         SmsReceivedHandler handler = new SmsReceivedHandler(messageEndpointFactory, workManager);
         serverBootstrap.getPipeline().addLast("handler", handler);
         if (this.activationSpec != null && this.activationSpec.getHost() != null
                  && !this.activationSpec.getHost().isEmpty())
         {
            this.serverChannel = serverBootstrap.bind(new LocalAddress(this.activationSpec.getHost()));
            logger.info("listening on host " + this.activationSpec.getHost());
         }
         else
         {
            this.serverChannel = serverBootstrap.bind(new LocalAddress("0.0.0.0"));
            logger.info("listening on host 0.0.0.0");
         }

         while (!isReleased())
         {
            Thread.sleep(1000);
         }

      }
      catch (Throwable se)
      {
         se.printStackTrace();
      }
   }
}