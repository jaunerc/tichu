package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SavePlayerPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.DealCardsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersBy;

@Service
@RequiredArgsConstructor
public class DealCardsService implements DealCardsUseCase {

    private static final int FIRST_CARD_DEAL_LIMIT = 8;

    private final FindGameByIdPort findGameByIdPort;
    private final SavePlayerPort savePlayerPort;

    @Override
    public List<Card> dealCards(UUID gameId, UUID playerId) {
        var game = findGameByIdPort.findGameById(gameId);
        var player = findPlayerById(game, playerId);

        var updatedPlayer = Player.Builder.of(player)
                .firstEightCardsReceived(true)
                .build();

        savePlayerPort.savePlayer(updatedPlayer);

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
