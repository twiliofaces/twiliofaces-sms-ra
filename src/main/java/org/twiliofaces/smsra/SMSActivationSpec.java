package org.twiliofaces.smsra;

import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.InvalidPropertyException;
import javax.resource.spi.ResourceAdapter;

public class SMSActivationSpec implements ActivationSpec
{
   private static final Logger logger = Logger
            .getLogger(SMSActivationSpec.class.getName());

   private String host;
   private ResourceAdapter resourceAdapter;

   public void setPort(String host)
   {
      this.host = host;
   }

   public String getHost()
   {
      return host;
   }

   public void validate() throws InvalidPropertyException
   {
      logger.info("validating activation spec");
      if (host == null)
      {
         throw new InvalidPropertyException("host property can not be null.");
      }

   }

   public ResourceAdapter getResourceAdapter()
   {
      return resourceAdapter;
   }

   public void setResourceAdapter(ResourceAdapter ra) throws ResourceException
   {
      this.resourceAdapter = ra;
   }

}