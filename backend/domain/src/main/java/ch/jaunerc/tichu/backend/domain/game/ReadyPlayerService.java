package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.ChangeGamePhaseUseCase;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.ReadyPlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReadyPlayerService implements ReadyPlayerUseCase {

    public static final int ALL_PLAYERS_READY_COUNT = 4;
    private final FindGameByIdPort findGameByIdPort;
    private final ChangeGamePhaseUseCase changeGamePhaseUseCase;

    @Override
    public int updateReadyPlayers(UUID gameId) {
        int readyPlayerCount = countNonNullPlayers(findGameByIdPort.findGameById(gameId));
        if (readyPlayerCount == ALL_PLAYERS_READY_COUNT) {
            changeGamePhaseUseCase.changeGamePhase(gameId);
        }
        return readyPlayerCount;
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
