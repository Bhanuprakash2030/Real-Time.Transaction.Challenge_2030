package dev.codescreen.cs.events;

import lombok.Data;

@Data
public class AuthorizationEvent {
    private String userId;
    private String messageId;
    private AmountEvent transactionAmount;

    @Data
    public static class AmountEvent {
        private String amount;
        private String currency;
        private DebitCredit debitOrCredit;
    }

    public enum DebitCredit {
        DEBIT, CREDIT;
    }
}
