package org.twiliofaces.smsra;

import java.util.logging.Logger;

import javax.resource.spi.endpoint.MessageEndpoint;
import javax.resource.spi.work.Work;

import org.twiliofaces.smsra.model.SMSMessage;

public class SMSMessageWorker implements Work
{

   private static final Logger logger = Logger
            .getLogger(SMSMessageWorker.class.getName());
   private SMSMessage udpMessage;
   private MessageEndpoint messageEndpoint;

   public SMSMessageWorker(SMSMessage udpMessage,
            MessageEndpoint messageEndpoint)
   {
      this.udpMessage = udpMessage;
      this.messageEndpoint = messageEndpoint;
   }

   public void release()
   {
      logger.info("release message worker");
   }

   public void run()
   {
      if (messageEndpoint instanceof SMSMessageListener)
      {
         ((SMSMessageListener) messageEndpoint).onMessage(udpMessage);
      }
      messageEndpoint.release();
   }
}