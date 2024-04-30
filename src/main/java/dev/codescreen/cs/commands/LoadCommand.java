package dev.codescreen.cs.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadCommand {

    @TargetAggregateIdentifier
    private String id;

    private String userId;

    private BigDecimal amount;

    private String currency;
}
