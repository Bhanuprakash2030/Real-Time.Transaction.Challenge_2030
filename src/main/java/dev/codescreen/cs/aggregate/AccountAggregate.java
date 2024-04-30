package dev.codescreen.cs.aggregate;

import dev.codescreen.cs.endpoints.DebitCredit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private String userId;

    private BigDecimal amount;

    private String currency;

    private DebitCredit debitCredit;

}
