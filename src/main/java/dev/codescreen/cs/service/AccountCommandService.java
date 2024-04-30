package dev.codescreen.cs.service;

import dev.codescreen.cs.commands.AuthorizationCommand;
import dev.codescreen.cs.commands.LoadCommand;
import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.endpoints.authorization.AuthorizationRequest;
import dev.codescreen.cs.endpoints.load.LoadRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final CommandGateway gateway;

    public CompletableFuture<Account> executeLoadCommand(LoadRequest request) {
        return gateway.send(LoadCommand.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.getUserId())
                .amount(new BigDecimal(request.getTransactionAmount().getAmount()))
                .currency(request.getTransactionAmount().getCurrency())
                .build());
    }

    public CompletableFuture<Account> executeAuthorizeCommand(AuthorizationRequest request) {
        return gateway.send(AuthorizationCommand.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.getUserId())
                .amount(new BigDecimal(request.getTransactionAmount().getAmount()))
                .currency(request.getTransactionAmount().getCurrency())
                .build());
    }
}
