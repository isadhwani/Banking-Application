package dev.codescreen.models.requests;


import dev.codescreen.models.responses.Amount;

public class AuthorizationRequest {

    public AuthorizationRequest(String userId, String messageId, Amount amount) {
        this.userId = userId;
        this.messageId = messageId;
        this.transactionAmount = amount;
    }
    private String userId;
    private String messageId;
    private Amount transactionAmount;

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public Amount getTransactionAmount() {
        return new Amount(transactionAmount.getAmount(), transactionAmount.getCurrency(), transactionAmount.getDebitOrCredit());
    }

    @Override
    public String toString() {
        return "AuthorizationRequest{" +
                "userId='" + userId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", amount=" + transactionAmount +
                '}';
    }
}