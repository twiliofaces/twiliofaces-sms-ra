package org.twiliofaces.smsra;

import java.util.logging.Logger;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.resource.spi.endpoint.MessageEndpoint;
import javax.resource.spi.work.Work;

public class SMSMessageWorker implements Work
{

   private static final Logger logger = Logger
            .getLogger(SMSMessageWorker.class.getName());
   private Message message;
   private MessageEndpoint messageEndpoint;

   public SMSMessageWorker(Message message,
            MessageEndpoint messageEndpoint)
   {
      this.message = message;
      this.messageEndpoint = messageEndpoint;
   }

   public void release()
   {
      logger.info("release message worker");
   }

   public void run()
   {
      if (messageEndpoint instanceof MessageListener)
      {
         ((MessageListener) messageEndpoint).onMessage(message);
      }
      messageEndpoint.release();
   }
}