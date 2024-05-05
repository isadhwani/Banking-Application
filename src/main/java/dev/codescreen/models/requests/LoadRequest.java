package dev.codescreen.models.requests;


import dev.codescreen.models.responses.Amount;

public class LoadRequest {


    /**
     * {
     *   "messageId": "55210c62-e480-asdf-bc1b-e991ac67FSAC",
     *   "userId": "2226e2f9-ih09-46a8-958f-d659880asdfD",
     *   "transactionAmount": {
     *     "amount": "100.23",
     *     "currency": "USD",
     *     "debitOrCredit": "CREDIT"
     *   }
     * }
     */

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
