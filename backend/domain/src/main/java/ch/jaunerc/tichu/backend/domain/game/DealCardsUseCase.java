package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.input.DealCardsInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SendGameStateOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersBy;

@Service
@RequiredArgsConstructor
public class DealCardsUseCase implements DealCardsInputPort {

    private static final int FIRST_CARD_DEAL_LIMIT = 8;

    private final FindGameByIdOutputPort findGameByIdPort;
    private final SavePlayerOutputPort savePlayerOutputPort;
    private final SendGameStateOutputPort sendGameStateOutputPort;

    @Override
    public List<Card> dealCards(UUID gameId, UUID playerId) {
        var game = findGameByIdPort.findGameById(gameId);
        var player = findPlayerById(game, playerId);

        var updatedPlayer = Player.Builder.of(player)
                .firstEightCardsReceived(true)
                .build();

        savePlayerOutputPort.savePlayer(updatedPlayer);
        sendGameStateOutputPort.sendGameState(game);

        return cardsForPlayer(player);
    }

    private static List<Card> cardsForPlayer(Player player) {
        if (player.firstEightCardsReceived()) { // TODO here is an additional guard needed so that the player receives the cards only if the game is in the right state
            return player.cards();
        }
        return player.cards().stream()
                .limit(FIRST_CARD_DEAL_LIMIT)
                .toList();
    }

    private static Player findPlayerById(Game game, UUID playerId) {
        return listOfPlayersBy(game).stream()
                .filter(p -> p.uuid().equals(playerId))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
