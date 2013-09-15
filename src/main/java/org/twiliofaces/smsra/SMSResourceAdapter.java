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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.TransactionSupport;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkManager;
import javax.transaction.xa.XAResource;

@Connector(
         eisType = "twiliofaces-ra", reauthenticationSupport = false,
         transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction)
public class SMSResourceAdapter implements ResourceAdapter
{

   private static final Logger logger = Logger
            .getLogger(SMSResourceAdapter.class.getName());
   private WorkManager workManager;
   private Map<String, Work> workers;

   public void start(BootstrapContext context)
            throws ResourceAdapterInternalException
   {
      logger.info("starting twiliofaces resource adapter");
      workManager = context.getWorkManager();
      workers = new HashMap<String, Work>();
   }

   public void stop()
   {
      logger.info("stop");
   }

   public void endpointActivation(MessageEndpointFactory messageEndpointFactory,
            ActivationSpec activationSpec) throws ResourceException
   {
      logger.info("endpoint activation");
      String uid = getUid(activationSpec);
      Work work = workers.get(uid);
      if (work == null)
      {
         work = new SMSListener((SMSActivationSpec) activationSpec, messageEndpointFactory,
                  workManager);
         workers.put(uid, work);
      }

      workManager.startWork(work);
   }

   public void endpointDeactivation(MessageEndpointFactory messageEndpointFactory,
            ActivationSpec activationSpec)
   {
      logger.info("endpoint deactivation");
      String uid = getUid(activationSpec);
      if (workers.containsKey(uid))
      {
         workers.get(uid).release();
      }
   }

   private String getUid(ActivationSpec spec)
   {
      SMSActivationSpec smsActivationSpec = (SMSActivationSpec) spec;
      String uid = "0.0.0.0";
      if (smsActivationSpec.getHost() != null && !smsActivationSpec.getHost().isEmpty())
      {
         uid = smsActivationSpec.getHost();

      }
      return uid;
   }

   public XAResource[] getXAResources(ActivationSpec[] specs)
            throws ResourceException
   {
      return new XAResource[0];
   }

   public int hashCode()
   {
      int result = 17;
      if (this.getClass().getSimpleName() != null)
         result += 31 * result + 7
                  * this.getClass().getSimpleName().hashCode();
      else
         result += 31 * result + 7;
      return result;
   }

   public boolean equals(Object other)
   {
      if (other == null)
         return false;
      if (other == this)
         return true;
      if (!(other.getClass().getSimpleName().equals(this.getClass()
               .getSimpleName())))
         return false;
      return true;
   }

}