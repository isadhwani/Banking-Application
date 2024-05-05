package dev.codescreen.models;

//import dev.codescreen.models.responses.Amount;

import dev.codescreen.models.responses.Amount;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {


    public Transaction(String transactionId, String amount, Amount transactionAmount) {
        this.id = transactionId;
        this.amount = amount;
       // this.transactionAmount = transactionAmount;
    }


    @Id
    private String id;

    private String userId;

    private String amount;

//    private Amount transactionAmount;



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
