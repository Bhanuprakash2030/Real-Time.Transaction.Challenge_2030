package dev.codescreen.cs.endpoints.load;

import dev.codescreen.cs.endpoints.AmountDto;
import dev.codescreen.cs.endpoints.DebitCredit;
import dev.codescreen.cs.service.AccountCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadRestController {

    private final AccountCommandService service;

    @PutMapping("/load/{messageId}")
    public CompletableFuture<LoadResponse> load(@PathVariable String messageId, @RequestBody LoadRequest request) {
        return service.executeLoadCommand(request)
                .thenApply(it -> LoadResponse.builder()
                        .userId(it.getUserId())
                        .messageId(messageId)
                        .balance(AmountDto.builder()
                                .amount(it.getAmount().toString())
                                .currency(it.getCurrency())
                                .debitOrCredit(DebitCredit.CREDIT)
                                .build())
                        .build());
    }

}
