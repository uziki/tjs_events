package com.tsystems.javaschool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

@Startup
@Singleton
public class Listener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private QueueConnection connection;
    private QueueSession session;
    private QueueReceiver receiver;
    private List<Event> events;

    @Inject
    private EventReceiver eventReceiver;

    @Inject
    private UpdatePageBean updatePageBean;

    @PostConstruct
    public void init() throws NamingException, JMSException {
        log.info("Event Receiver started");
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "events_queue");
        props.put("connectionFactoryNames", "queueCF");

        Context context = new InitialContext(props);
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
        Queue queue = (Queue) context.lookup("js-queue");

        connection = connectionFactory.createQueueConnection();
        connection.start();
        session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
        receiver = session.createReceiver(queue);

        final MessageListener messageListener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                log.info("Message received");
                try {
                    session.commit();
                } catch (JMSException e) {
                    log.error("Session not closed");
                    try {
                        session.rollback();
                    } catch (JMSException jmsException) {
                        log.error("Cant rollback session");
                    }
                }
                events = eventReceiver.getEvents();
                updatePageBean.onUpdate(events);
            }
        };
        receiver.setMessageListener(messageListener);
    }

    @PreDestroy
    public void destroy() throws JMSException {
        log.info("Event Receiver destroyed");
        receiver.close();
        session.close();
        connection.close();
    }
}
