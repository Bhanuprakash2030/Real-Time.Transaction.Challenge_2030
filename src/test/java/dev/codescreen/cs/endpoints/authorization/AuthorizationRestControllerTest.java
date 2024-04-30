package dev.codescreen.cs.endpoints.authorization;

import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.endpoints.AmountDto;
import dev.codescreen.cs.endpoints.DebitCredit;
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
import static org.mockito.Mockito.when;

class AuthorizationRestControllerTest {

    @InjectMocks
    AuthorizationRestController sut;

    @Mock
    AccountCommandService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authorize() throws Exception {
        String mid = UUID.randomUUID().toString();
        AuthorizationRequest request = AuthorizationRequest.builder()
                .messageId(mid)
                .userId("123")
                .transactionAmount(AmountDto.builder()
                        .debitOrCredit(DebitCredit.DEBIT)
                        .amount("123")
                        .currency("USD")
                        .build())
                .build();
        when(service.executeAuthorizeCommand(request)).thenReturn(CompletableFuture.completedFuture(Account.builder()
                        .amount(new BigDecimal("123"))
                .build()));
        AuthorizationResponse result = sut.authorize(mid, request).get();
        assertThat(result).isNotNull();
        assertThat(result.getMessageId()).isEqualTo(mid);
    }
}