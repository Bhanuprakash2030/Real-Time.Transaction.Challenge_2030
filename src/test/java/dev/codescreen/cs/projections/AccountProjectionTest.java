package dev.codescreen.cs.projections;

import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.data.AccountRepository;
import dev.codescreen.cs.events.AuthorizationEvent;
import dev.codescreen.cs.events.LoadEvent;
import dev.codescreen.cs.query.FindAccountQuery;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AccountProjectionTest {

    @InjectMocks
    AccountProjection sut;

    @Mock
    AccountRepository accountRepository;

    @Mock
    QueryUpdateEmitter queryUpdateEmitter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void onLoadEvent() {
        when(accountRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> sut.onLoadEvent(new LoadEvent()));

        Account account = Account.builder()
                .userId("uid")
                .amount(new BigDecimal(1234))
                .build();
        when(accountRepository.findById(anyString())).thenReturn(Optional.of(account));
        LoadEvent loadEvent = new LoadEvent();
        loadEvent.setUserId("uid");
        LoadEvent.AmountEvent transactionAmount = new LoadEvent.AmountEvent();
        transactionAmount.setAmount("123");
        loadEvent.setTransactionAmount(transactionAmount);
        sut.onLoadEvent(loadEvent);
    }

    @Test
    void onAuthorizationEvent() {
        when(accountRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> sut.onAuthorizationEvent(new AuthorizationEvent()));

        Account account = Account.builder()
                .userId("uid")
                .amount(new BigDecimal(1234))
                .build();
        when(accountRepository.findById(anyString())).thenReturn(Optional.of(account));
        AuthorizationEvent authorizationEvent = new AuthorizationEvent();
        authorizationEvent.setUserId("uid");
        AuthorizationEvent.AmountEvent transactionAmount = new AuthorizationEvent.AmountEvent();
        transactionAmount.setAmount("123");
        authorizationEvent.setTransactionAmount(transactionAmount);
        sut.onAuthorizationEvent(authorizationEvent);
    }

    @Test
    void onAccountQuery() {
        FindAccountQuery query = FindAccountQuery.builder()
                .accountId("123")
                .build();
        when(accountRepository.findById(anyString())).thenReturn(Optional.of(Account.builder().build()));
        Account result = sut.onAccountQuery(query);
        assertThat(result).isNotNull();
    }
}