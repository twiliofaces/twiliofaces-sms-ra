package org.twiliofaces.smsra.jms;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Enumeration for <code>Collection</code> represented with an iterator.
 * 
 * @link http://mockejb.sourceforge.net/
 * @author Dimitar Gospodinov
 */
public class CollectionEnumeration implements Enumeration
{
   private Iterator it;

   /**
    * Creates enumeration for the specified iterator.
    * 
    * @param it
    */
   CollectionEnumeration(Iterator it)
   {
      this.it = it;
   }

   /**
    * @see java.util.Enumeration#hasMoreElements()
    */
   public boolean hasMoreElements()
   {
      return it.hasNext();
   }

   /**
    * @see java.util.Enumeration#nextElement()
    */
   public Object nextElement()
   {
      return it.next();
   }
}
