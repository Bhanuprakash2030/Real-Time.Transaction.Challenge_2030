package dev.codescreen.cs.endpoints.load;

import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.endpoints.AmountDto;
import dev.codescreen.cs.endpoints.DebitCredit;
import dev.codescreen.cs.endpoints.authorization.AuthorizationRequest;
import dev.codescreen.cs.endpoints.authorization.AuthorizationResponse;
import dev.codescreen.cs.service.AccountCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoadRestControllerTest {

    @InjectMocks
    LoadRestController sut;

    @Mock
    AccountCommandService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void load() throws Exception {
        String mid = UUID.randomUUID().toString();
        LoadRequest request = LoadRequest.builder()
                .messageId(mid)
                .userId("123")
                .transactionAmount(AmountDto.builder()
                        .debitOrCredit(DebitCredit.DEBIT)
                        .amount("123")
                        .currency("USD")
                        .build())
                .build();
        when(service.executeLoadCommand(request)).thenReturn(CompletableFuture.completedFuture(Account.builder()
                        .amount(new BigDecimal("123"))
                .build()));
        LoadResponse result = sut.load(mid, request).get();
        assertThat(result).isNotNull();
        assertThat(result.getMessageId()).isEqualTo(mid);
    }
}