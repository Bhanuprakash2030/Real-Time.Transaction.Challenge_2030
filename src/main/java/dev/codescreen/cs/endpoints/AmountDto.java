package dev.codescreen.cs.endpoints;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountDto {
    private String amount;
    private String currency;
    private DebitCredit debitOrCredit;
}
