package dev.codescreen.models.responses;

//import dev.codescreen.models.Amount;

import jakarta.persistence.GeneratedValue;

public class LoadResponse extends ALoadResponse {
    /**
     * LoadResponse{
     * description:
     *
     * The result of an load.
     * userId*	string
     * minLength: 1
     * messageId*	string
     * minLength: 1
     * balance*	Amount{ [Jump to definition]
     * amount*	string
     * minLength: 1
     *
     * The amount in the denomination of the currency. For example, $1 = '1.00'
     * currency*	string
     * minLength: 1
     * debitOrCredit*	DebitCreditstring
     *
     * Debit or Credit flag for the network transaction. A Debit deducts funds from a user. A credit adds funds to a user.
     * Enum:
     * [ DEBIT, CREDIT ]
     *
     * }
     *
     * }
     */

    private String userId;
    @GeneratedValue
    private String messageId;
    private Amount balance;

    public LoadResponse(String userId, Amount balance) {
        this.userId = userId;
        this.balance = balance;
        messageId = java.util.UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "LoadResponse{" + "userId='" + userId + '\'' + ", messageId='" + messageId + '\'' + ", balance=" + balance + '}';
    }
}
