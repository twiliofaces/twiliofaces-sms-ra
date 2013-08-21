package org.twiliofaces.smsra.util;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.mockejb.jms.MapMessageImpl;

public class SmsUtils
{
   public static MapMessage fromStringParams(String params) throws JMSException
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

   private static void valorize(MapMessage smsMessage, String[] pairs) throws JMSException
   {
      String key = pairs[0];
      String value = pairs[1];
      smsMessage.setString(key, value);
   }
}
