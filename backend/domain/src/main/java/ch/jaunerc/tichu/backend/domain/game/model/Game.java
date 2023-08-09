package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.UUID;

public record Game(UUID gameId, Team firstTeam, Team secondTeam, GamePhase gamePhase) {
}
