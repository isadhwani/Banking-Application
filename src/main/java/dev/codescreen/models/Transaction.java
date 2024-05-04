package dev.codescreen.models;

import dev.codescreen.models.responses.TransactionAmount;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {


    public Transaction(String transactionId, String amount, TransactionAmount transactionAmount) {
        this.id = transactionId;
        this.amount = amount;
        this.transactionAmount = transactionAmount;
    }


    @Id
    private String id;

    private String userId;

    private String amount;

    private TransactionAmount transactionAmount;

    private String responseCode;


    public Transaction() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
