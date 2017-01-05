package com.bollen.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args){
		
		ConnectionFactory connectionFactory;
		
		Connection connection = null;
		
		Session session;
		
		Destination destination;
		
		MessageConsumer messageConsumer;
		
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("HelloWorld");
			
			messageConsumer = session.createConsumer(destination);
			
			while(true){
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
				if(textMessage != null){
					System.out.println("收到的消息:" + textMessage.getText());
				}else {
					break;
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
