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

   private ResourceAdapter resourceAdapter;

   public void validate() throws InvalidPropertyException
   {
      logger.info("validating activation spec");
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