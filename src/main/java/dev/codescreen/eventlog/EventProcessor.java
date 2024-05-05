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

    /**
     * Returns the latest event that has been processed. Used checking if the most recent event was approved or denied
     * @return Event: The latest event that has been processed
     */
    public Event getLatestEvent() {
        if(events.isEmpty()) {
            return null;
        }
        return this.events.get(events.size() - 1);
    }

    /**
     * Returns a list of all events that have been processed. Used for testing
     * @return List<Event>: A list of all events that have been processed
     */
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
}
