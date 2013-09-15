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

   public String getHost()
   {
      return host;
   }

   public void setHost(String host)
   {
      this.host = host;
   }

}