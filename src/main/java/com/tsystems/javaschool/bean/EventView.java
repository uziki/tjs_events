package com.tsystems.javaschool.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "eventView")
@SessionScoped
public class EventView implements Serializable {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private UpdatePageBean updatePageBean;

    public List<Event> getEvents() {
        log.info("EventView getEvents");
        return updatePageBean.getProductList();
    }
}
