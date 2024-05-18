package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.ChangeGamePhaseUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.ReadyPlayerUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.ShuffleDeckUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.streamOfPlayersBy;

@Service
@RequiredArgsConstructor
public class ReadyPlayerService implements ReadyPlayerUseCase {

    public static final int ALL_PLAYERS_READY_COUNT = 4;

    private final FindGameByIdOutputPort findGameByIdPort;
    private final ChangeGamePhaseUseCase changeGamePhaseUseCase;
    private final ShuffleDeckUseCase shuffleDeckUseCase;

    @Override
    public int updateReadyPlayers(UUID gameId) {
        int readyPlayerCount = countNonNullPlayers(findGameByIdPort.findGameById(gameId));
        if (readyPlayerCount == ALL_PLAYERS_READY_COUNT) {
            changeGamePhaseUseCase.changeGamePhase(gameId);
            shuffleDeckUseCase.shuffleDeck(gameId);
        }
        return readyPlayerCount;
    }

    private static int countNonNullPlayers(Game game) {
        return (int) Stream.concat(streamOfPlayersBy(game.firstTeam()), streamOfPlayersBy(game.secondTeam()))
                .filter(Objects::nonNull)
                .count();
    }
}
