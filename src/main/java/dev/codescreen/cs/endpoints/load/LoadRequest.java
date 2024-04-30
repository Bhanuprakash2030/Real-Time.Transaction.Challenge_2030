package dev.codescreen.cs.endpoints.load;

import dev.codescreen.cs.endpoints.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadRequest {
    private String userId;
    private String messageId;
    private AmountDto transactionAmount;
}
