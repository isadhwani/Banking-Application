package dev.codescreen.eventlog;

import dev.codescreen.models.enums.ResponseCode;

/**
 * An interface that represents an event that can be processed.
 */
public interface Event {
    /**
     * Process the event by executing logic depending on the event type.
     */
    public void process();

    /**
     *  Determine if an event was successful or not.
     * @return ResponseCode - the response code of the event
     */
    public ResponseCode getResponseCode();
}
