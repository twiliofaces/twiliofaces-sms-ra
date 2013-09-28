twiliofaces-sms-ra
=============

This is a JMS resource adapter, witch enable your application to receive twilio sms using a message driven bean. 

Simple instructions to use:

- add twiliofaces-sms-ra.rar to your jboss-as-7.x/standalone/deployments
- create a new web app, with twiliofaces, org.jboss.netty... dependencies:

`

    <?xml version="1.0" encoding="UTF-8"?>
    <jboss-deployment-structure>
    	<deployment>
    		<dependencies>
    			<module name="deployment.twiliofaces-sms-ra-0.0.1-SNAPSHOT.rar" />
    		</dependencies>
    	</deployment>
     </jboss-deployment-structure>
`

- in the WEB-INF/web.xml add http tunnel servlet:

`
    <servlet>
		<servlet-name>NettyTunnelingServlet</servlet-name>
		<servlet-class>org.jboss.netty.channel.socket.http.HttpTunnelingServlet</servlet-class>
		<init-param>
			<param-name>endpoint</param-name>
			<param-value>local:0.0.0.0</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>NettyTunnelingServlet</servlet-name>
		<url-pattern>/tunnel</url-pattern>
	</servlet-mapping>
	`
	
- create a mdb wich use twiliofaces-sms-ra adapter:


`

    import java.util.Enumeration;
    
    import javax.ejb.MessageDriven;
    import javax.jms.JMSException;
    import javax.jms.MapMessage;
    import javax.jms.Message;
    import javax.jms.MessageListener;
    
    import org.jboss.ejb3.annotation.ResourceAdapter;
    @ResourceAdapter("twiliofaces-sms-ra-0.0.1-SNAPSHOT.rar")
    @MessageDriven
    public class SmsReceiverMDB implements MessageListener
    {

    public void onMessage(Message smsMessage)
    {
      System.out.println("we received a new twilio sms message!");
      if (smsMessage instanceof MapMessage)
      {
         MapMessage mess = (MapMessage) smsMessage;
         try
         {
            Enumeration<?> e = mess.getMapNames();
            while (e.hasMoreElements())
            {
               String key = (String) e.nextElement();
               String value = mess.getString(key);
               System.out.println(key + ": " + value);
            }
         }
         catch (JMSException e1)
         {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }

    }

    }
    }

	`


A complete example of use:

https://github.com/twiliofaces/twf-jms

