package org.twiliofaces.smsra.jms;

import java.util.*;
import javax.jms.*;

/**
 * <code>Message</code> implementation.
 * 
 * @link http://mockejb.sourceforge.net/
 * @author Dimitar Gospodinov
 * @see javax.jms.Message
 */
public class MessageImpl implements Message
{

   private static final int MODE_READ_ONLY = 100;
   private static final int MODE_WRITE_ONLY = 200;

   private int bodyMode = MODE_WRITE_ONLY;

   private boolean propertiesWriteable = true;

   // JMS Headers
   private String jmsType;
   private long jmsTimestamp;
   private Destination jmsReplyTo;
   private int jmsPriority;
   private String jmsMessageId;
   private long jmsExpiration;
   private Destination jmsDestination;
   private int jmsDeliveryMode;
   private String jmsCorrelationId;

   private final PrimitiveMap properties = new PrimitiveMap();

   /**
    * Constructs empty message.
    */
   public MessageImpl()
   {
      super();
   }

   /**
    * Constructs new <code>MessageImpl</code> by copying all header fields and properties from the specified message
    * <code>msg</code>
    * 
    * @param msg
    */
   public MessageImpl(Message msg) throws JMSException
   {
      setJMSMessageID(msg.getJMSMessageID());
      setJMSTimestamp(msg.getJMSTimestamp());
      setJMSCorrelationID(msg.getJMSCorrelationID());
      setJMSReplyTo(msg.getJMSReplyTo());
      setJMSDestination(msg.getJMSDestination());
      setJMSDeliveryMode(msg.getJMSDeliveryMode());
      setJMSRedelivered(msg.getJMSRedelivered());
      setJMSType(msg.getJMSType());
      setJMSExpiration(msg.getJMSExpiration());
      setJMSPriority(msg.getJMSPriority());

      Enumeration e = msg.getPropertyNames();
      while (e.hasMoreElements())
      {
         String propertyName = (String) e.nextElement();
         setObjectProperty(
                  propertyName,
                  msg.getObjectProperty(propertyName));
      }
   }

   /**
    * @see javax.jms.Message#getJMSMessageID()
    */
   public String getJMSMessageID() throws JMSException
   {
      return jmsMessageId;
   }

   /**
    * @see javax.jms.Message#setJMSMessageID(java.lang.String)
    */
   public void setJMSMessageID(String messageId) throws JMSException
   {
      jmsMessageId = messageId;

   }

   /**
    * @see javax.jms.Message#getJMSTimestamp()
    */
   public long getJMSTimestamp() throws JMSException
   {
      return jmsTimestamp;
   }

   /**
    * @see javax.jms.Message#setJMSTimestamp(long)
    */
   public void setJMSTimestamp(long timestamp) throws JMSException
   {
      jmsTimestamp = timestamp;
   }

   /**
    * Not supported.
    * 
    * @see javax.jms.Message#getJMSCorrelationIDAsBytes()
    */
   public byte[] getJMSCorrelationIDAsBytes() throws JMSException
   {
      throw new UnsupportedOperationException();
   }

   /**
    * Not supported.
    * 
    * @see javax.jms.Message#setJMSCorrelationIDAsBytes(byte[])
    */
   public void setJMSCorrelationIDAsBytes(byte[] arg0) throws JMSException
   {
      throw new UnsupportedOperationException();
   }

   /**
    * @see javax.jms.Message#setJMSCorrelationID(java.lang.String)
    */
   public void setJMSCorrelationID(String correlationId) throws JMSException
   {
      jmsCorrelationId = correlationId;
   }

   /**
    * @see javax.jms.Message#getJMSCorrelationID()
    */
   public String getJMSCorrelationID() throws JMSException
   {
      return jmsCorrelationId;
   }

   /**
    * @see javax.jms.Message#getJMSReplyTo()
    */
   public Destination getJMSReplyTo() throws JMSException
   {
      return jmsReplyTo;
   }

   /**
    * @see javax.jms.Message#setJMSReplyTo(javax.jms.Destination)
    */
   public void setJMSReplyTo(Destination replyTo) throws JMSException
   {
      jmsReplyTo = replyTo;
   }

