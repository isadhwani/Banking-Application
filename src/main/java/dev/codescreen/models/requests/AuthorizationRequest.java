package dev.codescreen.models.requests;


import dev.codescreen.models.responses.TransactionAmount;

public class AuthorizationRequest {
    private String userId;
    private String messageId;

    private TransactionAmount transactionAmount;

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public TransactionAmount getTransactionAmount() {
        return new TransactionAmount(transactionAmount.getAmount(), transactionAmount.getCurrency(), transactionAmount.getDebitOrCredit());
    }
}