package com.singhpora.testutilities;


import java.util.ArrayList;
import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Adapted from the example here:
 * https://blogs.oracle.com/soaproactive/jms-step-3-using-the-queuereceivejava-sample-program-to-read-a-message-from-a-jms-queue
 * 
 * The copyright note below is retained as is. 
 */

/**
* This example shows how to establish a connection to
* and receive messages from a JMS queue. The classes in this
* package operate on the same JMS queue. Run the classes together to
* witness messages being sent and received, and to browse the queue
* for messages.  This class is used to receive and remove messages
* from the queue.
*
* @author Copyright (c) 1999-2005 by BEA Systems, Inc. All Rights Reserved.
*/
public class QueueReceive implements MessageListener
{
 // Defines the JNDI context factory.
 public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
 // Defines the JMS connection factory for the queue.
 public final static String JMS_FACTORY="weblogic.jms.XAConnectionFactory";

 private QueueConnectionFactory qconFactory;
 private QueueConnection qcon;
 private QueueSession qsession;
 private QueueReceiver qreceiver;
 private Queue queue;
 private boolean quit = false;
 
 int expectedMessageCount;

    public void setExpectedMessageCount(int expectedMessageCount) {
        this.expectedMessageCount = expectedMessageCount;
    }

    public int getExpectedMessageCount() {
        return expectedMessageCount;
    }

    public void setDequeuedMessages(ArrayList<String> dequeuedMessages) {
        this.dequeuedMessages = dequeuedMessages;
    }

    public ArrayList<String> getDequeuedMessages() {
        return dequeuedMessages;
    }
    ArrayList<String> dequeuedMessages = new ArrayList<String>();
 
 private String messageReceived ;
 
/**
 * Message listener interface.
 * @param msg  message
 */
 public void onMessage(Message msg)
 {
    try {
     String msgText;
     if (msg instanceof TextMessage) {
       msgText = ((TextMessage)msg).getText();
     } else {
       msgText = msg.toString();
     }
     System.out.println("Message Received: "+ msgText );
     
       synchronized(this) {
         messageReceived = msgText;
         quit = true;         
         this.notifyAll(); // Notify main thread to quit
       }
    } catch (JMSException jmse) {
        System.err.println("An exception occurred: "+jmse.getMessage());
    }
 }
 /**
  * Creates all the necessary objects for receiving
  * messages from a JMS queue.
  *
  * @param   ctx    JNDI initial context
  * @param    queueName    name of queue
  * @exception NamingException if operation cannot be performed
  * @exception JMSException if JMS fails to initialize due to internal error
  */
 public void init(Context ctx, String queueName)
    throws NamingException, JMSException
 {
    init(ctx, queueName, 1);
    
 }
 
public void init(Context ctx, String queueName, int expectedMessageCount)
       throws NamingException, JMSException {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    qreceiver = qsession.createReceiver(queue);
    
    this.setExpectedMessageCount(expectedMessageCount);
    qreceiver.setMessageListener(this);
    qcon.start();
}
    
    
/**
  * Closes JMS objects.
  * @exception JMSException if JMS fails to close objects due to internal error
  */
 public void close()throws JMSException
 {
    qreceiver.close();
    qsession.close();
    qcon.close();
 }
/**
 * main() method.
 *
 * @param args  WebLogic Server URL
 * @exception  Exception if execution fails
 */
 public static void main(String[] args) throws Exception {
    
    InitialContext ic = getInitialContext("t3://localhost:7001");
    QueueReceive qr = new QueueReceive();
    qr.init(ic, "jms.TextQueue");
    System.out.println(
        "JMS Ready To Receive Messages (To quit, send a \"quit\" message).");
    // Wait until a "quit" message has been received.
    synchronized(qr) {
     while (! qr.quit) {
       try {
         qr.wait();
       } catch (InterruptedException ie) {}
     }
    }
    qr.close();
 }
 private static InitialContext getInitialContext(String url)
    throws NamingException
 {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, url);
    return new InitialContext(env);
 }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setMessageReceived(String messageReceived) {
        this.messageReceived = messageReceived;
    }

    public String getMessageReceived() {
        return messageReceived;
    }
}
