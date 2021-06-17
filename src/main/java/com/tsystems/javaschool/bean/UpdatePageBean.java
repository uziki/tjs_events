package com.tsystems.javaschool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Singleton
public class UpdatePageBean implements Serializable {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    @Push(channel = "channel")
    private PushContext pushContext;

    private List<Event> eventList = new ArrayList<Event>(){{
        add(new Event("12:00", "10.06", "Petrov", "Boleet"));
    }};

    public void onUpdate(List<Event> events) {
        eventList = events;
        log.info("updated");
        pushContext.send("updated");
    }

    public List<Event> getProductList() {
        return eventList;
    }
}