package dev.codescreen.models.responses;


import dev.codescreen.models.enums.DebitOrCredit;


/**
 * Represents the amount of a transaction. with an amount, currency, and debit or credit.

 */
public class Amount {
    public Amount() {
    }

    public Amount(String amount, String currency, DebitOrCredit debitOrCredit) {
        this.amount = amount;
        this.currency = currency;
        this.debitOrCredit = debitOrCredit;
    }

    private String amount;
    private String currency;
    private DebitOrCredit debitOrCredit;

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public DebitOrCredit getDebitOrCredit() {
        return debitOrCredit;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDebitOrCredit(DebitOrCredit debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", debitOrCredit=" + debitOrCredit +
                '}';
    }

}