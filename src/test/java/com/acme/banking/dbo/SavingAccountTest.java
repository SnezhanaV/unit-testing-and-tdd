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

        Client client = new Client(clientId, clientName);

        //When
        SavingAccount account = new SavingAccount(1, client, amount);

        //Then
        assertThat(client.getAccounts())
                .containsOnly(account);
    }



    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        final double clientAmount = 100;
        Client client = new Client(clientId, clientName);
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(clientId, client, clientAmount);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("SavingAccount store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientAmount, sut.getAmount()),
                () -> assertEquals(client, sut.getClient())
        );

        //Hamcrest:
//        assertThat(sut,
//                allOf(
//                        hasProperty("id", notNullValue()),
//                        hasProperty("id", equalTo(clientId)),
//                        hasProperty("name", is(clientName))
//                ));

        //AssertJ:
        assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }
}
