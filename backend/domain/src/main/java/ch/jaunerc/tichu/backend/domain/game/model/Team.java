package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.UUID;

public record Team(UUID teamId, Player firstPlayer, Player secondPlayer, int points) {

    public final static class Builder {
        private final UUID teamId;
        private Player firstPlayer;
        private Player secondPlayer;
        private int points;

        public Builder(UUID teamId) {
            this.teamId = teamId;
        }

        public static Builder of(Team team) {
            return new Builder(team.teamId)
                    .firstPlayer(team.firstPlayer)
                    .secondPlayer(team.secondPlayer)
                    .points(team.points);
        }

        public Builder firstPlayer(Player firstPlayer) {
            this.firstPlayer = firstPlayer;
            return this;
        }

        public Builder secondPlayer(Player secondPlayer) {
            this.secondPlayer = secondPlayer;
            return this;
        }

        public Builder points(int points) {
            this.points = points;
            return this;
        }

        public Team build() {
            return new Team(
                    this.teamId,
                    this.firstPlayer,
                    this.secondPlayer,
                    this.points
            );
        }
    }
}
