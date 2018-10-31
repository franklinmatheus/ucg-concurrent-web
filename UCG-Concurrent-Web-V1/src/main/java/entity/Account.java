/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franklin
 */
public class Account {
    private double balance;
    private List<Integer> history;
    
    public Account() {
        this.balance = 0;
        this.history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Integer> getHistory() {
        return history;
    }

    public void setHistory(List<Integer> history) {
        this.history = history;
    }
    
    public void addTransaction(Integer transaction) {
        this.history.add(transaction);
    }
}
