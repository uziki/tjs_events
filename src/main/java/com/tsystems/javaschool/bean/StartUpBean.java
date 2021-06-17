package com.tsystems.javaschool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.naming.NamingException;

@Startup
@Singleton
public class StartUpBean {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private EventReciever eventReciever;

    @Inject
    private UpdatePageBean updatePageBean;

    @PostConstruct
    private void init()  {
        log.info("StartUpBean started");
        try {
            eventReciever.init();
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

}
