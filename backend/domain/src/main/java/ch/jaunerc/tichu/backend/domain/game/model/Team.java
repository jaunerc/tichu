package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.UUID;

public record Team(UUID teamId, Player firstPlayer, Player secondPlayer, int points) {

    public static Team withFirstPlayer(Team team, Player firstPlayer) {
        return new Team(
                team.teamId,
                firstPlayer,
                team.secondPlayer,
                team.points
        );
    }

    public static Team withSecondPlayer(Team team, Player secondPlayer) {
        return new Team(
                team.teamId,
                team.firstPlayer,
                secondPlayer,
                team.points
        );
    }
}
