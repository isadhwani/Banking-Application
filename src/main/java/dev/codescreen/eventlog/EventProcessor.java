package dev.codescreen.eventlog;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that processes events and stores them in a list.
 * Events are processed by calling the process method on the event and then adding the event to the list.
 *
 *  - List<Event> events: A list of events that have been processed.
 */
public class EventProcessor {

    List<Event> events = new ArrayList<>();

    public void processEvent(Event e) {
        events.add(e);
        e.process();
    }

    public Event getLatestEvent() {
        if(events.size() == 0) {
            return null;
        }
        return this.events.get(events.size() - 1);
    }
}
