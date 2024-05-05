package dev.codescreen.models.responses;

/**
 * A class that represents a response to a load request.
 */
public class LoadResponse extends ALoadResponse {
    private String userId;
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
