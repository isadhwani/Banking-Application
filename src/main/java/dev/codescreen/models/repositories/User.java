package dev.codescreen.models.repositories;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {


    public User(String id, float balance) {
        this.id = id;
        this.balance = balance;
    }
    @Id
    private String id;

    private float balance;

    /**
     * Copy constructor
     * @param u - User to copy
     */
    public User(User u) {
        this.id = u.id;
        this.balance = u.balance;
    }

    public String getId() {
        return this.id;
    }
    public float getBalance() {
        return balance;
    }

    public boolean canCredit(float creditAmount) {
        return creditAmount <= balance;
    }

    public void credit(float creditAmount) {
        balance -= creditAmount;
    }

    public void debit(float debitAmount) {
        balance += debitAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return id == other.id && balance == other.getBalance() ;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", balance=" + balance + '}';
    }
}
