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
   private MessageEndpointFactory endpointFactory;
   private WorkManager workManager;
   private ChannelFactory factory = null;
   private volatile Channel serverChannel;

   public SMSListener(SMSActivationSpec activationSpec,
            MessageEndpointFactory endpointFactory, WorkManager workManager)
   {
      logger.info("start twilio sms worker");
      this.endpointFactory = endpointFactory;
      this.workManager = workManager;
      this.released = false;
      this.factory = new DefaultLocalServerChannelFactory();
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
         EchoServerHandler handler = new EchoServerHandler(endpointFactory, workManager);
         serverBootstrap.getPipeline().addLast("handler", handler);
         this.serverChannel = serverBootstrap.bind(new LocalAddress("0.0.0.0"));
         // Now loop forever, waiting the end
         logger.info("listening on host 0.0.0.0");
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