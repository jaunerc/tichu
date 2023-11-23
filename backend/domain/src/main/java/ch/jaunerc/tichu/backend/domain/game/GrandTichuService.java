package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GrandTichuCall;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SavePlayerPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.GrandTichuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersBy;
import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersOrderedBySeatPosition;

@Service
@RequiredArgsConstructor
public class GrandTichuService implements GrandTichuUseCase {

    private final FindGameByIdPort findGameByIdPort;
    private final SavePlayerPort savePlayerPort;

    @Override
    public GrandTichuCall grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled) {
        var game = findGameByIdPort.findGameById(gameId);
        var player = findPlayerInGame(game, playerId);

        savePlayerPort.savePlayer(Player.Builder.of(player)
                .grandTichuCalled(isGrandTichuCalled)
                .build());

        return new GrandTichuCall(getPlayerNumber(game, playerId), isGrandTichuCalled);
    }

    private static Player findPlayerInGame(Game game, UUID playerId) {
        return listOfPlayersBy(game).stream()
                .filter(player -> player.uuid().equals(playerId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int getPlayerNumber(Game game, UUID playerId) {
        return getPlayerIndex(game, playerId) + 1; // TODO better use an enum to determine the players seat position
    }

    private static int getPlayerIndex(Game game, UUID playerId) {
        return listOfPlayersOrderedBySeatPosition(game).stream()
                .map(Player::uuid)
                .toList()
                .indexOf(playerId);
    }
}
