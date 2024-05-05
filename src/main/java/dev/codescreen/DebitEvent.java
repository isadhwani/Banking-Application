package dev.codescreen;

import dev.codescreen.models.User;

public class DebitEvent implements Event {

    private User user;
    private float amount;
    public DebitEvent(User user, float amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public void process() {
        System.out.println("Processing Debit Event");
        user.debit(amount);
    }
}
