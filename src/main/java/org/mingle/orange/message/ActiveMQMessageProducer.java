/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.message;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class ActiveMQMessageProducer {

    public static void main(String[] args) {
        ConnectionFactory cf = new ActiveMQConnectionFactory();
        Connection conn = null;
        Session session = null;
        try {
            conn = cf.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue("messageQueue");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText("hello");
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null)
                    session.close();
                if (conn != null)
                    conn.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
