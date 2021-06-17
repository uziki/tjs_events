package com.tsystems.javaschool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("eventsView")
@ApplicationScoped
@Singleton
public class UpdatePageBean implements Serializable {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    @Push(channel = "channel")
    private PushContext pushContext;

    private List<Event> events;

    public void onUpdate(List<Event> events) {
        this.events = events;
        log.info("updated");
        pushContext.send("updated");
    }

    public List<Event> getEvents() {
        log.info("Message on screen");
        return events;
    }
}