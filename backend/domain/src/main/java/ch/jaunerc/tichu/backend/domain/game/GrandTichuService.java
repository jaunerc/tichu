package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SavePlayerPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.GrandTichuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersBy;

@Service
@RequiredArgsConstructor
public class GrandTichuService implements GrandTichuUseCase {

    private final FindGameByIdPort findGameByIdPort;
    private final SavePlayerPort savePlayerPort;

    @Override
    public Game grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled) {
        var game = findGameByIdPort.findGameById(gameId);
        var player = findPlayerInGame(game, playerId);

        savePlayerPort.savePlayer(Player.Builder.of(player)
                .grandTichuCalled(isGrandTichuCalled)
                .build());

        return findGameByIdPort.findGameById(gameId);
    }

    private static Player findPlayerInGame(Game game, UUID playerId) {
        return listOfPlayersBy(game).stream()
                .filter(player -> player.uuid().equals(playerId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
