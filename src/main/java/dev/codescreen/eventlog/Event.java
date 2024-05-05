package dev.codescreen.eventlog;

import dev.codescreen.models.enums.ResponseCode;

/**
 * An interface that represents an event that can be processed.
 */
public interface Event {
    public void process();

    public ResponseCode getResponseCode();
}
