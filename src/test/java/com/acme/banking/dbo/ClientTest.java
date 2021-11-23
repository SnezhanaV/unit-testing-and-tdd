package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {

    @Test
    public void shouldCreateThrowWhenIdIsNegative() {
        //Given
        final int clientId = -1;
        final String dummyName = "client name";

        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(clientId, dummyName)
        );

        //Then
        assertTrue(thrown.getMessage().contains("Id is negative"));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    public void shouldCreateThrowWhenNameIsNull(String clientName) {
        //Given
        final int dummyId = 1;

        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyId, clientName)
        );

        //Then
        assertTrue(thrown.getMessage().contains("name is empty"));
    }

    @Test
    public void shouldCreateThrowWhenNameIsEmpty() {
        //Given
        final int dummyId = 1;
        final String clientName = "";

        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyId, clientName)
        );

        //Then
        assertTrue(thrown.getMessage().contains("name is empty"));

    }

    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @Test
    public void shouldNotAddAccountWhenAccountIsNull() {
        //Given
        Client sut = new Client(1, "dummy client name");

        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sut.addAccount(null)
        );

        //Then
        assertTrue(thrown.getMessage().contains("Account is empty"));
    }
}
