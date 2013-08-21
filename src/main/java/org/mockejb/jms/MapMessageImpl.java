package org.mockejb.jms;

import java.util.*;
import javax.jms.*;

/**
 * <code>MapMessage</code> implementation.
 * 
 * @author Dimitar Gospodinov
 * @see javax.jms.MapMessage
 */
public class MapMessageImpl extends MessageImpl implements MapMessage
{

   private final PrimitiveMap map = new PrimitiveMap();

   /**
    * Creates empty <code>MapMessageImpl<code>
    */
   public MapMessageImpl()
   {
      super();
   }

   /**
    * Creates <code>MapMessageImpl</code> and copies its header, properties and body from <code>msg<code>
    * 
    * @param msg
    * @throws JMSException
    */
   public MapMessageImpl(MapMessage msg) throws JMSException
   {
      super(msg);

      Enumeration e = msg.getMapNames();
      while (e.hasMoreElements())
      {
         String name = (String) e.nextElement();
         setObject(name, msg.getObject(name));
      }
   }

   /**
    * @see javax.jms.MapMessage#getBoolean(java.lang.String)
    */
   public boolean getBoolean(String name) throws JMSException
   {
      return map.getBoolean(name);
   }

   /**
    * @see javax.jms.MapMessage#getByte(java.lang.String)
    */
   public byte getByte(String name) throws JMSException
   {
      return map.getByte(name);
   }

   /**
    * @see javax.jms.MapMessage#getShort(java.lang.String)
    */
   public short getShort(String name) throws JMSException
   {
      return map.getShort(name);
   }

   /**
    * @see javax.jms.MapMessage#getChar(java.lang.String)
    */
   public char getChar(String name) throws JMSException
   {
      return map.getChar(name);
   }

   /**
    * @see javax.jms.MapMessage#getInt(java.lang.String)
    */
   public int getInt(String name) throws JMSException
   {
      return map.getInt(name);
   }

   /**
    * @see javax.jms.MapMessage#getLong(java.lang.String)
    */
   public long getLong(String name) throws JMSException
   {
      return map.getLong(name);
   }

   /**
    * @see javax.jms.MapMessage#getFloat(java.lang.String)
    */
   public float getFloat(String name) throws JMSException
   {
      return map.getFloat(name);
   }

   /**
    * @see javax.jms.MapMessage#getDouble(java.lang.String)
    */
   public double getDouble(String name) throws JMSException
   {
      return map.getDouble(name);
   }

   /**
    * @see javax.jms.MapMessage#getString(java.lang.String)
    */
   public String getString(String name) throws JMSException
   {
      return map.getString(name);
   }

   /**
    * @see javax.jms.MapMessage#getBytes(java.lang.String)
    */
   public byte[] getBytes(String name) throws JMSException
   {
      return map.getBytes(name);
   }

   /**
    * @see javax.jms.MapMessage#getObject(java.lang.String)
    */
   public Object getObject(String name) throws JMSException
   {
      return map.getObject(name);
   }

   /**
    * @see javax.jms.MapMessage#getMapNames()
    */
   public Enumeration getMapNames() throws JMSException
   {
      return map.getNames();
   }

   /**
    * @see javax.jms.MapMessage#setBoolean(java.lang.String, boolean)
    */
   public void setBoolean(String name, boolean value) throws JMSException
   {
      checkBodyWriteable();
      map.setBoolean(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setByte(java.lang.String, byte)
    */
   public void setByte(String name, byte value) throws JMSException
   {
      checkBodyWriteable();
      map.setByte(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setShort(java.lang.String, short)
    */
   public void setShort(String name, short value) throws JMSException
   {
      checkBodyWriteable();
      map.setShort(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setChar(java.lang.String, char)
    */
   public void setChar(String name, char value) throws JMSException
   {
      checkBodyWriteable();
      map.setChar(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setInt(java.lang.String, int)
    */
   public void setInt(String name, int value) throws JMSException
   {
      checkBodyWriteable();
      map.setInt(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setLong(java.lang.String, long)
    */
   public void setLong(String name, long value) throws JMSException
   {
      checkBodyWriteable();
      map.setLong(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setFloat(java.lang.String, float)
    */
   public void setFloat(String name, float value) throws JMSException
   {
      checkBodyWriteable();
      map.setFloat(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setDouble(java.lang.String, double)
    */
   public void setDouble(String name, double value) throws JMSException
   {
      checkBodyWriteable();
      map.setDouble(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setString(java.lang.String, java.lang.String)
    */
   public void setString(String name, String value) throws JMSException
   {
      checkBodyWriteable();
      map.setString(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setBytes(java.lang.String, byte[])
    */
   public void setBytes(String name, byte[] value) throws JMSException
   {
      checkBodyWriteable();
      map.setBytes(name, value);
   }

   /**
    * @see javax.jms.MapMessage#setBytes(java.lang.String, byte[], int, int)
    */
   public void setBytes(String name, byte[] value, int offset, int length)
            throws JMSException
   {

      checkBodyWriteable();

      if (value == null
               || length < 0
               || offset < 0
               || (offset + length) > value.length)
      {
         throw new IllegalArgumentException();
      }
      byte[] valueToSet = new byte[length];
      int i = 0;
      while (length > 0)
      {
         valueToSet[i++] = value[offset++];
         length--;
      }
      map.setBytes(name, valueToSet);
   }

   /**
    * @see javax.jms.MapMessage#setObject(java.lang.String, java.lang.Object)
    */
   public void setObject(String name, Object value) throws JMSException
   {
      checkBodyWriteable();
      map.setObject(name, value);
   }

   /**
    * @see javax.jms.MapMessage#itemExists(java.lang.String)
    */
   public boolean itemExists(String name) throws JMSException
   {
      return map.containsKey(name);
   }

   // Non-standard methods

   /**
    * Sets message body in read-only mode.
    * 
    * @throws JMSException
    */
   void resetBody() throws JMSException
   {
      setBodyReadOnly();
   }

}