   /**
    * @see javax.jms.Message#getJMSDestination()
    */
   public Destination getJMSDestination() throws JMSException
   {
      return jmsDestination;
   }

   /**
    * @see javax.jms.Message#setJMSDestination(javax.jms.Destination)
    */
   public void setJMSDestination(Destination destination)
            throws JMSException
   {
      jmsDestination = destination;
   }

   /**
    * @see javax.jms.Message#getJMSDeliveryMode()
    */
   public int getJMSDeliveryMode() throws JMSException
   {
      return jmsDeliveryMode;
   }

   /**
    * @see javax.jms.Message#setJMSDeliveryMode(int)
    */
   public void setJMSDeliveryMode(int deliveryMode) throws JMSException
   {
      jmsDeliveryMode = deliveryMode;
   }

   /**
    * Always returns <code>false</code>
    * 
    * @see javax.jms.Message#getJMSRedelivered()
    */
   public boolean getJMSRedelivered() throws JMSException
   {
      return false;
   }

   /**
    * Does nothing.
    * 
    * @see javax.jms.Message#setJMSRedelivered(boolean)
    */
   public void setJMSRedelivered(boolean redelivered) throws JMSException
   {
      // Does nothing.
   }

   /**
    * @see javax.jms.Message#getJMSType()
    */
   public String getJMSType() throws JMSException
   {
      return jmsType;
   }

   /**
    * @see javax.jms.Message#setJMSType(java.lang.String)
    */
   public void setJMSType(String type) throws JMSException
   {
      jmsType = type;
   }

   /**
    * @see javax.jms.Message#getJMSExpiration()
    */
   public long getJMSExpiration() throws JMSException
   {
      return jmsExpiration;
   }

   /**
    * @see javax.jms.Message#setJMSExpiration(long)
    */
   public void setJMSExpiration(long expiration) throws JMSException
   {
      jmsExpiration = expiration;
   }

   /**
    * @see javax.jms.Message#getJMSPriority()
    */
   public int getJMSPriority() throws JMSException
   {
      return jmsPriority;
   }

   /**
    * @see javax.jms.Message#setJMSPriority(int)
    */
   public void setJMSPriority(int priority) throws JMSException
   {
      jmsPriority = priority;
   }

   /**
    * @see javax.jms.Message#clearProperties()
    */
   public void clearProperties() throws JMSException
   {
      propertiesWriteable = true;
      properties.clear();
   }

   /**
    * @see javax.jms.Message#propertyExists(java.lang.String)
    */
   public boolean propertyExists(String propertyName) throws JMSException
   {
      return properties.containsKey(propertyName);
   }

   /**
    * @see javax.jms.Message#getBooleanProperty(java.lang.String)
    */
   public boolean getBooleanProperty(String name) throws JMSException
   {
      return properties.getBoolean(name);
   }

   /**
    * @see javax.jms.Message#getByteProperty(java.lang.String)
    */
   public byte getByteProperty(String name) throws JMSException
   {
      return properties.getByte(name);
   }

   /**
    * @see javax.jms.Message#getShortProperty(java.lang.String)
    */
   public short getShortProperty(String name) throws JMSException
   {
      return properties.getShort(name);
   }

   /**
    * @see javax.jms.Message#getIntProperty(java.lang.String)
    */
   public int getIntProperty(String name) throws JMSException
   {
      return properties.getInt(name);
   }

   /**
    * @see javax.jms.Message#getLongProperty(java.lang.String)
    */
   public long getLongProperty(String name) throws JMSException
   {
      return properties.getLong(name);
   }

   /**
    * @see javax.jms.Message#getFloatProperty(java.lang.String)
    */
   public float getFloatProperty(String name) throws JMSException
   {
      return properties.getFloat(name);
   }

   /**
    * @see javax.jms.Message#getDoubleProperty(java.lang.String)
    */
   public double getDoubleProperty(String name) throws JMSException
   {
      return properties.getDouble(name);
   }

   /**
    * @see javax.jms.Message#getStringProperty(java.lang.String)
    */
   public String getStringProperty(String name) throws JMSException
   {
      return properties.getString(name);
   }

   /**
    * @see javax.jms.Message#getObjectProperty(java.lang.String)
    */
   public Object getObjectProperty(String name) throws JMSException
   {
      return properties.getObject(name);
   }

