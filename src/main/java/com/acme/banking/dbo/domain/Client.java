package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if ((name == "") || (name == null)) throw new IllegalArgumentException("Client name is empty");
        if ((Math.signum(id)==-1) || (Math.signum(id)==0)) throw new IllegalArgumentException("Id is negative or 0");


        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        if ((name == "") || (name == null)) throw new IllegalArgumentException("Client name is empty");
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("Account is empty");

        accounts.add(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && name.equals(client.name) && accounts.equals(client.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accounts);
    }
}
