package com.tsystems.javaschool.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

@Singleton
public class EventReceiver implements Serializable {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public List<Event> getEvents() {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8081/hospital/events/today");
        ObjectMapper mapper = new ObjectMapper();
        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            log.error("failed to get JSON");
            //TODO ExceptionHandler
            throw new RuntimeException("Failed : HTTP error status : " + response.getStatus());
        }
        String JSONevents = response.getEntity(String.class);
        log.info("JSON received");
        List<Event> events = null;
        try {
            events = mapper.readValue(JSONevents, new TypeReference<List<Event>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return events;
    }

}
