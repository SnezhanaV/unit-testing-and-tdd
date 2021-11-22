package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Test suite")
public class SavingAccountTest {

    @Test
    public void shouldCliendHasAccountWhenAddAccount() {
        //Given
        final int clientId = 1;
        final String clientName = "client name";
        final double amount = 100;
        final int accountId = 1;

        Client sut = new Client(clientId, clientName);

        //When
        SavingAccount account = new SavingAccount(accountId, sut, amount);

        //Then
        assertThat(sut.getAccounts())
                .containsOnly(account);
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //Given
        final int accountId = 1;
        final double amount = 100;
        Client client = new Client(1, "dummy client name");

        //When
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        assumeTrue(sut != null);

        //Then
        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", accountId)
                .hasFieldOrPropertyWithValue("client", client)
                .hasFieldOrPropertyWithValue("amount", amount);
    }

    @Test
    public void shouldNotSaveAccountWhenClientIsNull() {
        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(1, null, 100)
        );

        //Then
        assertTrue(thrown.getMessage().contains("Client is null"));
    }

    @Test
    public void shouldNotSaveAccountWhenIdIsNegative() {
        //Given
        final int accountId = -1;
        Client client = new Client(1, "dummy client name");

        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, 100)
        );

        //Then
        assertTrue(thrown.getMessage().contains("Id is negative or 0"));
    }

}
