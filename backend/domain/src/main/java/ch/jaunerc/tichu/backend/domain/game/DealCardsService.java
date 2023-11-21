package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.DealCardsUseCase;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersBy;

@Service
@RequiredArgsConstructor
public class DealCardsService implements DealCardsUseCase {

    private static final int FIRST_CARD_DEAL_LIMIT = 8;

    private final FindGameByIdPort findGameByIdPort;

    @Override
    public List<Card> dealCards(UUID gameId, UUID playerId) {
        return cardsForPlayer(
                findGameByIdPort.findGameById(gameId),
                playerId);
    }

    private static List<Card> cardsForPlayer(Game game, UUID playerId) {
        var cards = allPlayerCards(listOfPlayersBy(game), playerId);

        switch (game.gamePhase()) {
            case DEALING_CARDS -> {
                return cards.stream()
                        .limit(FIRST_CARD_DEAL_LIMIT)
                        .toList();
            }
            case FIRST_EIGHT_CARDS_ARE_DEALT -> {
                return cards;
            }
            default -> throw new IllegalStateException("unable to deal cards in the current game phase: " + game.gamePhase());
        }
    }

    private static List<Card> allPlayerCards(List<Player> players, UUID playerId) {
        return players.stream()
                .filter(player -> player.uuid().equals(playerId))
                .map(Player::cards)
                .flatMap(Collection::stream)
                .toList();
    }
}
