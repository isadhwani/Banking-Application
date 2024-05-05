package dev.codescreen.models.responses;

import dev.codescreen.models.enums.ResponseCode;

/**
 * A class that represents a response to an authorization request coming from a user
 */
public class AuthorizationResponse extends AAuthorizationResponse {

    public AuthorizationResponse() {

    }
    public AuthorizationResponse(String userId, ResponseCode responseCode, Amount balance) {
        this.userId = userId;
        this.messageId = java.util.UUID.randomUUID().toString();
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

    public void setBalance(Amount balance) {
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

    public Amount getBalance() {
        return new Amount(balance.getAmount(), balance.getCurrency(), balance.getDebitOrCredit());
    }

    private String userId;

    private String messageId;

    private ResponseCode responseCode;

    private Amount balance;

    @Override
    public String toString() {
        return "AuthorizationResponse{" + "userId='" + userId + '\'' + ", messageId='" + messageId + '\'' + ", responseCode=" + responseCode + ", balance=" + balance + '}';
    }

}