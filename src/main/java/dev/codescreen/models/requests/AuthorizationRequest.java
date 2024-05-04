package dev.codescreen.models.requests;


public class AuthorizationRequest {
    private String userId;
    private String messageId;

//    private Amount transactionAmount;

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

//    public Amount getAmount() {
//        return new Amount(transactionAmount.getAmount(), transactionAmount.getCurrency(), transactionAmount.getDebitOrCredit());
//    }
}