package org.twiliofaces.smsra.jms;

import java.util.*;
import javax.jms.*;

/**
 * Map that sotre name-value pairs, where the values are <code>null</code> or instances of String, Boolean, Byte,
 * Character, Short, Integer, Long, Float, Double or byte[].
 * <p>
 * Conversion between types is supported as specified in <code>javax.jms.StreamMessage</code>
 * 
 * @link http://mockejb.sourceforge.net/
 * @author Dimitar Gospodinov
 */
public class PrimitiveMap
{

   private final Map map = new HashMap();

   private byte[] copyBytes(byte[] source)
   {
      if (source == null)
      {
         return null;
      }
      else
      {
         return (byte[]) source.clone();
      }
   }

   public boolean getBoolean(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Boolean)
      {
         return ((Boolean) value).booleanValue();
      }
      if (value == null || value instanceof String)
      {
         return Boolean.valueOf((String) value).booleanValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Boolean value!");
   }

   public byte getByte(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Byte)
      {
         return ((Byte) value).byteValue();
      }
      if (value == null || value instanceof String)
      {
         return Byte.valueOf((String) value).byteValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Byte value!");
   }

   public byte[] getBytes(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);
      if (value == null || value instanceof byte[])
      {
         return copyBytes((byte[]) value);
      }
      throw new MessageFormatException(
               name + " does not represent valid byte[] value!");
   }

   public char getChar(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);
      if (value == null)
      {
         throw new NullPointerException();
      }
      if (value instanceof Character)
      {
         return ((Character) value).charValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Char value!");
   }

   public short getShort(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Byte || value instanceof Short)
      {
         return ((Number) value).shortValue();
      }
      if (value == null || value instanceof String)
      {
         return Short.valueOf((String) value).shortValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Short value!");
   }

   public int getInt(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Byte
               || value instanceof Short
               || value instanceof Integer)
      {
         return ((Number) value).intValue();
      }
      if (value == null || value instanceof String)
      {
         return Integer.valueOf((String) value).intValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Integer value!");
   }

   public long getLong(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Byte
               || value instanceof Short
               || value instanceof Integer
               || value instanceof Long)
      {

         return ((Number) value).longValue();
      }
      if (value == null || value instanceof String)
      {
         return Long.valueOf((String) value).longValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Long value!");
   }

   public float getFloat(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Float)
      {
         return ((Float) value).floatValue();
      }
      if (value == null || value instanceof String)
      {
         return Float.valueOf((String) value).floatValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Float value!");
   }

   public double getDouble(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);

      if (value instanceof Float || value instanceof Double)
      {
         return ((Number) value).doubleValue();
      }
      if (value == null || value instanceof String)
      {
         return Double.valueOf((String) value).doubleValue();
      }
      throw new MessageFormatException(
               name + " does not represent valid Double value!");
   }

   public String getString(String name) throws JMSException
   {
      checkName(name);
      Object value = map.get(name);
      if (value == null)
      {
         return null;
      }
      if (value instanceof Boolean
               || value instanceof Byte
               || value instanceof Short
               || value instanceof Character
               || value instanceof Integer
               || value instanceof Long
               || value instanceof Float
               || value instanceof Double
               || value instanceof String)
      {

         return value.toString();
      }
      throw new MessageFormatException(
               name + " does not represent valid String value!");
   }

   public Object getObject(String name) throws JMSException
   {
      checkName(name);
      Object result = map.get(name);
      if (result instanceof byte[])
      {
         result = copyBytes((byte[]) result);
      }
      return result;
   }

   public void setBoolean(String name, boolean value) throws JMSException
   {

      checkName(name);
      map.put(name, new Boolean(value));
   }

   public void setByte(String name, byte value) throws JMSException
   {
      checkName(name);
      map.put(name, new Byte(value));
   }

   public void setBytes(String name, byte[] value)
   {
      checkName(name);
      map.put(name, copyBytes(value));
   }

   public void setChar(String name, char value)
   {
      checkName(name);
      map.put(name, new Character(value));
   }

   public void setShort(String name, short value) throws JMSException
   {
      checkName(name);
      map.put(name, new Short(value));
   }

   public void setInt(String name, int value) throws JMSException
   {
      checkName(name);
      map.put(name, new Integer(value));
   }

   public void setLong(String name, long value) throws JMSException
   {
      checkName(name);
      map.put(name, new Long(value));
   }

   public void setFloat(String name, float value) throws JMSException
   {
      checkName(name);
      map.put(name, new Float(value));
   }

   public void setDouble(String name, double value) throws JMSException
   {

      checkName(name);
      map.put(name, new Double(value));
   }

   public void setString(String name, String value) throws JMSException
   {

      checkName(name);
      map.put(name, value);
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.jms.Message#setObjectProperty(java.lang.String, java.lang.Object)
    */
   public void setObject(String name, Object value) throws JMSException
   {

      checkName(name);
      if (!(value == null
               || value instanceof byte[]
               || value instanceof Boolean
               || value instanceof Byte
               || value instanceof Character
               || value instanceof Short
               || value instanceof Integer
               || value instanceof Long
               || value instanceof Float
               || value instanceof Double
               || value instanceof String))
      {

         throw new MessageFormatException("Incorrect object type!");
      }
      if (value instanceof byte[])
      {
         value = copyBytes((byte[]) value);
      }
      map.put(name, value);
   }

   private void checkName(String name)
   {
      if (name == null || name.length() == 0)
      {
         throw new IllegalArgumentException("Name must be non-empty string!");
      }
   }

   public void clear()
   {
      map.clear();
   }

   public boolean containsKey(String key)
   {
      checkName(key);
      return map.containsKey(key);
   }

   public Enumeration getNames()
   {
      return new CollectionEnumeration(map.keySet().iterator());
   }

}
