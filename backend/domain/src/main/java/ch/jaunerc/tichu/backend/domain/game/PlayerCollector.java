package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerCollector {

    public static List<Player> listOfPlayersBy(Game game) {
        return Stream.concat(
                streamOfPlayersBy(game.firstTeam()),
                streamOfPlayersBy(game.secondTeam())
        ).filter(Objects::nonNull).toList();
    }

    public static Stream<Player> streamOfPlayersBy(Team team) {
        if (team == null) {
            return Stream.of();
        }
        return Stream.of(team.firstPlayer(), team.secondPlayer());
    }
}
