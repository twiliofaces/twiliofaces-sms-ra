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

@Connector(eisType = "udp", reauthenticationSupport = false, transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction)
public class SMSResourceAdapter implements ResourceAdapter
{

   private static final Logger logger = Logger
            .getLogger(SMSResourceAdapter.class.getName());
   private WorkManager workManager;
   private Map<String, Work> workers;

   public void start(BootstrapContext context)
            throws ResourceAdapterInternalException
   {
      logger.info("starting resource adapter");
      workManager = context.getWorkManager();
      workers = new HashMap<String, Work>();
   }

   public void stop()
   {
      logger.info("stop");
   }

   public void endpointActivation(MessageEndpointFactory endpointFactory,
            ActivationSpec spec) throws ResourceException
   {
      logger.info("endpoint activation");

      SMSActivationSpec udpActivationSpec = (SMSActivationSpec) spec;

      Work work = workers.get(udpActivationSpec.getHost());
      if (work == null)
      {
         work = new SMSListener(udpActivationSpec, endpointFactory,
                  workManager);
         workers.put(udpActivationSpec.getHost(), work);
      }

      workManager.startWork(work);
   }

   public void endpointDeactivation(MessageEndpointFactory endpointFactory,
            ActivationSpec spec)
   {
      logger.info("endpoint deactivation");

      SMSActivationSpec udpActivationSpec = (SMSActivationSpec) spec;
      if (workers.containsKey(udpActivationSpec.getHost()))
      {
         workers.get(udpActivationSpec.getHost()).release();
      }
   }

   public XAResource[] getXAResources(ActivationSpec[] specs)
            throws ResourceException
   {
      return new XAResource[0];
   }

   /**
    * Returns a hash code value for the object.
    * 
    * @return A hash code value for this object.
    */
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

   /**
    * Indicates whether some other object is equal to this one.
    * 
    * @param other The reference object with which to compare.
    * @return true If this object is the same as the obj argument, false otherwise.
    */
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