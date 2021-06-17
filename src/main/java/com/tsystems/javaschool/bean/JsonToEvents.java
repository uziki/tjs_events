package com.tsystems.javaschool.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Singleton;
import java.io.Serializable;
import java.util.List;

@Singleton
public class JsonToEvents implements Serializable {
    public List<Event> getEventList(String message) {
        ObjectMapper mapper = new ObjectMapper();
        List<Event> list = null;
        try {
            list = mapper.readValue(message, new TypeReference<List<Event>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

}
