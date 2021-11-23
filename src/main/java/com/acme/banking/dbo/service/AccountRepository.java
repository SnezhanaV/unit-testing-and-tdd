package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public interface AccountRepository {
    Collection<Account> getAccountsByClientId(int clientId);

    void save(Account account);
    void save(Client client);

    Account getAccountById(int fromAccountId);


}
