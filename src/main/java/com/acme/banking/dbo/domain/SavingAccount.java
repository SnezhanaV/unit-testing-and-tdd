package com.acme.banking.dbo.domain;
import java.lang.Math;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (client == null) throw new IllegalArgumentException("Client is null");
        if ((Math.signum(id)==-1) || (Math.signum(id)==0)) throw new IllegalArgumentException("Id is negative or 0");
//        if (amount < 0) throw new IllegalArgumentException("Amount is negative");

        this.id = id;
        this.client = client;
        this.amount = amount;
        client.addAccount(this);
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }
}
