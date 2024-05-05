package dev.codescreen.eventlog;

import dev.codescreen.models.repositories.User;
import dev.codescreen.models.enums.ResponseCode;

/**
 * A class that represents a debit event that can be processed.
 */
public class DebitEvent implements Event {

    private User user;
    private float amount;

    private ResponseCode responseCode;
    public DebitEvent(User user, float amount, ResponseCode resposeCode) {
        this.user = user;
        this.amount = amount;
        this.responseCode = resposeCode;
    }

    @Override
    public void process() {
        user.debit(amount);
    }

    @Override
    public ResponseCode getResponseCode() {
        return responseCode;
    }


    public User getUser() {
        return new User(user);
    }

    public float getAmount() {
        return amount;
    }

}
