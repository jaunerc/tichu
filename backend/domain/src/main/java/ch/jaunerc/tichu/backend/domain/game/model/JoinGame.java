package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.UUID;

public record JoinGame(UUID gameId, UUID playerId, PlayerSeatId playerSeatId) {
}
