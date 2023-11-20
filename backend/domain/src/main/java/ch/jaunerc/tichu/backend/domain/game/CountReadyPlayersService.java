package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.CountReadyPlayersUseCase;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CountReadyPlayersService implements CountReadyPlayersUseCase {

    private final FindGameByIdPort findGameByIdPort;

    @Override
    public int countReadyPlayers(UUID gameId) {
        return countNonNullPlayers(findGameByIdPort.findGameById(gameId));
    }

    private static int countNonNullPlayers(Game game) {
        return (int) Stream.concat(streamOfPlayersBy(game.firstTeam()), streamOfPlayersBy(game.secondTeam()))
                .filter(Objects::nonNull)
                .count();
    }

    private static Stream<Player> streamOfPlayersBy(Team team) {
        if (team == null) {
            return Stream.of();
        }
        return Stream.of(team.firstPlayer(), team.secondPlayer());
    }
}
