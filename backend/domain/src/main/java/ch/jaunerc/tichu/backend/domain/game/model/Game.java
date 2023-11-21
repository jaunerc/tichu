package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.UUID;

public record Game(UUID gameId, Team firstTeam, Team secondTeam, GamePhase gamePhase) {

    public static final class Builder {
        private final UUID gameId;
        private GamePhase gamePhase;
        private Team firstTeam;
        private Team secondTeam;

        public Builder(UUID gameId, GamePhase gamePhase) {
            this.gameId = gameId;
            this.gamePhase = gamePhase;
        }

        public static Builder of(Game game) {
            return new Builder(game.gameId, game.gamePhase)
                    .firstTeam(game.firstTeam)
                    .secondTeam(game.secondTeam);
        }

        public Builder firstTeam(Team firstTeam) {
            this.firstTeam = firstTeam;
            return this;
        }

        public Builder secondTeam(Team secondTeam) {
            this.secondTeam = secondTeam;
            return this;
        }

        public Builder gamePhase(GamePhase gamePhase) {
            this.gamePhase = gamePhase;
            return this;
        }

        public Game build() {
            return new Game(
                    gameId,
                    firstTeam,
                    secondTeam,
                    gamePhase
            );
        }
    }
}