   /**
    * @see javax.jms.Message#getPropertyNames()
    */
   public Enumeration getPropertyNames() throws JMSException
   {
      return properties.getNames();
   }

   /**
    * @see javax.jms.Message#setBooleanProperty(java.lang.String, boolean)
    */
   public void setBooleanProperty(String name, boolean value)
            throws JMSException
   {

      checkPropertiesWriteable();
      properties.setBoolean(name, value);
   }

   /**
    * @see javax.jms.Message#setByteProperty(java.lang.String, byte)
    */
   public void setByteProperty(String name, byte value) throws JMSException
   {

      checkPropertiesWriteable();
      properties.setByte(name, value);
   }

   /**
    * @see javax.jms.Message#setShortProperty(java.lang.String, short)
    */
   public void setShortProperty(String name, short value)
            throws JMSException
   {

      checkPropertiesWriteable();
      properties.setShort(name, value);
   }

   /**
    * @see javax.jms.Message#setIntProperty(java.lang.String, int)
    */
   public void setIntProperty(String name, int value) throws JMSException
   {

      checkPropertiesWriteable();
      properties.setInt(name, value);
   }

   /**
    * @see javax.jms.Message#setLongProperty(java.lang.String, long)
    */
   public void setLongProperty(String name, long value) throws JMSException
   {

      checkPropertiesWriteable();
      properties.setLong(name, value);
   }

   /**
    * @see javax.jms.Message#setFloatProperty(java.lang.String, float)
    */
   public void setFloatProperty(String name, float value)
            throws JMSException
   {

      checkPropertiesWriteable();
      properties.setFloat(name, value);
   }

   /**
    * @see javax.jms.Message#setDoubleProperty(java.lang.String, double)
    */
   public void setDoubleProperty(String name, double value)
            throws JMSException
   {

      checkPropertiesWriteable();
      properties.setDouble(name, value);
   }

   /**
    * @see javax.jms.Message#setStringProperty(java.lang.String, java.lang.String)
    */
   public void setStringProperty(String name, String value)
            throws JMSException
   {

      checkPropertiesWriteable();
      properties.setString(name, value);
   }

   /**
    * @see javax.jms.Message#setObjectProperty(java.lang.String, java.lang.Object)
    */
   public void setObjectProperty(String name, Object value)
            throws JMSException
   {

      checkPropertiesWriteable();
      properties.setObject(name, value);
   }

   /**
    * Does nothing.
    * 
    * @see javax.jms.Message#acknowledge()
    */
   public void acknowledge() throws JMSException
   {
      // Does nothing.
   }

   /**
    * @see javax.jms.Message#clearBody()
    */
   public void clearBody() throws JMSException
   {
      bodyMode = MODE_WRITE_ONLY;
   }

   // Non standard methods

   private boolean propertiesWriteable()
   {
      return propertiesWriteable;
   }

   public void setPropertiesNotWriteable()
   {
      propertiesWriteable = false;
   }

   private void checkPropertiesWriteable()
            throws MessageNotWriteableException
   {
      if (!propertiesWriteable())
      {
         throw new MessageNotWriteableException("Message is in read-only mode!");
      }
   }

   void setBodyReadOnly()
   {
      bodyMode = MODE_READ_ONLY;
   }

   /**
    * Checks if this message can be read from. If not then throws <code>MessageNotReadableException</code>
    * 
    * @throws MessageNotReadableException if read operations are not allowed.
    */
   void checkBodyReadable() throws JMSException
   {
      if (bodyMode == MODE_WRITE_ONLY)
      {
         throw new MessageNotReadableException("Message is not in Read-Only mode!");
      }
   }

   /**
    * Checks if this message can be written to. If not then throws <code>MessageNotWriteableException</code>
    * 
    * @throws MessageNotWriteableException if write operations are not allowed.
    */
   void checkBodyWriteable() throws JMSException
   {
      if (bodyMode == MODE_READ_ONLY)
      {
         throw new MessageNotWriteableException("Message is not in Write-Only mode!");
      }
   }

   /**
    * Sets message body in read-only mode.
    * 
    * @throws JMSException
    */
   void resetBody() throws JMSException
   {
      // Does nothing for generic message
   }

}
