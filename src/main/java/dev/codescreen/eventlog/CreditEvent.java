package dev.codescreen.eventlog;

import dev.codescreen.models.repositories.User;
import dev.codescreen.models.enums.ResponseCode;

/**
 * A class that represents a debit event that can be processed.
 *
 */
public class CreditEvent implements Event {

    private User user;
    private float amount;

    private ResponseCode responseCode;
    public CreditEvent(User user, float amount) {
        this.user = user;
        this.amount = amount;

        if (user.canCredit(amount)) {
            this.responseCode = ResponseCode.APPOROVED;
        } else {
            this.responseCode = ResponseCode.DECLINDED;
        }
    }

    @Override
    public void process() {
        System.out.println("Processing Credit Event");
        if (responseCode == ResponseCode.APPOROVED)
            user.credit(amount);
    }

    @Override
    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
