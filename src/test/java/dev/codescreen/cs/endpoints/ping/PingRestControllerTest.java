package dev.codescreen.cs.endpoints.ping;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PingRestControllerTest {

    @Test
    void ping() {
        PingRestController sut = new PingRestController();
        PingDto result = sut.ping();
        assertThat(result).isNotNull();
        assertThat(result.getServerTime()).isNotNull();
    }
}