package com.tsystems.javaschool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.*;
import java.util.List;

@Singleton
public class MyMessageListener implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private QueueSession session;
    private List<Event> eventList = null;

    @Inject
    private JsonToEvents jsonToEvents;

    @Inject
    private UpdatePageBean updatePageBean;


    public MyMessageListener(){}


    public MyMessageListener(QueueSession session) {
        log.info("MyMessageListener started");
        this.session = session;
    }

    public void onMessage(Message message) {
        log.info("MyMessageListener onMessage trigger");
        TextMessage m = (TextMessage) message;
        try {
            System.out.println(m.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            try {
                session.rollback();
            } catch (JMSException jmsException) {
                jmsException.printStackTrace();
            }
        }
        try {
            eventList = jsonToEvents.getEventList(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

        updatePageBean.onUpdate(eventList);
    }
}
