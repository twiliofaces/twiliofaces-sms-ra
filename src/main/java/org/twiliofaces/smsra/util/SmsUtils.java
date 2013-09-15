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
package org.twiliofaces.smsra.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.twiliofaces.smsra.jms.MapMessageImpl;

public class SmsUtils
{
   public static MapMessage fromStringParams(String params) throws JMSException, UnsupportedEncodingException
   {
      MapMessage smsMessage = new MapMessageImpl();
      String[] parts = params.split("&");
      for (String part : parts)
      {
         String[] pairs = part.split("=");
         if (pairs != null && pairs.length == 2)
            valorize(smsMessage, pairs);
      }
      if (smsMessage.getString("To") != null && !smsMessage.getString("To").isEmpty()
               && smsMessage.getString("SmsSid") != null
               && !smsMessage.getString("SmsSid").isEmpty()
               && smsMessage.getString("AccountSid") != null && !smsMessage.getString("AccountSid").isEmpty() &&
               smsMessage.getString("From") != null && !smsMessage.getString("From").isEmpty() &&
               smsMessage.getString("Body") != null && !smsMessage.getString("Body").isEmpty())
         return smsMessage;
      else
         return null;
   }

   private static void valorize(MapMessage smsMessage, String[] pairs) throws JMSException,
            UnsupportedEncodingException
   {
      String key = pairs[0];
      String value = URLDecoder.decode(pairs[1], "UTF-8");
      smsMessage.setString(key, value);
   }
}
