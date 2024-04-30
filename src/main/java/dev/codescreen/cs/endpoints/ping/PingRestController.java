package dev.codescreen.cs.endpoints.ping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PingRestController {

    @GetMapping("/ping")
    @Operation(summary = "Allows consumers to check if the application is running properly")
    @ApiResponses(
            @ApiResponse(
                    description = "Current server LocalDateTime wrapped inside PingDto",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PingDto.class))
            )
    )
    public PingDto ping() {
        return PingDto.builder()
                .serverTime(LocalDateTime.now().toString())
                .build();
    }
}
