package com.example.orientation19;

import java.util.ArrayList;
import java.util.List;

public class EventPOJO {
    private String event_title;
    private String desc;
    private String time;
    private String venue;

    public EventPOJO(String event_title, String desc, String time, String venue) {
        this.event_title = event_title;
        this.desc = desc;
        this.time = time;
        this.venue = venue;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    private List<EventPOJO> events;

    private void sampleData(){
        events = new ArrayList<>();
        events.add(new EventPOJO("Title","Desciprtion","12:00","LHC"));
        events.add(new EventPOJO("Title","Desciprtion","12:00","LHC"));
        events.add(new EventPOJO("Title","Desciprtion","12:00","LHC"));
        events.add(new EventPOJO("Title","Desciprtion","12:00","LHC"));

    }
}