package ch.jaunerc.tichu.backend.websocket;

import java.util.UUID;

public record ReadyRequestDto(UUID playerId) {
}
