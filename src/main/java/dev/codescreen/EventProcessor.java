package dev.codescreen;

import java.util.ArrayList;
import java.util.List;

public class EventProcessor {

    List<Event> events = new ArrayList<>();

    public void processEvent(Event e) {
        e.process();
        events.add(e);
    }
}
