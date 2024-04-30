package dev.codescreen.cs.endpoints.authorization;

import dev.codescreen.cs.endpoints.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {
    private String userId;
    private String messageId;
    private AmountDto transactionAmount;
}
