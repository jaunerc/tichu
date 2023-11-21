package ch.jaunerc.tichu.backend.domain.game.model;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record Deck(List<Card> cards) {

    public static final int CARDS_PER_PLAYER = 14;

    public List<Card> firstPlayerCards() {
        return cardsForPlayer(1);
    }

    public List<Card> secondPlayerCards() {
        return cardsForPlayer(2);
    }

    public List<Card> thirdPlayerCards() {
        return cardsForPlayer(3);
    }

    public List<Card> fourthPlayerCards() {
        return cardsForPlayer(4);
    }

    private List<Card> cardsForPlayer(int playerNumber) {
        var playerIndex = playerNumber -1;
        return cards.subList(playerIndex* CARDS_PER_PLAYER, playerIndex*CARDS_PER_PLAYER + CARDS_PER_PLAYER);
    }

    public static Deck createShuffledDeck() {
        var allCards = Arrays.asList(Card.values());
        Collections.shuffle(allCards);
        return new Deck(allCards);
    }
}
