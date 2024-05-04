package dev.codescreen.models.responses;


import dev.codescreen.models.enums.ResponseCode;

public class AuthorizationResponse {

    public AuthorizationResponse() {

    }
    public AuthorizationResponse(String userId, String messageId, ResponseCode responseCode, TransactionAmount balance) {
        this.userId = userId;
        this.messageId = messageId;
        this.responseCode = responseCode;
        this.balance = balance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public void setBalance(TransactionAmount balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public TransactionAmount getBalance() {
        return new TransactionAmount(balance.getAmount(), balance.getCurrency(), balance.getDebitOrCredit());
    }

    private String userId;

    private String messageId;

    private ResponseCode responseCode;

    private TransactionAmount balance;

    @Override
    public String toString() {
        return "AuthorizationResponse{" + "userId='" + userId + '\'' + ", messageId='" + messageId + '\'' + ", responseCode=" + responseCode + ", balance=" + balance + '}';
    }

}