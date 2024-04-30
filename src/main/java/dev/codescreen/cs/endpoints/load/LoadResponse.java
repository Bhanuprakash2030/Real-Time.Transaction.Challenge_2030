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
public class LoadResponse {
    private String messageId;
    private String userId;
    private AmountDto balance;
}
