package dev.codescreen.cs.projections;

import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.data.AccountRepository;
import dev.codescreen.cs.events.AuthorizationEvent;
import dev.codescreen.cs.events.LoadEvent;
import dev.codescreen.cs.query.FindAccountQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class AccountProjection {

    private final AccountRepository accountRepository;
    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void onLoadEvent(LoadEvent event) {
        handleEvent(event.getUserId(), event.getTransactionAmount().getAmount(), BigDecimal::add);
    }

    @EventHandler
    public void onAuthorizationEvent(AuthorizationEvent event) {
        handleEvent(event.getUserId(), event.getTransactionAmount().getAmount(), BigDecimal::subtract);
    }

    @QueryHandler
    public Account onAccountQuery(FindAccountQuery query) {
        return accountRepository.findById(query.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    private void handleEvent(String userId, String transactionAmount, BiFunction<BigDecimal, BigDecimal, BigDecimal> biFunction) {
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("No account found for id: " + userId));
        BigDecimal transactionAmountDecimal = new BigDecimal(transactionAmount);
        BigDecimal newAmount = biFunction.apply(account.getAmount(), transactionAmountDecimal);
        account.setAmount(newAmount);
        queryUpdateEmitter.emit(FindAccountQuery.class, query -> query.getAccountId().equals(account.getUserId()), account);
        accountRepository.save(account);
    }
}
