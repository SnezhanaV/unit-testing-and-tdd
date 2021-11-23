package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public class Processing {
    private AccountRepository accounts;

    /**
     * DI
     */
    public Processing(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public void createClient(String name) {
        if ((name == "") || (name == null)) throw new IllegalArgumentException("Client name is empty");
        accounts.save(new Client(name));
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        //....
        return accounts.getAccountsByClientId(clientId);
        //....
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account from = accounts.getAccountById(fromAccountId);
        Account to = accounts.getAccountById(toAccountId);

        from.setAmount( from.getAmount() - amount );
        to.setAmount( to.getAmount() + amount );

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
