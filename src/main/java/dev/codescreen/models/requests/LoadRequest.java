package dev.codescreen.models.requests;


import dev.codescreen.models.responses.Amount;

/**
 * A class that represents a load request. This class is used to load money into a user's account.

 */
public class LoadRequest {

    private String messageId;

    private String userId;

    private Amount transactionAmount;

    LoadRequest() {

    }

    public LoadRequest(String userId, String messageId,  Amount transactionAmount) {
        this.messageId = messageId;
        this.userId = userId;
        this.transactionAmount = transactionAmount;
    }

    public Amount getTransactionAmount() {
        return new Amount(transactionAmount.getAmount(), transactionAmount.getCurrency(), transactionAmount.getDebitOrCredit());
    }

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

}
