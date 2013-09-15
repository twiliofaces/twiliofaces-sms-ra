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