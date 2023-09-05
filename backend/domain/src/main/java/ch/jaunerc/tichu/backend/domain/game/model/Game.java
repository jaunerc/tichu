package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.UUID;

public record Game(UUID gameId, Team firstTeam, Team secondTeam, GamePhase gamePhase) {

    public static Game updateFirstTeam(Game game, Team updatedFirstTeam) {
        return new Game(
                game.gameId,
                updatedFirstTeam,
                game.secondTeam,
                game.gamePhase
        );
    }

    public static Game updateSecondTeam(Game game, Team updatedSecondTeam) {
        return new Game(
                game.gameId,
                game.firstTeam,
                updatedSecondTeam,
                game.gamePhase);
    }
}
