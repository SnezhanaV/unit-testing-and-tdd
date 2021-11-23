package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ProcessingTest {
    private AccountRepository accountRepoStub = mock(AccountRepository.class);

    @Test
    public void shouldCallSaveWhenCreateClient(){

}

    @Test
    public void shouldGetErrorWhenCreateClientWithNullName(){
        //Given
        Processing sut = new Processing(accountRepoStub);

        //When
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sut.createClient("")
        );

        //Then
        assertTrue(thrown.getMessage().contains("Client name is empty"));
    }
}
