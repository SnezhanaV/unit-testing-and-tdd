package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private AccountRepository accountRepoStub;
    private Processing sut;

    @BeforeEach
    public void setUp(){
        accountRepoStub = mock(AccountRepository.class);
        sut = new Processing(accountRepoStub);
    }

    @Test
    public void shouldCallSaveWhenCreateClient(){
        //When
        sut.createClient("dummy client name");

        //Then
        verify(accountRepoStub).save(any(Client.class));
    }

    @Test
    public void shouldGetErrorWhenCreateClientWithNullName(){
        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sut.createClient("")
        );

        //Then
        assertTrue(thrown.getMessage().contains("Client name is empty"));
    }

    @Test
    public void shouldSaveAccountWhenCreateClient(){
        //Given
        final Account accountMock=mock(Account.class);
        when(accountRepoStub.save((Client) any())).thenReturn(accountMock);

        //When
        final Account account = sut.createClient("dummy client name");

        //Then
        assertAll(
                () -> assertEquals(accountMock, account)
        );
        verify(accountRepoStub).save(new Client("dummy client name"));
    }
}
