package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamJoiner {

    public static Game joinFirstOrSecondTeam(Game game, Player player) {
        return tryToAddThePlayerToTheTeam(game.firstTeam(), player)
                .map(updateFirstTeamOf(game))
                .or(() -> tryToAddThePlayerToTheTeam(game.secondTeam(), player)
                        .map(updateSecondTeamOf(game)))
                .orElseThrow(() -> new IllegalStateException("Unable to join the game with the id=" + game.gameId()));

    }

    private static Function<Team, Game> updateFirstTeamOf(Game game) {
        return team -> Game.Builder.of(game)
                .firstTeam(team)
                .build();
    }

    private static Function<Team, Game> updateSecondTeamOf(Game game) {
        return team -> Game.Builder.of(game)
                .secondTeam(team)
                .build();
    }

    private static Optional<Team> tryToAddThePlayerToTheTeam(Team team, Player player) {
        return teamHasCapacity(team) ? Optional.of(addPlayer(team, player)) : Optional.empty();
    }

    private static Team addPlayer(Team team, Player player) {
        if (team.firstPlayer() == null) {
            return Team.withFirstPlayer(team, player);
        }

        return Team.withSecondPlayer(team, player);
    }

    private static boolean teamHasCapacity(Team team) {
        return team == null
                || team.firstPlayer() == null
                || team.secondPlayer() == null;
    }
}
