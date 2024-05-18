package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.input.ChangeGamePhaseInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.input.ReadyPlayerInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.input.ShuffleDeckInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.streamOfPlayersBy;

@Service
@RequiredArgsConstructor
public class ReadyPlayerUseCase implements ReadyPlayerInputPort {

    public static final int ALL_PLAYERS_READY_COUNT = 4;

    private final FindGameByIdOutputPort findGameByIdPort;
    private final ChangeGamePhaseInputPort changeGamePhaseInputPort;
    private final ShuffleDeckInputPort shuffleDeckInputPort;

    @Override
    public int updateReadyPlayers(UUID gameId) {
        int readyPlayerCount = countNonNullPlayers(findGameByIdPort.findGameById(gameId));
        if (readyPlayerCount == ALL_PLAYERS_READY_COUNT) {
            changeGamePhaseInputPort.changeGamePhase(gameId);
            shuffleDeckInputPort.shuffleDeck(gameId);
        }
        return readyPlayerCount;
    }

    private static int countNonNullPlayers(Game game) {
        return (int) Stream.concat(streamOfPlayersBy(game.firstTeam()), streamOfPlayersBy(game.secondTeam()))
                .filter(Objects::nonNull)
                .count();
    }
}
