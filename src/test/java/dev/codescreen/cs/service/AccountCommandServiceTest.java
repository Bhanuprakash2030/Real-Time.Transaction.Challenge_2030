package dev.codescreen.cs.service;

import dev.codescreen.cs.commands.LoadCommand;
import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.endpoints.AmountDto;
import dev.codescreen.cs.endpoints.authorization.AuthorizationRequest;
import dev.codescreen.cs.endpoints.load.LoadRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountCommandServiceTest {

    @InjectMocks
    AccountCommandService sut;

    @Mock
    CommandGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeLoadCommand() {
        when(gateway.send(any(LoadCommand.class))).thenReturn(CompletableFuture.completedFuture(Account.builder().build()));
        CompletableFuture<Account> result = sut.executeLoadCommand(LoadRequest.builder()
                .transactionAmount(AmountDto.builder()
                        .amount("123")
                        .build())
                .build());
        assertThat(result).isNull();
    }

    @Test
    void executeAuthorizeCommand() {
        when(gateway.send(any(LoadCommand.class))).thenReturn(CompletableFuture.completedFuture(Account.builder().build()));
        CompletableFuture<Account> result = sut.executeAuthorizeCommand(AuthorizationRequest.builder()
                .transactionAmount(AmountDto.builder()
                        .amount("123")
                        .build())
                .build());
        assertThat(result).isNull();
    }
}