package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SavePlayerPort;
import ch.jaunerc.tichu.backend.domain.game.port.ShuffleDeckUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.Deck.createShuffledDeck;

@Service
@RequiredArgsConstructor
public class ShuffleDeckService implements ShuffleDeckUseCase {

    private final FindGameByIdPort findGameByIdPort;
    private final SavePlayerPort savePlayerPort;

    @Override
    public void shuffleDeck(UUID gameId) {
        var game = findGameByIdPort.findGameById(gameId);
        var deck = createShuffledDeck();

        var player1 = game.firstTeam().firstPlayer();
        var player2 = game.firstTeam().secondPlayer();
        var player3 = game.secondTeam().firstPlayer();
        var player4 = game.secondTeam().secondPlayer();

        savePlayer(player1, deck.firstPlayerCards());
        savePlayer(player2, deck.secondPlayerCards());
        savePlayer(player3, deck.thirdPlayerCards());
        savePlayer(player4, deck.fourthPlayerCards());
    }

    private void savePlayer(Player player1, List<Card> deck) {
        savePlayerPort.savePlayer(Player.Builder.of(player1)
                .cards(deck)
                .build());
    }
}
