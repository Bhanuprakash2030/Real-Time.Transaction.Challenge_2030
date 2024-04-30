package dev.codescreen.cs.endpoints.authorization;

import dev.codescreen.cs.endpoints.AmountDto;
import dev.codescreen.cs.endpoints.DebitCredit;
import dev.codescreen.cs.service.AccountCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthorizationRestController {

    private final AccountCommandService service;

    @PutMapping("/{messageId}")
    public CompletableFuture<AuthorizationResponse> authorize(@PathVariable String messageId, @RequestBody AuthorizationRequest request) {
        return service.executeAuthorizeCommand(request)
                .thenApply(it -> AuthorizationResponse.builder()
                        .userId(it.getUserId())
                        .messageId(messageId)
                        .balance(AmountDto.builder()
                                .amount(it.getAmount().toString())
                                .currency(it.getCurrency())
                                .debitOrCredit(DebitCredit.DEBIT)
                                .build())
                        .build());
    }
}
